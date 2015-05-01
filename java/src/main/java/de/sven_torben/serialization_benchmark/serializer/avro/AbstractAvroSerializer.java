package de.sven_torben.serialization_benchmark.serializer.avro;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.avro.Schema;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import de.sven_torben.serialization_benchmark.testdata.avro.Catalog;
import de.sven_torben.serialization_benchmark.serializer.AbstractSerializer;

public abstract class AbstractAvroSerializer extends
		AbstractSerializer<Catalog> {

	private static DatumReader<Catalog> datumReader = new SpecificDatumReader<Catalog>(
			Catalog.class);
	private static DatumWriter<Catalog> datumWriter = new SpecificDatumWriter<Catalog>(
			Catalog.class);

	public AbstractAvroSerializer() {
		super(Catalog.class);
	}

	@Override
	public final void serialize(final OutputStream stream, final Catalog data)
			throws IOException {
		final Encoder encoder = createEncoder(stream, data.getSchema());
		datumWriter.write(data, encoder);
		encoder.flush();
	}

	@Override
	public final Catalog deserialize(final InputStream stream) throws Exception {
		return datumReader.read(null,
				createDecoder(stream, Catalog.getClassSchema()));
	}

	protected abstract Encoder createEncoder(OutputStream stream, Schema schema)
			throws IOException;

	protected abstract Decoder createDecoder(InputStream stream,
			Schema classSchema) throws IOException;

}
