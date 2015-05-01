package de.sven_torben.serialization_benchmark.serializer;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractSerializer<T> implements ISerializer<T> {

	private final Class<T> type;
	
	public AbstractSerializer(final Class<T> type)
	{
		this.type = type;
	}
	
	@Override
	public abstract void serialize(OutputStream stream, T data) throws Exception;
	
	@Override
	public abstract T deserialize(InputStream stream) throws Exception;

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public final Class<T> getType() {
		return type;
	}
	
}
