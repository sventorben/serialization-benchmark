using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Serializer
{
    abstract class AbstractSerializer<T> : ISerializer<T>
    {

        protected AbstractSerializer(T testSample)
        {
            TestSample = testSample;
        }

        public virtual String Name
        {
            get { return this.GetType().Name; }
        }

        public T TestSample
        {
            get;
            protected set;
        }


        public abstract void Serialize(System.IO.Stream stream, T data);

        public abstract T Deserialize(System.IO.Stream stream);
    }
}
