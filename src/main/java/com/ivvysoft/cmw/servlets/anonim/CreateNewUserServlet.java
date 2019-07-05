package com.ivvysoft.cmw.servlets.anonim;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.AssertionFailure;
import org.hibernate.exception.ConstraintViolationException;
import org.mindrot.jbcrypt.BCrypt;

import com.ivvysoft.cmw.model.User;
import com.ivvysoft.cmw.repository.UserRepository;
import com.ivvysoft.cmw.repository.UserRepositoryImpl;
import com.ivvysoft.cmw.servlets.logedin.IsLogedInUser;

@WebServlet("/createNewUser")
public class CreateNewUserServlet extends IsLogedInUser {

	private static final long serialVersionUID = 1L;

	private UserRepository userRepository = new UserRepositoryImpl();

	public CreateNewUserServlet() {
	}

	private String hashPassword(String plainTextPassword) {
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("anonimUser\\create-new-user-form.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String newUserName = request.getParameter("userName");
		final String pass = request.getParameter("pass");
		final String pass2 = request.getParameter("pass2");

		if (newUserName == null || newUserName.isEmpty() || pass == null || pass.isEmpty()) {
			request.setAttribute("errorMessage", "Invalid user name or password");
			request.getRequestDispatcher("anonimUser\\create-new-user-form.jsp").forward(request, response);
		} else if (pass.equals(pass2)) {
			final User user = new User();
			user.setUserName(newUserName);
			user.setPassword(hashPassword(pass));

			try {
				userRepository.create(user);
				request.setAttribute("successMessage", "User was created!");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			} catch (ConstraintViolationException | SQLException | AssertionFailure e) {
				request.setAttribute("errorMessage", "User name already exist, please try another!");
				request.getRequestDispatcher("anonimUser\\create-new-user-form.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("errorMessage", "The password does not match, try again");
			request.getRequestDispatcher("anonimUser\\create-new-user-form.jsp").forward(request, response);
		}
	}
}
