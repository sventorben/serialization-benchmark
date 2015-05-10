using De.Sven_Torben.Serialization_Benchmark.Testdata;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web.Script.Serialization;

namespace De.Sven_Torben.Serialization_Benchmark.Serializer
{
    class MSJavaScriptSerializer : AbstractSerializer<Catalog>
    {
        private readonly JavaScriptSerializer serializer = new JavaScriptSerializer();

        internal MSJavaScriptSerializer() : base(new Catalog()) { }

        public override void Serialize(Stream stream, Catalog data)
        {
            using (var sw = new StreamWriter(stream))
            {
                sw.Write(serializer.Serialize(data));
            }
        }

        public override Catalog Deserialize(Stream stream)
        {
            using (var sr = new StreamReader(stream))
            {
                return serializer.Deserialize<Catalog>(sr.ReadToEnd());
            }
        }
    }
}
