package com.jdbc.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.jdbc.PreparedStatementWrapper;

public class BankingSystem 
{
	
	//public static int withdraw;
	private static Connection connection = null;
	private static Scanner scanner=new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		
		BankingSystem bankingSystem = new BankingSystem();
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL="jdbc:mysql://localhost:3306/jdbcdb";
			String username="root";
			String password="root";
	connection=	DriverManager.getConnection(dbURL, username, password);
	
	while(true)
	{
		System.out.println("Enter choice");
		System.out.println("1.Insert record");
		System.out.println("2.Select record");
		System.out.println("3.Update record");
		System.out.println("4.Delete record");
	    System.out.println("5.Deposit amount");
	    System.out.println("6.Widthdraw amount");
	    System.out.println("7.Exit");
		
		int choice = scanner.nextInt();
		switch(choice) {
		case 1:
			bankingSystem.insertRecord();
			break;
		case 2:
			bankingSystem.selectRecord();
			break;
		case 3:
			bankingSystem.updateRecord();
			break;
		case  4:
			bankingSystem.deleteRecord();
			break;
		case 5:
			bankingSystem.depositAmount();
		break;
		
		case 6:
			bankingSystem.withdrawAmount();
			break;
		case 7:
			System.out.println("exit");
			System.exit(0);
		}
	}
	
		
	
	
		}

	private void insertRecord() throws SQLException 
	{
		String sql="insert into bank (Name,branch,phnone_no,balance) values (?,?,?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		System.out.println("Enter name");
		scanner.nextLine();
		preparedStatement.setString(1,scanner.nextLine());
		
		System.out.println("Enter branch");
		preparedStatement.setString(2,scanner.nextLine());
		
		System.out.println("Enter phoneno");
		preparedStatement.setInt(3,scanner.nextInt());
		
		System.out.println("Enter  balance");
		preparedStatement.setInt(4,scanner.nextInt());
		
	int rows=preparedStatement.executeUpdate();
	

	if(rows>0 )
	{
		System.out.println("Record inserted successfully");
	}
	
	
	}
	private void selectRecord() throws SQLException 
	{
		 System.out.println("Enter account no to find result"); 
		
		int number= scanner.nextInt();
		String sql="select * from bank where account_no=  " + number;
         Statement statement = connection  .createStatement();


         ResultSet result= statement.executeQuery(sql);
         if(result.next())
         {
        	 
        	 String name=result.getString("NAME");
        	 int accountNumber=result.getInt("account_no");
        	 String branch=result.getString("branch");
        	 int phoneNo=result.getInt("phnone_no");
        	 int balance=result.getInt("balance");
        	
        	 System.out.println("Name : " + name); 
        	 System.out.println("Account number : "+ accountNumber); 
        	 System.out.println("Branch : " + branch); 
        	 System.out.println("Phone number : " + phoneNo); 
        	 System.out.println("Balance : " + balance); 
        	 
        	 
         }else {
        	 
        	 
        	 System.out.println("**************NO record found***************"); 
         }

	}
	
