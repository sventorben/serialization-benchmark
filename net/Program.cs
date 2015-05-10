using De.Sven_Torben.Serialization_Benchmark.Logging;
using De.Sven_Torben.Serialization_Benchmark.Serializer;
using De.Sven_Torben.Serialization_Benchmark.Testdata;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark
{
    class Program
    {

        private const int DEFAULT_NUM_TEST_ITERATIONS = 10;
        private static List<ISerializer<Catalog>> serializers = new List<ISerializer<Catalog>>();

        private static ISerializer<Catalog>[] SERIALIZERS = new ISerializer<Catalog>[] { 
            new MSXmlSerializer(), 
            new MSDataContractSerializer(),
            new MSNetDataContractSerializer(),
            new MSDataContractJsonSerializer(),
            new MSJavaScriptSerializer(),
            new ProtocolBuffersSerializer(),
            new NewtonsoftBsonSerializer(),
            new NewtonsoftJsonSerializer(),
            new MSHadoopAvroSerializer(),
        };

        static Program()
        {
            serializers.AddRange(SERIALIZERS);
        }

        static void Main(string[] args)
        {
            var logger = new CombinedLogger(new ConsoleLogger(), new CsvFileLogger());
            var writer = new FileRawWriter();

            var iterations = readIterationsFromCommandLine();
            new TestSuite(serializers, logger, writer).Run(iterations);

            Console.WriteLine("-- done --");
            Console.ReadLine();
        }

        private static int readIterationsFromCommandLine()
        {
            // TODO: read parameter from CLI
            return DEFAULT_NUM_TEST_ITERATIONS;
        }
    }
}
