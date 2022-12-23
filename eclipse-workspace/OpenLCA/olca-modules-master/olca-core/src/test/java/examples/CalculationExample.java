package examples;

import org.openlca.core.DataDir;
import org.openlca.core.database.ImpactMethodDao;
import org.openlca.core.math.SystemCalculator;
import org.openlca.core.model.CalculationSetup;
import org.openlca.core.model.ProductSystem;
import org.openlca.nativelib.NativeLib;

import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Collections;


public class CalculationExample {

	public static void main(String[] args) {
		NativeLib.loadFrom(DataDir.get().root());
		try (var db = DataDir.get().openDatabase("test1012")) {
			var system = db.get(ProductSystem.class,
				"80abdce2-7d8b-455c-a48d-3d82cea05aed");
			var method = new ImpactMethodDao(db)
				.getForRefId("effb055a-ad78-39bd-8dc0-341411db4ae7");
			
			// system.targetAmount = USER_INPUT;
			
			var setup = CalculationSetup.fullAnalysis(system)
				.withImpactMethod(method);
			//var setup = CalculationSetup.simple(system)
			//		.withImpactMethod(method);
			System.out.printf("%s %s%s / %s\n\n", 
					system.name, system.targetAmount, system.targetUnit.name, method.name);
			var calc = new SystemCalculator(db);
			var r = calc.calculateFull(setup);
			//var r = calc.calculateSimple(setup);
			
			//fs.getTotalFlowResults();
			
			int i=0;
			HashMap<String, Double> fs = new HashMap<>();
			
			for (i=0; i<r.enviIndex().size(); i++) {				
				var f = r.enviIndex().at(i);
				double flowResult = r.getTotalFlowResult(f);
				fs.put(f.flow().name, flowResult);
				//if (r.getTotalFlowResult(f) > 0.000001) {
				// System.out.println(f.flow().name + "  -> " + r.getTotalFlowResult(f));					
				//}						
			}
			// 내림차순 정렬
			List<String> listKeySet = new ArrayList<>(fs.keySet());
			Collections.sort(listKeySet, (value1, value2) -> 
			(fs.get(value2).compareTo(fs.get(value1))));	

			System.out.println("The total inventory result of the given flow - Top 10:");
			int idx = 0;
			for(String key : listKeySet) {
				if (idx < 10) {
					System.out.printf("%s  -> %.8f\n", key, fs.get(key));
				}
				idx++;
			}
			/*
			for (i=0; i<r.impactIndex().size(); i++) {				
				var impact = r.impactIndex().at(i);
				System.out.println("\nthe impact category results for the given result:");
				System.out.printf("%s : %.6f\n", impact.name, r.getTotalImpactResult(impact));
			}
			*/
			//System.out.println(f.flow().name + "  -> " + r.getTotalFlowResult(f));					
			// System.out.println("r leng:"+r.impactIndex().size());
			
			i=0;
			while (i < r.impactIndex().size()) {
				if (r.impactIndex().at(i).toString().contains("GWP")) {
					var impact =  r.impactIndex().at(i);
					//System.out.println(impact.name + "  -> " + );		
					System.out.printf("%n%s  -> %.6f %s%n%n",
							impact.name, r.getTotalImpactResult(impact), impact.referenceUnit);
					break;
				}
				i++;
			}


		}
	}
}
