package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScrollFrame extends JFrame implements ActionListener {
	private static final String GET_STUDENTS_QRY="SELECT SNO,SNAME,SADD FROM STUDENT";
	private JLabel lno,lname,ladd;
	private JTextField  tsno,tsname,tsadd;
	private JButton bfirst,bnext,bprevious,blast;
	private Connection con=null;
	private Statement st=null;
	private ResultSet rs=null;
	//constructor
	public ScrollFrame() {
		System.out.println("constructor");
		setTitle("Scroll Frame App");
		setLayout(new FlowLayout());
		setSize(300,400);
		setBackground(Color.cyan);
		//add comps
	    lno=new JLabel("student number");
	    add(lno);
	    tsno=new JTextField(10);
	    add(tsno);
	    
	    lname=new JLabel("student name");
	    add(lname);
	    tsname=new JTextField(10);
	    add(tsname);
	    
	    ladd=new JLabel("student Address");
	    add(ladd);
	    tsadd=new JTextField(10);
	    add(tsadd);
	    
	    bfirst=new JButton("first");
	    add(bfirst);
	    bfirst.addActionListener(this);
	    
	    bnext=new JButton("next");
	    add(bnext);
	    bnext.addActionListener(this);
	    
	    bprevious=new JButton("previous");
	    bprevious.addActionListener(this);
	    add(bprevious);
	    
	    blast=new JButton("last");
	    add(blast);
	    blast.addActionListener(this);
	    
	    setVisible(true);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    initialize();
	}//constructor
   private void  initialize(){
	   System.out.println("intialize()");
	   try{
		   //register jdbc driver
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   //Estalblish the connection
		   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
          //create Statement obj having type,mode
		   if(con!=null)
			   st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					                                      ResultSet.CONCUR_UPDATABLE);
		   //send and execute SQL Query
		   if(st!=null)
			   rs=st.executeQuery(GET_STUDENTS_QRY);
	   }//try
	   catch(SQLException se){
		   se.printStackTrace();
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
   }//initialize
   
	@Override
	public void actionPerformed(ActionEvent ae) {
		boolean flag=false;
		try{
		System.out.println("actionPerfomed(-) --->"+ae.getActionCommand());
		if(ae.getSource()==bfirst){
			System.out.println("first button");
			rs.first();
			flag=true;
		}
		else if(ae.getSource()==blast){
			System.out.println("last button");
			rs.last();
			flag=true;
		}
		else if(ae.getSource()==bnext){
			System.out.println("next button");
			if(!rs.isLast()){
				rs.next();
				flag=true;
			}
		}
		else {
			if(!rs.isFirst()){
				System.out.println("previous button");
			 rs.previous();
			 flag=true;
			}
		}//else
		//set values to text boxes
		if(flag==true){
			tsno.setText(rs.getString(1));
			tsname.setText(rs.getString(2));
			tsadd.setText(rs.getString(3));
		}//if
		
		}//try
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}//actionPerfomed(-)
	
	public static void main(String[] args) {
		System.out.println("main(-)");
		ScrollFrame frame=new ScrollFrame();
	}
}
