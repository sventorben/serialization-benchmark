using De.Sven_Torben.Serialization_Benchmark.Testdata;
using ProtoBuf;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Serializer
{
    class ProtocolBuffersSerializer : AbstractSerializer<Catalog>
    {

        internal ProtocolBuffersSerializer()
            : base(new Catalog()) { }

        public override void Serialize(Stream stream, Catalog data)
        {
            ProtoBuf.Serializer.Serialize(stream, data);
        }

        public override Catalog Deserialize(Stream stream)
        {
            return ProtoBuf.Serializer.Deserialize<Catalog>(stream);
        }
    }
}
