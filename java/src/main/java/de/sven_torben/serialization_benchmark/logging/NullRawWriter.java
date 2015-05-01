package de.sven_torben.serialization_benchmark.logging;

public class NullRawWriter implements RawWriter {

	@Override
	public void writeToFile(final String serializerName, final byte[] raw) {
	}

}
