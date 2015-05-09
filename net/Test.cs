using De.Sven_Torben.Serialization_Benchmark.Serializer;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;

namespace De.Sven_Torben.Serialization_Benchmark
{
    class Test<T>
    {
        private TestSuite suite;
        private ISerializer<T> serializer;

        public Test(TestSuite suite, ISerializer<T> serializerToTest)
        {
            this.suite = suite;
            this.serializer = serializerToTest;
        }

        public void run(int iterations)
        {
            long[] writeTimes = new long[iterations];
            long[] readTimes = new long[iterations];

            if (iterations <= 0)
                return;

            // one iteration for warm up (JIT compiler etc) and writing raw bytes to
            // file for logging
            byte[] raw = PerformWriteTest().Data;
            suite.RawWriter.WriteToFile(serializer.Name, raw);
            PerformReadTest(raw);

            // actual tests
            for (int i = 0; i < iterations; i++)
            {

                TestResult<byte[]> result = PerformWriteTest();
                writeTimes[i] = result.Time;

                TestResult<T> cResult = PerformReadTest(result.Data);
                readTimes[i] = cResult.Time;
            }

            suite.Logger.Log(serializer.Name, raw.Length, writeTimes, readTimes);
        }

        private TestResult<T> PerformReadTest(byte[] data)
        {
            return Time(raw => Deserialize(raw), data);
        }

        private TestResult<byte[]> PerformWriteTest()
        {
            return Time(data => Serialize(data),
                    suite.CreateTestData(serializer.TestSample));
        }

        private byte[] Serialize(T testData)
        {
            using (MemoryStream stream = new MemoryStream())
            {
                serializer.Serialize(stream, testData);
                stream.Flush();
                return stream.ToArray();
            }
        }

        private T Deserialize(byte[] raw)
        {
            using (MemoryStream stream = new MemoryStream(raw))
            {
                return serializer.Deserialize(stream);
            }
        }

        private TestResult<Out> Time<Out, In>(Func<In, Out> func, In input)
        {
            TestResult<Out> result = new TestResult<Out>();
            Stopwatch timer = new Stopwatch();
            timer.Start();
            result.Data = func.Invoke(input);
            timer.Stop();
            result.Time = timer.ElapsedMilliseconds;
            return result;
        }
    }
}
