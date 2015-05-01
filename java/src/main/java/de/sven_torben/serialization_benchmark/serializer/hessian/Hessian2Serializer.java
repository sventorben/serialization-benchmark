package de.sven_torben.serialization_benchmark.serializer.hessian;

import java.io.InputStream;
import java.io.OutputStream;

import com.caucho.hessian.io.AbstractHessianInput;
import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

public final class Hessian2Serializer extends AbstractHessianSerializer {

	@Override
	protected final AbstractHessianOutput createOutput(final OutputStream stream) {
		return new Hessian2Output(stream);
	}

	@Override
	protected AbstractHessianInput createInput(final InputStream stream) {
		return new Hessian2Input(stream);
	}

}
