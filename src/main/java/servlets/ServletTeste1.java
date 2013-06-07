package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "st1", urlPatterns = { "/st1" })
public class ServletTeste1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletTeste1() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		writer.write("<html><body>GET/POST response");
		request.login("daniel", "daniel");
		System.out.println(request.getUserPrincipal().getName());
	}

}
