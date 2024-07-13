package com.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.HomeSliderDto;

public class HomeSliderService {

	public int maxId(HttpServletRequest request) {
		try {
			Connection connection = (Connection) new DataDb(request).connection;

			String dbname = connection.getCatalog();

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.tables "
							+ "WHERE table_name='home_slider_tb' AND TABLE_SCHEMA=?");
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
	
	// Insert Method for HomeSlider Item
		public boolean insertHomeSlider(HomeSliderDto dto, HttpServletRequest request, ServletConfig config)
				throws IOException {

			DataDb db = new DataDb(request);

			try {
				

				// Insert Query of Spend
				
				PreparedStatement ps = db.connection
						.prepareStatement("INSERT INTO home_slider_tb (title,link)VALUES(?,?);");

				ps.setString(1, dto.getTitle());
				ps.setString(2, dto.getLink());

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

		// Method for Show data on Manage page
		public ArrayList<HomeSliderDto> getHomeSliderInfo(ServletConfig config, HttpServletRequest request)
				throws IOException {

			DataDb db = new DataDb(request);
			PreparedStatement preparedStatement = null;

			ArrayList<HomeSliderDto> list = new ArrayList<HomeSliderDto>();

			try {

				// Select query for showing data on manage table
				preparedStatement = db.connection.prepareStatement("SELECT 	id, title, link FROM home_slider_tb;");

				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					HomeSliderDto dto = new HomeSliderDto();

					dto.setId(resultSet.getInt(1));
					dto.setTitle(resultSet.getString(2));
					dto.setLink(resultSet.getString(3));

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

		// Show data on edit page according to id
		public HomeSliderDto getHomeSliderInfoById(int id, ServletConfig config, HttpServletRequest request)
				throws IOException {

			DataDb db = new DataDb(request);
			PreparedStatement preparedStatement = null;

			HomeSliderDto dto = new HomeSliderDto();

			try {

				// Select query for showing data on Edit page
				preparedStatement = db.connection.prepareStatement("SELECT 	id, title, link FROM home_slider_tb WHERE id=?;");

				preparedStatement.setInt(1, id);

				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					
					dto.setId(resultSet.getInt(1));
					dto.setTitle(resultSet.getString(2));
					dto.setLink(resultSet.getString(3));

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

		// Method For Updating data on edit page
		public boolean updateHomeSlider(HomeSliderDto dto, HttpServletRequest request, ServletConfig config)
				throws IOException {

			DataDb db = new DataDb(request);
			PreparedStatement ps = null;

			try {
				
				// Update Query for update data
				ps = db.connection
						.prepareStatement("UPDATE home_slider_tb SET title = ?,link = ? WHERE id = ? ;");

				ps.setString(1, dto.getTitle());

				ps.setString(2, dto.getLink());
				ps.setInt(3, dto.getId());

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

		public ArrayList<HomeSliderDto> getActiveHomeSliderInfo(ServletConfig config, HttpServletRequest request)
				throws IOException {

			DataDb db = new DataDb(request);
			PreparedStatement preparedStatement = null;

			ArrayList<HomeSliderDto> list = new ArrayList<HomeSliderDto>();

			try {

				preparedStatement = db.connection.prepareStatement("SELECT id, title, link  WHERE  status='Active';");

				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					HomeSliderDto dto = new HomeSliderDto();

					dto.setId(resultSet.getInt(1));
					dto.setLink(resultSet.getString(2));

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
	
}
