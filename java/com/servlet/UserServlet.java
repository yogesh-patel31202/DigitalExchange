package com.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.UserDto;
import com.service.UserService;

/**
 * Servlet implementation class userservlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * Default constructor.
	 */
	public UserServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		UserDto dto = new UserDto();
		UserService ser = new UserService();

		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));

		dto.setName(request.getParameter("Name") == null ? "" : request.getParameter("Name"));
	
		dto.setMobile(request.getParameter("Mobile") == null ? "" : request.getParameter("Mobile"));
	
		dto.setEmail(request.getParameter("Email") == null ? "" : request.getParameter("Email"));

		dto.setPassword(request.getParameter("Password") == null ? "" : request.getParameter("Password"));
		
		dto.setAddress(request.getParameter("Address") == null ? "" : request.getParameter("Address"));

		dto.setStatus(request.getParameter("Status") == null ? "" : request.getParameter("Status"));

		String flag = request.getParameter("Flag") == null ? "" : request.getParameter("Flag");

		System.out.println(flag);
		

		if (flag.equalsIgnoreCase("Insert")) {

			boolean query_status = ser.insertUserInfo(dto, request, config);

			if (query_status) {

				response.sendRedirect("registration.jsp?msg=Yes");
			} else {

				response.sendRedirect("registration.jsp?msg=No");

			}
		}
		
		if (flag.equalsIgnoreCase("Update")) {
			
			int query_return_id = ser.updateUserInfo(dto, request, config);

			if (query_return_id>0) {

				response.sendRedirect("manage_user.jsp?msg=Yes");
			} else {
				response.sendRedirect("manage_user.jsp?msg=No");
			}
			
		}

	}
}
