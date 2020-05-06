package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {
	
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hcs", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String code, String name, String email, String desc, String district, String tel) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into hospital(hospitalID,hospitalCode,hospitalName,hospitalEmail,hospitalDesc,hospitalDistrict,hospitalTel)"
					+ " values ( ?,  ?,  ?,  ?,  ?,  ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, desc);
			preparedStmt.setString(6, district);
			preparedStmt.setString(7, tel);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newHospital = readHospital(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newHospital + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the hospital details.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	public String readHospital()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border='1'><tr><th>Hospital Code</th><th>Hospital Name</th><th>Hospital Email</th><th>Hospital Description</th><th>Hospital District</th><th>Hospital Phone No</th><th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from hospital";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));
				String hospitalCode = rs.getString("hospitalCode");
				String hospitalName = rs.getString("hospitalName");
				String hospitalEmail = rs.getString("hospitalEmail");
				String hospitalDesc = rs.getString("hospitalDesc");
				String hospitalDistrict = rs.getString("hospitalDistrict");
				String hospitalTel = rs.getString("hospitalTel");
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidItemIDUpdate\' name=\'hidItemIDUpdate\' type=\'hidden\' value=\'" + hospitalID + "'>" 
							+ hospitalCode + "</td>";      
				output += "<td>" + hospitalName + "</td>";     
				output += "<td>" + hospitalEmail + "</td>";
				output += "<td>" + hospitalDesc + "</td>";
				output += "<td>" + hospitalDistrict + "</td>";
				output += "<td>" + hospitalTel + "</td>"; 
	 
				// buttons     
//				output += "<td><input name=\'btnUpdate\' type=\'button\' value=\'Update\' class=\' btnUpdate btn btn-secondary\'></td>"
//						//+ "<td><form method=\"post\" action=\"items.jsp\">      "
//						+ "<input name=\'btnRemove\' type=\'submit\' value=\'Remove\' class=\'btn btn-danger\'> "
//						+ "<input name=\"hidItemIDDelete\" type=\"hidden\" value=\"" + itemID + "\">" + "</form></td></tr>"; 
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-docid='" + hospitalID + "'>" + "</td></tr>"; 
			} 
	 
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the items.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	
	public String updateHospital(String ID, String code, String name, String email, String desc, String district,String tel) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE hospital SET hospitalCode=?,hospitalName=?,hospitalEmail=?,hospitalDesc=?,hospitalDistrict=?,hospitalTel=? WHERE hospitalID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, desc);
			preparedStmt.setString(5, district);
			preparedStmt.setString(6, tel);
			preparedStmt.setInt(7, Integer.parseInt(ID));	 
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospital = readHospital();    
			output = "{\"status\":\"success\", \"data\": \"" +        
					newHospital + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteHospital(String hospitalID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from hospital where hospitalID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(hospitalID));
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospital = readHospital();    
			output = "{\"status\":\"success\", \"data\": \"" +       
					newHospital + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the item.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	 
}
