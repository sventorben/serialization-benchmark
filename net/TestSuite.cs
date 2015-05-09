using De.Sven_Torben.Serialization_Benchmark.Logging;
using De.Sven_Torben.Serialization_Benchmark.Serializer;
using De.Sven_Torben.Serialization_Benchmark.Testdata;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace De.Sven_Torben.Serialization_Benchmark
{
    class TestSuite
    {
        private static readonly Dictionary<Type, Object> testdata = new Dictionary<Type, Object>();

        static TestSuite()
        {
            testdata.Add(typeof(Catalog), new NetCatalogCreator().Create());
        }

        private List<ISerializer<Catalog>> serializers;

        public TestSuite(List<ISerializer<Catalog>> serializers) : this(serializers, new ConsoleLogger()) { }

        public TestSuite(List<ISerializer<Catalog>> serializers, Logger logger) : this(serializers, logger, new NullRawWriter()) { }

        public TestSuite(List<ISerializer<Catalog>> serializers, Logger logger, RawWriter rawWriter) : this(serializers, logger, rawWriter, true) { }

        public TestSuite(List<ISerializer<Catalog>> serializers, Logger logger, RawWriter rawWriter, bool gzipEnabled)
        {
            this.serializers = serializers;
            Logger = logger;
            RawWriter = rawWriter;
            GzipEnabled = gzipEnabled;
        }

        public bool GzipEnabled { get; set; }

        public Logger Logger { get; protected set; }

        public RawWriter RawWriter { get; protected set; }

        public void Run(int iterations)
        {
            List<ISerializer<Catalog>> serializersToTest = new List<ISerializer<Catalog>>(serializers);
            if (GzipEnabled)
            {
                serializers.ForEach(s => serializersToTest.Add(s.WithGzipCompression()));
            }
            serializersToTest.AsParallel().ForAll(s => new Test<Catalog>(this, s).run(iterations));
        }

        public T CreateTestData<T>(T example)
        {
            return (T)testdata.Single(kvp => kvp.Key == example.GetType()).Value;
        }

    }
}
