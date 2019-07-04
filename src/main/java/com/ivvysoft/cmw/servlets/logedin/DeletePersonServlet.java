package com.ivvysoft.cmw.servlets.logedin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ivvysoft.cmw.model.User;
import com.ivvysoft.cmw.repository.PersonRepository;
import com.ivvysoft.cmw.repository.PersonRepositoryImpl;

@WebServlet("/deletePerson")
public class DeletePersonServlet extends IsLogedInUser {

	private static final long serialVersionUID = 1L;

	private PersonRepository personRepository = new PersonRepositoryImpl();

	public DeletePersonServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		request.getRequestDispatcher("loginedUser\\delete-person-form.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		final int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		final User user = (User) session.getAttribute("user");
		personRepository.delete(user, id);

		request.getRequestDispatcher("loginedUser\\delete-person.jsp").forward(request, response);
	}
}
