package com.nk.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/roleurl")
public class Role_App extends HttpServlet {
	private static final String INSERT_DETAILS="SELECT * FROM LOGIN WHERE USERNAME=? ";

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//get PrintWriter 
		PrintWriter pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");	
		//read form data
	ServletContext ct=getServletContext();
	//read the context params
	String driver=ct.getInitParameter("class");
	String url=ct.getInitParameter("url");
	String username=ct.getInitParameter("username");
	String password=ct.getInitParameter("password");
	//loading driver class name
	try {
	Class.forName(driver);

	}//try block

	catch(ClassNotFoundException cnf) {
		cnf.printStackTrace();
	}
	//reading cookie valkues
	Cookie cookie[]=req.getCookies();
	for(Cookie ck:cookie) {
		pw.println("<h1 align='center'> ---cookie values "+ck.getName()+" "+ck.getValue()+"-----</h1>");
	}
	//write  JDBC  code
	//reading the inputs from the form data
			String uname=req.getParameter("uname");
	try(Connection con=DriverManager.getConnection(url,username,password);
			PreparedStatement ps=con.prepareStatement(INSERT_DETAILS);){
		
				ps.setString(1,uname);
			 ResultSet rs=ps.executeQuery();
		while(rs.next()) {
		 pw.println("<h1 align='center'> ====="+rs.getString(4)+" welcome=====</h1>");
		 		}
		}catch(SQLException se) {
	se.printStackTrace();
	}
}
}
