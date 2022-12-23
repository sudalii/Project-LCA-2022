package examples;

import java.io.File;

import org.openlca.core.database.Derby;
import org.openlca.core.math.SystemCalculator;
import org.openlca.core.model.CalculationSetup;
import org.openlca.core.model.ProductSystem;

public class LibraryCalculatorExample {

	public static void main(String[] args) throws Exception {

		var workspace = "C:\\Users\\KETI\\openLCA-data-1.4";
		var db = new Derby(new File(
				workspace + "\\databases\\test1012_copy\\"));

		var system = db.get(ProductSystem.class,
				"80abdce2-7d8b-455c-a48d-3d82cea05aed");
		var setup = CalculationSetup.simple(system);

		var result = new SystemCalculator(db)
				.calculateSimple(setup);
		
		int i = 0;
		while(result.enviIndex().at(i) != null) {
			var flow = result.enviIndex().at(i);
			System.out.println(flow.flow().name
					+ " => " + result.getTotalFlowResult(flow));	
			i++;
		}
		
		db.close();

	}

}
