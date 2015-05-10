using De.Sven_Torben.Serialization_Benchmark.Testdata;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Serializer
{
    class MSNetDataContractSerializer : AbstractSerializer<Catalog>
    {
        private readonly NetDataContractSerializer serializer;

        internal MSNetDataContractSerializer()
            : base(new Catalog())
        {
            serializer = new NetDataContractSerializer();
        }

        public override void Serialize(Stream stream, Catalog data)
        {
            serializer.WriteObject(stream, data);
        }

        public override Catalog Deserialize(Stream stream)
        {
            return (Catalog)serializer.ReadObject(stream);
        }
    }
}
