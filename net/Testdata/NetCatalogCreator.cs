using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace De.Sven_Torben.Serialization_Benchmark.Testdata
{
    class NetCatalogCreator : AbstractCatalogCreator<Catalog, CatalogItem>
    {
        protected override Catalog CreateCatalog(List<CatalogItem> items)
        {
            var catalog = new Catalog();
            catalog.Items = items.ToArray();
            return catalog;
        }

        protected override CatalogItem CreateItem(int id, string name, int quantity, decimal price, DateTime inStockSince)
        {
            var item = new CatalogItem();
            item.Id = id;
            item.Name = name;
            item.QuantityAvailable = quantity;
            item.Price = price;
            item.InStockSince = inStockSince;
            return item;
        }
    }
}
