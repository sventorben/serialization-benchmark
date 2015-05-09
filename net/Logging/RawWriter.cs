using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Logging
{
    interface RawWriter
    {
        void WriteToFile(String serializerName, byte[] raw);
    }
}
