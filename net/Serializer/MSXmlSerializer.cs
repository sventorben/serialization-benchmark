using De.Sven_Torben.Serialization_Benchmark.Testdata;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Serialization;

namespace De.Sven_Torben.Serialization_Benchmark.Serializer
{
    class MSXmlSerializer : AbstractSerializer<Catalog>
    {
        private XmlSerializer serializer;

        internal MSXmlSerializer()
            : base(new Catalog())
        {
            serializer = new XmlSerializer(TestSample.GetType());
        }

        public override void Serialize(Stream stream, Catalog data)
        {
            using (var sw = new StreamWriter(stream))
            {
                using (var xmlWriter = XmlWriter.Create(sw, new XmlWriterSettings { Indent = false, NewLineOnAttributes = false }))
                {
                    serializer.Serialize(xmlWriter, data);
                }
            }
        }

        public override Catalog Deserialize(Stream stream)
        {
            return (Catalog)serializer.Deserialize(stream);
        }
    }
}
