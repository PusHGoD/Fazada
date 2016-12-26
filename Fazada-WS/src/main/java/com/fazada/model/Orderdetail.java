package com.fazada.model;
// Generated Nov 16, 2016 3:15:51 PM by Hibernate Tools 5.2.0.Beta1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Orderdetail generated by hbm2java
 */
@Entity
@Table(name = "orderdetail", catalog = "fazada")
public class Orderdetail implements java.io.Serializable {

	private OrderdetailId id;
	private Order order;
	private Product product;
	private int quantity;

	public Orderdetail() {
	}

	public Orderdetail(OrderdetailId id, Order order, Product product, int quantity) {
		this.id = id;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "orderId", column = @Column(name = "orderID", nullable = false)),
			@AttributeOverride(name = "productId", column = @Column(name = "productID", nullable = false)) })
	public OrderdetailId getId() {
		return this.id;
	}

	public void setId(OrderdetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderID", nullable = false, insertable = false, updatable = false)
	@JsonBackReference(value="order-orderdetails")
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productID", nullable = false, insertable = false, updatable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
