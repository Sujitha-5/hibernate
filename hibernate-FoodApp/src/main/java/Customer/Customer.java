package Customer;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.FoodOrder;

public class Customer {
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();
	static Scanner sc=new Scanner(System.in);
	public void seeFoodOrder()
	{
		System.out.println("Enter the fooOrder Id : ");
		int id=sc.nextInt();
		FoodOrder foodorder=entityManager.find(FoodOrder.class, id);
		if(foodorder!=null)
		{
			System.out.println("===================================================");
			System.out.println("Food status : "+foodorder.getStatus());
			System.out.println("====================================================");
		}
		
	}

}
