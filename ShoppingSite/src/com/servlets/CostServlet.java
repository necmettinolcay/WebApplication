package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CostServlet
 */
@WebServlet("/CostServlet")
public class CostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cost = request.getParameter("param1");
		String costOld = request.getParameter("param3");
		float value = 0;
		float value1 = 0;
		try{
			value = Float.parseFloat(cost);
		}catch (NumberFormatException e){
		       System.out.println("not a number"); 
		   }
		try{
			value1 = Float.parseFloat(costOld);
		}catch (NumberFormatException e){
		       System.out.println("not a number"); 
		   }
		
		
		request.getSession().setAttribute("cost", value+value1 );
		request.setAttribute("cost", value+value1);
		request.setAttribute("path", request.getParameter("param2"));
		request.setAttribute("price", request.getParameter("param1"));
		ProductNameBean productName = RunUtils.showProduct(request.getParameter("param2"), request.getParameter("param1") );
		
		//-----------------------------------
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<String> previousItems =
		(ArrayList<String>)session.getAttribute("previousItems");
		if (previousItems == null) {
		previousItems = new ArrayList<String>();
		}
		String newItem = request.getParameter("param2");
		if ((newItem != null) &&
		(!newItem.trim().equals(""))) {
		previousItems.add(newItem);
		}
		session.setAttribute("previousItems", previousItems);
		//-----------------------------------
		
		
		
		
		request.setAttribute("productName", productName);
		String address = "product.jsp";
	    RequestDispatcher dispatcher =
	      request.getRequestDispatcher(address);
	    dispatcher.forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
