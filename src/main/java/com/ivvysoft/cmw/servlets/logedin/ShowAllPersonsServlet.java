package com.ivvysoft.cmw.servlets.logedin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ivvysoft.cmw.model.Person;
import com.ivvysoft.cmw.model.User;
import com.ivvysoft.cmw.repository.PersonRepository;
import com.ivvysoft.cmw.repository.PersonRepositoryImpl;

@WebServlet("/showAllPersons")
public class ShowAllPersonsServlet extends IsLogedInUser {

	private static final long serialVersionUID = 1L;

	private PersonRepository personRepository = new PersonRepositoryImpl();

	public ShowAllPersonsServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		final User user = (User) session.getAttribute("user");
		
			final List<Person> persons = personRepository.showAll(user);
			request.setAttribute("persons_list", persons);
			request.getRequestDispatcher("loginedUser\\show-all-persons.jsp").forward(request, response);
	}
}
