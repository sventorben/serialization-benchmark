package de.sven_torben.serialization_benchmark.serializer;

import java.io.InputStream;
import java.io.OutputStream;

import com.owlike.genson.Genson;

import de.sven_torben.serialization_benchmark.testdata.java.Catalog;

public final class GensonSerializer extends AbstractSerializer<Catalog> {

	private static Genson genson = new Genson();
	
	public GensonSerializer() {
		super(Catalog.class);
	}

	@Override
	public final void serialize(final OutputStream stream, final Catalog data) throws Exception {
		genson.serialize(data, stream);
	}

	@Override
	public final Catalog deserialize(final InputStream stream) throws Exception {
		return genson.deserialize(stream, Catalog.class);
	}

}
