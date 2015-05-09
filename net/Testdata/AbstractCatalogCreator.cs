using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Testdata
{
    /// <summary>
    /// This code is borrowed from http://stackoverflow.com/a/7993031
    /// </summary>
    abstract class AbstractCatalogCreator<TC, TCI>
    {
        private static readonly int RANDOM_SEED = new Random().Next(int.MaxValue);
        private static readonly int NUM_TEST_ITEMS = 5000;

        private static string[] lipsum =
                @"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin non nisi in tortor imperdiet tempor nec at erat. Vivamus hendrerit congue augue pretium lobortis. Pellentesque mollis elit lorem, at aliquam nibh lobortis vel. Aenean malesuada nunc nec efficitur tristique. Donec a tincidunt tortor, ac tincidunt odio. Aenean mollis elementum eros, vel gravida eros iaculis sodales. In tristique dignissim rutrum. Proin id imperdiet sapien, sed scelerisque mauris. Maecenas quis volutpat mi, sit amet egestas lacus. Donec ultrices mi quis justo consequat blandit. Etiam molestie tellus eu luctus elementum. Ut porta est nisi, at imperdiet ligula gravida sit amet. Suspendisse gravida mattis tempor. Curabitur tellus justo, eleifend sit amet sollicitudin suscipit, tristique eget ante. Fusce eget dapibus nisi, eget blandit leo. Cras ut laoreet est. Vivamus in placerat ante. Praesent faucibus sodales est vel vestibulum. Nunc sagittis justo vel sollicitudin fermentum."
                .Split(' ');

        public TC Create()
        {
            return CreateCatalog(CreateItems());
        }

        private List<TCI> CreateItems()
        {

            Random rand = new Random(RANDOM_SEED);

            List<TCI> items = new List<TCI>(NUM_TEST_ITEMS);

            DateTime start = new DateTime(1995, 1, 1, 0, 0, 0);
            int range = (DateTime.Today - start).Days;

            for (int i = 0; i < NUM_TEST_ITEMS; i++)
            {
                StringBuilder name = new StringBuilder(
                    lipsum[rand.Next(lipsum.Length)]);
                int wordCount = rand.Next(5);
                for (int j = 0; j < wordCount; j++)
                {
                    name.Append(' ').Append(lipsum[rand.Next(lipsum.Length)]);
                }
                items.Add(CreateItem(
                    i,
                    name.ToString(),
                    rand.Next(1000),
                    rand.Next(10000) / 100M,
                    start.AddDays(rand.Next(range))));
            }

            return items;
        }

        protected abstract TC CreateCatalog(List<TCI> items);

        protected abstract TCI CreateItem(int id, String name, int quantity, decimal price, DateTime inStockSince);
    }
}
