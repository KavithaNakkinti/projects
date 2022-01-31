package com.nk.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Student_enroll extends HttpServlet {
	private static final String INSERT_DETAILS="INSERT INTO STUDENTENROLL VALUES(?,?,?)";
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//get writer obj
	PrintWriter pw=res.getWriter();
	//set content type
	res.setContentType("text/html");
	//read the form input
	String name=req.getParameter("sname");
		int id=Integer.parseInt(req.getParameter("id"));
	String branch=req.getParameter("branch");
	try {
	//load drivermanager class
	Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	catch(ClassNotFoundException cnf){
		cnf.printStackTrace();
	}
	//establish the connection
	try{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		PreparedStatement ps=con.prepareStatement(INSERT_DETAILS);
		//Set values to the query
		
		if(ps!=null) {
		ps.setString(1, name);
		ps.setInt(2,id);
	
		ps.setString(3,branch);
	}else {
		System.out.println("ps not created");
	}
		if(ps!=null) {
			int result=ps.executeUpdate();
			
			if(result!=0)
				pw.write("<h1 align='center'>student enrollment successfully<h1>");

				
			
		}
		
	}
	catch(SQLException se) {
		se.printStackTrace();
	}
}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
}
