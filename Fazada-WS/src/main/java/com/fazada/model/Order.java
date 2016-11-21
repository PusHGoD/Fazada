package com.fazada.model;
// Generated Nov 16, 2016 3:15:51 PM by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Order generated by hbm2java
 */
@Entity
@Table(name = "order", catalog = "fazada")
public class Order implements java.io.Serializable {

	private int orderId;
	private Account account;
	private Date dateTime;
	private int total;
	private int orderstatus;
	private Set<Orderdetail> orderdetails = new HashSet<Orderdetail>(0);

	public Order() {
	}

	public Order(int orderId, Account account, Date dateTime, int total, int orderstatus) {
		this.orderId = orderId;
		this.account = account;
		this.dateTime = dateTime;
		this.total = total;
		this.orderstatus = orderstatus;
	}

	public Order(int orderId, Account account, Date dateTime, int total, int orderstatus,
			Set<Orderdetail> orderdetails) {
		this.orderId = orderId;
		this.account = account;
		this.dateTime = dateTime;
		this.total = total;
		this.orderstatus = orderstatus;
		this.orderdetails = orderdetails;
	}

	@Id
	@Column(name = "orderID", unique = true, nullable = false)
	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userID", nullable = false)
	@JsonBackReference(value="account-order")
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_time", nullable = false, length = 10)
	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Column(name = "total", nullable = false)
	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Column(name = "orderstatus", nullable = false)
	public int getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
	@JsonManagedReference(value="order-orderdetails")
	@Fetch(FetchMode.SELECT)
	public Set<Orderdetail> getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(Set<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

}
