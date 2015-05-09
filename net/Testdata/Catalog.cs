//using Ch.Elca.Iiop.Idl;
using ProtoBuf;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;
//using ThriftSharp;

namespace De.Sven_Torben.Serialization_Benchmark.Testdata
{
    [Serializable]
    [ProtoContract]
    [DataContract]
    [XmlRoot]
    [XmlType]
    //[ThriftStruct("Catalog")]
    //[ImplClass("MessageTypeComparison.CatalogImpl"), RepositoryID("IDL:MessageTypeComparison/Catalog:1.0")]
    public class Catalog
    {

        [ProtoMember(1, DataFormat = DataFormat.Group)]
        [DataMember]
        [XmlArray]
        //[ThriftField(1, true, "itemis")]
        public CatalogItem[] Items { get; set; }
    }
}
