package model;

import java.sql.*;

public class Provider {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertData(String nic, String name, String income, String level, String provider) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = "insert into insurance" + "(`NIC`,`Name`,`Income`,`Provider`,`Level`,`Claim`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, nic);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(income));
			preparedStmt.setString(5, provider);
			preparedStmt.setString(6, level);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed

			output = "<table border=\"1\"><tr><th>NIC</th>" + "<th>Name</th><th>Income</th>" + "<th>Level</th>"+ "<th>Provider</th>"+ "<th>Claim</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from insurance";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String pID = Integer.toString(rs.getInt("pID"));
				String nic = rs.getString("nic");
				String name = rs.getString("name");
				String income = Double.toString(rs.getDouble("income"));
				String level = rs.getString("level");

				// Add into the html table
				output += "<tr><td>" + nic + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + income + "</td>";
				output += "<td>" + level + "</td>";

				// buttons
				// buttons
				output += "<td><input name=\"btnUpdate\" " + " type=\"button\" value=\"Update\"></td>"
						+ "<td><form method=\"post\" action=\"insForm.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" value=\"Remove\">" + "<input name=\"nic\" type=\"hidden\" " + " value=\""
						+ nic + "\">" + "</form></td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the data.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateData(String nic, String name, String income, String provider, String level) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE items SET nic=?,name=?,income=?,level=?,provider=? WHERE pID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, nic);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(income));
			preparedStmt.setInt(4, Integer.parseInt(pID));
			preparedStmt.setString(5, provider);
			preparedStmt.setString(6, level);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the data.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteData(String pID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {

				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from items where pID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the data.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
