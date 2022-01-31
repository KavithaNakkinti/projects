
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/regurl")
public class Registration_App extends HttpServlet {
	private static final String INSERT_DETAILS="INSERT INTO LOGIN VALUES(?,?,?,?)";
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
		PreparedStatement ps=con.prepareStatement(INSERT_DETAILS)){
	//reading the inputs from the form data
	String uname=req.getParameter("uname");
	String pword=req.getParameter("pword");
	String mail=req.getParameter("mail");
	String role=req.getParameter("role");

	//setting the parameters to the query
	ps.setString(1,uname);
	ps.setString(2, pword);
	ps.setString(3, mail);
	ps.setString(4,role);
	int count=0;
	if(ps!=null)
	 count=ps.executeUpdate();
			if(count!=0) {
				pw.println("<h1 align='center'>Successfully Registered</h1>");
				pw.println("<br><br><p style='text-align:center'><a  href='Login.html'>Login </a>");
				RequestDispatcher rd=req.getRequestDispatcher("/role.html");
				rd.include(req, res);
				}else
					pw.println("<h1 align='center'>registraction unsuccessfull</h1>");
		    pw.println("<p style='text-align:center'><a  href='Reg.html'>Registration Form </a>");
	     //close stream
	    pw.close();
}
catch(SQLException se) {
	se.printStackTrace();
	}
}
}

