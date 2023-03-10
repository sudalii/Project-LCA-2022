package org.openlca.ilcd.tests.network;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;
import org.openlca.ilcd.descriptors.CategorySystemList;
import org.openlca.ilcd.descriptors.DataStock;
import org.openlca.ilcd.descriptors.DataStockList;
import org.openlca.ilcd.io.SodaClient;
import org.openlca.ilcd.io.SodaConnection;
import org.openlca.ilcd.lists.CategoryList;
import org.openlca.ilcd.lists.CategorySystem;
import org.openlca.ilcd.lists.ContentType;

public class StocksAndCategoryListsTest {

	@Test
	public void testGetDataStocks() {
		Assume.assumeTrue(TestServer.isAvailable());
		try (var client = TestServer.newClient()) {
			var stocks = client.getDataStockList();
			assertTrue(stocks.dataStocks.size() > 0);
		}
	}

	@Test
	@Ignore
	public void testGetCategorySystems() {
		SodaConnection con = new SodaConnection();
		con.url = "http://www.oekobaudat.de/OEKOBAU.DAT/resource";
		try (SodaClient client = new SodaClient(con)) {
			CategorySystemList list = client.getCategorySystemList();
			List<String> names = list.getNames();
			assertEquals(1, names.size());
			assertTrue(names.contains("OEKOBAU.DAT"));
		}
	}

	@Test
	@Ignore
	public void testGetCategorySystem() {
		SodaConnection con = new SodaConnection();
		con.url = "http://www.oekobaudat.de/OEKOBAU.DAT/resource";
		try (SodaClient client = new SodaClient(con)) {
			CategorySystem system = client.getCategorySystem("OEKOBAU.DAT");
			boolean processTypeFound = false;
			for (CategoryList list : system.categories) {
				if (list.type == ContentType.PROCESS) {
					assertTrue(list.categories.size() > 5);
					processTypeFound = true;
				}
			}
			assertTrue(processTypeFound);
		}
	}

}
