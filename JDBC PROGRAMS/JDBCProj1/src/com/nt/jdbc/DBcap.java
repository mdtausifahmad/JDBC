//DatabaseMetaData program
package com.nt.jdbc;
import java.sql.*;

public class DBcap
{
public static void main(String args[])throws Exception
{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:oradsn","system","manager");

              // create DatabaseMetaData obj
			DatabaseMetaData dbmd =con.getMetaData();

			System.out.println("dbmd obj class name:"+dbmd.getClass());
			
			System.out.println("database name "+dbmd.getDatabaseProductName());
				System.out.println("database version "+dbmd.getDatabaseProductVersion());
				System.out.println("jdbc driver version "+dbmd.getDriverVersion());
				System.out.println("sql key words = "+dbmd.getSQLKeywords());
				System.out.println("numeric functions "+dbmd.getNumericFunctions());
				System.out.println("String Functions "+dbmd.getStringFunctions());
				System.out.println("System Functions"+dbmd.getSystemFunctions());
				System.out.println("Search String Escape"+dbmd.getSearchStringEscape());
				System.out.println(" supportsStoredProcedures = "+dbmd.supportsStoredProcedures());
				System.out.println(" getMaxRowSize = "+dbmd.getMaxRowSize());
				System.out.println(" getMaxStatementLength = "+dbmd.getMaxStatementLength());
				System.out.println("get Max tables in a select query="+dbmd.getMaxTablesInSelect());
				System.out.println("get Max Length of Table Name="+dbmd.getMaxTableNameLength());
				System.out.println("jdbc api version is"+dbmd.getJDBCMajorVersion()+" . "+dbmd.getJDBCMinorVersion());
	 }	//main
}//class
