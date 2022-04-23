package net.javaguides.usermanagement.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//This DAO class provides CRUD operations to the customer table in the database.

public class CustomerDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/electricbill";
	private String jdbcUsername = "root";
	private String jdbcPassword = "mathssin90";
	
	private static final String SELECT_CUSTOMER_BY_ID = "select id,name,address,unitsconsumed,billamount,date from users where id =?";
	private static final String SELECT_ALL_CUSTOMER = "select * from customer";
	private static final String DELETE_CUSTOMER_SQL = "delete from customer where id = ?;";
	private static final String UPDATE_CUSTOMER_SQL = "update customer set name = ?,address= ?, unitsconsumed =?,billamount =?,date =? where id = ?;";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	
	//update customer
	public boolean updateCustomer(Customer customer) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_SQL);) {
			statement.setString(1, customer.getName());
			statement.setString(2, customer.getAddress());
			statement.setString(3, customer.getUnitsconsumed());
			statement.setString(4, customer.getBillamount());
			statement.setString(5, customer.getDate());
			

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	//select customer by id
	public Customer selectCustomer(int id) {
		Customer customer = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				String unitsconsumed = rs.getString("unitsconsumed");
				String billamount = rs.getString("billamount");
				String date = rs.getString("date");
				
				customer = new Customer(id, name, address, unitsconsumed, billamount ,date );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	//select customer
	public List<Customer> selectAllCustomer() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Customer> customer = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String unitsconsumed = rs.getString("unitsconsumed");
				String billamount = rs.getString("billamount");
				String date = rs.getString("date");
				
				customer.add(new Customer(id, name, address, unitsconsumed,billamount,date));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	//delete customer
	public boolean deleteCustomer(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	

}