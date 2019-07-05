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

@WebServlet("/findPersonByName")
public class FindPersonByNameServlet extends IsLogedInUser {

	private static final long serialVersionUID = 1L;

	private PersonRepository personRepository = new PersonRepositoryImpl();

	public FindPersonByNameServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!doCheck(request, response)) {
			return;
		}
		request.getRequestDispatcher("loginedUser\\find-person-by-name-form.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		final String firstName = request.getParameter("firstName");
		final String lastName = request.getParameter("lastName");
		HttpSession session = request.getSession();
		final User user = (User) session.getAttribute("user");
		final List<Person> persons = personRepository.findByFirstOrLastName(user, firstName, lastName);

		if (persons != null) {
			request.setAttribute("persons", persons);
			request.getRequestDispatcher("loginedUser\\find-person-by-name.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "No person with such name");
			request.getRequestDispatcher("loginedUser\\find-person-by-name-form.jsp").forward(request, response);
		}

	}
}
