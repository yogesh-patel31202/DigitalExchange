package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.ItemCategoryDto;
import com.service.ItemCategoryService;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/ItemCategoryServlet")
public class ItemCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		

		ItemCategoryDto dto = new ItemCategoryDto();
		ItemCategoryService ser = new ItemCategoryService();
		
		
		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		dto.setName(request.getParameter("Name") == null ? "" : request.getParameter("Name"));
		
		
		dto.setStatus(request.getParameter("Status") == null ? "active" : request.getParameter("Status"));
		dto.setAdmin_id_fk(Integer.parseInt(request.getParameter("Admin_id_fk") == null ? "0" : request.getParameter("Admin_id_fk")));
		
		boolean b = false;
		
		// condition for insert
		if(dto.getId()==0) {
			
			b = ser.insertCategory(dto, request, config);
			
			if(b) {
				response.sendRedirect("add_item_category.jsp?msg=Yes");
			}
			else {
				response.sendRedirect("add_item_category.jsp?msg=No");
			}
		}
		//condition for updating
		else {
			
			b = ser.updateCategory(dto, request, config);
			
			if(b) {
				response.sendRedirect("manage_item_category.jsp?msg=YesUp");
			}
			else {
				response.sendRedirect("manage_item_category.jsp?msg=NoUp");
			}
		}
		
	}

}
