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

@WebServlet("/findPersonById")
public class FindPersonByIdServlet extends IsLogedInUser {

	private static final long serialVersionUID = 1L;

	private PersonRepository personRepository = new PersonRepositoryImpl();

	public FindPersonByIdServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!doCheck(request, response)) {
			return;
		}
		request.getRequestDispatcher("loginedUser\\find-person-by-id-form.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		final int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		final User user = (User) session.getAttribute("user");
		final Person person = personRepository.findByUserId(user, id);

		if (person != null) {
			request.setAttribute("person", person);
			request.getRequestDispatcher("loginedUser\\edit-person-form.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "No person with such ID");
			request.getRequestDispatcher("loginedUser\\find-person-by-id-form.jsp").forward(request, response);
		}

	}
}
