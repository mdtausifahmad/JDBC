package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TxMgmtTest {

	public static void main(String[] args) {
		int srcNo=0,destNo=0,amount=0;
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter source Account no:");
				 srcNo=sc.nextInt();
				 System.out.println("Enter Dest Account no:");
				 destNo=sc.nextInt();
				 System.out.println("Enter Amount to Transfer:");
				 amount=sc.nextInt();
			}//if
				//register jdbc driver
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//Etablish the connection
					 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			  //begin Tx
			   if(con!=null)
				   con.setAutoCommit(false);
			   //create STatement obj
			   if(con!=null)
				   st=con.createStatement();
			   //perform transafer money operation
			   if(st!=null){
				   st.addBatch("update jdbc_account set balance=balance-"+amount+" where acno="+srcNo);
				   st.addBatch("update jdbc_account set balance=balance+"+amount+" where acno="+destNo);
			   }//if
              if(st!=null){
            	  result=st.executeBatch();
              }
              //perform Tx mgment
              if(result!=null){
            	  for(int i=0;i<result.length;++i){
            		  if(result[i]==0){
                          flag=true;
                          break;
            		  }//if
            	  }//for
              }//if
              
              if(flag==true){
            	  con.rollback();
            	 System.out.println("Tx rolledb back (Money not transfered");
              }
              else{
            	  con.commit();
            	  System.out.println("Tx committed (Money  transfered");
              }//else
			
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//close jdbc objs and streams
			try{
				if(st!=null)
					st.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(sc!=null)
					sc.close();
			}
			catch(Exception se){
				se.printStackTrace();
			}

			
		}//finally
	}//main
}//class
