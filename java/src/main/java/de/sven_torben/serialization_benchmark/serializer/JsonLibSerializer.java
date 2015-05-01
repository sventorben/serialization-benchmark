package de.sven_torben.serialization_benchmark.serializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.groovy.JsonSlurper;
import de.sven_torben.serialization_benchmark.testdata.java.Catalog;

public final class JsonLibSerializer extends AbstractSerializer<Catalog> {

	public JsonLibSerializer() {
		super(Catalog.class);
	}

	@Override
	public final void serialize(final OutputStream stream, final Catalog data) throws Exception {
		final OutputStreamWriter writer = new OutputStreamWriter(stream);
		JSONSerializer.toJSON(data).write(writer);
		writer.flush();
		writer.close();
	}

	@Override
	public final Catalog deserialize(final InputStream stream) throws Exception {
		final JsonSlurper slurper = new JsonSlurper();
		return (Catalog) JSONObject.toBean((JSONObject)slurper.parse(stream), Catalog.class);
	}

}
