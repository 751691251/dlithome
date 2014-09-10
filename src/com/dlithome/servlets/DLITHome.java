package com.dlithome.servlets;

import java.io.IOException;
import java.io.PrintWriter;  

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dlithome.service.ServiceDispatcher;
import com.dlithome.utils.*;

/**
 * Servlet implementation class DLITHome
 */
@WebServlet("/dlithome")
public class DLITHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DLITHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/** 
	     *
	     */  
	
	      
	        String signature = request.getParameter("signature");  
	      
	        String timestamp = request.getParameter("timestamp");  
	        
	        String nonce = request.getParameter("nonce");  
	      
	        String echostr = request.getParameter("echostr");  
	  
	        PrintWriter out = response.getWriter();  
	        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
	            out.print(echostr);  
	        }  

	        out.close();  
	        out = null;  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        
  
        String respMessage = ServiceDispatcher.processRequest(request);  
          
        System.out.println("respMessage is " + respMessage);
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.flush();
        out.close();  
	}

}
