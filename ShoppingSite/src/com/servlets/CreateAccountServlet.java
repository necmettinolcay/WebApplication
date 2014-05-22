package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int sonuc;
		String password1 = request.getParameter("password1");
	    
		if(username.isEmpty() && name.isEmpty() && surname.isEmpty() && email.isEmpty() && password.isEmpty()){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/kayit.html");
			PrintWriter out1= response.getWriter();
			
			out1.println("<script language=javascript>alert('Alanlar bos birakilamaz!')</script>");
			
			
			rd.include(request, response);
			
		}
		else{
			if(password.equals(password1)){
			sonuc = RunUtils.insertPerson(username, name, surname, email, password);
			
			
			if(sonuc == 0){
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/kayit.html");
				PrintWriter out1= response.getWriter();
				
				out1.println("<script language=javascript>alert('Kayit yapilamadi lutfen tekrar deneyiniz')</script>");
				
				
				rd.include(request, response);
			}
			else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/giris.html");
				PrintWriter out1= response.getWriter();
				
				out1.println("<script language=javascript>alert('Kayit basari ile yapildi lutfen giris yapiniz')</script>");
				
				
				rd.include(request, response);
				
			}
			
			
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/kayit.html");
			PrintWriter out1= response.getWriter();
			
			out1.println("<script language=javascript>alert('Sifre ve sifre tekrar ayni olmak zorunda!')</script>");
			
			
			rd.include(request, response);
			
			
		}
	   }
	}

}
