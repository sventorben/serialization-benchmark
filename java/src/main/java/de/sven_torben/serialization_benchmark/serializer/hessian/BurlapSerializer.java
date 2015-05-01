package de.sven_torben.serialization_benchmark.serializer.hessian;

import java.io.InputStream;
import java.io.OutputStream;

import com.caucho.burlap.io.BurlapInput;
import com.caucho.burlap.io.BurlapOutput;
import com.caucho.hessian.io.AbstractHessianInput;
import com.caucho.hessian.io.AbstractHessianOutput;

public final class BurlapSerializer extends AbstractHessianSerializer {

	@Override
	protected final AbstractHessianOutput createOutput(final OutputStream stream) {
		return new BurlapOutput(stream);
	}

	@Override
	protected final AbstractHessianInput createInput(final InputStream stream) {
		return new BurlapInput(stream);
	}

}
