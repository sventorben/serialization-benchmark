package de.sven_torben.serialization_benchmark;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.function.Function;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;

import de.sven_torben.serialization_benchmark.serializer.GzipSerializer;
import de.sven_torben.serialization_benchmark.serializer.ISerializer;

public final class Test<T> {

	private final TestSuite suite;
	private final ISerializer<T> serializer;
	private boolean gzipEnabled;

	public Test(final TestSuite suite, final ISerializer<T> serializerToTest) {
		this(suite, serializerToTest, true);
	}

	public Test(final TestSuite suite, final ISerializer<T> serializerToTest,
			final boolean gzipEnabled) {
		this.suite = suite;
		this.serializer = serializerToTest;
		this.gzipEnabled = gzipEnabled;
	}

	public boolean isGzipEnabled() {
		return this.gzipEnabled;
	}

	public void setGzipEnabled(final boolean gzipEnabled) {
		this.gzipEnabled = gzipEnabled;
	}

	public final void run(final int iterations) {
		run(serializer, iterations);
		if (gzipEnabled) {
			run(new GzipSerializer<T>(serializer), iterations);
		}
	}

	private final void run(final ISerializer<T> serializer, final int iterations) {
		final long[] writeTimes = new long[iterations];
		final long[] readTimes = new long[iterations];

		if (iterations <= 0)
			return;

		// one iteration for warm up (JIT compiler etc) and writing raw bytes to
		// file for logging
		final byte[] raw = performWriteTest().getData();
		suite.getRawWriter().writeToFile(serializer.getName(), raw);
		performReadTest(raw);

		// actual tests
		for (int i = 0; i < iterations; i++) {

			final TestResult<byte[]> result = performWriteTest();
			writeTimes[i] = result.getTime();

			final TestResult<T> cResult = performReadTest(result.getData());
			readTimes[i] = cResult.getTime();
		}

		suite.getLogger().log(serializer.getName(), raw.length, writeTimes,
				readTimes);
	}

	private TestResult<T> performReadTest(byte[] data) {
		return time(raw -> deserialize(raw), data);
	}

	private TestResult<byte[]> performWriteTest() {
		return time(data -> serialize(data),
				suite.createTestData(serializer.getType()));
	}

	private byte[] serialize(final T testData) {
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			serializer.serialize(stream, testData);
			stream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(stream);
		}
		return stream.toByteArray();
	}

	private T deserialize(final byte[] raw) {
		final ByteArrayInputStream stream = new ByteArrayInputStream(raw);
		T data = null;
		try {
			data = serializer.deserialize(stream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(stream);
		}
		return data;
	}

	private <Out, In> TestResult<Out> time(final Function<In, Out> func,
			final In input) {
		final TestResult<Out> result = new TestResult<Out>();
		final StopWatch timer = new StopWatch();
		timer.start();
		result.setData(func.apply(input));
		timer.stop();
		result.setTime(timer.getTime());
		return result;
	}
}
