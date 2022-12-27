package model;

import java.util.*;
import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;	// time.Date 대신 LocalDate를 권장함, Date format은 String으로 처리하는 것이 편함
import java.time.format.DateTimeFormatter;
//import org.json.JSONObject;

import org.openlca.core.DataDir;
import org.openlca.core.database.FlowDao;
import org.openlca.core.database.IDatabase;
import org.openlca.core.database.ProcessDao;
import org.openlca.core.database.ProductSystemDao;
import org.openlca.core.database.ImpactMethodDao;

import org.openlca.core.math.SystemCalculator;
import org.openlca.core.matrix.ProductSystemBuilder;
import org.openlca.core.matrix.cache.MatrixCache;
import org.openlca.core.matrix.linking.LinkingConfig;
import org.openlca.core.matrix.linking.ProviderLinking;
import org.openlca.core.model.CalculationSetup;
import org.openlca.core.model.Exchange;
import org.openlca.core.model.Flow;
import org.openlca.core.model.ImpactCategory;
import org.openlca.core.model.ImpactFactor;
import org.openlca.core.model.Process;
import org.openlca.core.model.ProcessType;
import org.openlca.nativelib.NativeLib;

public class UserDAO {
	//연결
	   private Connection conn;
	   //SQL문장 전송
	   private PreparedStatement ps;
	   //URL
	   private final String URL="jdbc:mysql://localhost:3306/openlca";
	   private final String user = "root";
	   private final String pw = "6457";
	   
	   //String table_name = "user_info";	// html에서 받아온 user data table
	   //int select_id;	// table 식별자로 ID 지정
	   
	   // 연결 준비
	   // 1. 드라이버 등록
	   public UserDAO(){
		   
	      try{ 
	         Class.forName("com.mysql.cj.jdbc.Driver"); 
	      }catch(Exception ex){
	    	  System.out.println("In UserDAO()");
	    	  System.out.println(ex.getMessage());
	      }
	      
	   }
	   //연결
	   public void getConnection() {
	      try{
	    	  //Connection conn = MySQL.getMySQLConnection();
	    	  conn = DriverManager.getConnection(URL, user, pw);   
	      }catch(Exception ex){
	    	  System.out.println(ex.getMessage());
	      }
	   }
	   //해제
	   public void disConnection() {
	   try{
	      if(ps!=null) ps.close();
	      if(conn!=null) conn.close();
	      }catch(Exception ex){}
	   }
	   
	   //기능
	   //1.목록(게시판) SELECT
	   public ArrayList<UserVO> boardListData(){
		   ArrayList<UserVO> list = new ArrayList<UserVO>();
		   try {	   
			   //연결
			   getConnection();
			   
			   //SQL문장 전송
	           String table_name = "user_info"; // join 형태로 가져올 수도 있음
			   String sql = "SELECT * FROM " + table_name;	// query로 control
			   
			   ps = conn.prepareStatement(sql);
			   ResultSet rs = ps.executeQuery();
			   
			   while(rs.next()){
				   UserVO vo = new UserVO();
				   vo.setId(rs.getInt(1));
				   System.out.println("id:" + vo);
				  
				   vo.setProjectName(rs.getString(2));
				   System.out.println("projectName:" + vo);

				   vo.setProductName(rs.getString(3));
				   System.out.println("productName:" + vo);

				   vo.setCreateDate(rs.getString(4));
				   System.out.println("createDate:" + vo);

				   vo.setProductAmount(rs.getFloat(7));
				   System.out.println("productAmout:" + vo);
				   
				   vo.setImpactCategory(rs.getString(9));
				   System.out.println("ImpactCategory:" + vo);
				   
				   vo.setLciaMethod(rs.getString(10));
				   System.out.println("LciaMethod:" + vo);
				   
				   vo.setResult(rs.getFloat(11));
				   System.out.println("result:" + vo);
				   
				   vo.setResultUnit(rs.getString(12));
				   System.out.println("setResultUnit:" + vo);
				   

				   list.add(vo);
				   System.out.println("\n\nIn while - list:" + list + "\n\n");

			   }
			   rs.close();
			   System.out.println("list:" + list);
			   
			   //System.out.printf("DataDir.get().root(): %s%n", DataDir.get().root());
			   //NativeLib.loadFrom(DataDir.get().root());	// openLCA DB load
	           //System.out.printf("DataDir.get(): %s%n", DataDir.get());
	           
		   }catch(Exception ex) {
			   System.out.println("In boardListData()");
			   System.out.println(ex.getMessage());
		   }finally {
			   disConnection();
		   }
		   return list;
	   }
	   
