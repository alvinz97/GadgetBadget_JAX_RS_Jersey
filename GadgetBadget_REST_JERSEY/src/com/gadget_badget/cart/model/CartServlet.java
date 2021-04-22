package com.gadget_badget.cart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CartServlet 
{
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
			 
			 //Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget_rest_jersey", "root", ""); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		 	return con; 
    } 


    public String insertCart(String cart_code, String project_code, String project_name, String project_qty , String project_unit_price , String customer_id) 
    { 
            String output = ""; 
            try  { 
                Connection con = connect(); 
                if (con == null)  {
                    return "Error while connecting to the database for inserting."; 
                } 

                // create a prepared statement
                String query = " INSERT INTO cart_tab(`cartID`,`cartCode`,`projectCode`,`projectName`,`projectQty`,`projectUnitPrice`,`customerID`)"
                + " VALUES (?, ?, ?, ?, ? ,? ,?)"; 
                PreparedStatement preparedStmt = con.prepareStatement(query); 
                
                
                // binding values
                preparedStmt.setInt(1, 0); 
                preparedStmt.setString(2, cart_code); 
                preparedStmt.setString(3, project_code); 
                preparedStmt.setString(4, project_name);
                preparedStmt.setDouble(5, Double.parseDouble(project_qty)); 
                preparedStmt.setDouble(6, Double.parseDouble(project_unit_price)); 
                preparedStmt.setString(7, customer_id); 
                
            
                preparedStmt.execute(); 
                con.close(); 
                output = "Project has been added into the cart successfully..!"; 
            }   catch (Exception e)  { 
                output = "Error while inserting the project into the cart..!."; 
                System.err.println(e.getMessage()); 
            } 
        return output; 
    }

    
		 public String readCart() 
		 { 
			 String output = ""; 
			 String boostrap_link_1 = "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\' integrity=\'sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\' crossorigin=\'anonymous\'>";
			 String boostrap_link_2 = "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>";
			 String meta_1 = "<meta charset='utf-8'>";
			 String meta_2 = "<meta name='viewport' content='width=device-width, initial-scale=1'>";
			 String script_1 = "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>";
			 String script_2 = "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>";
			 
             String header = "<nav class='navbar navbar-inverse'>"
             + "<div class='container-fluid'>"
             + "<div class='navbar-header'>"
             + "<a class='navbar-brand' href='#'>GadgetBadget</a>"
             + "</div>"
             + "<ul class='nav navbar-nav'>"
             + "<li class='active'><a href='#'>Home</a></li>"
             + "<li class='dropdown'><a class='dropdown-toggle' data-toggle='dropdown' href='#'>Category<span class='caret'></span></a>"
             + "<ul class='dropdown-menu'>"
             + "<li><a href='http://localhost:9002/GadgetBadget_REST_JERSEY/myService/Projects'>UI Design</a></li>"
             + "<li><a href='http://localhost:9002/GadgetBadget_REST_JERSEY/myService/Carts'>Back End</a></li>"
             + "<li><a href='http://localhost:9002/GadgetBadget_REST_JERSEY/myService/Orders'>UI/UX</a></li>"
             + "</ul>"
             + "</li>"
             + ""
             + "<li><a href='http://localhost:9002/GadgetBadget_REST_JERSEY/myService/Projects'>Products</a></li>"
             + "<li><a href='http://localhost:9002/GadgetBadget_REST_JERSEY/myService/Carts'>Cart</a></li>"
             + "<li><a href='http://localhost:9002/GadgetBadget_REST_JERSEY/myService/Orders'>Order</a></li>"
             + "<li><a href='http://localhost:9002/GadgetBadget_REST_JERSEY/myService/Employees'>Employees</a></li>"
             + "<li><a href='#'>Contact Us</a></li>"
             + "<li><a href='#'>About Us</a></li>"
             + ""
             + "</ul>"
             + "<ul class='nav navbar-nav navbar-right'>"
             + "<li><a href='http://localhost:9002/GadgetBadget_REST_JERSEY/SignUp.jsp'><span class='glyphicon glyphicon-user'></span> Sign Up</a></li>"
             + "<li><a href='http://localhost:9002/GadgetBadget_REST_JERSEY/SignIn.jsp'><span class='glyphicon glyphicon-log-in'></span> Login</a></li>"
             + "</ul>"
             + "</div>"
             + "</nav>";
			 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {return "Error while connecting to the database for reading."; } 
				 
				 	 con.close(); 
				 	 // Complete the html table
				 	 output += "</table></div>"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while reading the project details...!"; 
				 System.err.println(e.getMessage()); 
			 } 
		 	 return output;  
		 } 

} 