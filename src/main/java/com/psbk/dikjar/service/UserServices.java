/**
 * 
 */
package com.psbk.dikjar.service;

import java.util.List;

import com.psbk.dikjar.dao.UserDAO;
import com.psbk.dikjar.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Apey
 *
 */

@Service("UserService")
@Transactional(readOnly = true)
public class UserServices {

	@Autowired
	UserDAO userDAO;

	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO
	 *            the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * 
	 * @param users
	 */
	@Transactional(readOnly = false)
	public void addUsers(Users users) {
		getUserDAO().addUser(users);
	}

	/**
	 * 
	 * @param users
	 */
	@Transactional(readOnly = false)
	public void updateUsers(Users users) {
		getUserDAO().updateUser(users);
	}

	/**
	 * 
	 * @param users
	 */
	@Transactional(readOnly = false)
	public void deleteUsers(Users users) {
		getUserDAO().deleteUser(users);
	}

	/**
	 * Query hibernate
	 */

	/**
	 * 
	 * @param id_dosen
	 * @return
	 */
	public Users getUsersByEmail(String id_dosen) {
		return getUsersByEmail(id_dosen);
	}

	/**
	 * 
	 * @return
	 */
	public List<Users> getUsers() {
		return getUserDAO().getUsers();
	}

}