	   // user_info SELECT (WHERE) ?no=1
	   public UserVO boardRead() {
		   UserVO vo=new UserVO();
	       try {
	           // 연결
	           getConnection();

	       }catch(Exception e) {
	           System.out.println(e.getMessage());
	       }
	       finally {
	           disConnection();
	       }
	       return vo;
	   }
	   
	   public int selectMaxId() {
		   int select_id = 0;
		   ResultSet rs = null;
		   try {	    
	           String id_sql = "select max(id) from user_info";
	           ps = conn.prepareStatement(id_sql);
	           rs = ps.executeQuery();
	           rs.next();
	           select_id = rs.getInt(1);
		           
	    	   rs.close();

	       }catch(Exception e) {
	           System.out.println(e.getMessage());
	       }
	       finally {
	       }
	       return select_id;
	   }
	   
	   /*
	   public void resultFlowsInsert(name, lci_result, char_factor, lcia_result) {
		   try {	    
			   getConnection();
			   
			   int select_id = selectMaxId();
	           String re_sql = "INSERT INTO result_flows VALUES(?, ?, ?, ?, ?)";
	           ResultSet rs = conn.prepareStatement(re_sql).executeQuery();
	           rs.next();

	           ps.setDouble(5, vo.getProductAmount());
	           ps.executeUpdate(); //auto COMMIT
	    	   rs.close();

	       }catch(Exception e) {
	           System.out.println(e.getMessage());
	       }
	       finally {
	       }
	       return
	   }
	   */
	   
