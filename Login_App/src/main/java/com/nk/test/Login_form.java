package com.nk.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loginurl")
public class Login_form extends HttpServlet {
	public static final String GET_DETAILS="SELECT COUNT(*) FROM LOGIN WHERE USERNAME=? AND PASSWORD=?";

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
	//write  JDBC  code
	try(Connection con=DriverManager.getConnection(url,username,password);
			PreparedStatement ps=con.prepareStatement(GET_DETAILS)){
		//reading the inputs from the form data
		
		String uname=req.getParameter("uname");
		String pword=req.getParameter("pword");
	
	//creatig cookies
	Cookie ck=new Cookie(uname,pword);
	res.addCookie(ck);
		//setting the parameters to the query
				ps.setString(1,uname);
		ps.setString(2, pword);
		ResultSet rs=null;
		if(ps!=null)
			 rs=ps.executeQuery();
				if(rs!=null) {
					rs.next();
					int count=rs.getInt(1);
					if(count==0) {
						System.out.println("INVALID CREDENTIALS");
						
						  pw.println("<h1 align='center'>login Denined</h1>"); pw.
						  println("<br><br><p style='text-align:center'><a  href='Login.html'>Login </a>"
						  );
						 	
						
					}else {
						pw.println("<h1 align='center'>login successfull</h1>");
						RequestDispatcher rd=req.getRequestDispatcher("role.html");
				        rd.forward(req, res);
			}//if
				}
	
		 
		    pw.println("<p style='text-align:center'><a  href='Reg.html'>Registration </a>");
		     //close stream
		    pw.close();
		} //try
	catch (SQLException e) {
		e.printStackTrace();
}
 }
}
