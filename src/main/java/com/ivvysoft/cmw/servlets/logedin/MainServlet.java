package com.ivvysoft.cmw.servlets.logedin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends IsLogedInUser {

	private static final long serialVersionUID = 1L;

	public MainServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!doCheck(request, response)) {
			return;
		}
		request.getRequestDispatcher("main-page.jsp").forward(request, response);
	}
}
