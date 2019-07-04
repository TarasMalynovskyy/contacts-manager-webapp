package com.ivvysoft.cmw.repository;

import java.sql.SQLException;

import com.ivvysoft.cmw.model.User;

public interface UserRepository {

	void create(final User user) throws SQLException;

	User findByUserName(final String userName);

}
