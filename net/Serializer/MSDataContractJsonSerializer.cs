using De.Sven_Torben.Serialization_Benchmark.Testdata;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Json;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Serializer
{
    class MSDataContractJsonSerializer : AbstractSerializer<Catalog>
    {
        private readonly DataContractJsonSerializer serializer;

        internal MSDataContractJsonSerializer()
            : base(new Catalog())
        {
            serializer = new DataContractJsonSerializer(TestSample.GetType());
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
