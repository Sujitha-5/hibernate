package ManagerDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.FoodProduct;
import com.ty.dto.Menu;
import com.ty.dto.Users;

public class CreateMenu {
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();
	static Scanner sc=new Scanner(System.in);
	public void createMenu()
	{
		System.out.println("Enter the user id : ");
		int id1=sc.nextInt();
		Users users=entityManager.find(Users.class, id1);
		if(users!=null)
		{
			if(users.getRole().equalsIgnoreCase("Manager"))
			{
				FoodProductDao foodproduct=new FoodProductDao();
				Menu menu=new Menu();
				System.out.println("Enter the menu id : ");
				int id=sc.nextInt();
				sc.nextLine();
				List<FoodProduct> foodProducts=new ArrayList<>();
				System.out.println("Enter how many products you want to add");
				int count=sc.nextInt();
				for(int i=1;i<=count;i++)
				{
					FoodProduct food=foodproduct.saveFoodProduct();
					foodProducts.add(food);
					food.setMenu(menu);
					entityTransaction.begin();
					entityManager.persist(food);
					entityTransaction.commit();

				}
				
				menu.setId(id);
				menu.setFoodproducts(foodProducts);
				menu.setUser(users);
				users.setMenu(menu);
				entityTransaction.begin();

				entityManager.persist(menu);
				entityManager.merge(users);
				
				entityTransaction.commit();
				
			}
		}
		
		
		
	}
	public void updateMenu()
	{
		System.out.println("Enter the menu id : ");
		int id=sc.nextInt();
		sc.nextLine();
		
		Menu menu=entityManager.find(Menu.class, id);
		if(menu!=null)
		{
			FoodProductDao foodproduct=new FoodProductDao();
			System.out.println("1.AddFoodProduct");
			System.out.println("2.Change Exisitingfood");
			System.out.println("3.Remove FoodProduct");
			System.out.println("Enter your choice : ");
			int choice=sc.nextInt();
			switch(choice)
			{
				case 1:
					List<FoodProduct> foodProducts=menu.getFoodproducts();
					FoodProduct food=foodproduct.saveFoodProduct();
					foodProducts.add(food);
					food.setMenu(menu);
					menu.setFoodproducts(foodProducts);
					entityTransaction.begin();
					
					entityManager.merge(menu);
					entityTransaction.commit();
					
					break;
				case 2:
					foodproduct.updateFoodProduct();
//					entityTransaction.begin();
//					
//					entityManager.merge(menu);
//					entityTransaction.commit();
//					break;
				case 3:
					foodproduct.removeFoodProduct();
					break;
					
					
			}
			
			
		}
	}

}
