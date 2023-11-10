package ManagerDao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.FoodProduct;
import com.ty.dto.Menu;

public class FoodProductDao {
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();
	static Scanner sc=new Scanner(System.in);
	public FoodProduct saveFoodProduct()
	{
		System.out.println("Enter the menu id : ");
		int menuid=sc.nextInt();
		sc.nextLine();
		Menu menu=entityManager.find(Menu.class, menuid);
		FoodProduct foodproduct=new FoodProduct();
		System.out.println("Enter the food product id : ");
		int id =sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the name of product : ");
		String name=sc.nextLine();
		System.out.println("Enter the type of food Product : ");
		String type=sc.nextLine();
		System.out.println("Enter the about the foodproduct : ");
		String about=sc.nextLine();
		System.out.println("Enter the availability : ");
		String availability=sc.nextLine();
		
		System.out.println("Enter the cost of foodProduct : ");
		double price=sc.nextDouble();
		
		foodproduct.setId(id);
		foodproduct.setName(name);
		foodproduct.setType(type);
		foodproduct.setAbout(about);
		foodproduct.setAvailabilty(availability);
		foodproduct.setPrice(price);
		foodproduct.setMenu(menu);
		entityTransaction.begin();
		entityManager.merge(foodproduct);
		entityTransaction.commit();
		return foodproduct;
		
	}
	public FoodProduct updateFoodProduct()
	{
		System.out.println("Enter the food product id : ");
		int id =sc.nextInt();
		sc.nextLine();
		FoodProduct foodproduct=entityManager.find(FoodProduct.class, id);
		if(foodproduct!=null)
		{
			System.out.println("Enter the availability : ");
			String availability=sc.nextLine();
			System.out.println("Enter the cost of foodProduct : ");
			double price=sc.nextDouble();
			foodproduct.setAvailabilty(availability);
			foodproduct.setPrice(price);
			entityTransaction.begin();
			entityManager.merge(foodproduct);
			entityTransaction.commit();
		}
		return foodproduct;
	}
	public void removeFoodProduct()
	{
		System.out.println("Enter the food product id : ");
		int id =sc.nextInt();
		sc.nextLine();
		FoodProduct foodproduct=entityManager.find(FoodProduct.class, id);
		if(foodproduct!=null)
		{
			entityTransaction.begin();
			entityManager.remove(foodproduct);
			entityTransaction.commit();
			
		}
	}

}
