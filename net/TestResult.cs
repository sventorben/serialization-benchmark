using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace De.Sven_Torben.Serialization_Benchmark
{
    class TestResult<T>
    {
        public long Time { get; set; }
        public T Data { get; set; }
    }
}
