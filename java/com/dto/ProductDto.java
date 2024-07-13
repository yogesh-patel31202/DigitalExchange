package com.dto;

public class ProductDto {

	private int id, seller_id_fk, category_id_fk,admin_id_fk;
	private String title, description, posting_date, status, approve_date, item_category , seller_name , seller_mobile, seller_email,
	admin_name, admin_mobile,type;
	private float price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSeller_id_fk() {
		return seller_id_fk;
	}
	public void setSeller_id_fk(int seller_id_fk) {
		this.seller_id_fk = seller_id_fk;
	}
	public int getCategory_id_fk() {
		return category_id_fk;
	}
	public void setCategory_id_fk(int category_id_fk) {
		this.category_id_fk = category_id_fk;
	}
	public int getAdmin_id_fk() {
		return admin_id_fk;
	}
	public void setAdmin_id_fk(int admin_id_fk) {
		this.admin_id_fk = admin_id_fk;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApprove_date() {
		return approve_date;
	}
	public void setApprove_date(String approve_date) {
		this.approve_date = approve_date;
	}
	public String getItem_category() {
		return item_category;
	}
	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	public String getSeller_mobile() {
		return seller_mobile;
	}
	public void setSeller_mobile(String seller_mobile) {
		this.seller_mobile = seller_mobile;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_mobile() {
		return admin_mobile;
	}
	public void setAdmin_mobile(String admin_mobile) {
		this.admin_mobile = admin_mobile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
