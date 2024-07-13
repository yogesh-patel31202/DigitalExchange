package com.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

import com.dto.ProductDto;
import com.service.ProductService;


/**
 * Servlet implementation class SpendServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		ProductDto dto = new ProductDto();
		ProductService ser = new ProductService();
		
		File savesFile = null;
		FileItem item = null;
		String flag = "";
		String remove_images_id = "";
		int product_id = 0;
		int query_return_id = 0;

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
					if (name.equals("Id"))dto.setId(Integer.parseInt(item.getString() == null||item.getString() == "" ? "0": item.getString().trim()));

					if (name.equals("Seller_id_fk"))dto.setSeller_id_fk(Integer.parseInt(item.getString() == null||item.getString() == "" ? "0" : item.getString().trim()));
					
					if (name.equals("Category_id_fk"))dto.setCategory_id_fk(Integer.parseInt(item.getString() == null||item.getString() == "" ? "0" : item.getString().trim()));
					
					if (name.equals("Admin_id_fk"))dto.setAdmin_id_fk(Integer.parseInt(item.getString() == null||item.getString() == "" ? "0" : item.getString().trim()));
					
					//float
					if (name.equals("Price"))dto.setPrice(Float.parseFloat(item.getString() == null||item.getString() == "" ? "0" : item.getString().trim()));
					
					
					//String
					if (name.equals("Title"))
						dto.setTitle(item.getString() == null ? "" : item
								.getString().trim());
					
					if (name.equals("Description"))
						dto.setDescription(item.getString() == null ? ""
								: item.getString().trim());
					
					if (name.equals("Status"))
						dto.setStatus(item.getString() == null ? "" : item
								.getString().trim());
					
					if (name.equals("Type"))
						dto.setType(item.getString() == null ? "" : item
								.getString().trim());
					
					if (name.equals("Approve_date"))
						dto.setApprove_date(item.getString() == null ? ""
								: item.getString().trim());
					
					if (name.equals("Remove_images_id"))
						remove_images_id = item.getString() == null||item.getString() == "" ? ""
								: item.getString().trim();

					if (name.equals("Flag"))
						flag = item.getString() == null ? ""
								: item.getString().trim();
					//System.out.println(flag);
					
				} 
			}
			
				if (flag.equalsIgnoreCase("Insert")) {

					product_id = ser.insertProductInfo(dto, request, config);

				}
				
				if (flag.equalsIgnoreCase("Approve")) {
					
					query_return_id = ser.approveProductInfo(dto, request, config);
				}
				
				if(!remove_images_id.equalsIgnoreCase("")) {
				String[] remove_images_arr = remove_images_id.split(",");
				System.out.println("length="+remove_images_arr.length);
				
				for (String image_id : remove_images_arr) {

					boolean img_status = ser.deleteProductImg(image_id, request, config);
					System.out.println("Image Delete or Not = "+ img_status);
					
					try {
						Files.delete(Paths.get(path + "ProductImage/" + image_id + ".jpg"));
						
						System.out.println(path + "ProductImage/" + image_id + ".jpg");

					} catch (Exception e2) {
						System.err.println("Error deleting file: " + e2.getMessage());
					}
		            
		        }
		}
				
				Iterator<FileItem> itr_file = items.iterator();
				while (itr_file.hasNext()) {
					 item = itr_file.next();
					
					if (!item.isFormField()&& item.getSize() < 300000000 && item.getSize()> 0) {
						
								int j = ser.maxId(request);

									savesFile = new File(path + "ProductImage/" + j + ".jpg");
									try {
										item.write(savesFile);
										if(dto.getId()==0) {
											boolean img_status = ser.insertProductImg(product_id, request, config);
											System.out.println(img_status);
										}else {
											boolean img_status = ser.insertProductImg(dto.getId(), request, config);
											System.out.println(img_status);
										}
										System.out.println(savesFile);

									} catch (Exception e2) {
									
									}

						}

				}
			
		}
		
		if (flag.equalsIgnoreCase("Insert")) {
		if (product_id>0) {

			response.sendRedirect("add_product.jsp?msg=Yes");
		} else {

			response.sendRedirect("add_product.jsp?msg=No");
			}
		}
		
		if (flag.equalsIgnoreCase("Approve")) {
		if (query_return_id>0) {

			response.sendRedirect("index_admin.jsp?msg=Yes");
		} else {
			response.sendRedirect("index_admin.jsp?msg=No");
			}
		}

	}

}
