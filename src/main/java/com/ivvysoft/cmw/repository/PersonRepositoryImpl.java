package com.ivvysoft.cmw.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ivvysoft.cmw.model.Person;
import com.ivvysoft.cmw.model.Person_;
import com.ivvysoft.cmw.model.User;
import com.ivvysoft.cmw.util.HibernateSessionFactoryUtil;

public class PersonRepositoryImpl implements PersonRepository {

	@Override
	public  List<Person> findByFirstOrLastName(final User user, final String firstName, final String lastName) {
		try {
			HibernateSessionFactoryUtil.getSession().beginTransaction();

			CriteriaBuilder builder = HibernateSessionFactoryUtil.getSession().getCriteriaBuilder();
			CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
			Root<Person> root = criteria.from(Person.class);

			final ParameterExpression<String> firstNameParameter = builder.parameter(String.class);
			final ParameterExpression<String> lastNameParameter = builder.parameter(String.class);
			final ParameterExpression<User> userIdParameter = builder.parameter(User.class);

			final Predicate firstNameEquals = builder.equal(root.get(Person_.firstName), firstNameParameter);
			final Predicate lastNameEquals = builder.equal(root.get(Person_.lastName), lastNameParameter);
			final Predicate userIdEquals = builder.equal(root.get(Person_.user), userIdParameter);

			criteria.select(root).where(builder.and(builder.or(firstNameEquals, lastNameEquals)), userIdEquals);

			final List<Person> persons = (List<Person>) HibernateSessionFactoryUtil.getSession().createQuery(criteria)
					.setParameter(firstNameParameter, firstName).setParameter(lastNameParameter, lastName)
					.setParameter(userIdParameter, user).getResultList();

			return persons;
		} catch (NoResultException e) {
			return null;
		} finally {
			HibernateSessionFactoryUtil.getSession().getTransaction().commit();
		}
	}

	@Override
	public List<Person> showAll(final User user) {
		try {
			HibernateSessionFactoryUtil.getSession().beginTransaction();
			CriteriaBuilder builder = HibernateSessionFactoryUtil.getSession().getCriteriaBuilder();
			CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
			Root<Person> root = criteria.from(Person.class);

			final ParameterExpression<User> userIdParameter = builder.parameter(User.class);
			final Predicate userIdEquals = builder.equal(root.get(Person_.user), userIdParameter);

			criteria.select(root).where(userIdEquals);
			criteria.orderBy(builder.desc(root.get(Person_.id)));
			final List<Person> persons = HibernateSessionFactoryUtil.getSession().createQuery(criteria)
					.setParameter(userIdParameter, user).getResultList();

			return persons;
		} finally {
			HibernateSessionFactoryUtil.getSession().getTransaction().commit();
		}
	}

	@Override
	public void create(final Person person) {
		try {
			HibernateSessionFactoryUtil.getSession().beginTransaction();
			HibernateSessionFactoryUtil.getSession().save(person);
		} finally {
			HibernateSessionFactoryUtil.getSession().getTransaction().commit();
		}
	}

	@Override
	public void delete(final User user, final int id) {
		try {
			HibernateSessionFactoryUtil.getSession().beginTransaction();
			HibernateSessionFactoryUtil.getSession().delete(findByIdWithoutTransaction(user, id));
		} finally {
			HibernateSessionFactoryUtil.getSession().getTransaction().commit();
		}
	}

	@Override
	public Person edit(final Person person) {
		try {
			HibernateSessionFactoryUtil.getSession().beginTransaction();
			HibernateSessionFactoryUtil.getSession().update(person);
			final Person p = HibernateSessionFactoryUtil.getSession().get(Person.class, person.getId());
			
			return p;
		} finally {
			HibernateSessionFactoryUtil.getSession().getTransaction().commit();
		}
	}
	
	@Override
	public Person findByUserId(final User user, final int id) {
		try {
			HibernateSessionFactoryUtil.getSession().beginTransaction();
			return findByIdWithoutTransaction(user, id);
		} finally {
			HibernateSessionFactoryUtil.closeCurrentSession();
		}
	}

	private Person findByIdWithoutTransaction(final User user, final int id) {
		try {
			CriteriaBuilder builder = HibernateSessionFactoryUtil.getSession().getCriteriaBuilder();
			CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
			Root<Person> root = criteria.from(Person.class);

			final ParameterExpression<Integer> personIdParameter = builder.parameter(Integer.class);
			final ParameterExpression<User> userIdParameter = builder.parameter(User.class);
			final Predicate personIdEquals = builder.equal(root.get(Person_.id), personIdParameter);
			final Predicate userIdEquals = builder.equal(root.get(Person_.user), userIdParameter);

			criteria.select(root).where(builder.and(personIdEquals), (userIdEquals));

			final Person person = HibernateSessionFactoryUtil.getSession().createQuery(criteria)
					.setParameter(personIdParameter, id).setParameter(userIdParameter, user).getSingleResult();

			return person;
		} catch (NoResultException e) {
			return null;
		}
	}
}