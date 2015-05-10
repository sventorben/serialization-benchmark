using De.Sven_Torben.Serialization_Benchmark.Testdata;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Serializer
{
    class NewtonsoftJsonSerializer : AbstractSerializer<Catalog>
    {
        private readonly JsonSerializer serializer;

        internal NewtonsoftJsonSerializer()
            : base(new Catalog())
        {
            serializer = new JsonSerializer();
            serializer.Formatting = Formatting.None;
        }

        public override void Serialize(Stream stream, Catalog data)
        {

            using (var sw = new StreamWriter(stream))
            {
                using (var jsonWriter = new JsonTextWriter(sw))
                {
                    serializer.Serialize(jsonWriter, data);
                }
            }
        }

        public override Catalog Deserialize(Stream stream)
        {
            using (var sr = new StreamReader(stream))
            {
                using (var jsonReader = new JsonTextReader(sr))
                {
                    return serializer.Deserialize<Catalog>(jsonReader);
                }
            }
        }
    }

}
