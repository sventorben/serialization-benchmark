using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Logging
{
    sealed class ConsoleLogger : AbstractDetailsLogger
    {

        protected override void Log(string serializerName, int bytes, int iterations, long minWrite, long minRead, double avgWrite, double avgRead, long maxWrite, long maxRead)
        {
            Console.Out.WriteLine("{0,-60} : {1,8} bytes, {2,4} iterations, {3,5}ms min write, {4,5}ms min read, {5,5:0.0}ms avg write, {6,5:0.0}ms avg read, {7,5}ms max write, {8,5}ms max read", serializerName, bytes, iterations, minWrite,
                minRead, avgWrite, avgRead, maxWrite, maxRead);
        }

    }
}
