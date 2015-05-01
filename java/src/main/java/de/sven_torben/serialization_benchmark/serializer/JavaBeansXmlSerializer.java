package de.sven_torben.serialization_benchmark.serializer;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.sven_torben.serialization_benchmark.testdata.java.Catalog;

public final class JavaBeansXmlSerializer extends AbstractSerializer<Catalog> {

	public JavaBeansXmlSerializer() {
		super(Catalog.class);
	}

	@Override
	public final void serialize(final OutputStream stream, final Catalog data)
			throws IOException {
		final XMLEncoder encoder = new XMLEncoder(stream);
		encoder.writeObject(data);
		encoder.close();
	}

	@Override
	public Catalog deserialize(final InputStream stream) throws IOException {
		final XMLDecoder decoder = new XMLDecoder(stream);
		final Catalog o = (Catalog) decoder.readObject();
		decoder.close();
		return o;
	}

}