	   // LCA Calculation (SELECT)
	   public ArrayList<UserVO> Calc() {
		   ArrayList<UserVO> list = new ArrayList<UserVO>();
		   
		   NativeLib.loadFrom(DataDir.get().root());	// openLCA DB load
		   var db = DataDir.get().openDatabase("test1109_copy6");
		   //String lcia_method;
		   String ps_name = "pelletTest1116"; // 추후 받아야 함
           double DB_product_amount = 0.0;
           double result = 0.0;
           double result2 = 0.0;
           String result_unit = null;
		   
	       try {
	           // 1) 연결
	           getConnection();
	           System.out.printf("\n\n---------------------------openLCA DB Open & Connect User Input---------------------------\n");
	           
	           // 2) 해당 id or project name으로 선택
	           int select_id = selectMaxId();
	           System.out.printf("select_id: %d%n", select_id);
	           
	           // 3) 계산 수행
			   String sql = "SELECT * FROM user_info as ui, inputs as i, outputs as o"
			   		+ " WHERE ui.id=" + select_id
			   		+ " AND i.id=" + select_id
			   		+ " AND o.id=" + select_id;
			   ResultSet rs = conn.prepareStatement(sql).executeQuery();
			   
			   var process = db.getForName(Process.class, "pellet").copy(); // process 불러오기 -> 추후 process "생성"으로 변경하기
			      
			   String beforeIName = null;
			   String beforeOName = null;
			   double lci_result_methane = 0;
			   float lci_result_co2 = 0;
			   
			   System.out.println("exchanges: ");
			   while(rs.next()) {
				   
				   for (Exchange e : process.exchanges) {
					   Flow f = e.flow;
					   if (f.name.contains("Pellet")) {
						   DB_product_amount = e.amount;
						   System.out.printf("PRODUCT_FLOW print: %s %s%n", f.name, DB_product_amount);
						}
					   if (e.isInput && f.name.contains(rs.getString(14))) {	// 14: i_name
						   System.out.printf("f.name: %s, beforeName: %s%n", f.name, beforeIName);
						   if (f.name != beforeIName) {
							   // calc = (userInput * DBProductAmount * flowDBData)/userInputProductAmount
							   // 15: user input amount, 16: user input unit, 7: user product_amount
							   System.out.printf("Input flow - ");
							   System.out.printf("(%f * %f * %f)/%f%n", 
									   rs.getFloat(15), DB_product_amount, e.amount, rs.getFloat(7));
							   
							   if (e.amount == 0) {
								   e.amount = (rs.getFloat(15) * DB_product_amount) / rs.getFloat(7);
							   } else {
								   e.amount = (rs.getFloat(15) * DB_product_amount * e.amount) / rs.getFloat(7);
							   }
							   e.unit.name = rs.getString(16);
							   
							   System.out.printf("%s (%s) : %s%s%n%n",
									   f.name, f.flowType, e.amount, e.unit.name);	
							   beforeIName = f.name;
						   }
					   }
					   else if (e.isInput == false && f.name.contains(rs.getString(19))) { // 19: o_name
						   System.out.printf("f.name: %s, beforeName: %s%n", f.name, beforeOName);
						   if (f.name != beforeOName) {	
							   // 20: user output amount, 21: user input unit, 7: user product_amount
							   System.out.printf("Output flow - ");
							   System.out.printf("(%f * %f * %f)/%f%n", 
									   rs.getFloat(20), DB_product_amount, e.amount, rs.getFloat(7));
							   if (e.amount == 0) {
								   e.amount = (rs.getFloat(20) * DB_product_amount) / rs.getFloat(7);							   
							   } else {
								   e.amount = (rs.getFloat(20) * DB_product_amount * e.amount) / rs.getFloat(7);
							   }
							   e.unit.name = rs.getString(21);
							   System.out.printf("%s (%s) : %s%s%n%n",
									   f.name, f.flowType, e.amount, e.unit.name);				   
							   beforeOName = f.name;
						   }
					   }
					   if (f.name.equals("Methane")) {
						   lci_result_methane = e.amount;
					   } else if(f.name.equals("Carbon dioxide")) {
						   lci_result_co2 = (float)e.amount;
					   }
				   }	// for() end
				}	// while() end
			   rs.close();
			   
			   rs = conn.prepareStatement("SELECT * FROM user_info WHERE id=" + select_id).executeQuery();
			   rs.next();
			   
			   process.name = ps_name;
			   
			   process = new ProcessDao(db).insert(process);
			   System.out.printf("process: %s%n", process);
			   
			   // ***************************************************************************************************//			   
			   // openLCA와의 비교를 위한 코드: 변경된 Process 카피해서 Methane의 Category만 high population density로 변경
			   var process2 = process.copy();
			   
			   for (Exchange e : process2.exchanges) {
				   Flow f = e.flow;
				   System.out.println(f);
				   if (f.name.equals("Methane")) {
					   process2.exchanges.remove(e);	// 기존 메탄 지우기
					   break;
				   }
			   }
			   var newFlow = new FlowDao(db).getForRefId("b53d3744-3629-4219-be20-980865e54031");
			   System.out.println("newFlow: " + newFlow.name + newFlow.category);

			   var methane2 = Exchange.output(newFlow, lci_result_methane);
			   process2.exchanges.add(methane2); // 다른 카테고리의 메탄 추가
			   
			   process2.name = ps_name + "_2";
			   process2 = db.update(process2);			   
			   // ***************************************************************************************************//
			   
			   float char_factor1 = 0;
			   float char_factor2 = 0;
			   
			   var method = new ImpactMethodDao(db).getForName(rs.getString(10)).get(0);	// list라서 .get(0) 추가			   
			   for (ImpactCategory ic : method.impactCategories) {
					System.out.println("ImpactCategory: " + ic);
					if (ic.name.contains("GWP")) {
						for (ImpactFactor ifr : ic.impactFactors) {
							if (ifr.flow.name.equals("Carbon dioxide") && ifr.flow.category.name.equals("unspecified")) {
								char_factor1 = (float)ifr.value;
							}
							if (ifr.flow.name.equals("Methane") && ifr.flow.category.name.equals("unspecified")) {
								char_factor2 = (float)ifr.value;
								//ifr.value = 128;
								//System.out.println("ImpactFactor: " + ifr.flow.name + " //"
								//		+ ifr.flow.category.name + " //value: " + ifr.value);
							}
						}
						break;
					}
				}
			   
			   
			   // create and auto-complete the product system
			   var config = new LinkingConfig()
					   .providerLinking(ProviderLinking.PREFER_DEFAULTS)
					   .preferredType(ProcessType.UNIT_PROCESS);
			  
			   var system = new ProductSystemBuilder(MatrixCache.createLazy(db), config)
					   .build(process);

			   var system2 = new ProductSystemBuilder(MatrixCache.createLazy(db), config)
					   .build(process2);
			   
			   system.targetAmount = rs.getFloat(7);	// 7: user product_amount
			   system.name = rs.getString(3);
			   system2.targetAmount = rs.getFloat(7);	// 7: user product_amount
			   system2.name = rs.getString(3) + "_2";
			   
			   System.out.printf("\n\n");
			   System.out.printf("product name: %s / target amount: %s%s / LCIA method: %s\n\n",
					   system.name, system.targetAmount, system.targetUnit.name, method.name);
			   
			   // save the product system
			   new ProductSystemDao(db).insert(system);
			   new ProductSystemDao(db).insert(system2);
			   
			   System.out.printf("\n\n----------------------------------LCA Calc Result----------------------------------\n");
			   var setup = CalculationSetup.simple(system) // == simpleCalc
					   .withImpactMethod(method);
			   var calc = new SystemCalculator(db);
			   var r = calc.calculateSimple(setup);				   

			   var setup2 = CalculationSetup.simple(system2) // == simpleCalc
					   .withImpactMethod(method);
			   var calc2 = new SystemCalculator(db);
			   var r2 = calc2.calculateSimple(setup2);			
			   
			   System.out.println("중간 1");
			   int i;
			   double flow_result1 = 0;
			   double flow_result2 = 0;
			   
			   for (i=0; i<r.enviIndex().size(); i++) {	
					if ((r.enviIndex().at(i).toString().contains("Carbon dioxide"))){
						var f = r.enviIndex().at(i);
						flow_result1 = r.getTotalFlowResult(f);
					}
					if ((r.enviIndex().at(i).toString().contains("Methane"))) {
						var f = r.enviIndex().at(i);
						flow_result2 = r.getTotalFlowResult(f);
						//System.out.println(f.flow().name + flowResult);
					}
			   }
			   System.out.println("중간 2");
			   i=0;
			   while (i < r.impactIndex().size()) {
				   if (r.impactIndex().at(i).toString().contains("GWP")) {
					   var impact =  r.impactIndex().at(i);
					   					   
					   System.out.printf("%nKETI: %s  -> %.6f %s%n%n",
							   impact.name, r.getTotalImpactResult(impact), impact.referenceUnit);
					   result = r.getTotalImpactResult(impact);
					   result_unit = impact.referenceUnit;
					   break;
				   }
				   i++;
			   }
			   System.out.println("중간 3");
			   i=0;
			   while (i < r2.impactIndex().size()) {
				   if (r2.impactIndex().at(i).toString().contains("GWP")) {
					   var impact =  r2.impactIndex().at(i);
					   					   
					   System.out.printf("%nOpenLCA: %s  -> %.6f %s%n%n",
							   impact.name, r2.getTotalImpactResult(impact), impact.referenceUnit);
					   result2 = r2.getTotalImpactResult(impact);
					   break;
				   }
				   i++;
			   }
			   
			   
			   float percent = (float)(result/result2*100);
			   
			   UserVO vo = new UserVO();
			   vo.setProjectName(rs.getString(2));
			   vo.setProductName(rs.getString(3));
			   vo.setProductAmount(rs.getFloat(7));
			   vo.setResult((float)result);
			   vo.setResultUnit(result_unit);
			   
			   vo.setLciResult1((lci_result_co2/rs.getFloat(7))); // target amount로 나눠야 함
			   vo.setCharFactor1((float)char_factor1);
			   vo.setFlowLciaResult1((float)flow_result1);
			   vo.setLciResult2((((float)lci_result_methane)/rs.getFloat(7))); // target amount로 나눠야 함
			   vo.setCharFactor2((float)char_factor2);
			   vo.setFlowLciaResult2((float)flow_result2);
			   
			   // compare
			   vo.setResult2((float)result2);
			   vo.setPercent(percent);
			   
			   list.add(vo);
			   rs.close();
			  
			   ps = conn.prepareStatement("UPDATE user_info SET result=?, result_unit=? "
			   		+ "WHERE id=" + select_id);
			   
			   ps.setFloat(1, vo.getResult());
	           ps.setString(2, vo.getResultUnit());
	           ps.executeUpdate(); //auto COMMIT
	           
	       }catch(Exception e) {
	           System.out.println(e.getMessage());
	           db.close();
	       }
	       finally {
	           disConnection();
	           db.close();
	       }
	       return list;
	   }
	   
