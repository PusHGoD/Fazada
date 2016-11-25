package com.fazada.model;
// Generated Nov 16, 2016 3:15:51 PM by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Account generated by hbm2java
 */
@Entity
@Table(name = "account", catalog = "fazada")
public class Account implements java.io.Serializable {

	private int id;
	private String userName;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private boolean active;
	private String email;
	private String role;
	private Set<Order> orders = new HashSet<Order>(0);

	public Account() {
	}

	public Account(int id, String userName, String password, String firstName, String lastName, Date dateOfBirth,
			boolean active) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.active = active;
	}

	public Account(int id, String userName, String password, String firstName, String lastName, Date dateOfBirth,
			boolean active, String email, String role, Set<Order> orders) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.active = active;
		this.email = email;
		this.role = role;
		this.orders = orders;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "user_name", nullable = false, length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "first_name", nullable = false, length = 30)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 30)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth", nullable = false, length = 10)
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "role", length = 10)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
//	@JsonManagedReference(value="account-order")
//	@JsonIgnore
//	public Set<Order> getOrders() {
//		return this.orders;
//	}
//
//	public void setOrders(Set<Order> orders) {
//		this.orders = orders;
//	}

}
