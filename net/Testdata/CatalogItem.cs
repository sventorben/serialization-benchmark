//using Ch.Elca.Iiop.Idl;
using ProtoBuf;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace De.Sven_Torben.Serialization_Benchmark.Testdata
{
    [Serializable]
    [ProtoContract]
    [DataContract]
    [XmlRoot]
    [XmlType]
    //[ImplClass("MessageTypeComparison.CatalogItemImpl"), RepositoryID("IDL:MessageTypeComparison/CatalogItem:1.0")]
    public class CatalogItem : IComparable<CatalogItem>
    {
        [XmlAttribute]
        [ProtoMember(1)]
        [DataMember]
        public int Id { get; set; }

        [XmlAttribute]
        [ProtoMember(2)]
        [DataMember]
        public string Name { get; set; }

        [XmlAttribute]
        [ProtoMember(3)]
        [DataMember]
        public decimal Price { get; set; }

        [XmlAttribute]
        [ProtoMember(4)]
        [DataMember]
        public int QuantityAvailable { get; set; }

        [XmlAttribute]
        [ProtoMember(5)]
        [DataMember]
        public DateTime InStockSince { get; set; }


        public int CompareTo(CatalogItem other)
        {
            if (this == other)
                return 0;
            return this.Id - other.Id;
        }
    }
}
