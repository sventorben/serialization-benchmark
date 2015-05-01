package de.sven_torben.serialization_benchmark.serializer.hessian;

import java.io.InputStream;
import java.io.OutputStream;

import com.caucho.hessian.io.AbstractHessianInput;
import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class HessianSerializer extends AbstractHessianSerializer {

	@Override
	protected final AbstractHessianOutput createOutput(final OutputStream stream) {
		return new HessianOutput(stream);
	}

	@Override
	protected final AbstractHessianInput createInput(final InputStream stream) {
		return new HessianInput(stream);
	}

}
