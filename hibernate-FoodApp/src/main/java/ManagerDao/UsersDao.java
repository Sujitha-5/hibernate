package ManagerDao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.Users;

import Customer.Customer;
import StaffDao.CustomerDao;

public class UsersDao {
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();
	static Scanner sc=new Scanner(System.in);
	public Users signUp()
	{
		Users users=new Users();
//		System.out.println("Enter the id : ");
//		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the Name : ");
		String name=sc.nextLine();
		System.out.println("Enter the Email : ");
		String email=sc.nextLine();
		System.out.println("Enter the password : ");
		String password=sc.nextLine();
		System.out.println("Entee the role : ");
		String role=sc.nextLine();
		//users.setId(id);
		users.setName(name);
		users.setEmail(email);
		users.setPassword(password);
		users.setRole(role);
		entityTransaction.begin();
		entityManager.merge(users);
		entityTransaction.commit();
		return users;
	}
	public void login()
	{
		System.out.println("Enter the Email : ");
		String email=sc.nextLine();
		sc.nextLine();
		System.out.println("Enter the password : ");
		String password=sc.nextLine();
		
		
		Query query =entityManager.createQuery("SELECT u FROM Users u");
		List<Users> user=query.getResultList();
		for(Users u:user)
		{
			if(u.getEmail().equalsIgnoreCase(email) && u.getPassword().equalsIgnoreCase(password))
			{
				System.out.println("Login Succesffully");
				String role=u.getRole();
			
					if(role.equalsIgnoreCase("Manager"))
					{
						while(true)
						{
							System.out.println("=====Welcome Manager");
							System.out.println("1.Branch");
							System.out.println("2.Staff");
							System.out.println("3.Menu");
							System.out.println("4.Exit");
							System.out.println("Enter your choice : ");
							int choice=sc.nextInt();
							switch(choice)
							{
								case 1:
									branch();
									break;
								case 2:
									staff();
									break;
								case 3:
									menu();
									break;
								case 4:
									return;
									
									
							}
						}
						
					}
					else if(role.equalsIgnoreCase("Staff"))
					{
						CustomerDao customerDao=new CustomerDao();
						while(true)
						{
							System.out.println("===Welcome Staff===");
							System.out.println("1.CreateCustomer");
							System.out.println("2.Create FoodOrder");
							System.out.println("3.Update FoodOrder");
							System.out.println("4.Exit");
							System.out.println("Enter your choice : ");
							int choice=sc.nextInt();
							switch(choice)
							{
								case 1:
									customerDao.createCustomer();
									break;
								case 2:
									customerDao.createFoodOrder();
									break;
								case 3:
									customerDao.UpdateFoodOrder();
									break;
								case 4:
									return;
									
							}
							
						}
						
						
					}
					else if(role.equalsIgnoreCase("Customer"))
					{
						Customer customer=new Customer();
						customer.seeFoodOrder();
					}
					else
					{
						System.out.println("Invalid credientials");
					}
					
				
				
//								
			}
			
		}
		
		
	}
	public void branch() {
		BranchModify branch=new BranchModify();
		System.out.println("1.Save Branch");
		System.out.println("2.Update Branch");
		System.out.println("3.Remove Branch");
		System.out.println("4.Display Branch");
		System.out.println("5.Exit");
		System.out.println("Enter your choice : ");
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
				branch.CreateBranch();
				break;
			case 2:
				branch.updateBranch();
				break;
			case 3:
				branch.deleteBranch();
				break;
			case 4:
				branch.displayBranch();
				break;
			case 5:
				return;
		}
	}
	public void menu()
	{
		CreateMenu menu=new CreateMenu();
		System.out.println("1.Create Menu");
		
		System.out.println("2.Update Menu");
		System.out.println("3.Exit");
		System.out.println("Enter your choice : ");
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
				menu.createMenu();
				break;
			case 2:
				menu.updateMenu();
				break;
			case 3:
				return;
		}
	}
	public void staff()
	{
		CreateStaff staff=new CreateStaff();
		System.out.println("1.AddStaff");
		System.out.println("2.RemoveStaff");
		System.out.println("3.Exit");
		System.out.println("Enter your choice : ");
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
				staff.addstaff();
				break;
			case 2:
				staff.removeStaff();
			case 3:
				return;
		}
	}
	

}
