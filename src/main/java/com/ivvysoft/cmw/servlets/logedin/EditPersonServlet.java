package com.ivvysoft.cmw.servlets.logedin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ivvysoft.cmw.model.Person;
import com.ivvysoft.cmw.model.User;
import com.ivvysoft.cmw.repository.PersonRepository;
import com.ivvysoft.cmw.repository.PersonRepositoryImpl;

@WebServlet("/editPerson")
public class EditPersonServlet extends IsLogedInUser {

	private static final long serialVersionUID = 1L;

	private PersonRepository personRepository = new PersonRepositoryImpl();

	public EditPersonServlet() {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		final Person person = new Person();
		HttpSession session = request.getSession();
		final User user = (User) session.getAttribute("user");
		
		person.setId(Integer.parseInt(request.getParameter("id")));
		person.setUser(user);
		person.setFirstName(request.getParameter("firstName"));
		person.setLastName(request.getParameter("lastName"));
		person.setPhone(request.getParameter("phone"));
		person.setEmail(request.getParameter("email"));
		final Person p = personRepository.edit(person);

		request.setAttribute("p", p);
		RequestDispatcher dispatcher = request.getRequestDispatcher("loginedUser\\edit-person.jsp");
		dispatcher.forward(request, response);
	}
}
