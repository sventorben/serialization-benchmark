package de.sven_torben.serialization_benchmark.serializer;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import de.sven_torben.serialization_benchmark.testdata.java.Catalog;

public final class XStreamSerializer extends AbstractSerializer<Catalog> {

	private static List<Class<? extends HierarchicalStreamDriver>> SKIP_DRIVERS = new ArrayList<Class<? extends HierarchicalStreamDriver>>(
			Arrays.asList(JsonHierarchicalStreamDriver.class));

	private static String XSTREAM_DRIVER_PACKAGE = "com.thoughtworks.xstream.io";

	private final XStream serializer;
	private final HierarchicalStreamDriver driver;

	public XStreamSerializer(final HierarchicalStreamDriver driver) {
		super(Catalog.class);
		this.driver = driver;
		this.serializer = new XStream(driver);
		serializer.autodetectAnnotations(true);
	}

	@Override
	public final void serialize(final OutputStream stream, final Catalog data) {
		final HierarchicalStreamWriter writer = driver.createWriter(stream);
		serializer.marshal(data, writer);
		writer.flush();
		writer.close();
	}

	@Override
	public final Catalog deserialize(final InputStream stream) {
		final HierarchicalStreamReader reader = driver.createReader(stream);
		final Catalog catalog = (Catalog) serializer.unmarshal(reader);
		reader.close();
		return catalog;
	}

	@Override
	public String getName() {
		return String.format("%s with %s", super.getName(), driver.getClass()
				.getSimpleName());
	}

	public static Set<XStreamSerializer> createAllWithDefaultDrivers() {

		final Set<XStreamSerializer> xstreamSerializers = new HashSet<XStreamSerializer>();

		new Reflections(XSTREAM_DRIVER_PACKAGE)
				.getSubTypesOf(HierarchicalStreamDriver.class)
				.parallelStream()
				.filter(d -> !SKIP_DRIVERS.contains(d) && !d.isInterface()
						&& !Modifier.isAbstract(d.getModifiers()))
				.forEach(
						d -> xstreamSerializers
								.add(createSerializerForClass(d)));
		return xstreamSerializers;
	}

	private static XStreamSerializer createSerializerForClass(
			Class<? extends HierarchicalStreamDriver> d) {

		XStreamSerializer serializer = null;
		try {
			return serializer = new XStreamSerializer(d.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return serializer;
	}

}
