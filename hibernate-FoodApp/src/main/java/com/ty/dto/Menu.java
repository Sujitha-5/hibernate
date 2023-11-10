package com.ty.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Menu {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@OneToMany(mappedBy="menu")
	List<FoodProduct> foodproducts;
	@OneToOne
	@JoinColumn(name="BranchManager")
	private Users user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<FoodProduct> getFoodproducts() {
		return foodproducts;
	}
	public void setFoodproducts(List<FoodProduct> foodproducts) {
		this.foodproducts = foodproducts;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
}
