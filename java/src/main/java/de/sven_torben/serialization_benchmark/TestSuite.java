package de.sven_torben.serialization_benchmark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.sven_torben.serialization_benchmark.testdata.protobuf.CatalogProtos;
import de.sven_torben.serialization_benchmark.logging.ConsoleLogger;
import de.sven_torben.serialization_benchmark.logging.Logger;
import de.sven_torben.serialization_benchmark.logging.NullRawWriter;
import de.sven_torben.serialization_benchmark.logging.RawWriter;
import de.sven_torben.serialization_benchmark.serializer.GzipSerializer;
import de.sven_torben.serialization_benchmark.serializer.ISerializer;
import de.sven_torben.serialization_benchmark.testdata.avro.AvroCatalogCreator;
import de.sven_torben.serialization_benchmark.testdata.java.Catalog;
import de.sven_torben.serialization_benchmark.testdata.java.JavaCatalogCreator;
import de.sven_torben.serialization_benchmark.testdata.protobuf.ProtoBufCatalogCreator;

public final class TestSuite {

	private static Map<Class<?>, Object> testdata = new HashMap<Class<?>, Object>();

	static {
		testdata.put(Catalog.class, new JavaCatalogCreator().create());
		testdata.put(CatalogProtos.Catalog.class,
				new ProtoBufCatalogCreator().create());
		testdata.put(
				de.sven_torben.serialization_benchmark.testdata.avro.Catalog.class,
				new AvroCatalogCreator().create());

	}

	private final List<ISerializer<?>> serializers;
	private final Logger logger;
	private final RawWriter rawWriter;
	private boolean gzipEnabled = true;

	public TestSuite(final List<ISerializer<?>> serializers) {
		this(serializers, new ConsoleLogger());
	}

	public TestSuite(final List<ISerializer<?>> serializers, final Logger logger) {
		this(serializers, logger, new NullRawWriter());
	}

	public TestSuite(final List<ISerializer<?>> serializers,
			final Logger logger, final RawWriter rawWriter) {
		this(serializers, logger, rawWriter, true);
	}

	public TestSuite(final List<ISerializer<?>> serializers,
			final Logger logger, final RawWriter rawWriter,
			final boolean gzipEnabled) {
		this.serializers = serializers;
		this.logger = logger;
		this.gzipEnabled = gzipEnabled;
		this.rawWriter = rawWriter;
	}

	public void setGzipEnabled(final boolean enabled) {
		this.gzipEnabled = enabled;
	}

	public boolean isGzipEnabled() {
		return gzipEnabled;
	}

	public Logger getLogger() {
		return logger;
	}

	public RawWriter getRawWriter() {
		return rawWriter;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final void run(final int iterations) {
		final List<ISerializer<?>> serializersToTest = new ArrayList<ISerializer<?>>(
				serializers);
		if (gzipEnabled) {
			serializers.forEach(s -> serializersToTest
					.add(new GzipSerializer(s)));
		}
		serializersToTest.parallelStream().forEach(
				s -> new Test(this, s).run(iterations));
	}

	@SuppressWarnings("unchecked")
	public final <T> T createTestData(final Class<T> type) {
		return (T) testdata.get(type);
	}

}
