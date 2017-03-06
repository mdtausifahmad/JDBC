package com.nt.basics;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropsTest {

	public static void main(String[] args) {
		InputStream is=null;
		Properties props=null;
		try{
			//locate properties file
			is=new FileInputStream("src/com/nt/commons/myfile.properties");
		   //Load data into Properties file
			props=new Properties();
			props.load(is);
			//display content
			System.out.println(props);
			System.out.println("name is"+props.getProperty("name"));
		}//try
		catch(Exception e){
			e.printStackTrace();
		}
	}//main
}//class
