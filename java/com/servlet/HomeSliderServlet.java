package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dto.HomeSliderDto;
import com.service.HomeSliderService;

/**
 * Servlet implementation class HomeSliderServlet
 */
@WebServlet("/HomeSliderServlet")
public class HomeSliderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeSliderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	private ServletConfig config;
	String Name = "";
	String path = "";

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;

		path = config.getServletContext().getRealPath("/");
		System.out.println(path);
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		HomeSliderDto dto = new HomeSliderDto();
		HomeSliderService ser = new HomeSliderService();
		File savesFile = null;
		FileItem item = null;

		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = null;
			try {

				items = upload.parseRequest(request);

			} catch (Exception e) {
			}

			Iterator<FileItem> itr = items.iterator();

			while (itr.hasNext()) {
				item = itr.next();

				if (item.isFormField()) {

					String name = item.getFieldName();
					//Integer
					if (name.equals("Id"))dto.setId(Integer.parseInt(item.getString() == null ? "0": item.getString().trim()));

					
					//String
					if (name.equals("Title"))
						dto.setTitle(item.getString() == null||item.getString() == "" ? " " : item
								.getString().trim());
					
					if (name.equals("Link"))
						dto.setLink(item.getString() == null||item.getString() == ""  ? " "
								: item.getString().trim());

				} else {

					if (item.getSize() != 0) {
						if (item.getSize() < 30000000) {
							if (dto.getId() == 0) {

								int j = ser.maxId(request);
								savesFile = new File(path + "HomeSliderImage/" + j
										+ ".jpg");
								
								try {
									 
									
									item.write(savesFile);
									System.out.println(savesFile);
 
								} catch (Exception e2) {
								}

							} else {

								savesFile = new File(path + "HomeSliderImage/"
										+ dto.getId() + ".jpg");
								try {
									item.write(savesFile);

								} catch (Exception e2) {
								}
							}

						}

					}

				}
			}
		}
		
		 

		boolean b ;

		// condition for insert
		if (dto.getId() == 0) {

			b = ser.insertHomeSlider(dto, request, config);

			if (b ) {
				response.sendRedirect("add_home_slider.jsp?msg=Yes");
			} else {
				response.sendRedirect("add_home_slider.jsp?msg=No");
			}
		}
		// condition for updating
		else {
		 
			b =  ser.updateHomeSlider(dto,  request, config);

			if (b) {
				response.sendRedirect("manage_home_slider.jsp?msg=YesUp");
			} else {
				response.sendRedirect("manage_home_slider.jsp?msg=NoUp");
			}
		}
	
	
		
		
	}

}
