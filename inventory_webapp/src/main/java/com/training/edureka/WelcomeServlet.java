package com.training.edureka;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */

public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		out.println("Name: "+name+"Email: "+email+"Mobile Number: "+mobile+"Address: "+address+"Password: "+password);
		out.println();
		
        ServletContext context = getServletContext();
        String dbUrl_context = context.getInitParameter("dburl");
        ServletConfig config=getServletConfig();
        String dbUrl = config.getInitParameter("url");
        String dbUser = config.getInitParameter("dbuser");
        String dbPwd = config.getInitParameter("dbpwd");
        out.println(dbUrl+" "+dbUser+ " "+dbPwd);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl_context, dbUser,dbPwd);
            PreparedStatement ps=con.prepareStatement("insert into user values(?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, address);
            ps.setString(5,password);
            int count=ps.executeUpdate();
            
            if(count>=1)
                out.println("<h1> Welcome to our World! Dear "+ name +"</h1>");
            else
                out.println("<h1> Something went wrong  </h1>");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String qs = request.getQueryString();
		
		String city = qs.split("&")[0];
		String place = qs.split("&")[1];
		
		out.println("Hola");
		out.println(city);
		out.println(place);
	}
}


  









