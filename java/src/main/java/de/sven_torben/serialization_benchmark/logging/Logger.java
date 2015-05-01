package de.sven_torben.serialization_benchmark.logging;

public interface Logger {

	void log(final String serializerName, int bytes, long[] writeTimes, long[] readTimes);
	
}
