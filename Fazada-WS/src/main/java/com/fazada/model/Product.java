package com.fazada.model;
// Generated Nov 16, 2016 3:15:51 PM by Hibernate Tools 5.2.0.Beta1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name = "product", catalog = "fazada")
public class Product implements java.io.Serializable {

	private int productId;
	private String productName;
	private String brand;
	private int price;
	private boolean status;
	private Set<Orderdetail> orderdetails = new HashSet<Orderdetail>(0);

	public Product() {
	}

	public Product(int productId, String productName, int price, boolean status) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.status = status;
	}

	public Product(int productId, String productName, String brand, int price, boolean status,
			Set<Orderdetail> orderdetails) {
		this.productId = productId;
		this.productName = productName;
		this.brand = brand;
		this.price = price;
		this.status = status;
		this.orderdetails = orderdetails;
	}

	@Id

	@Column(name = "productID", unique = true, nullable = false)
	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Column(name = "productName", nullable = false, length = 45)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "brand", length = 45)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "price", nullable = false)
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
	@JsonManagedReference(value="orderdetails-product")
	public Set<Orderdetail> getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(Set<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

}
