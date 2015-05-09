using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace De.Sven_Torben.Serialization_Benchmark.Serializer
{
    interface ISerializer<T>
    {
        void Serialize(Stream stream, T data);
        T Deserialize(Stream stream);
	    String Name { get; }
        T TestSample { get; }
    }
}
