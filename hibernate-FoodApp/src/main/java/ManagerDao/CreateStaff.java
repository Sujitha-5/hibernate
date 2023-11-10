package ManagerDao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.Users;

public class CreateStaff {
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();
	static Scanner sc=new Scanner(System.in);
	public void addstaff()
	{
		Users users=new Users();
//		System.out.println("Enter the id : ");
//		int id=sc.nextInt();
//		sc.nextLine();
		System.out.println("Enter the Name : ");
		String name=sc.nextLine();
		System.out.println("Enter the Email : ");
		String email=sc.nextLine();
		System.out.println("Enter the password : ");
		String password=sc.nextLine();
	//	users.setId(id);
		users.setName(name);
		users.setEmail(email);
		users.setPassword(password);
		users.setRole("Staff");
		
		entityTransaction.begin();
		entityManager.merge(users);
		entityTransaction.commit();
	}
	public void removeStaff()
	{
		System.out.println("Enter the id : ");
		int id=sc.nextInt();
		sc.nextLine();
		Users users=entityManager.find(Users.class, id);
		if(users!=null)
		{
			entityTransaction.begin();
			entityManager.remove(users);
			entityTransaction.commit();
		}
	}
	public void displayStaff()
	{
		Query query=entityManager.createQuery("SELECT u FROM Users u");
		List<Users> user=query.getResultList();
		for(Users u:user)
		{
			if(u.getRole().equalsIgnoreCase("Staff"))
			{
				System.out.println("Staff Name : "+u.getName());
				System.out.println("Staff Email : "+u.getEmail());
				
				
			}
		}
	}

}
