package de.sven_torben.serialization_benchmark.serializer.avro;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.avro.Schema;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;

public final class AvroJsonSerializer extends AbstractAvroSerializer {

	@Override
	protected Encoder createEncoder(final OutputStream stream,
			final Schema schema) throws IOException {
		return EncoderFactory.get().jsonEncoder(schema, stream);
	}

	@Override
	protected Decoder createDecoder(final InputStream stream,
			final Schema schema) throws IOException {
		return DecoderFactory.get().jsonDecoder(schema, stream);
	}

}
