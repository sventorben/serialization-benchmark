package de.sven_torben.serialization_benchmark.logging;

public final class ConsoleLogger extends AbstractDetailsLogger {

	@Override
	protected final void log(String serializerName, int bytes, int iterations,
			long minWrite, long minRead, double avgWrite, double avgRead,
			long maxWrite, long maxRead) {
		System.out
				.println(String
						.format("%60s : %8d bytes, %4d iterations, %5dms min write, %5dms min read, %8.2fms avg write, %8.2fms avg read, %5dms max write, %5dms max read",
								serializerName, bytes, iterations, minWrite,
								minRead, avgWrite, avgRead, maxWrite, maxRead));

	}

}
