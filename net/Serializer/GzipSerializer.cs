using System;
using System.Collections.Generic;
using System.IO;
using System.IO.Compression;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Serializer
{
    sealed class GzipSerializer<T> : ISerializer<T>
    {
        private const int BUFFER_SIZE = 64 * 1024;
        private readonly ISerializer<T> @delegate;

        internal GzipSerializer(ISerializer<T> @delegate)
        {
            this.@delegate = @delegate;
        }

        public void Serialize(Stream stream, T data)
        {
            using (var gzip = new GZipStream(stream, CompressionMode.Compress))
            {
                using (var bs = new BufferedStream(gzip, BUFFER_SIZE))
                {
                    @delegate.Serialize(bs, data);
                }
            }
        }

        public T Deserialize(Stream stream)
        {

            using (var gzip = new GZipStream(stream, CompressionMode.Decompress))
            {
                using (var bs = new BufferedStream(gzip, BUFFER_SIZE))
                {
                    return @delegate.Deserialize(bs);
                }
            }
        }

        public String Name
        {
            get { return @delegate.Name + " with GZIP"; }
        }

        public T TestSample
        {
            get { return @delegate.TestSample; }
        }
    }
}
