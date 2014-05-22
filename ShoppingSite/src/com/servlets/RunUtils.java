package com.servlets;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



//import com.utils.DbUtil;;

public class RunUtils {
	
	
	//private static  Connection conn; 

	 
	 public RunUtils() {
		 
	 }
	 public static Connection getConnection(){
		 Connection conn = null;
		 try{
			 Class.forName("com.mysql.jdbc.Driver");
			 String url="jdbc:mysql://localhost/shopdb";
			 String username="root";
			 String password="7056";
			
			 conn=DriverManager.getConnection(url,username,password);
		 }
			 catch (SQLException ex) {  
		            //message = "ERROR: " + ex.getMessage();  
		            ex.printStackTrace();  
				} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 return conn;
}
		 
	 
	 
	 public static ProductBean randomProduct() {
		 ArrayList<String> path1 = new ArrayList<String>();
		 ArrayList<Float> price1 = new ArrayList<Float>();
		 ArrayList<String> name1 = new ArrayList<String>();
		 ArrayList<String> description1 = new ArrayList<String>();
		
		 
		 
		
		 try{
			
			 Connection conn = getConnection();
			 String sql = "select productPicture,productPrice,productName,productDescription from product order by rand() limit 3";
			 PreparedStatement statement = conn.prepareStatement(sql);
			 
			 ResultSet rs = statement.executeQuery(sql);
			 
			 while(rs.next()) {
				 path1.add(rs.getString("productPicture"));
				 price1.add(rs.getFloat("productPrice"));
				 name1.add(rs.getString("productName"));
				 description1.add(rs.getString("productDescription"));
			 }
		
		
		 }
		 catch (SQLException ex) {  
	            //message = "ERROR: " + ex.getMessage();  
	            ex.printStackTrace();  
			}
		 return(new ProductBean(path1,price1,name1,description1));
	 }
	 
	 
	 public static ProductBean selectCategory(String category){
		 ArrayList<String> path1 = new ArrayList<String>();
		 ArrayList<Float> price1 = new ArrayList<Float>();
		 ArrayList<String> name1 = new ArrayList<String>();
		 ArrayList<String> description1 = new ArrayList<String>();
		 String categoryName = category;
		 
		 try{
			 Connection conn = getConnection();
			 String sql = "select productPicture,productPrice,productName,productDescription from product,category where product.categoryID=category.categoryID and category.categoryName='" + categoryName + "'";     
			 PreparedStatement statement = conn.prepareStatement(sql);
			 
			 ResultSet rs = statement.executeQuery(sql);
			 
			 while(rs.next()) {
				 path1.add(rs.getString("productPicture"));
				 price1.add(rs.getFloat("productPrice"));
				 name1.add(rs.getString("productName"));
				 description1.add(rs.getString("productDescription"));
				 
			 }
		 }
		 catch (SQLException ex) {  
	            //message = "ERROR: " + ex.getMessage();  
	            ex.printStackTrace();  
			}
		 return(new ProductBean(path1,price1,name1,description1));
		 
		 
	 }
	 public static ProductNameBean showProduct(String path, String price){
		 String name = null ;
		 String description = null ;
		// float price1 = Float.parseFloat(price);
		 
		 try{
			 Connection conn = getConnection();
			 String sql = "select productName,productDescription from product  where product.productPicture='"+path+"'";     
			 
			 PreparedStatement statement = conn.prepareStatement(sql);
			 
			 ResultSet rs = statement.executeQuery(sql);
			 while(rs.next()) {
				 name = (rs.getString("productName"));
				 description = (rs.getString("productDescription"));
				 
			 }

			 
		 }
		 catch (SQLException ex) {  
	            //message = "ERROR: " + ex.getMessage();  
	            ex.printStackTrace();  
			}
		 return(new ProductNameBean(name,description));
	 }
	 
	 
	 public static ProductBean selectDetailsProduct(String category, String cost1, String cost2,String searchword){
		 float cost3 = Float.parseFloat(cost1);
		 float cost4  = Float.parseFloat(cost2);
		 ArrayList<String> path1 = new ArrayList<String>();
		 ArrayList<Float> price1 = new ArrayList<Float>();
		 ArrayList<String> name1 = new ArrayList<String>();
		 ArrayList<String> description1 = new ArrayList<String>();
		
		 
		 if (category.equals("hepsi")){
			 try{
				 Connection conn = getConnection();
				 String sql = "select productPicture,productPrice,productName,productDescription from product where product.productPrice between'"+cost3+"'and '"+cost4+"' and product.productName like '%" + searchword + "%'";     
				 PreparedStatement statement = conn.prepareStatement(sql);
				 
				 ResultSet rs = statement.executeQuery(sql);
				 
				 while(rs.next()) {
					 path1.add(rs.getString("productPicture"));
					 price1.add(rs.getFloat("productPrice"));
					 name1.add(rs.getString("productName"));
					 description1.add(rs.getString("productDescription"));
					 
				 }
			 }
			 catch (SQLException ex) {  
		            //message = "ERROR: " + ex.getMessage();  
		            ex.printStackTrace();  
				}
			 
		 }
		 else{
			 try{
				 Connection conn = getConnection();
				 String sql = "select productPicture,productPrice,productName,productDescription from product,category where product.categoryID=category.categoryID and category.categoryName='" + category + "' and product.productPrice between'"+cost1+"'and '"+cost2+"' and product.productName like '%" + searchword + "%'" ;     
				 PreparedStatement statement = conn.prepareStatement(sql);
				 
				 ResultSet rs = statement.executeQuery(sql);
				 
				 while(rs.next()) {
					 path1.add(rs.getString("productPicture"));
					 price1.add(rs.getFloat("productPrice"));
					 name1.add(rs.getString("productName"));
					 description1.add(rs.getString("productDescription"));
					 
				 }
			 }
			 catch (SQLException ex) {  
		            //message = "ERROR: " + ex.getMessage();  
		            ex.printStackTrace();  
				}
		 }
		 
		 
		 return(new ProductBean(path1,price1,name1,description1));
		 
	 }
	 
	 public static UserNameBean getUserName(String username,String password){
		 String name = null;
		 String surname = null;
		
		 try{
			 Connection conn = getConnection();
			 String sql = "select name,surname from user  where user.userName='"+username+"' and user.password='"+password+"'";     
			 
			 PreparedStatement statement = conn.prepareStatement(sql);
			 
			 ResultSet rs = statement.executeQuery(sql);
			 while(rs.next()) {
				 name = (rs.getString("name"));
				 surname = (rs.getString("surname"));
				 
			 }
			 
			
			 
		 }
		 catch (SQLException ex) {  
			   name = "unknow";
			   surname = "unknow";
			   ex.printStackTrace(); 
		}
		
		return new UserNameBean(name,surname);
	 }
	 
	 public static int insertPerson(String username, String name, String surname, String email, String password){
		 int numRowsChanged;
		 try{
			 Connection conn = getConnection();
			 String sql = "insert into user values(?,?,?,?,?)";
			 PreparedStatement statement = conn.prepareStatement(sql);
			 statement.setString(1, username);
			 statement.setString(2, name);
			 statement.setString(3, surname);
			 statement.setString(4, email);
			 statement.setString(5, password);
			 numRowsChanged = statement.executeUpdate();
			 
			 
			 
			 
			 
		 }catch (SQLException ex) {  
			numRowsChanged = 0;
		}
		 
		return  numRowsChanged;
		 
	 }
	 
	 
	
	 
}
