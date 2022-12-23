package model;
import model.UserDAO;
import java.sql.*;
import java.util.*;


public class CalcDAO {
	   String table_name = "user_info";	// html에서 받아온 user data table

	   public CalcDAO() {
		   
	   }
	   
	   public UserVO calc(int id) {
		   UserVO vo = new UserVO();
		   
		   try {
			   
		   }catch(Exception e) {
	           System.out.println(e.getMessage());
		   }finally {
			   //UserDAO.disConnection();
			   //UserDAO.
		   }
		   
		   return vo;
	   }
}
