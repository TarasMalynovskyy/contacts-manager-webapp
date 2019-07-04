package com.ivvysoft.cmw.servlets.logedin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ivvysoft.cmw.model.Person;
import com.ivvysoft.cmw.model.User;
import com.ivvysoft.cmw.repository.PersonRepository;
import com.ivvysoft.cmw.repository.PersonRepositoryImpl;

@WebServlet("/createPerson")
public class CreatePersonServlet extends IsLogedInUser {

	private static final long serialVersionUID = 1L;

	private PersonRepository personRepository = new PersonRepositoryImpl();

	public CreatePersonServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		request.getRequestDispatcher("loginedUser\\create-person-form.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		HttpSession session = request.getSession();
		final User user = (User) session.getAttribute("user");
		final Person person = new Person();
		person.setFirstName(request.getParameter("firstName"));
		person.setLastName(request.getParameter("lastName"));
		person.setPhone(request.getParameter("phone"));
		person.setEmail(request.getParameter("email"));
		person.setUser(user);

		personRepository.create(person);

		request.getRequestDispatcher("loginedUser\\create-person.jsp").forward(request, response);
	}
}
