package com.ty.dto;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class FoodOrder {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String status;
	private double totalPrice;
	@CreationTimestamp
	private LocalTime orderCreatedTime;
	private LocalTime orderDeliverTime;
	private String customerName;
//	@Column(unique=true)
	private long contactNumber;
	@ManyToOne
	@JoinColumn(name="Customer")
	private Users users;
	@OneToMany
	List<Item> items;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public LocalTime getOrderCreatedTime() {
		return orderCreatedTime;
	}
	public void setOrderCreatedTime(LocalTime orderCreatedTime) {
		this.orderCreatedTime = orderCreatedTime;
	}
	public LocalTime getOrderDeliverTime() {
		return orderDeliverTime;
	}
	public void setOrderDeliverTime(LocalTime orderDeliverTime) {
		this.orderDeliverTime = orderDeliverTime;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Users getUser() {
		return users;
	}
	public void setUser(Users user) {
		this.users = user;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	

}
