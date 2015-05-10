using De.Sven_Torben.Serialization_Benchmark.Testdata;
using Newtonsoft.Json;
using Newtonsoft.Json.Bson;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Serializer
{
    class NewtonsoftBsonSerializer : AbstractSerializer<Catalog>
    {
        private readonly JsonSerializer serializer;

        internal NewtonsoftBsonSerializer()
            : base(new Catalog())
        {
            serializer = new JsonSerializer();
            serializer.Formatting = Formatting.None;
        }

        public override void Serialize(Stream stream, Catalog data)
        {
            using (var bsonWriter = new BsonWriter(stream))
            {
                serializer.Serialize(bsonWriter, data);
            }
        }

        public override Catalog Deserialize(Stream stream)
        {
            using (var bsonReader = new BsonReader(stream))
            {
                return serializer.Deserialize<Catalog>(bsonReader);
            }
        }
    }
}
