using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Logging
{
    class NullRawWriter : RawWriter
    {
        public void WriteToFile(string serializerName, byte[] raw)
        {
        }
    }
}
