package com.ivvysoft.cmw.servlets.anonim;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.ivvysoft.cmw.model.User;
import com.ivvysoft.cmw.repository.UserRepository;
import com.ivvysoft.cmw.repository.UserRepositoryImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
	}

	private UserRepository userRepository = new UserRepositoryImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("anonimUser\\login-form.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String newUserName = request.getParameter("userName");
		final String pass = request.getParameter("pass");

		final User user = userRepository.findByUserName(newUserName);

		if (user != null) {
			
			if (BCrypt.checkpw(pass, user.getPassword())) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/main");

			} else {
				request.setAttribute("errorMessage", "Password is wrong");
				request.getRequestDispatcher("anonimUser\\login-form.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("errorMessage", "Username is wrong");
			request.getRequestDispatcher("anonimUser\\login-form.jsp").forward(request, response);
		}
	}
}
