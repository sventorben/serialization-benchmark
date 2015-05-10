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
        private static readonly string DEFAULT_LOG_FOLDER = Path.Combine(Directory.GetCurrentDirectory(), "logs");

        private string logFolder;

        internal FileRawWriter()
            : this(DEFAULT_LOG_FOLDER)
        {
        }

        internal FileRawWriter(string logFolder)
        {
            this.logFolder = logFolder;
            Console.Out.WriteLine(string.Format("Raw logs: {0}", logFolder));
        }

        public void WriteToFile(string serializerName, byte[] raw)
        {
            EnsureLogFolder();
            File.WriteAllBytes(Path.Combine(logFolder, string.Format("{0}.log", serializerName)), raw);
        }

        private void EnsureLogFolder()
        {
            if (!Directory.Exists(logFolder))
            {
                Directory.CreateDirectory(logFolder);
            }
        }
    }
}
