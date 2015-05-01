package de.sven_torben.serialization_benchmark.serializer;

import java.io.InputStream;
import java.io.OutputStream;

public interface ISerializer<T> {
	void serialize(OutputStream stream, T data) throws Exception;

	T deserialize(InputStream stream) throws Exception;

	String getName();

	Class<T> getType();
}
