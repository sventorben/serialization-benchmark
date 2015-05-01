package de.sven_torben.serialization_benchmark.serializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.IOUtils;

public final class GzipSerializer<T> extends AbstractSerializer<T> {

	private final ISerializer<T> serializer;

	public GzipSerializer(final ISerializer<T> serializer) {
		super(serializer.getType());
		this.serializer = serializer;
	}

	@Override
	public final void serialize(final OutputStream stream, final T data)
			throws Exception {
		final GZIPOutputStream gzip = new GZIPOutputStream(stream);
		try {
			serializer.serialize(gzip, data);
			gzip.flush();
		} finally {
			IOUtils.closeQuietly(gzip);
		}
	}

	@Override
	public T deserialize(final InputStream stream) throws Exception {
		final GZIPInputStream gzip = new GZIPInputStream(stream);
		final T result;
		try {
			result = serializer.deserialize(gzip);
		} finally {
			IOUtils.closeQuietly(gzip);
		}
		return result;
	}

	@Override
	public final String getName() {
		return String.format("%s with GZIP", serializer.getName());
	}

}
