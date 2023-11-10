package View;

import java.util.Scanner;

import ManagerDao.UsersDao;

public class view {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		UsersDao user=new UsersDao();
		while(true)
		{
			System.out.println("1.Signup");
			System.out.println("2.Login");
			System.out.println("3.Exit");
			System.out.println("Enter your choice : ");
			int choice=sc.nextInt();
			switch(choice)
			{
				case 1:
					user.signUp();
					break;
				case 2:
					user.login();
					break;
				case 3:
					return;
			}
		}
	}


}
