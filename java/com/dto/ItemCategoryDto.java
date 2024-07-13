package com.dto;

public class ItemCategoryDto {
	
	int id, admin_id_fk;
	String name,status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdmin_id_fk() {
		return admin_id_fk;
	}
	public void setAdmin_id_fk(int admin_id_fk) {
		this.admin_id_fk = admin_id_fk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
