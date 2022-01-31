package com.nk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeCycle extends HttpServlet {
	static {
		System.out.println("Static Block excuted");
	}
	public LifeCycle(){
		System.out.println("0-param constractor executed");
	}
	@Override
	public void init(ServletConfig cg) throws ServletException {
		System.out.println("init method");

		/*
		 * System.out.println("jdbc url"+cg.getInitParameter("jdbcurl"));
		 * System.out.println("jdbc user"+cg.getInitParameter("username"));
		 * System.out.println("jdbc password"+cg.getInitParameter("password"));
		 */
	}
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	PrintWriter pw=res.getWriter();
	res.setContentType("text/html");
	System.out.println("service method ");
	
	pw.println("<h1> Life cycle of Servlet method is executed</>");
	
	
pw.close();
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
 public void destroy(){
	System.out.println(" destroy method id excuted");
}
}
