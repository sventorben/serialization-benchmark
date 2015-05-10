using De.Sven_Torben.Serialization_Benchmark.Testdata;
using Microsoft.Hadoop.Avro;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Serializer
{
    class MSHadoopAvroSerializer : AbstractSerializer<Catalog>
    {
        private readonly IAvroSerializer<Catalog> serializer;

        internal MSHadoopAvroSerializer()
            : base(new Catalog())
        {
            serializer = AvroSerializer.Create<Catalog>();
        }

        public override void Serialize(Stream stream, Catalog data)
        {
            serializer.Serialize(stream, data);
        }

        public override Catalog Deserialize(Stream stream)
        {
            return serializer.Deserialize(stream);
        }
    }
}
