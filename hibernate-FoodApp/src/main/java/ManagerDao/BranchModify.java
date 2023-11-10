package ManagerDao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.Branch;
import com.ty.dto.Users;

public class BranchModify {
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();
	static Scanner sc=new Scanner(System.in);
	public void CreateBranch()
	{
		Branch branch=new Branch();
		
		System.out.println("Enter the user id : ");
		int id=sc.nextInt();
		Users u=entityManager.find(Users.class, id);
		if(u!=null)
		{
			if(u.getRole().equalsIgnoreCase("Manager"))
			{
//				System.out.println("Enter the Branch id : ");
//				int id1=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the Branch Name : ");
				String name=sc.nextLine();
				System.out.println("Enter the Branch Address : ");
				String address=sc.nextLine();
				System.out.println("Enter the Branch phoneNumber : ");
				long phoneNumber=sc.nextLong();
				System.out.println("Enter the Branch Email : ");
				String Email=sc.nextLine();
				
				
				//branch.setId(id1);
				branch.setName(name);
				branch.setAddress(address);
				branch.setEmail(Email);
				branch.setPhoneNumber(phoneNumber);
				branch.setUser(u);
				entityTransaction.begin();
				entityManager.persist(branch);
				entityTransaction.commit();
				
			}
			else
			{
				System.out.println("You cannot create Branch");
			}
		}
		else
		{
			System.out.println("No user is present");
		}
		
	
		
		
	}
	public void updateBranch()
	{
		System.out.println("Enter the Branch id : ");
		int id1=sc.nextInt();
		sc.nextLine();
		Branch branch=entityManager.find(Branch.class, id1);
		if(branch!=null)
		{
			System.out.println("Enter the Branch phoneNumber : ");
			long phoneNumber=sc.nextLong();
			sc.nextLine();
			System.out.println("Enter the Branch Email : ");
			String Email=sc.nextLine();
			branch.setEmail(Email);
			branch.setPhoneNumber(phoneNumber);
			entityTransaction.begin();
			entityManager.merge(branch);
			entityTransaction.commit();
			
		}
		else
		{
			System.out.println("No branch is present ");
		}
		
	}
	public void displayBranch()
	{
		Query query=entityManager.createQuery("SELECT b FROM Branch b");
		List<Branch> branch=query.getResultList();
		for(Branch b:branch)
		{
			System.out.println("Branch Id : "+b.getId());
			System.out.println("Branch Name : "+b.getName());
			System.out.println("Branch Address : "+b.getAddress());
			System.out.println("Branch Email : "+b.getEmail());
			System.out.println("Branch Phone Number : "+b.getPhoneNumber());
			
		}
	}
	public void deleteBranch()
	{
		System.out.println("Enter the branch id : ");
		int id=sc.nextInt();
		sc.nextLine();
		Branch branch=entityManager.find(Branch.class, id);
		if(branch!=null)
		{
			entityTransaction.begin();
			entityManager.remove(branch);
			entityTransaction.commit();
		}
	}

}
