using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Logging
{
    class FileRawWriter : RawWriter
    {
        private static readonly string LOG_FOLDER = Path.Combine(Directory.GetCurrentDirectory(), "logs");

        public void WriteToFile(string serializerName, byte[] raw)
        {
            EnsureLogFolder();
            File.WriteAllBytes(Path.Combine(LOG_FOLDER, string.Format("{0}.log", serializerName)), raw);
        }

        private void EnsureLogFolder()
        {
            if (!Directory.Exists(LOG_FOLDER))
            {
                Directory.CreateDirectory(LOG_FOLDER);
            }
        }
    }
}
