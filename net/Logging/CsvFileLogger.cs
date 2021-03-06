﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Logging
{
    sealed class CsvFileLogger : AbstractDetailsLogger
    {
        private static readonly Encoding ENCODING = Encoding.UTF8;

        private string logFile;

        public CsvFileLogger() : this(Path.Combine(Directory.GetCurrentDirectory(), "logs", "benchmark.log")) { }

        public CsvFileLogger(string logFilePath)
        {
            if (!String.IsNullOrEmpty(logFilePath))
            {
                this.logFile = logFilePath;
                EnsureCleanLogfile();
                Console.Out.WriteLine("CSV Log: {0}", logFile);
            }
        }

        private void EnsureCleanLogfile()
        {
            if (File.Exists(logFile))
            {
                File.Delete(logFile);
            }
            string dirName = Path.GetDirectoryName(logFile);
            if (!Directory.Exists(dirName))
            {
                Directory.CreateDirectory(dirName);
            }
            File.AppendAllText(logFile, "serializerName;bytes;iterations;min write;min read;avg write;avg read; max write; max read" + Environment.NewLine, ENCODING);
        }

        [MethodImpl(MethodImplOptions.Synchronized)]
        protected override void Log(string serializerName, int bytes, int iterations, long minWrite, long minRead, double avgWrite, double avgRead, long maxWrite, long maxRead)
        {
            if (!String.IsNullOrEmpty(logFile))
            {
                File.AppendAllText(logFile, string.Format("{0};{1};{2};{3};{4};{5};{6};{7};{8}{9}", serializerName, bytes, iterations, minWrite, minRead, avgWrite, avgRead, maxWrite, maxRead, Environment.NewLine), ENCODING);
            }
        }
    }
}
