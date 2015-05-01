package de.sven_torben.serialization_benchmark.serializer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import de.sven_torben.serialization_benchmark.testdata.java.Catalog;

public final class GsonSerializer extends AbstractSerializer<Catalog> {

	private static String CHARSET = "UTF-8";
	
	private static Gson gson = new Gson();

	public GsonSerializer() {
		super(Catalog.class);
	}

	@Override
	public void serialize(final OutputStream stream, final Catalog data)
			throws Exception {
		final OutputStreamWriter osw = new OutputStreamWriter(stream, CHARSET);
		final JsonWriter writer = new JsonWriter(osw);
		try {
			gson.toJson(data, Catalog.class, writer);
			writer.flush();
			osw.flush();
		} finally {
			IOUtils.closeQuietly(osw);
			IOUtils.closeQuietly(writer);
		}
	}

	@Override
	public Catalog deserialize(InputStream stream) throws Exception {
		final JsonReader reader = new JsonReader(new InputStreamReader(stream,
				CHARSET));
		final Catalog catalog;
		try {
			catalog = gson.fromJson(reader, Catalog.class);
		} finally {
			IOUtils.closeQuietly(reader);
		}
		return catalog;
	}

}
