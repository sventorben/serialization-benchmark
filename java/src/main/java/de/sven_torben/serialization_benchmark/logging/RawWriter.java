package de.sven_torben.serialization_benchmark.logging;

public interface RawWriter {
	void writeToFile(String serializerName, byte[] raw);
}
