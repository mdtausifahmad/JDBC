package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;

public class PMDTest {

	public static void main(String[] args)throws Exception {
		//register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Etablish the connection
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//create PreparedStatement obj
		PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?)");
		// create ParameterMetaData
		ParameterMetaData pmd=ps.getParameterMetaData();
		// get Parameter details
		int cnt=pmd.getParameterCount();
		System.out.println("Paremeter count"+cnt);
		for(int i=1;i<=cnt;++i){
			System.out.println("parmeter index:"+i);
			System.out.println("Parameter Mode"+pmd.getParameterMode(i));
			System.out.println("Parameter TypeName"+pmd.getParameterTypeName(i));
			System.out.println("Singned?"+pmd.isSigned(i));
			System.out.println("Nullable?"+pmd.isNullable(i));
			System.out.println("Precision"+pmd.getPrecision(i));
			System.out.println("Scale :"+pmd.getScale(i));
		}
		
   //close jdbc objs
		ps.close();
		con.close();
	}//main(-)
}//class
