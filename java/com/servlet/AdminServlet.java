package com.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.AdminDto;
import com.service.AdminService;

/**
 * Servlet implementation class userservlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * Default constructor.
	 */
	public AdminServlet() {
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

		AdminDto dto = new AdminDto();
		AdminService ser = new AdminService();

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

			boolean query_status = ser.insertAdminInfo(dto, request, config);

			if (query_status) {

				response.sendRedirect("add_admin.jsp?msg=Yes");
			} else {

				response.sendRedirect("add_admin.jsp?msg=No");

			}
		}
		
		if (flag.equalsIgnoreCase("Update")) {
			
			int query_return_id = ser.updateAdminInfo(dto, request, config);

			if (query_return_id>0) {

				response.sendRedirect("manage_admin.jsp?msg=Yes");
			} else {
				response.sendRedirect("manage_admin.jsp?msg=No");
			}
			
		}

	}
}
