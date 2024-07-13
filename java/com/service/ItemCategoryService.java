package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.ItemCategoryDto;

public class ItemCategoryService {
	



	// Insert Method for Category Item
				public boolean insertCategory(ItemCategoryDto dto, HttpServletRequest request, ServletConfig config) throws IOException {
					
					DataDb db = new DataDb(request);
					
					try {
						
						//Insert Query of PathologyItem
						
						PreparedStatement ps = db.connection.prepareStatement("INSERT INTO item_categories_tb (admin_id_fk, name)VALUES(?,?);");

						ps.setInt(1, dto.getAdmin_id_fk());
						ps.setString(2, dto.getName());
						

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

				//Method for Show data on Manage page
					public ArrayList<ItemCategoryDto> getCategoryInfo(ServletConfig config, HttpServletRequest request) throws IOException{
						
						DataDb db = new DataDb(request);
						PreparedStatement preparedStatement=null;
						
						ArrayList<ItemCategoryDto> list = new ArrayList<ItemCategoryDto>();
						
						try {
							
							String sql = "SELECT id, admin_id_fk, name, Status  FROM item_categories_tb;";
							// Select query for showing data on manage table
							preparedStatement = db.connection.prepareStatement(sql);
							System.out.println(preparedStatement);
							ResultSet resultSet = preparedStatement.executeQuery();
							
							while(resultSet.next()) {
								
								ItemCategoryDto dto =new ItemCategoryDto();
								
								dto.setId(resultSet.getInt(1));
								dto.setAdmin_id_fk(resultSet.getInt(2));
								dto.setName(resultSet.getString(3));
								dto.setStatus(resultSet.getString(4));
								
								list.add(dto);
							}
						}
						catch (Exception e) {
							
						}
						finally {
							if (db.connection != null)
								try {
									db.connection.close();
								} catch (Exception e) {

								}
						}
						return list;
					}
					//Show data on edit page according to id
						public ItemCategoryDto getCategoryInfoById(int id, ServletConfig config, HttpServletRequest request) throws IOException{
							
							DataDb db = new DataDb(request);
							PreparedStatement preparedStatement=null;
							
							ItemCategoryDto dto = new ItemCategoryDto();
							
							try {
								
								String sql = "SELECT id, admin_id_fk, name, Status  FROM item_categories_tb WHERE  id=?;";
								// Select query for showing data on Edit page
								preparedStatement = db.connection.prepareStatement(sql);
								
								preparedStatement.setInt(1, id);
								
								ResultSet resultSet = preparedStatement.executeQuery();
								
								while(resultSet.next()) {
									dto.setId(resultSet.getInt(1));
									dto.setAdmin_id_fk(resultSet.getInt(2));
									dto.setName(resultSet.getString(3));
									dto.setStatus(resultSet.getString(4));

								}
							}
							catch (Exception e) {
								
							}
							finally {
								if (db.connection != null)
									try {
										db.connection.close();
									} catch (Exception e) {

									}
							}
							return dto;
						}
						
						// Method For Updating data on edit page
						public boolean updateCategory(ItemCategoryDto dto, HttpServletRequest request,
								ServletConfig config) throws IOException {
							
							DataDb db = new DataDb(request);
							PreparedStatement ps=null;
							
							try {
								
								String sql = "UPDATE item_categories_tb SET  admin_id_fk=?, name=?, Status=?  WHERE id=?;";
								//Update Query for update data 
								ps = db.connection.prepareStatement(sql);

								ps.setInt(1, dto.getAdmin_id_fk());
								ps.setString(2, dto.getName());
								
								ps.setString(3, dto.getStatus());
								ps.setInt(4, dto.getId());

								System.out.println(ps);
								
								int i = ps.executeUpdate();
								
								if (i != 0) {
									return true;
								}
							} 
							catch (Exception e) {

								e.printStackTrace();
							}
							return false;
						}
						
						
						public ArrayList<ItemCategoryDto> getActiveCategoryInfo(ServletConfig config, HttpServletRequest request) throws IOException{
							
							DataDb db = new DataDb(request);
							PreparedStatement preparedStatement=null;
							
							ArrayList<ItemCategoryDto> list = new ArrayList<ItemCategoryDto>();
							
							try {
								
								String sql = "SELECT id, admin_id_fk,Name, Status  FROM item_categories_tb  WHERE Status='active';";
								preparedStatement = db.connection.prepareStatement(sql);
								
								ResultSet resultSet = preparedStatement.executeQuery();
								
								while(resultSet.next()) {
									
									ItemCategoryDto dto =new ItemCategoryDto();
									
									dto.setId(resultSet.getInt(1));
									dto.setAdmin_id_fk(resultSet.getInt(2));
									dto.setName(resultSet.getString(3));
									dto.setStatus(resultSet.getString(4));		
									
									list.add(dto);
								}
							}
							catch (Exception e) {
								
							}
							finally {
								if (db.connection != null)
									try {
										db.connection.close();
									} catch (Exception e) {

									}
							}
							return list;
						}
}
