package com.example.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcDemo {
	private final static String DB_URL="jdbc:mysql://localhost:3306/jdbctraining";
	private final static String USER_NAME="training";
	private final static String PASSWORD="training";
	
	
	public static void main (String args[])
	{
		try(Connection con=DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);) {
			System.out.println("connection successfull"+con);
			
			Statement stmt=con.createStatement();
			String query="INSERT INTO employee (name, age, designation, department, country) VALUES ('Raja', 30, 'Developer', 'CSE', 'Colombo')";
			int count=stmt.executeUpdate(query);
			System.out.println("Count of table"+count);
			
			String updatequery = "UPDATE employee SET designation = 'applicar' WHERE id = 4;";
		    int update=stmt.executeUpdate(updatequery);
		    System.out.println("Updtion in table"+count);
			
			String deletequery = "DELETE FROM employee WHERE id = 6";
			int deletion=stmt.executeUpdate(deletequery);
			System.out.println("Deletion in table"+count);
			
			System.out.println("Count of table"+count);
			
			
			query="select * from employee";
			ResultSet rs=stmt.executeQuery(query);
			
			
			
			
			System.out.println("Id\t Name \t Age \t Designation \t Department \t Country");
			
			while(rs.next())
			{
				System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getInt("age")+"\t"+rs.getString("designation")+"\t"+rs.getString("department")+"\t"+rs.getString("country")+"");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("connection failed"+e.getMessage());
		}
		
		
	}
}
