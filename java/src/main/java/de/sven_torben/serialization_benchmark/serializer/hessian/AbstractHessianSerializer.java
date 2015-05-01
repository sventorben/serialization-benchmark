package de.sven_torben.serialization_benchmark.serializer.hessian;

import java.io.InputStream;
import java.io.OutputStream;

import com.caucho.hessian.io.AbstractHessianInput;
import com.caucho.hessian.io.AbstractHessianOutput;

import de.sven_torben.serialization_benchmark.serializer.AbstractSerializer;
import de.sven_torben.serialization_benchmark.testdata.java.Catalog;

public abstract class AbstractHessianSerializer extends AbstractSerializer<Catalog> {

	public AbstractHessianSerializer() {
		super(Catalog.class);
	}

	@Override
	public final void serialize(final OutputStream stream, final Catalog data) throws Exception {
		final AbstractHessianOutput out = createOutput(stream);
		out .writeObject(data);
		out.flush();
		out.close();
	}
	
	@Override
	public final Catalog deserialize(final InputStream stream) throws Exception {
		final AbstractHessianInput hi = createInput(stream);
		return (Catalog) hi.readObject();
	}

	protected abstract AbstractHessianOutput createOutput(OutputStream stream);
	protected abstract AbstractHessianInput createInput(InputStream stream);
	
}
