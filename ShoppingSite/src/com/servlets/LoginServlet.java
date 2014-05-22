package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		boolean newbie = true;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie c: cookies){
				if( ( c.getName().equals("username") && c.getValue().equals(username)) ){
					
						newbie = false;
						break;
				}
				
				
			}
		}
		String title ;
		if(newbie){
			Cookie returnVisitorCookie = new Cookie("username", username);
			Cookie returnVisitorCookie1 = new Cookie("password", password);
			returnVisitorCookie.setMaxAge(60*60*24*365);
			returnVisitorCookie1.setMaxAge(60*60*24*365);
			response.addCookie(returnVisitorCookie);
			response.addCookie(returnVisitorCookie1);
			title = "Welcome Aboard";
			
		}else{
			title = "Welcome back";
		}
		
		UserNameBean nameBean = RunUtils.getUserName(username, password);
		//String name = nameBean.getName();
		
		if(nameBean.getName() != null){
			request.setAttribute("Title", title);
			//request.setAttribute("userName", nameBean);
			request.getSession().setAttribute("userName", nameBean);
			String address = "user.jsp";
		    RequestDispatcher dispatcher =
		      request.getRequestDispatcher(address);
		    dispatcher.forward(request, response);
			
		}
		else{
			  
			
		    RequestDispatcher rd = getServletContext().getRequestDispatcher("/giris.html");
			PrintWriter out1= response.getWriter();
			
			out1.println("<script language=javascript>alert('Kullanici adi veya sifre yanlis lutfen tekrar giriniz')</script>");
			
			
			rd.include(request, response);
		}
		
		
	}

}
