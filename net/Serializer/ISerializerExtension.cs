using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Serializer
{
    static class ISerializerExtensions
    {
        public static ISerializer<T> WithGzipCompression<T>(this ISerializer<T> serializer)
        {
            return new GzipSerializer<T>(serializer);
        }
    }
}
