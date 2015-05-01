package de.sven_torben.serialization_benchmark.serializer;

import java.io.InputStream;
import java.io.OutputStream;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import de.sven_torben.serialization_benchmark.testdata.java.Catalog;

public final class JsonIoSerializer extends AbstractSerializer<Catalog> {

	public JsonIoSerializer() {
		super(Catalog.class);
	}

	@Override
	public final void serialize(final OutputStream stream, final Catalog data) throws Exception {
		final JsonWriter writer = new JsonWriter(stream);
		writer.write(data);
		writer.flush();
		writer.close();
	}

	@Override
	public final Catalog deserialize(final InputStream stream) throws Exception {
		final JsonReader reader = new JsonReader(stream);
		final Catalog catalog = (Catalog) reader.readObject();
		reader.close();
		return catalog;
	}

}
