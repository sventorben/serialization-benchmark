package de.sven_torben.serialization_benchmark.logging;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public final class CsvFileLogger extends AbstractDetailsLogger {

	private File logFile;

	public CsvFileLogger() {
		this(Paths.get(System.getProperty("user.dir"), "logs", "benchmark.log")
				.toAbsolutePath().toString());
	}

	public CsvFileLogger(final String logFilePath) {
		if (StringUtils.isNotEmpty(logFilePath)) {
			this.logFile = new File(logFilePath);
			ensureCleanLogFile();
			System.out.println(String.format("CSV Log: %s",
					logFile.getAbsolutePath()));
		}
	}

	private void ensureCleanLogFile() {
		if (this.logFile.exists()) {
			this.logFile.delete();
		}
		try {
			FileUtils.touch(logFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected final void log(final String serializerName, final int bytes,
			final int iterations, final long minWrite, final long minRead,
			final double avgWrite, final double avgRead, final long maxWrite,
			final long maxRead) {
		if (logFile != null) {
			try {
				FileUtils.write(logFile, String.format(
						"%s;%d;%d;%d;%d;%.2f;%.2f;%d;%d%n", serializerName,
						bytes, iterations, minWrite, minRead, avgWrite,
						avgRead, maxWrite, maxRead), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
