package examples;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openlca.core.DataDir;
import org.openlca.core.database.ImpactMethodDao;
import org.openlca.core.database.MySQL;
import org.openlca.core.math.SystemCalculator;
import org.openlca.core.model.CalculationSetup;
import org.openlca.core.model.Exchange;
import org.openlca.core.model.Flow;
import org.openlca.core.model.ImpactMethod;
import org.openlca.core.model.ProductSystem;
import org.openlca.nativelib.NativeLib;
import org.openlca.core.model.Process;



public class MySQLUserInfo {
	
	public static void main(String[] args) 
			throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		
		Connection conn = MySQL.getMySQLConnection();
		String table_name = "user_info";//"user_calc";	// html에서 받아온 user data table
		int select_id = 6;	// 식별자로 일단 ID 지정, project_name or product_name으로 변경할지 생각..
		
		String sql = "SELECT * FROM " + table_name;		// query로 control
		ResultSet rs = null;
		Statement stmt = null;
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);	// 실제 쿼리 진행. rs = ResultSet 구조로 데이터 참조돼 있음
		
		System.out.println(sql);
		
		int id = 0;
		String project_name = null;
		String product_name = null;
		String fu = null;
		double product_amount = 0.0;

		while(rs.next()) {
			id = rs.getInt(1);
			project_name = rs.getString(2);
			product_name = rs.getString(3);
			fu = rs.getString(6);
			product_amount = rs.getFloat(7);
			
			if (id == select_id) {
				System.out.printf("\n\n----------------------------User DB Infomation(in HTML)----------------------------\n");
				System.out.printf("id: %s%n"
						+ "project_name: %s%"
						+ "nproduct_name: %s%n"
						+ "fu: %s%n"
						+ "product_amount: %f%n%n",
						id, project_name, product_name, fu, product_amount);	
				
				NativeLib.loadFrom(DataDir.get().root());	// DB load
				try (var db = DataDir.get().openDatabase("test1012_copy")) {
					System.out.printf("\n\n---------------------------openLCA DB Open & Connect User Input---------------------------\n");
					var system = db.get(ProductSystem.class,
						"80abdce2-7d8b-455c-a48d-3d82cea05aed");	// Process load
					//var processes = db.get(Process , id)
					
					var method = new ImpactMethodDao(db).getForName("CML-IA baseline").get(0);	// list라서 .get(0) 추가

					/*
					List<ImpactMethod> methods = new ImpactMethodDao(db).getAll();
					System.out.printf("method All:\n");
					for (ImpactMethod mhd : methods) {
						System.out.println(mhd.name);
					}
					*/
					var process = db.getForName(Process.class, "pellet");
					System.out.println("exchanges: ");
					for (Exchange e : process.exchanges) {
						Flow f = e.flow;
						
						if (e.isInput) {
							
							System.out.printf("Input flows - ");
							System.out.printf("%s (%s) : %s%s%n",
									f.name, f.flowType, e.amount, e.unit.name);
						} else {
							
							System.out.printf("Output flows - ");
							System.out.printf("%s (%s) : %s%s%n",
									f.name, f.flowType, e.amount, e.unit.name);
						}
						
							
					}
					//System.out.println("exchanges: "+process.exchanges);
					System.out.println("getForName(): "+ process);
					System.out.println("targetFlowPropertyFactor: " + system.targetFlowPropertyFactor);

					system.targetAmount = product_amount;	// User Input
					System.out.printf("\n\n");
					var setup = CalculationSetup.fullAnalysis(system)
						.withImpactMethod(method);		
					
					System.out.printf("product name: %s / target amount: %s%s / LCIA method: %s\n\n", 
							system.name, system.targetAmount, system.targetUnit.name, method.name);


					
					System.out.printf("\n\n----------------------------------LCA Calc Result----------------------------------\n");
					// LCA 계산 수행: 따로 할당같은걸 추가하지 않는 이상 calcuateFull()은 calculateSimple()과 결과가 동일하다
					var calc = new SystemCalculator(db);
					var r = calc.calculateFull(setup);		
					
					int i = 0;
					HashMap<String, Double> fs = new HashMap<>();
					
					for (i=0; i<r.enviIndex().size(); i++) {				
						var f = r.enviIndex().at(i);
						double flowResult = r.getTotalFlowResult(f);
						fs.put(f.flow().name, flowResult);		
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
							System.out.printf("%n%s  -> %.6f %s%n%n",
									impact.name, r.getTotalImpactResult(impact), impact.referenceUnit);
							break;
						}
						i++;
					}
					
				}
			}

		}
		
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		if(conn != null) conn.close();

		
		
	}

}
