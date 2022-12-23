package examples;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.openlca.core.DataDir;
import org.openlca.core.database.FlowDao;
import org.openlca.core.database.ImpactMethodDao;
import org.openlca.core.database.MySQL;
import org.openlca.core.database.ProcessDao;
import org.openlca.core.database.ProductSystemDao;
import org.openlca.core.math.SystemCalculator;
import org.openlca.core.matrix.ProductSystemBuilder;
import org.openlca.core.matrix.cache.MatrixCache;
import org.openlca.core.matrix.linking.LinkingConfig;
import org.openlca.core.matrix.linking.ProviderLinking;
import org.openlca.core.model.CalculationSetup;
import org.openlca.core.model.Exchange;
import org.openlca.core.model.Flow;
import org.openlca.core.model.FlowPropertyFactor;
import org.openlca.core.model.ImpactMethod;
import org.openlca.core.model.ImpactCategory;
import org.openlca.core.model.ImpactFactor;
import org.openlca.core.model.ProductSystem;
import org.openlca.nativelib.NativeLib;
import org.openlca.core.model.Process;
import org.openlca.core.model.ProcessType;



public class MySQLUserInfoByProcess {
	
	public static void main(String[] args) 
			throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		
		Connection conn = MySQL.getMySQLConnection();
		String table_name = "user_info";//"user_calc";	// html에서 받아온 user data table
		int select_id = 1;	// 식별자로 일단 ID 지정, project_name or product_name으로 변경할지 생각..
		
