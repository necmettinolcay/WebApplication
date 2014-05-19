package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductOptionServlet
 */
@WebServlet("/ProductOptionServlet")
public class ProductOptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductOptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchword = request.getParameter("keyword");
		String selectedValue1 = request.getParameter("category");
		String selectedValue2 =   request.getParameter("cost1");
		String selectedValue3 = request.getParameter("cost2");
		
		String category;
		String cost1;
		String cost2;
		
		switch(selectedValue1){
		case "Ayakkabi":
			category = "Ayakkabi";
			break;
		case "Kaban ve Mont":
			category = "Kaban ve Mont";
			break;
		
		default:
			category = "hepsi";
			break;
	}
		
		switch(selectedValue2){
			case "10":
				cost1 = "10";
				break;
			case "30":
				cost1 = "30";
				break;
			case "50":
				cost1 = "50";
				break;
			default:
				cost1 = "0";
				break;
		}
		switch(selectedValue3){
		case "50":
			cost2 = "50";
			break;
		case "100":
			cost2 = "100";
			break;
		case "200":
			cost2 = "200";
			break;
		default:
			cost2 = "201";
			break;
	}
		ProductBean selectDetails = RunUtils.selectDetailsProduct(category, cost1, cost2,searchword);
		
		request.setAttribute("selectCategory", selectDetails);
		 String address = "category.jsp";
		    RequestDispatcher dispatcher =
		      request.getRequestDispatcher(address);
		    dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
