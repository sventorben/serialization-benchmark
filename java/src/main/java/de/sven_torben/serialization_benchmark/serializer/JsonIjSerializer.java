package de.sven_torben.serialization_benchmark.serializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import cc.plural.jsonij.JSON;
import cc.plural.jsonij.marshal.JSONMarshaler;
import de.sven_torben.serialization_benchmark.testdata.java.Catalog;

public final class JsonIjSerializer extends AbstractSerializer<Catalog> {

	public JsonIjSerializer() {
		super(Catalog.class);
	}

	@Override
	public final void serialize(final OutputStream stream, final Catalog data)
			throws Exception {
		final OutputStreamWriter writer = new OutputStreamWriter(stream);
		writer.write(JSONMarshaler.marshalObject(data).toJSON());
		writer.flush();
		writer.close();
	}

	@Override
	public Catalog deserialize(final InputStream stream) throws Exception {
		JSON.parse(stream);
		System.err.println(String
				.format("WARNING: %s does not create java object from JSON!",
						getName()));
		return null;
	}

}
