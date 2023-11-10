package StaffDao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.FoodOrder;
import com.ty.dto.FoodProduct;
import com.ty.dto.Item;
import com.ty.dto.Menu;
import com.ty.dto.Users;

public class CustomerDao {
	static EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
	static EntityManager entityManager=entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction=entityManager.getTransaction();
	static Scanner sc=new Scanner(System.in);
	public void createCustomer()
	{
		Users users=new Users();
//		System.out.println("Enter the customer id : ");
//		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the customer Name : ");
		String name=sc.nextLine();
		System.out.println("Enter the customer Email : ");
		String email=sc.nextLine();
		System.out.println("Enter the customer password : ");
		String password=sc.nextLine();
		//users.setId(id);
		users.setName(name);
		users.setEmail(email);
		users.setPassword(password);
		users.setRole("Customer");
		entityTransaction.begin();
		entityManager.merge(users);
		entityTransaction.commit();
	}
	public void createFoodOrder()
	{
		FoodOrder foodorder=new FoodOrder();
		System.out.println("Enter the foodorder Id : ");
		int id=sc.nextInt();
		sc.nextLine();
		
//		System.out.println("Enter the foodorder status : ");
//		String status=sc.nextLine();
		
//		System.out.println("3.Enter the foodorder price : ");
//		double price=sc.nextDouble();
		
		System.out.println("4.Enter the orderCreatedTime : ");
		System.out.println("Enter the time in hours : ");
		int hour=sc.nextInt();
		System.out.println("Enter the time in minutes : ");
		int minute=sc.nextInt();
		
		LocalTime createdtime=LocalTime.of(hour, minute);
		System.out.println("4.Enter the orderDeliveryTime : ");
		System.out.println("Enter the time in hours : ");
		int delivaryhour=sc.nextInt();
		System.out.println("Enter the time in minutes : ");
		int delivaryminute=sc.nextInt();
		
		LocalTime delivarytime=LocalTime.of(delivaryhour, delivaryminute);
		sc.nextLine();
//		
		
		System.out.println("Enter the customer id : ");
		int cid=sc.nextInt();
		Users customer=entityManager.find(Users.class,cid);
		if(customer!=null)
		{
			if(customer.getRole().equals("Customer"))
			{
				foodorder.setCustomerName(customer.getName());
				
			}
			List<Users> u=new ArrayList<>();
			u.add(customer);
			foodorder.setUser(customer);
		}
		else
		{
			Users users=new Users();
			sc.nextLine();
			System.out.println("Enter the customer Name : ");
			String name=sc.nextLine();
			System.out.println("Enter the customer Email : ");
			String email=sc.nextLine();
			System.out.println("Enter the customer password : ");
			String password=sc.nextLine();
			//users.setId(id);
			users.setName(name);
			users.setEmail(email);
			users.setPassword(password);
			users.setRole("Customer");
			entityTransaction.begin();
			entityManager.merge(users);
			entityTransaction.commit();
		}
		System.out.println("Enter the contact number : ");
		long phonenumber=sc.nextLong();
		
		
		foodorder.setId(id);
		foodorder.setStatus("Delivered");
		
		foodorder.setOrderCreatedTime(createdtime);
		foodorder.setOrderDeliverTime(delivarytime);
	
		
		foodorder.setContactNumber(phonenumber);
		
		List<Item> item=new ArrayList<>();
		System.out.println("How many items you want to add : ");
		int count=sc.nextInt();
		double cost = 0;
		for(int i=1;i<=count;i++)
		{
			Item item1=saveItem();
			item.add(item1);
			cost=cost+(item1.getPrice()*item1.getQuantity());
		}
		foodorder.setItems(item);
		foodorder.setTotalPrice(cost);
		entityTransaction.begin();
		entityManager.merge(foodorder);
		entityTransaction.commit();
		
		
	}
	public static Item saveItem()
	{
		Item item=new Item();
		Query query=entityManager.createQuery("SELECT m FROM Menu m");
		List<Menu> menu=query.getResultList();
		for(Menu m:menu)
		{
			List<FoodProduct> foodproducts =m.getFoodproducts();
			for(FoodProduct food:foodproducts)
			{
				System.out.println("Food Id : "+food.getId());
				System.out.println("Food Name : "+food.getName());
				System.out.println("Food Type : "+food.getType());
				System.out.println("Food Price : "+food.getPrice());
			}
			
		}
				
		System.out.println("Enter the Item id : ");
		int id=sc.nextInt();
		System.out.println("Enter the productId : ");
		int pid=sc.nextInt();
		FoodProduct food=entityManager.find(FoodProduct.class, pid);
		if(food!=null)
		{
			System.out.println("Enter the quantity : ");
			int quantity=sc.nextInt();
			item.setId(id);
			item.setProductid(pid);
			item.setName(food.getName());
			item.setType(food.getType());
			item.setQuantity(quantity);
			item.setPrice(food.getPrice());
			
			
			entityTransaction.begin();
			entityManager.persist(item);
			entityTransaction.commit();
		}
		
		
		
		
		return item;
		

	}
	public void UpdateFoodOrder()
	{
		System.out.println("Enter the food Order id : ");
		int id =sc.nextInt();
		sc.nextLine();
		FoodOrder foodorder=entityManager.find(FoodOrder.class, id);
		if(foodorder!=null)
		{
			
			System.out.println("1.Add Item");
			System.out.println("2.Change Existing Item");
			System.out.println("3.Remove Item");
			System.out.println("Enter your choice : ");
			int choice=sc.nextInt();
			switch(choice)
			{
				case 1:
					List<Item> i=foodorder.getItems();
					Item item=saveItem();
					i.add(item);
					foodorder.setItems(i);
					entityTransaction.begin();
					
					entityManager.merge(foodorder);
					entityTransaction.commit();
					break;
				case 2:
					System.out.println("Enter the foodorder status : ");
					String status=sc.nextLine();
					foodorder.setStatus(status);
					entityTransaction.begin();
					
					entityManager.merge(foodorder);
					entityTransaction.commit();
					
					
					break;
				case 3:
					removeItem();
					break;
					
					
				
			}
		}
	}
	public Item updateItem()
	{
		System.out.println("Enter the Item id : ");
		int id=sc.nextInt();
		Item item=entityManager.find(Item.class, id);
		if(item!=null)
		{
			System.out.println("Enter the quantity : ");
			int quantity=sc.nextInt();
			item.setQuantity(quantity);
			entityTransaction.begin();
			entityManager.merge(item);
			entityTransaction.commit();
			
		}
		return item;
	}
	public void removeItem()
	{
		
		System.out.println("Enter the Item id : ");
		int id=sc.nextInt();
		entityTransaction.begin();
		Query query=entityManager.createQuery("SELECT f FROM FoodOrder f");
		List<FoodOrder> foodorder=query.getResultList();
		for(FoodOrder f:foodorder)
		{
			List<Item> item1=f.getItems();
			for(Item i:item1)
			{
				if(i.getId()==id)
				{
					item1.remove(i);
					f.setItems(item1);
					double c=price();
					f.setTotalPrice(c);
					entityManager.merge(f);
					break;
					
				}
			}

		}
		Item item=entityManager.find(Item.class, id);
		if(item!=null)
		{
		

			entityManager.remove(item);
			entityTransaction.commit();
			
			
			
		}
		
		
	}
	public double price()
	{
		FoodOrder foodorder=new FoodOrder();
		System.out.println("Enter the foodorder Id : ");
		int id=sc.nextInt();
		sc.nextLine();
		double cost=0;
		FoodOrder f=entityManager.find(FoodOrder.class, id);
		if(f!=null)
		{
			List<Item> item=f.getItems();
			for(Item i:item)
			{
				cost=cost+(i.getPrice()*i.getQuantity());
			}
		}
		return cost;
	}
	

}
