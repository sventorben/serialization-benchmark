package de.sven_torben.serialization_benchmark.logging;

import java.util.Arrays;

public class CombinedLogger implements Logger {

	private final Logger[] loggers;

	public CombinedLogger(final Logger... loggers) {
		this.loggers = loggers;
	}

	@Override
	public void log(final String serializerName, final int bytes,
			final long[] writeTimes, final long[] readTimes) {
		Arrays.stream(loggers).parallel().forEach(logger -> logger.log(serializerName, bytes, writeTimes,
				readTimes));
	}

}
