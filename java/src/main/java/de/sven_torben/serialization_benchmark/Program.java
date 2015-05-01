package de.sven_torben.serialization_benchmark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import de.sven_torben.serialization_benchmark.logging.CombinedLogger;
import de.sven_torben.serialization_benchmark.logging.ConsoleLogger;
import de.sven_torben.serialization_benchmark.logging.CsvFileLogger;
import de.sven_torben.serialization_benchmark.logging.FileRawWriter;
import de.sven_torben.serialization_benchmark.logging.Logger;
import de.sven_torben.serialization_benchmark.logging.RawWriter;
import de.sven_torben.serialization_benchmark.serializer.FlexJsonSerializer;
import de.sven_torben.serialization_benchmark.serializer.GensonSerializer;
import de.sven_torben.serialization_benchmark.serializer.GsonSerializer;
import de.sven_torben.serialization_benchmark.serializer.ISerializer;
import de.sven_torben.serialization_benchmark.serializer.JacksonSerializer;
import de.sven_torben.serialization_benchmark.serializer.JavaBeansXmlSerializer;
import de.sven_torben.serialization_benchmark.serializer.JsonIoSerializer;
import de.sven_torben.serialization_benchmark.serializer.JsonLibSerializer;
import de.sven_torben.serialization_benchmark.serializer.ProtocolBuffersSerializer;
import de.sven_torben.serialization_benchmark.serializer.XStreamSerializer;
import de.sven_torben.serialization_benchmark.serializer.avro.AvroBinarySerializer;
import de.sven_torben.serialization_benchmark.serializer.avro.AvroJsonSerializer;
import de.sven_torben.serialization_benchmark.serializer.hessian.BurlapSerializer;
import de.sven_torben.serialization_benchmark.serializer.hessian.Hessian2Serializer;
import de.sven_torben.serialization_benchmark.serializer.hessian.HessianSerializer;

@SuppressWarnings("static-access")
public class Program {

	private static String PROGRAM_NAME = "java -jar serialization-benchmark";
	private static int DEFAULT_NUM_TEST_ITERATIONS = 10;

	private static List<ISerializer<?>> serializers = new ArrayList<ISerializer<?>>();
	private static Options commandLineOptions = new Options();
	
	private static ISerializer<?>[] SERIALIZERS = {
			new JavaBeansXmlSerializer(),
			new ProtocolBuffersSerializer(),
			new HessianSerializer(),
			new Hessian2Serializer(),
			new BurlapSerializer(),
			new AvroJsonSerializer(),
			new AvroBinarySerializer(),
			new JacksonSerializer(),
			new JsonIoSerializer(),
			new GsonSerializer(),
			new JsonLibSerializer(),
			new GensonSerializer(),
			// new JsonIjSerializer(),
			new FlexJsonSerializer()
	};

	static {
		serializers.addAll(Arrays.asList(SERIALIZERS));
		serializers.addAll(XStreamSerializer.createAllWithDefaultDrivers());
		
		commandLineOptions.addOption(
				OptionBuilder
				.withArgName("#num")
				.withLongOpt("test-iterations")
				.withDescription("number of test iterations (Default: " + DEFAULT_NUM_TEST_ITERATIONS + ")")
				.withType(Number.class)
				.hasArg()
				.create("ti"));
		commandLineOptions.addOption(
				OptionBuilder
				.withLongOpt("help")
				.withDescription("Print this help")
				.create("h"));
	}

	public static void main(final String[] args) throws FileNotFoundException,
			IOException {
		
		final CommandLine cl = parseCommandLine(args);
		
		final Logger logger = new CombinedLogger(new ConsoleLogger(), new CsvFileLogger());
		final RawWriter writer = new FileRawWriter();
		
		final int iterations = (int) Long.parseLong(cl.getOptionValue("ti", String.valueOf(DEFAULT_NUM_TEST_ITERATIONS)));
		new TestSuite(serializers, logger, writer).run(iterations);
		
		System.out.println("-- done --");

	}

	private static CommandLine parseCommandLine(final String[] args) {
		final CommandLine cl; 
		try {
			cl = createCommandLine(args);
			cl.getParsedOptionValue("ti");
		} catch (ParseException e) {
			System.err.println("CLI parse exception: " + e.getMessage());
			printHelp();
			System.exit(-1);
			return null;
		}

		if (cl.hasOption("h"))  {
			printHelp();
			System.exit(0);
		}
		
		return cl;
	}

	private static void printHelp() {
		final HelpFormatter formater = new HelpFormatter();
		formater.printHelp(PROGRAM_NAME, commandLineOptions);
	}

	private static CommandLine createCommandLine(final String[] args) throws ParseException {
		final CommandLineParser parser = new BasicParser();
		return parser.parse(commandLineOptions, args);
	}

}
