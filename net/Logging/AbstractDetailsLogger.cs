using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Logging
{
    abstract class AbstractDetailsLogger : Logger
    {
        public void Log(string serializerName, int bytes, long[] writeTimes, long[] readTimes)
        {
            Log(
                serializerName,
                bytes,
                writeTimes.Length,
                writeTimes.AsParallel().Min(),
                readTimes.AsParallel().Min(),
                writeTimes.AsParallel().Average(),
                readTimes.AsParallel().Average(),
                writeTimes.AsParallel().Max(),
                readTimes.AsParallel().Max()
            );
        }

        protected abstract void Log(String serializerName, int bytes, int iterations, long minWrite, long minRead, double avgWrite, double avgRead, long maxWrite, long maxRead);
    }
}
