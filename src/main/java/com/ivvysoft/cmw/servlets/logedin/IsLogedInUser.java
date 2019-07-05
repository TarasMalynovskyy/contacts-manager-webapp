package com.ivvysoft.cmw.servlets.logedin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class IsLogedInUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected boolean doCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			request.getRequestDispatcher("/").forward(request, response);
			return false;
		}
		return true;
	}
}
