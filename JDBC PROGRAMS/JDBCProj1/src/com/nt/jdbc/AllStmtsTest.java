package com.nt.jdbc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AllStmtsTest extends JFrame implements ActionListener{
 private static final String  GET_ALL_SNOS="SELECT SNO FROM ALL_STUDENT";
 private static final String  GET_STUDENT_BY_SNO="SELECT * FROM ALL_STUDENT WHERE SNO=?";
 private static final String  CALL_PROCEDURE="{call FIND_PASS_FAIL(?,?,?,?)}"; 
private JLabel lno,lname,lm1,lm2,lm3,lresult;
private JTextField tname,tm1,tm2,tm3,tresult;
private JComboBox tno; 
private JButton bdetails,bresult;
private Connection con;
private Statement st;
private PreparedStatement ps;
private CallableStatement cs;
private ResultSet rs1,rs2;	
int m1,m2,m3;
public AllStmtsTest(){
	System.out.println("AllStmtsTEst:0-parma constructor");
	setSize(300,300);
	setLayout(new FlowLayout());
	setTitle("Swing Frame App");
	//add comps
	lno=new JLabel("student no");
	add(lno);
	tno=new JComboBox();
	add(tno);
	
	bdetails=new JButton("details");
	bdetails.addActionListener(this);
	add(bdetails);
	
	lname=new JLabel("name:");
    add(lname);
	tname=new JTextField(10);
	tname.setEditable(false);
	add(tname);
	
	lm1=new JLabel("marks1:");
    add(lm1);
	tm1=new JTextField(10);
	tm1.setEditable(false);
	add(tm1);
	
	lm2=new JLabel("marks2:");
    add(lm2);
	tm2=new JTextField(10);
	tm2.setEditable(false);
	add(tm2);
	
	lm3=new JLabel("marks3:");
    add(lm3);
	tm3=new JTextField(10);
	tm3.setEditable(false);
	add(tm3);
	
	bresult=new JButton("Result");
	bresult.addActionListener(this);
	add(bresult);
	
	lresult=new JLabel("Result::");
	add(lresult);
	
	tresult=new JTextField(10);
	tresult.setEditable(false);
	add(tresult);
	setVisible(true);
	initialize();
	setDefaultCloseOperation(EXIT_ON_CLOSE);
}//constructor

public void  initialize(){
	System.out.println("AllStmtsTest:initialize()");
	try{
		//create jdbc con
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		//create Simple statment
		st=con.createStatement();
		//send and execute SQL Query
		rs1=st.executeQuery(GET_ALL_SNOS);
		//copy snos to ComboBox from ResultSet
		while(rs1.next()){
			tno.addItem(rs1.getInt(1));
		}//while
		//close ResultSet
		rs1.close();
		//create PreparedStatement obj
		ps=con.prepareStatement(GET_STUDENT_BY_SNO);
		//create CallableStatement obj
		cs=con.prepareCall(CALL_PROCEDURE);
		cs.registerOutParameter(4,Types.VARCHAR);
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
	
}//initialize()

	@Override
	public void actionPerformed(ActionEvent ae) {
		int no=0;
		
		System.out.println("actionPerformed(-)");
		try{
		if(ae.getSource()==bdetails){
			//get Selected item from comboBox
			no=(Integer)tno.getSelectedItem();
			// set selecte item as the query param value
			ps.setInt(1,no);
			//execute Query
			rs2=ps.executeQuery();
			//set ResultSet values to text boxes
			if(rs2.next()){
				tname.setText(rs2.getString(2));
				tm1.setText(rs2.getString(3));
				tm2.setText(rs2.getString(4));
				tm3.setText(rs2.getString(5));
			}//if
			rs2.close();
		  }//if
		else{
			System.out.println("result button is clicked");
			//gather marks from text boxes
			m1=Integer.parseInt(tm1.getText());
			m2=Integer.parseInt(tm2.getText());
			m3=Integer.parseInt(tm3.getText());
			//set values to Procedure IN params
			cs.setInt(1,m1); cs.setInt(2,m2); cs.setInt(3,m3);
			//call pl/sql procedure
			cs.execute();
			//gather result from OUT param and set Text box
			tresult.setText(cs.getString(4));
		}//else
		}//try
		catch(SQLException se ){
			se.printStackTrace();
		}
	}//method
	
	public static void main(String[] args) {
		System.out.println("AllStmtsTest:main(-)");
		AllStmtsTest test=new  AllStmtsTest();
	}//main
}//class
