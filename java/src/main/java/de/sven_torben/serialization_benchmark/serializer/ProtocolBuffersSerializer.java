package de.sven_torben.serialization_benchmark.serializer;

import java.io.InputStream;
import java.io.OutputStream;

import de.sven_torben.serialization_benchmark.testdata.protobuf.CatalogProtos;
import de.sven_torben.serialization_benchmark.testdata.protobuf.CatalogProtos.Catalog;

public final class ProtocolBuffersSerializer extends AbstractSerializer<Catalog> {

	public ProtocolBuffersSerializer() {
		super(CatalogProtos.Catalog.class);
	}

	@Override
	public void serialize(final OutputStream stream, final CatalogProtos.Catalog data) throws Exception {
		data.writeTo(stream);
	}

	@Override
	public CatalogProtos.Catalog deserialize(final InputStream stream) throws Exception {
		return CatalogProtos.Catalog.parseFrom(stream);
	}

}
