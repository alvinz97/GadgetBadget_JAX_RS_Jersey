package com.gadget_badget.projects.model;

import java.sql.*;

public class ProjectServlet 
{
	
	//A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
			 
			 //Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget_rest_jersey", "root", "issa123"); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		 	return con; 
		} 
	
	
	
	
		 public String insertProject(String p_code, String p_name, String p_desc, String p_dev_by , String p_price , String p_category , String p_service_charge) 
		 { 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for inserting."; 
				 } 
			 	 	 // create a prepared statement
				 	 String query = " INSERT INTO project_tab(`projectID`,`projectCode`,`projectName`,`projectDesc`,`projectDevBy`,`projectPrice`,`projectCategory`,`projectServiceCharge`)"
				     + " VALUES (?, ?, ?, ?, ? ,? ,? ,?)"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
					 
					 // binding values
					 preparedStmt.setInt(1, 0); 
					 preparedStmt.setString(2, p_code); 
					 preparedStmt.setString(3, p_name); 
					 preparedStmt.setString(4, p_desc);
					 preparedStmt.setString(5, p_dev_by); 
					 preparedStmt.setDouble(6, Double.parseDouble(p_price)); 
					 preparedStmt.setString(7, p_category); 
					 preparedStmt.setDouble(8, Double.parseDouble(p_service_charge)); 
					 
				 
					 preparedStmt.execute(); 
					 con.close(); 
					 output = "Project details have been inserted successfully..!"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while inserting the project..!."; 
				 System.err.println(e.getMessage()); 
			 } 
		 	return output; 
		 } 
		 
		 
		 
		 public String readProjects() 
		 { 
			 String output = ""; 
			 String boostrap_link = "<head><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\' integrity=\'sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\' crossorigin=\'anonymous\'></head>";
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {return "Error while connecting to the database for reading."; } 
				 // Prepare the html table to be displayed
				 output = boostrap_link + "<table border='1' style='text-align:center'><tr>"
				 + "<th>Project Code</th>"
				 + "<th>Project Name</th>" +
				 "<th>Project Description</th>" + 
				 "<th>Project Developed By</th>" + 
				 "<th>Project Price</th>" +
				 "<th>Project Category</th>" +
				 "<th>Project Service Charge</th>" +
				 "<th>Update</th><th>Remove</th></tr>"; 
			 
				 String query = "SELECT * FROM project_tab"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
					 String projectID = Integer.toString(rs.getInt("projectID")); 
					 String projectCode = rs.getString("projectCode"); 
					 String projectName = rs.getString("projectName"); 
					 String projectDesc = rs.getString("projectDesc"); 
					 String projectDevBy = rs.getString("projectDevBy"); 
					 String projectPrice = Double.toString(rs.getDouble("projectPrice")); 
					 String projectCategory = rs.getString("projectCategory"); 
					 String projectServiceCharge = Double.toString(rs.getDouble("projectServiceCharge")); 
					 
					 // Add into the html table
					 output += "<tr><td>" + projectCode + "</td>"; 
					 output += "<td>" + projectName + "</td>"; 
					 output += "<td>" + projectDesc + "</td>"; 
					 output += "<td>" + projectDevBy + "</td>"; 
					 output += "<td>" + projectPrice + "</td>"; 
					 output += "<td>" + projectCategory + "</td>"; 
					 output += "<td>" + projectServiceCharge + "</td>"; 
					 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
					 + "<td><form method='post' action='items.jsp'>"
					 + "<input style='margin-top:15px' name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
					 + "<input name='itemID' type='hidden' value='" + projectID 
					 + "'>" + "</form></td></tr>"; 
				 } 
				 	 con.close(); 
				 	 // Complete the html table
				 	 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while reading the project details...!"; 
				 System.err.println(e.getMessage()); 
			 } 
		 	 return output; 
		 } 
		
		
		
		public String updateProject(String p_ID, String p_code, String p_name, String p_desc, String p_dev_by , String p_price , String p_category , String p_service_charge)
		{ 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for updating."; 
				 } 
				 // create a prepared statement
				 String query = "UPDATE project_tab SET projectCode=? , projectName=? , projectDesc=? , projectDevBy=? , projectPrice=? , projectCategory=? , projectServiceCharge=?  WHERE projectID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 // binding values
				 preparedStmt.setString(1, p_code); 
				 preparedStmt.setString(2, p_name); 
				 preparedStmt.setString(3, p_desc); 
				 preparedStmt.setString(4, p_dev_by); 
				 preparedStmt.setDouble(5, Double.parseDouble(p_price)); 
				 preparedStmt.setString(6, p_category); 
				 preparedStmt.setDouble(7, Double.parseDouble(p_service_charge)); 
				 preparedStmt.setInt(8, Integer.parseInt(p_ID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Updated successfully"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while updating the item."; 
				 System.err.println(e.getMessage()); 
			 } 
			 	return output; 
			 } 
		
		
		
			 public String deleteProject(String projectID) 
			 { 
				 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for deleting."; 
			 } 
			 
			 	 // create a prepared statement
				 String query = "DELETE FROM project_tab WHERE projectID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(projectID)); 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while deleting the project details."; 
				 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
} 