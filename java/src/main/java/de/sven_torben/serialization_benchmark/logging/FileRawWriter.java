package de.sven_torben.serialization_benchmark.logging;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

public final class FileRawWriter implements RawWriter {

	private final File logDir;

	public FileRawWriter() {
		this(Paths.get(System.getProperty("user.dir"), "logs").toAbsolutePath()
				.toString());
	}

	public FileRawWriter(final String logFolderPath) {
		this.logDir = new File(logFolderPath);
		System.out.println(String.format("Raw logs: %s",
				logDir.getAbsolutePath()));
	}

	@Override
	public void writeToFile(final String serializerName, final byte[] raw) {
		ensureLogDir();
		final File log = Paths
				.get(logDir.getAbsolutePath().toString(),
						serializerName + ".raw").toAbsolutePath().toFile();
		try {
			FileUtils.writeByteArrayToFile(log, raw, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ensureLogDir() {
		if (!logDir.exists()) {
			logDir.mkdirs();
		}
	}

}
