package com.ty.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.Branch;
import com.ty.dto.Users;

public class BranchDao {
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();
	static Scanner sc=new Scanner(System.in);
	public Branch saveBranch()
	{
		Branch branch=new Branch();
		
		System.out.println("Enter the user id : ");
		int id=sc.nextInt();
		Users u=entityManager.find(Users.class, id);
		if(u!=null)
		{
			if(u.getRole().equalsIgnoreCase("Manager"))
			{
				System.out.println("Enter the id : ");
				int id1=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the Branch Name : ");
				String name=sc.nextLine();
				System.out.println("Enter the Branch Address : ");
				String address=sc.nextLine();
				System.out.println("Enter the Branch phoneNumber : ");
				long phoneNumber=sc.nextLong();
				System.out.println("Enter the Branch Email : ");
				String Email=sc.nextLine();
				
				
				branch.setId(id);
				branch.setName(name);
				branch.setAddress(address);
				branch.setEmail(Email);
				branch.setPhoneNumber(phoneNumber);
				branch.setUser(u);
				entityTransaction.begin();
				entityManager.persist(branch);
				entityTransaction.commit();
				
			}
		}
		return branch;
		
		
		
	}

}
