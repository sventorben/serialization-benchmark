package de.sven_torben.serialization_benchmark.serializer;

import java.io.InputStream;
import java.io.OutputStream;

import org.codehaus.jackson.map.ObjectMapper;

import de.sven_torben.serialization_benchmark.testdata.java.Catalog;

public final class JacksonSerializer extends AbstractSerializer<Catalog> {

	private static ObjectMapper mapper = new ObjectMapper();
	
	public JacksonSerializer() {
		super(Catalog.class);
	}

	@Override
	public final void serialize(final OutputStream stream, final Catalog data) throws Exception {
		mapper.writeValue(stream, data);
	}

	@Override
	public final Catalog deserialize(final InputStream stream) throws Exception {
		return mapper.readValue(stream, Catalog.class);
	}

}
