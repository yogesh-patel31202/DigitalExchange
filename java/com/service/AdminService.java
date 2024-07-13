package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.AdminDto;

public class AdminService {

	// ---- insert  -----//

	public boolean insertAdminInfo(AdminDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection.prepareStatement("INSERT INTO admin_tb ( name, mobile, password, address, email, status ) VALUES( ?, ?, ?, ?, ?, ?);");

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getMobile());
			ps.setString(3, dto.getPassword());
			ps.setString(4, dto.getAddress());
			ps.setString(5, dto.getEmail());
			ps.setString(6, "Active");

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

	// ---- End Inser Admin ----- //

	// ----- Select Admin Array list -------//

	public ArrayList<AdminDto> getAdminInfo(ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<AdminDto> list = new ArrayList<AdminDto>();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement("SELECT 	id, name, mobile, password, address, status, email FROM admin_tb ;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				AdminDto dto = new AdminDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setMobile(resultSet.getString(3));
				dto.setPassword(resultSet.getString(4));
				dto.setAddress(resultSet.getString(5));
				dto.setStatus(resultSet.getString(6));
				dto.setEmail(resultSet.getString(7));
				
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

	// ----- End Admin Array list -------//
	
	
	// ----- Select Admin data -------//

	public AdminDto getAdminInfoById(int user_id, ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		AdminDto dto = new AdminDto();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement("SELECT id, name, mobile, password, address, status, email FROM admin_tb WHERE id = ? ;");
			preparedStatement.setInt(1, user_id);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {


				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setMobile(resultSet.getString(3));
				dto.setPassword(resultSet.getString(4));
				dto.setAddress(resultSet.getString(5));
				dto.setStatus(resultSet.getString(6));
				dto.setEmail(resultSet.getString(7));

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

	// ----- End Admin data -------//

	// ----- Update Admin -------//

	public int updateAdminInfo(AdminDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps = db.connection.prepareStatement("UPDATE admin_tb SET name = ? , mobile = ? , password = ? , address = ? , status = ?, email = ? WHERE id = ? ;");

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getMobile());
			ps.setString(3, dto.getPassword());
			ps.setString(4, dto.getAddress());
			ps.setString(5, dto.getStatus());
			ps.setString(6, dto.getEmail());
			ps.setInt(7, dto.getId());
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

	// ----- End Update Admin -------//

	// ------ Check login method ------//

	public int checkAdminLogin(AdminDto dto, ServletConfig config, HttpServletRequest request) throws IOException {
		try {

			DataDb db = new DataDb(request);
			String sql = "SELECT id FROM admin_tb WHERE mobile= ? && password= ?";

			PreparedStatement preparedStatement = db.connection.prepareStatement(sql);

			preparedStatement.setString(1, dto.getMobile());
			preparedStatement.setString(2, dto.getPassword());
			System.out.print(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}

		} catch (Exception e) {
			// LogFileService.catchLogFile(e, config);
			return 0;
		}
	}

	// ------ End Check login method ------//
	
	

}
