package com.customer_management;
import java.sql.*;

public class User {

	//Connection
	public Connection connect()
	{
		Connection con = null;

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/USER_MANAGEMENT",	"root", "");
			//For testing
			System.out.print("Successfully connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return con;
	}

	//Insert
	public String insertUser(String nic, String name, String dfb, String desc)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into users(`userID`,`userNic`,`userName`,`dateofbirth`,`userDesc`) values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, nic);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(dfb));
			preparedStmt.setString(5, desc); 

			//execute the statement
			preparedStmt.execute();
			con.close();

			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
			
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the users.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Read
	public String readUsers()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the HTML table to be displayed
			output = "<table  class='table table-dark table-striped'><tr><th>User NIC</th>"
					+"<th>User Name</th><th>Date of birth</th>"
					+ "<th>User Description</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from users";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next())
			{
				String userID = Integer.toString(rs.getInt("userID"));
				String userNic = rs.getString("userNic");
				String userName = rs.getString("userName");
				String dateofbirth = Double.toString(rs.getDouble("dateofbirth"));
				String userDesc = rs.getString("userDesc");

				// Add a row into the HTML table
				output += "<tr><td>" + userNic + "</td>";
				output += "<td>" + userName + "</td>";
				output += "<td>" + dateofbirth + "</td>"; 
				output += "<td>" + userDesc + "</td>";
				

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-userid='" + userID + "'></td>"
						+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='" + userID + "'>" + "</td></tr>";
			}

			con.close();

			// Complete the HTML table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the users.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Update
	public String updateUser(int id, String nic, String name, String dfb, String desc)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = "update users set `userNic`=?,`userName`=?,`dateofbirth`=?,`userDesc`=? where `userID`=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, nic);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(dfb));
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, id);

			//execute the statement
			preparedStmt.executeUpdate();
			con.close();

			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
			
			
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Delete
	public String removeUser(int id)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = "delete from users where `userID`=?;";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, id);

			//execute the statement
			preparedStmt.executeUpdate();
			con.close();

			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
}
