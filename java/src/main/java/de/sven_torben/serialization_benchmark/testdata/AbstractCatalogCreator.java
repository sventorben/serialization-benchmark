package de.sven_torben.serialization_benchmark.testdata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/*
 * This code is borrowed from http://stackoverflow.com/a/7993031 
 */
public abstract class AbstractCatalogCreator<TC, TCI> {

	private static int RANDOM_SEED = new Random().nextInt(Integer.MAX_VALUE);
	private static int NUM_TEST_ITEMS = 5000;

	private static String[] lipsum = StringUtils
			.split("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin non nisi in tortor imperdiet tempor nec at erat. Vivamus hendrerit congue augue pretium lobortis. Pellentesque mollis elit lorem, at aliquam nibh lobortis vel. Aenean malesuada nunc nec efficitur tristique. Donec a tincidunt tortor, ac tincidunt odio. Aenean mollis elementum eros, vel gravida eros iaculis sodales. In tristique dignissim rutrum. Proin id imperdiet sapien, sed scelerisque mauris. Maecenas quis volutpat mi, sit amet egestas lacus. Donec ultrices mi quis justo consequat blandit. Etiam molestie tellus eu luctus elementum. Ut porta est nisi, at imperdiet ligula gravida sit amet. Suspendisse gravida mattis tempor. Curabitur tellus justo, eleifend sit amet sollicitudin suscipit, tristique eget ante. Fusce eget dapibus nisi, eget blandit leo. Cras ut laoreet est. Vivamus in placerat ante. Praesent faucibus sodales est vel vestibulum. Nunc sagittis justo vel sollicitudin fermentum.",
					' ');

	public final TC create() {
		return createCatalog(createItems());
	}

	private List<TCI> createItems() {

		final Random rand = new Random(RANDOM_SEED);

		final List<TCI> items = new ArrayList<TCI>(NUM_TEST_ITEMS);

		final DateTime start = new DateTime(1995, 1, 1, 0, 0, 0);
		final Period p = new Period(start.getMillis(), DateTime.now()
				.getMillis(), PeriodType.days());
		final int range = p.getDays();

		for (int i = 0; i < NUM_TEST_ITEMS; i++) {
			final StringBuilder name = new StringBuilder(
					lipsum[rand.nextInt(lipsum.length)]);
			final int wordCount = rand.nextInt(5);
			for (int j = 0; j < wordCount; j++) {
				name.append(' ').append(lipsum[rand.nextInt(lipsum.length)]);
			}
			items.add(createItem(
					i,
					name.toString(),
					rand.nextInt(1000),
					new BigDecimal(rand.nextInt(10000)).divide(
							new BigDecimal(100)).doubleValue(),
					start.plusDays(rand.nextInt(range))));
		}

		return items;
	}

	protected abstract TC createCatalog(List<TCI> items);

	protected abstract TCI createItem(int id, String name, int quantity,
			double price, DateTime inStockSince);

}
