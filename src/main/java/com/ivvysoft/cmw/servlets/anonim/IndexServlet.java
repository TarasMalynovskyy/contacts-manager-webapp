package com.ivvysoft.cmw.servlets.anonim;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ivvysoft.cmw.model.User;
import com.ivvysoft.cmw.servlets.logedin.IsLogedInUser;

@WebServlet("/indexServlet")
public class IndexServlet extends IsLogedInUser {

	private static final long serialVersionUID = 1L;

	public IndexServlet() {
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		final User user = (User) session.getAttribute("user");
		if (user == null) {
			request.getRequestDispatcher("/index-form.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/main").forward(request, response);
		}
	}
}