private void updateRecord() throws SQLException
{
	

	 System.out.println("Enter account no to find result"); 
	
	int number= scanner.nextInt();
	String sql="select * from bank where account_no=  " + number;
    Statement statement = connection  .createStatement();


    ResultSet result= statement.executeQuery(sql);
    if(result.next())
    {
   	 
   	 String name=result.getString("NAME");
   	 int accountNumber=result.getInt("account_no");
   	 String branch=result.getString("branch");
   	 int phoneNo=result.getInt("phnone_no");
   	 int balance=result.getInt("balance");
   	
   	 System.out.println("Name is" + name); 
   	 System.out.println("Account number is"+ accountNumber); 
   	 System.out.println("Branch is" + branch); 
   	 System.out.println("Phone number is" + phoneNo); 
   	 System.out.println("Balance is" + balance); 
   	 
   	 System.out.println(" What do you want upadte");
   	 System.out.println(" 1.Name");
   	 System.out.println(" 2.Branch");
   	 System.out.println(" 3.Phone no");
   	  int choice=scanner.nextInt();
   	  String sqlQuery="update bank set";
     switch(choice)
     {
     case 1:
    	 System.out.println("Enter new name");
    	 scanner.nextLine();
    	 String newName=scanner.nextLine();
    	 sqlQuery = sqlQuery + " Name = ? where account_no= "+accountNumber;
         PreparedStatement  preaparedStatement=	 connection.prepareStatement(sqlQuery);
         preaparedStatement.setString(1, newName);
          int rows=preaparedStatement.executeUpdate();
          if(rows > 0)
          {
        	  System.out.println("record updated successfully");
          }
    	
    	 break;
    
     case 2:
    	 System.out.println("Enter new branch");
    	 scanner.nextLine();
    	 String newBranch=scanner.nextLine();
    	 sqlQuery = sqlQuery + " branch = ? where account_no= "+accountNumber;
         PreparedStatement  preaparedStatement1=	 connection.prepareStatement(sqlQuery);
         preaparedStatement1.setString(1, newBranch);
          int rows1=preaparedStatement1.executeUpdate();
          if(rows1 > 0)
          {
        	  System.out.println("record updated successfully");
          }
    	 
    	 
    	 
    	 break;
    	 
     case 3:
    	 
    	 System.out.println("Enter new Phone no");
    	 //scanner.nextInt();
    	 int newPhoneno=scanner.nextInt();
    	 sqlQuery = sqlQuery + " phnone_no = ? where account_no= "+accountNumber;
         PreparedStatement  preaparedStatement2=	 connection.prepareStatement(sqlQuery);
         preaparedStatement2.setInt(1, newPhoneno);
          int rows2=preaparedStatement2.executeUpdate();
          if(rows2 > 0)
          {
        	  System.out.println("record updated successfully");
          }
    	 

    	 
    	 
    	  break;
    	  
    default:
    		 break;
     }
   	 
    }else {
   	 
   	 
   	 System.out.println("**************NO record found***************"); 
    }


	
}
	void deleteRecord() throws SQLException 

{
	System.out.println("Enter account number to delete");
	int accountNumber=scanner.nextInt();
	String sql="delete from bank where account_no =  " + accountNumber;
	PreparedStatement ps= connection.prepareStatement(sql);
	int rows= ps.executeUpdate();

	if(rows>0)
	{
		System.out.println("********Record  deleted successfully********");
	}
}
private void depositAmount() throws SQLException 
{
	 System.out.println("Enter account number to be deposit"); 
	
	int anumber= scanner.nextInt();
	String sql="select balance from bank where account_no=  " + anumber;
     Statement statement = connection  .createStatement();
    

     ResultSet result = statement.executeQuery(sql);
     //hint accountNumber=result.getInt("account_no");
     int deposit;
     if(result.next())
     {
    	
    	 int balance1=result.getInt("balance");
    	 
    
    	 System.out.println("Current balance is "+ balance1); 
    	 
    	
    	
    	System.out.println("enter  depoisit amount"); 
    	 deposit=scanner.nextInt();
    	 balance1  =balance1+deposit;
    	 System.out.println("Available Balance " + balance1);
    	String sql1= "update bank set balance = ? where account_no =  "+anumber;
    	PreparedStatement ps=connection.prepareStatement(sql1);
    	ps.setInt(1, balance1);
    	ps.executeUpdate();
    	
			
			System.out.println("*****Amount deposited  successfully********");	
		
     }
   
		
}


private void withdrawAmount() throws SQLException 
{
	 System.out.println("Enter account number to widthdraw"); 
	
	int anumber= scanner.nextInt();
	String sql="select * from bank where account_no=  " + anumber;
     Statement statement = connection  .createStatement();
    

     ResultSet result = statement.executeQuery(sql);
     //hint accountNumber=result.getInt("account_no");
     //hint withdraw;
     if(result.next())
     {
    	
    	 int balance1=result.getInt("balance");
    	 
    
    	 System.out.println("Current balance is "+ result.getInt(5)); 
    	 
    	
    	
    	System.out.println("enter  widthdraw  amount"); 
    int	withdraw=scanner.nextInt();
    	 balance1  =balance1-withdraw;
    	 System.out.println("Available Balance " + balance1);
    	// sqlQuery = statement.executeUpdate("update bank set balance = 100 where account_no =1 ",+ balance1);
    	 String sql1= "update bank set balance = ? where account_no =  "+anumber;
     	PreparedStatement ps=connection.prepareStatement(sql1);
     	ps.setInt(1, balance1);
     	ps.executeUpdate();
     	
 			
 			System.out.println("*****Amount withdraw  successfully********");	
 		
		
     }
}




}


