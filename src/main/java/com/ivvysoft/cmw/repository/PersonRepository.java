package com.ivvysoft.cmw.repository;

import java.util.List;

import com.ivvysoft.cmw.model.Person;
import com.ivvysoft.cmw.model.User;

public interface PersonRepository {

	List<Person> findByFirstOrLastName(final User user, final String firstName, final String lastName);

	List<Person> showAll(final User user);

	void create(final Person person);

	void delete(final User user, final int id);

	Person edit(final Person person);
	
	Person findByUserId(final User user, final int id);

}
