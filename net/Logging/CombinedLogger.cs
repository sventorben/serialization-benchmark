using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Logging
{
    class CombinedLogger : Logger
    {
        private Logger[] loggers;

        public CombinedLogger(params Logger[] loggers)
        {
            this.loggers = loggers;
        }

        public void Log(string serializerName, int bytes, long[] writeTimes, long[] readTimes)
        {
            loggers.AsParallel().ForAll(l => l.Log(serializerName, bytes, writeTimes, readTimes));
        }
    }
}