		String sql = "SELECT * FROM " + table_name;		// query로 control
		ResultSet rs = null;
		Statement stmt = null;
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);	// 실제 쿼리 진행. rs = ResultSet 구조로 데이터 참조돼 있음
		
		System.out.println(sql);
		
		int id = 1;
		String project_name = null;
		String product_name = null;
		String fu = null;
		double product_amount = 1;
		
		List<String> input_name = new ArrayList<>();
		List<String> output_name = new ArrayList<>();
		
		
		
		// user_calc
		
		
		while(rs.next()) {
			id = rs.getInt(1);
			project_name = rs.getString(2);
			product_name = rs.getString(3);
			fu = rs.getString(6);
			//product_amount = rs.getFloat(7);
			
			if (id == select_id) {
				System.out.printf("\n\n----------------------------User DB Infomation(in HTML)----------------------------\n");
				System.out.printf("id: %s%n"
						+ "project_name: %s%"
						+ "nproduct_name: %s%n"
						+ "fu: %s%n"
						+ "product_amount: %f%n%n",
						id, project_name, product_name, fu, product_amount);	
				
				NativeLib.loadFrom(DataDir.get().root());	// DB load
				try (var db = DataDir.get().openDatabase("test1109_copy6")) {
					System.out.printf("\n\n---------------------------openLCA DB Open & Connect User Input---------------------------\n");
					//var system = db.get(ProductSystem.class,
					//	"80abdce2-7d8b-455c-a48d-3d82cea05aed");	// Process load
					//var processes = db.get(Process , id)
					
					var method = new ImpactMethodDao(db).getForName("CML-IA baseline").get(0).copy();	// list라서 .get(0) 추가
					for (ImpactCategory ic : method.impactCategories) {
						if (ic.name.contains("GWP")) {
							System.out.println("ImpactCategory: " + ic);
							for (ImpactFactor ifr : ic.impactFactors) {
								if (ifr.flow.name.equals("Methane") && ifr.flow.category.name.equals("unspecified")) {
									//ifr.value = 128;
									//method = db.update(method);
									System.out.println("ImpactFactor: " + ifr.flow.name + " //"
											+ ifr.flow.category.name + " //value: " + ifr.value);
									break;
								}
							}
							
						}
					}

					//System.out.println("\n"+method.library + method + method.source+"\n");
					/****** process2와 process 같이 db에서의 Process 호출에는 두 가지 방법이 있음. 결과 동일 *****/
					//var process2 = new ProcessDao(db).getForName("Electricity").get(0);
					//System.out.printf("ProcessDao(): %s%n", process2);
					double lci_result_methane = 0.0;
					/**
					 * Modify Process Values
					 */
					//String[] userInputFlows = ;
					// pellet (compare test)
					//ProcessDao pd = new ProcessDao(db);
					var process = db.getForName(Process.class, "pellet").copy();
					System.out.println("exchanges: ");

					for (Exchange e : process.exchanges) {
						Flow f = e.flow;
						//System.out.println("f: " + ix + f);
						
						if (f.name.contains("Pellet")) {
							System.out.printf("PRODUCT_FLOW print: %s %s%n", f.name, e.amount);
						}
						if (e.isInput && f.name.contains("Water")) {							
							e.amount = 0.3;
							System.out.printf("Input flows - ");
							System.out.printf("%s (%s) : %s%s%n",
									f.name, f.flowType, e.amount, e.unit.name);
						} else if (e.isInput == false && f.name.equals("Methane")) {
							System.out.printf("Output flows - ");
							System.out.printf("%s (%s) : %s%s %s%n",
									f.name, f.flowType, e.amount, e.unit.name, f.category.name);
							lci_result_methane = e.amount;
							process.exchanges.remove(e);
							break;
							}
							System.out.println("\n\n");
							//System.out.println("factor: " + );
					}
					var newFlow = new FlowDao(db).getForRefId("b53d3744-3629-4219-be20-980865e54031");
					System.out.println("newFlow: " + newFlow.name + newFlow.category);

					var methane2 = Exchange.output(newFlow, lci_result_methane);
					process.exchanges.add(methane2);
					
					process.name = "pellet1117-cp_test_remove_and_add";
					process = db.update(process);
					//System.out.println("exchanges: "+process.exchanges);
					
					// create and auto-complete the product system
					var config = new LinkingConfig()
						.providerLinking(ProviderLinking.PREFER_DEFAULTS)
						.preferredType(ProcessType.UNIT_PROCESS);
					
					var system = new ProductSystemBuilder(MatrixCache.createLazy(db), config)
						.build(process);
					
					System.out.println("targetFlowPropertyFactor: " + system.targetFlowPropertyFactor);

					system.targetAmount = product_amount;	// User Input
					System.out.printf("\n\n");
					var setup = CalculationSetup.fullAnalysis(system) // == simpleCalc
						.withImpactMethod(method);		
					
					System.out.printf("product name: %s / target amount: %s%s / LCIA method: %s\n\n", 
							system.name, system.targetAmount, system.targetUnit.name, method.name);
					
					// save the product system
					//new ProductSystemDao(db).insert(system);
					
					
					
					System.out.printf("\n\n----------------------------------LCA Calc Result----------------------------------\n");
					// LCA 계산 수행: 따로 할당같은걸 추가하지 않는 이상 calcuateFull()은 calculateSimple()과 결과가 동일하다
					var calc = new SystemCalculator(db);
					var r = calc.calculateFull(setup);		
					
					int i = 0;
					HashMap<String, Double> fs = new HashMap<>();
					
					for (i=0; i<r.enviIndex().size(); i++) {	
						if ((r.enviIndex().at(i).toString().contains("Carbon dioxide"))
								|| (r.enviIndex().at(i).toString().contains("Methane"))) {
							var f = r.enviIndex().at(i);
							double flowResult = r.getTotalFlowResult(f);
							fs.put(f.flow().name, flowResult);
						}
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
					
					i=0;
					while (i < r.impactIndex().size()) {
						if (r.impactIndex().at(i).toString().contains("GWP")) {
							var impact =  r.impactIndex().at(i);
							System.out.printf("%n%s  -> %.12f %s%n%n",
									impact.name, r.getTotalImpactResult(impact), impact.referenceUnit);
							break;
						}
						i++;
					}
					
					db.close();
					
				}	// try() end 
			}	// if() end
		}	// while() end
		
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if(conn != null) conn.close();

	}

}