	   // user_info INSERT: project-info.jsp
	   public void userInfoInsert(UserVO vo) {
	       try {
	           // 연결
	           getConnection();
	           
	           String table_name = "user_info";
	           String sql="INSERT INTO " + table_name 
	        		   + "(project_name, product_name, create_date, fu, product_amount) "
	        		   + "VALUES(?, ?, ?, ?, ?)";	// ?로 나중에 전달할 데이터들 지정 -> setXXX()로 실제 값 지정
	           ps=conn.prepareStatement(sql);
	           ps.setString(1, vo.getProjectName());
	           ps.setString(2, vo.getProductName());
	           ps.setString(3, vo.getCreateDate());
	           ps.setString(4, vo.getFu());
	           ps.setDouble(5, vo.getProductAmount());
	           ps.executeUpdate(); //auto COMMIT
	           
	           /*
	           String id_sql = "select max(id) from user_info";
	           ps = conn.prepareStatement(id_sql);
	           ResultSet rs = ps.executeQuery();
	           rs.next();
	           select_id = rs.getInt(1);	// 전역변수에 적재
	           */
	    	   System.out.printf("sql: %s%n" ,sql);
	    	   System.out.printf("ps: %s%n" ,ps);

	       }catch(Exception e) {
	    	   System.out.println("In userInfoInsert()");
	           System.out.println(e.getMessage());	           
	       }
	       finally {
	       
	       }
	   }
	  
