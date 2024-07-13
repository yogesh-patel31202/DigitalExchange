package com.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.ProductDto;
import com.dto.ProductImgDto;
import com.mysql.jdbc.Statement;

public class ProductService {
	
	
	public int maxId(HttpServletRequest request) {
		try {
			Connection connection = (Connection) new DataDb(request).connection;
			
			
			String dbname = connection.getCatalog();
			
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.tables "
							+ "WHERE table_name='product_img_tb' AND TABLE_SCHEMA=?");
			preparedStatement.setString(1, dbname);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return 0;
	}
	

	// ---- insert  -----//

	public int insertProductInfo(ProductDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection.prepareStatement("INSERT INTO products_tb ( seller_id_fk, category_id_fk, title, description, price, status) \r\n"
					+ "VALUES (?,?,?,?,?,?);",Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, dto.getSeller_id_fk());
			ps.setInt(2, dto.getCategory_id_fk());
			ps.setString(3, dto.getTitle());
			ps.setString(4, dto.getDescription());
			ps.setFloat(5, dto.getPrice());
			ps.setString(6, "Panding");

			System.out.println(ps);

			int i = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int product_id = rs.getInt(1);

			if (i != 0) {

				return product_id;

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;

	}

	// ---- End Inser Product ----- //
	
	
	// ---- insert Product Image -----//

	public boolean insertProductImg(int product_id, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection.prepareStatement("INSERT INTO product_img_tb (product_id_fk) VALUES (?);");

			ps.setInt(1, product_id);
			System.out.println(ps);

			int i = ps.executeUpdate();

			if (i != 0) {

				return true;

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;

	}

	// ---- End Product Image ----- //
	
	// ---- delete Product Image -----//
	
	public boolean deleteProductImg(String image_id, HttpServletRequest request, ServletConfig config) throws IOException {
		
		DataDb db = new DataDb(request);
		
		try {
			
			PreparedStatement ps = db.connection.prepareStatement("DELETE FROM product_img_tb WHERE id = ?;");
			
			ps.setString(1, image_id);
			System.out.println(ps);
			
			int i = ps.executeUpdate();
			
			if (i != 0) {
				
				return true;
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return false;
		
	}
	
	// ---- delete Product Image ----- //
	
	// ----- Select Product Image list -------//

	public ArrayList<ProductImgDto> getProductImgByProductId(int product_id,ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<ProductImgDto> list = new ArrayList<ProductImgDto>();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement("SELECT id FROM product_img_tb WHERE product_id_fk = ?;");
			preparedStatement.setInt(1, product_id);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ProductImgDto dto = new ProductImgDto();

				dto.setId(resultSet.getInt(1));
				list.add(dto);

			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}

	// ----- End Product Image list -------//
	
	// ----- Select Product Image list -------//
	
	public ProductImgDto getOneProductImgByProductId(int product_id,ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);
		ProductImgDto dto = new ProductImgDto();
		
		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement("SELECT id FROM product_img_tb WHERE product_id_fk = ?;");
			preparedStatement.setInt(1, product_id);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				
				dto.setId(resultSet.getInt(1));
				
			}
		} catch (Exception e) {
			
		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {
					
				}
		}
		return dto;
	}
	
	// ----- End Product Image list -------//
	
	// ----- Select Product Array list -------//

	public ArrayList<ProductDto> getProductInfoNotApprove(ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<ProductDto> list = new ArrayList<ProductDto>();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement(""
					+ "SELECT 	pt.id, seller_id_fk, category_id_fk, title, description, price, posting_date, pt.status,\r\n"
					+ "pt.admin_id_fk, approve_date , ct.name, \r\n"
					+ "ut.name , ut.mobile , ut.email, at.name , at.mobile \r\n"
					+ "FROM products_tb pt LEFT JOIN item_categories_tb ct ON pt.category_id_fk=ct.id \r\n"
					+ "LEFT JOIN users_tb ut ON pt.seller_id_fk = ut.id\r\n"
					+ "LEFT JOIN admin_tb AT ON pt.admin_id_fk = at.id WHERE pt.status = 'Panding';");
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ProductDto dto = new ProductDto();

				dto.setId(resultSet.getInt(1));
				dto.setSeller_id_fk(resultSet.getInt(2));
				dto.setCategory_id_fk(resultSet.getInt(3));
				dto.setTitle(resultSet.getString(4));
				dto.setDescription(resultSet.getString(5));
				dto.setPrice(resultSet.getFloat(6));
				dto.setPosting_date(resultSet.getString(7));
				dto.setStatus(resultSet.getString(8));
				dto.setAdmin_id_fk(resultSet.getInt(9));
				dto.setApprove_date(resultSet.getString(10));
				dto.setItem_category(resultSet.getString(11));
				dto.setSeller_name(resultSet.getString(12));
				dto.setSeller_mobile(resultSet.getString(13));
				dto.setSeller_email(resultSet.getString(14));
				dto.setAdmin_name(resultSet.getString(15));
				dto.setAdmin_mobile(resultSet.getString(16));
				
				list.add(dto);

			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}

	// ----- End Product Array list -------//
	
	
	// ----- Select Product Array list -------//

	public ArrayList<ProductDto> getProductInfoApprove(ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<ProductDto> list = new ArrayList<ProductDto>();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement(""
					+ "SELECT 	pt.id, seller_id_fk, category_id_fk, title, description, price, posting_date, pt.status,\r\n"
					+ "pt.admin_id_fk, approve_date , ct.name, \r\n"
					+ "ut.name , ut.mobile , ut.email, at.name , at.mobile \r\n"
					+ "FROM products_tb pt LEFT JOIN item_categories_tb ct ON pt.category_id_fk=ct.id \r\n"
					+ "LEFT JOIN users_tb ut ON pt.seller_id_fk = ut.id\r\n"
					+ "LEFT JOIN admin_tb AT ON pt.admin_id_fk = at.id WHERE pt.status = 'Approve';");
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ProductDto dto = new ProductDto();

				dto.setId(resultSet.getInt(1));
				dto.setSeller_id_fk(resultSet.getInt(2));
				dto.setCategory_id_fk(resultSet.getInt(3));
				dto.setTitle(resultSet.getString(4));
				dto.setDescription(resultSet.getString(5));
				dto.setPrice(resultSet.getFloat(6));
				dto.setPosting_date(resultSet.getString(7));
				dto.setStatus(resultSet.getString(8));
				dto.setAdmin_id_fk(resultSet.getInt(9));
				dto.setApprove_date(resultSet.getString(10));
				dto.setItem_category(resultSet.getString(11));
				dto.setSeller_name(resultSet.getString(12));
				dto.setSeller_mobile(resultSet.getString(13));
				dto.setSeller_email(resultSet.getString(14));
				dto.setAdmin_name(resultSet.getString(15));
				dto.setAdmin_mobile(resultSet.getString(16));
				
				list.add(dto);

			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}

	// ----- End Product Array list -------//
	

	// ----- Select Product Array list -------//

	public ArrayList<ProductDto> getProductInfo(ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<ProductDto> list = new ArrayList<ProductDto>();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement(""
					+ "SELECT 	pt.id, seller_id_fk, category_id_fk, title, description, price, posting_date, pt.status,\r\n"
					+ "pt.admin_id_fk, approve_date , ct.name, \r\n"
					+ "ut.name , ut.mobile , ut.email, at.name , at.mobile, pt.type \r\n"
					+ "FROM products_tb pt LEFT JOIN item_categories_tb ct ON pt.category_id_fk=ct.id \r\n"
					+ "LEFT JOIN users_tb ut ON pt.seller_id_fk = ut.id\r\n"
					+ "LEFT JOIN admin_tb AT ON pt.admin_id_fk = at.id");
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ProductDto dto = new ProductDto();

				dto.setId(resultSet.getInt(1));
				dto.setSeller_id_fk(resultSet.getInt(2));
				dto.setCategory_id_fk(resultSet.getInt(3));
				dto.setTitle(resultSet.getString(4));
				dto.setDescription(resultSet.getString(5));
				dto.setPrice(resultSet.getFloat(6));
				dto.setPosting_date(resultSet.getString(7));
				dto.setStatus(resultSet.getString(8));
				dto.setAdmin_id_fk(resultSet.getInt(9));
				dto.setApprove_date(resultSet.getString(10));
				dto.setItem_category(resultSet.getString(11));
				dto.setSeller_name(resultSet.getString(12));
				dto.setSeller_mobile(resultSet.getString(13));
				dto.setSeller_email(resultSet.getString(14));
				dto.setAdmin_name(resultSet.getString(15));
				dto.setAdmin_mobile(resultSet.getString(16));
				dto.setType(resultSet.getString(17));
				
				list.add(dto);

			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}

	// ----- End Product Array list -------//
	

	// ----- Select Product Array list -------//

	public ArrayList<ProductDto> getOfferProductInfo(ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<ProductDto> list = new ArrayList<ProductDto>();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement(""
					+ "SELECT 	pt.id, seller_id_fk, category_id_fk, title, description, price, posting_date, pt.status,\r\n"
					+ "pt.admin_id_fk, approve_date , ct.name, \r\n"
					+ "ut.name , ut.mobile , ut.email, at.name , at.mobile \r\n"
					+ "FROM products_tb pt LEFT JOIN item_categories_tb ct ON pt.category_id_fk=ct.id \r\n"
					+ "LEFT JOIN users_tb ut ON pt.seller_id_fk = ut.id\r\n"
					+ "LEFT JOIN admin_tb AT ON pt.admin_id_fk = at.id WHERE pt.type = 'Offer';");
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ProductDto dto = new ProductDto();

				dto.setId(resultSet.getInt(1));
				dto.setSeller_id_fk(resultSet.getInt(2));
				dto.setCategory_id_fk(resultSet.getInt(3));
				dto.setTitle(resultSet.getString(4));
				dto.setDescription(resultSet.getString(5));
				dto.setPrice(resultSet.getFloat(6));
				dto.setPosting_date(resultSet.getString(7));
				dto.setStatus(resultSet.getString(8));
				dto.setAdmin_id_fk(resultSet.getInt(9));
				dto.setApprove_date(resultSet.getString(10));
				dto.setItem_category(resultSet.getString(11));
				dto.setSeller_name(resultSet.getString(12));
				dto.setSeller_mobile(resultSet.getString(13));
				dto.setSeller_email(resultSet.getString(14));
				dto.setAdmin_name(resultSet.getString(15));
				dto.setAdmin_mobile(resultSet.getString(16));
				
				list.add(dto);

			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}

	// ----- End Product Array list -------//
	
	// ----- Select Product Array list -------//
	
	public ArrayList<ProductDto> getAvailProductInfo(ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);
		
		ArrayList<ProductDto> list = new ArrayList<ProductDto>();
		
		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement(""
					+ "SELECT 	pt.id, seller_id_fk, category_id_fk, title, description, price, posting_date, pt.status,\r\n"
					+ "pt.admin_id_fk, approve_date , ct.name, \r\n"
					+ "ut.name , ut.mobile , ut.email, at.name , at.mobile, pt.type \r\n"
					+ "FROM products_tb pt LEFT JOIN item_categories_tb ct ON pt.category_id_fk=ct.id \r\n"
					+ "LEFT JOIN users_tb ut ON pt.seller_id_fk = ut.id\r\n"
					+ "LEFT JOIN admin_tb AT ON pt.admin_id_fk = at.id WHERE pt.status='Approve';");
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				ProductDto dto = new ProductDto();
				
				dto.setId(resultSet.getInt(1));
				dto.setSeller_id_fk(resultSet.getInt(2));
				dto.setCategory_id_fk(resultSet.getInt(3));
				dto.setTitle(resultSet.getString(4));
				dto.setDescription(resultSet.getString(5));
				dto.setPrice(resultSet.getFloat(6));
				dto.setPosting_date(resultSet.getString(7));
				dto.setStatus(resultSet.getString(8));
				dto.setAdmin_id_fk(resultSet.getInt(9));
				dto.setApprove_date(resultSet.getString(10));
				dto.setItem_category(resultSet.getString(11));
				dto.setSeller_name(resultSet.getString(12));
				dto.setSeller_mobile(resultSet.getString(13));
				dto.setSeller_email(resultSet.getString(14));
				dto.setAdmin_name(resultSet.getString(15));
				dto.setAdmin_mobile(resultSet.getString(16));
				dto.setType(resultSet.getString(17));
				
				list.add(dto);
				
			}
		} catch (Exception e) {
			
		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {
					
				}
		}
		return list;
	}
	
	// ----- End Product Array list -------//

	// ----- Select Product Array list -------//

	public ArrayList<ProductDto> getSpecialProductInfo(ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<ProductDto> list = new ArrayList<ProductDto>();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement(""
					+ "SELECT 	pt.id, seller_id_fk, category_id_fk, title, description, price, posting_date, pt.status,\r\n"
					+ "pt.admin_id_fk, approve_date , ct.name, \r\n"
					+ "ut.name , ut.mobile , ut.email, at.name , at.mobile \r\n"
					+ "FROM products_tb pt LEFT JOIN item_categories_tb ct ON pt.category_id_fk=ct.id \r\n"
					+ "LEFT JOIN users_tb ut ON pt.seller_id_fk = ut.id\r\n"
					+ "LEFT JOIN admin_tb AT ON pt.admin_id_fk = at.id WHERE pt.type = 'Special';");
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ProductDto dto = new ProductDto();

				dto.setId(resultSet.getInt(1));
				dto.setSeller_id_fk(resultSet.getInt(2));
				dto.setCategory_id_fk(resultSet.getInt(3));
				dto.setTitle(resultSet.getString(4));
				dto.setDescription(resultSet.getString(5));
				dto.setPrice(resultSet.getFloat(6));
				dto.setPosting_date(resultSet.getString(7));
				dto.setStatus(resultSet.getString(8));
				dto.setAdmin_id_fk(resultSet.getInt(9));
				dto.setApprove_date(resultSet.getString(10));
				dto.setItem_category(resultSet.getString(11));
				dto.setSeller_name(resultSet.getString(12));
				dto.setSeller_mobile(resultSet.getString(13));
				dto.setSeller_email(resultSet.getString(14));
				dto.setAdmin_name(resultSet.getString(15));
				dto.setAdmin_mobile(resultSet.getString(16));
				
				list.add(dto);

			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}

	// ----- End Product Array list -------//
	
	

	// ----- Select Product Array list -------//

	public ArrayList<ProductDto> getLatestProductInfo(ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<ProductDto> list = new ArrayList<ProductDto>();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement(""
					+ "SELECT 	pt.id, seller_id_fk, category_id_fk, title, description, price, posting_date, pt.status,\r\n"
					+ "pt.admin_id_fk, approve_date , ct.name, \r\n"
					+ "ut.name , ut.mobile , ut.email, at.name , at.mobile \r\n"
					+ "FROM products_tb pt LEFT JOIN item_categories_tb ct ON pt.category_id_fk=ct.id \r\n"
					+ "LEFT JOIN users_tb ut ON pt.seller_id_fk = ut.id\r\n"
					+ "LEFT JOIN admin_tb AT ON pt.admin_id_fk = at.id WHERE pt.type = 'Latest';");
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ProductDto dto = new ProductDto();

				dto.setId(resultSet.getInt(1));
				dto.setSeller_id_fk(resultSet.getInt(2));
				dto.setCategory_id_fk(resultSet.getInt(3));
				dto.setTitle(resultSet.getString(4));
				dto.setDescription(resultSet.getString(5));
				dto.setPrice(resultSet.getFloat(6));
				dto.setPosting_date(resultSet.getString(7));
				dto.setStatus(resultSet.getString(8));
				dto.setAdmin_id_fk(resultSet.getInt(9));
				dto.setApprove_date(resultSet.getString(10));
				dto.setItem_category(resultSet.getString(11));
				dto.setSeller_name(resultSet.getString(12));
				dto.setSeller_mobile(resultSet.getString(13));
				dto.setSeller_email(resultSet.getString(14));
				dto.setAdmin_name(resultSet.getString(15));
				dto.setAdmin_mobile(resultSet.getString(16));
				
				list.add(dto);

			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}

	// ----- End Product Array list -------//
	
	

	// ----- Select Product Array list -------//

	public ArrayList<ProductDto> getBlockedProductInfo(ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<ProductDto> list = new ArrayList<ProductDto>();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement(""
					+ "SELECT 	pt.id, seller_id_fk, category_id_fk, title, description, price, posting_date, pt.status,\r\n"
					+ "pt.admin_id_fk, approve_date , ct.name, \r\n"
					+ "ut.name , ut.mobile , ut.email, at.name , at.mobile \r\n"
					+ "FROM products_tb pt LEFT JOIN item_categories_tb ct ON pt.category_id_fk=ct.id \r\n"
					+ "LEFT JOIN users_tb ut ON pt.seller_id_fk = ut.id\r\n"
					+ "LEFT JOIN admin_tb AT ON pt.admin_id_fk = at.id WHERE pt.type = 'Block';");
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ProductDto dto = new ProductDto();

				dto.setId(resultSet.getInt(1));
				dto.setSeller_id_fk(resultSet.getInt(2));
				dto.setCategory_id_fk(resultSet.getInt(3));
				dto.setTitle(resultSet.getString(4));
				dto.setDescription(resultSet.getString(5));
				dto.setPrice(resultSet.getFloat(6));
				dto.setPosting_date(resultSet.getString(7));
				dto.setStatus(resultSet.getString(8));
				dto.setAdmin_id_fk(resultSet.getInt(9));
				dto.setApprove_date(resultSet.getString(10));
				dto.setItem_category(resultSet.getString(11));
				dto.setSeller_name(resultSet.getString(12));
				dto.setSeller_mobile(resultSet.getString(13));
				dto.setSeller_email(resultSet.getString(14));
				dto.setAdmin_name(resultSet.getString(15));
				dto.setAdmin_mobile(resultSet.getString(16));
				
				list.add(dto);

			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}

	// ----- End Product Array list -------//
	
	
	
	// ----- Select Product data -------//

	public ProductDto getProductInfoByProductId(int product_id, ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ProductDto dto = new ProductDto();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement("SELECT 	pt.id, seller_id_fk, category_id_fk, title, description, price, posting_date, pt.status,\r\n"
					+ "pt.admin_id_fk, approve_date , ct.name, \r\n"
					+ "ut.name , ut.mobile , ut.email, at.name , at.mobile, pt.type \r\n"
					+ "FROM products_tb pt LEFT JOIN item_categories_tb ct ON pt.category_id_fk=ct.id \r\n"
					+ "LEFT JOIN users_tb ut ON pt.seller_id_fk = ut.id\r\n"
					+ "LEFT JOIN admin_tb AT ON pt.admin_id_fk = at.id WHERE pt.id = ?;");
			preparedStatement.setInt(1, product_id);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
				dto.setId(resultSet.getInt(1));
				dto.setSeller_id_fk(resultSet.getInt(2));
				dto.setCategory_id_fk(resultSet.getInt(3));
				dto.setTitle(resultSet.getString(4));
				dto.setDescription(resultSet.getString(5));
				dto.setPrice(resultSet.getFloat(6));
				dto.setPosting_date(resultSet.getString(7));
				dto.setStatus(resultSet.getString(8));
				dto.setAdmin_id_fk(resultSet.getInt(9));
				dto.setApprove_date(resultSet.getString(10));
				dto.setItem_category(resultSet.getString(11));
				dto.setSeller_name(resultSet.getString(12));
				dto.setSeller_mobile(resultSet.getString(13));
				dto.setSeller_email(resultSet.getString(14));
				dto.setAdmin_name(resultSet.getString(15));
				dto.setAdmin_mobile(resultSet.getString(16));
				dto.setType(resultSet.getString(17));

		}catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return dto;
	}

	// ----- End Product data -------//

	// ---- Approve  -----//

	public int approveProductInfo(ProductDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection.prepareStatement("UPDATE products_tb SET category_id_fk = ? , title = ? , description = ? , price = ? ,\r\n"
					+ "status = ? , admin_id_fk = ? , approve_date = ?, type = ? \r\n"
					+ "WHERE id = ? ;");

			ps.setInt(1, dto.getCategory_id_fk());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getDescription());
			ps.setFloat(4, dto.getPrice());
			ps.setString(5, dto.getStatus());
			ps.setInt(6, dto.getAdmin_id_fk());
			ps.setString(7, dto.getApprove_date());
			ps.setString(8, dto.getType());
			ps.setInt(9, dto.getId());

			System.out.println(ps);

			int i = ps.executeUpdate();

			if (i != 0) {

				return dto.getId();

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;

	}

	// ---- End Approve Product ----- //

}
