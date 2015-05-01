package de.sven_torben.serialization_benchmark.logging;

import java.util.Arrays;

public abstract class AbstractDetailsLogger implements Logger {

	@Override
	public final void log(final String serializerName, final int bytes,
			final long[] writeTimes, final long[] readTimes) {
		log(
				serializerName, 
				bytes, 
				writeTimes.length,
				Arrays.stream(writeTimes).parallel().min().getAsLong(),
				Arrays.stream(readTimes).parallel().min().getAsLong(),
				Arrays.stream(writeTimes).parallel().average().getAsDouble(),
				Arrays.stream(readTimes).parallel().average().getAsDouble(),
				Arrays.stream(writeTimes).parallel().max().getAsLong(),
				Arrays.stream(readTimes).parallel().max().getAsLong()
		);

	}

	protected abstract void log(String serializerName, int bytes, int iterations,
			long minWrite, long minRead, double avgWrite, double avgRead,
			long maxWrite, long maxRead);

}