	   // user_info INSERT: data-make-process.jsp
	   public void userCalcInsert(UserVO vo) {
	       try {
	           // 연결
	           getConnection();
	           String input_names[] = {"Water", "Electricity", "Packaging waste"};
	           String output_names[] = {"Water", "Electricity"};
	           	           
	           // 1) inputs table
	           for (int i=0; i < vo.getInputs().length; i++) {
	        	   String sql = "INSERT INTO inputs(i_name, amount, unit) "
		        		   + "VALUES(?, ?, ?) ";// ?로 나중에 전달할 데이터들 지정 -> setXXX()로 실제 값 지정

		           ps=conn.prepareStatement(sql);
		           ps.setString(1, input_names[i]);
		    	   System.out.printf("ps: %s%n" ,ps);

		           ps.setFloat(2, Float.parseFloat(vo.getInputs()[i]));
		    	   System.out.printf("ps: %s%n" ,ps);

		           ps.setString(3, vo.getIUnits()[i]);
		    	   System.out.printf("ps: %s%n" ,ps);

		           ps.executeUpdate(); //auto COMMIT
		           
		    	   System.out.printf("sql: %s%n" ,sql);
		    	   //System.out.printf("ps: %s%n" ,ps);
	           }
	           
	           // 2) outputs table
	           for (int i=0; i < vo.getOutputs().length; i++) {
	        	   String sql = "INSERT INTO outputs(o_name, amount, unit) "
		        		   + "VALUES(?, ?, ?) ";	// ?로 나중에 전달할 데이터들 지정 -> setXXX()로 실제 값 지정

		           ps=conn.prepareStatement(sql);
		           ps.setString(1, output_names[i]);
		           ps.setFloat(2, Float.parseFloat(vo.getOutputs()[i]));
		           ps.setString(3, vo.getOUnits()[i]);
		           ps.executeUpdate(); //auto COMMIT
		           
		    	   System.out.printf("sql: %s%n" ,sql);
		    	   System.out.printf("ps: %s%n" ,ps);
	           }
	         
	           int select_id = selectMaxId();
	           String sql = "UPDATE inputs as i, outputs as o"
	           		+ " SET i.id = " + select_id + ", o.id = " + select_id 
	           		+ " WHERE i.id IS NULL AND o.id IS NULL";
	           conn.prepareStatement(sql).executeUpdate();
	           
	           // 3) user_info table: user_info에 이미 insert되어 있으니 update로 값 추가
        	   sql = "UPDATE user_info SET "
        			   + "impact_category = ?, lcia_method = ? WHERE id = " + select_id;

	           ps=conn.prepareStatement(sql);
	           ps.setString(1, vo.getImpactCategory());
	           ps.setString(2, vo.getLciaMethod());
	           ps.executeUpdate(); //auto COMMIT
	           
	    	   System.out.printf("sql: %s%n" ,sql);
	    	   System.out.printf("ps: %s%n" ,ps);	           
	    	   
	           
	       }catch(Exception e) {
	    	   System.out.println("In userCalcInsert()");
	           System.out.println(e.getMessage());	           
	       }
	       finally {
	       
	       }
	   }
	
	   //4.글수정 UPDATE
	   //5.글삭제 DELETE
	   //6.찾기 SELECT	
}
