package net.javaguides.usermanagement.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
    	this.customerDAO = new CustomerDAO();
        
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		switch (action) {
		case "/new":
			showNewForm(request,response);
			
					
			break;
		case "/delete":
			try {
				deleteCustomer(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			break;
		case "/edit":
			try {
				showEditForm(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			break;
		case "/update":
			try {
				updateCustomer(request , response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		default:
			//handle list
			try {
				listCustomer(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
	}

	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		customerDAO.deleteCustomer(id);
		response.sendRedirect("list");

	}
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Customer existingcustomer = customerDAO.selectCustomer(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer-list.jsp");
		request.setAttribute("customer", existingcustomer);
		dispatcher.forward(request, response);

	}
	
	private void updateCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String unitsconsumed = request.getParameter("unitsconsumed");
		String billamount = request.getParameter("billamount");
		String date = request.getParameter("date");
		

		Customer customer = new Customer(id, name, address, unitsconsumed,billamount,date);
		customerDAO.updateCustomer(customer);
		response.sendRedirect("list");
	}
	
	private void listCustomer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Customer> listCustomer = customerDAO.selectAllCustomer();
		request.setAttribute("listCustomer", listCustomer);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer-list.jsp");
		dispatcher.forward(request, response);
	}


}