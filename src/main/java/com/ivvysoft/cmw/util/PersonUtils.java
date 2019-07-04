package com.ivvysoft.cmw.util;

import java.util.List;

import com.ivvysoft.cmw.model.Person;

public class PersonUtils {

	public static String personsToString(final List<Person> persons) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < persons.size(); i++) {
			int id = persons.get(i).getId();
			String firstName = persons.get(i).getFirstName();
			String lastName = persons.get(i).getLastName();
			String phone = persons.get(i).getPhone();
			String email = persons.get(i).getEmail();

			sb.append ("ID: " + id + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nPhone: " + phone
					+ "\nEmail: " + email + "\n\n");
		}

		return sb.toString();
	}

	public static void personToString(final Person person) {
			System.out.println("New ID: " + person.getId());
			System.out.println("New First Name: " + person.getFirstName());
			System.out.println("New Last Name: " + person.getLastName());
			System.out.println("New Phone: " + person.getPhone());
			System.out.println("New Email: " + person.getEmail());
			System.out.println();
	}
}
