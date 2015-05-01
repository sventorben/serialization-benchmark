package de.sven_torben.serialization_benchmark.serializer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import de.sven_torben.serialization_benchmark.testdata.java.Catalog;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public final class FlexJsonSerializer extends AbstractSerializer<Catalog> {

	private static JSONSerializer serializer = new JSONSerializer();
	private static JSONDeserializer<Catalog> deserializer = new JSONDeserializer<Catalog>();
	
	public FlexJsonSerializer() {
		super(Catalog.class);
	}

	@Override
	public final void serialize(final OutputStream stream, final Catalog data) throws Exception {
		final OutputStreamWriter writer = new OutputStreamWriter(stream);
		serializer.deepSerialize(data, writer);
		writer.flush();
		writer.close();
	}

	@Override
	public final Catalog deserialize(final InputStream stream) throws Exception {
		final InputStreamReader reader = new InputStreamReader(stream);
		Catalog catalog = deserializer.deserialize(reader);
		reader.close();
		return catalog;
	}

}
