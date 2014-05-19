/**
 * 
 */
package com.psbk.dikjar.service;

import java.util.List;

import com.psbk.dikjar.dao.DosenDAO;
import com.psbk.dikjar.dao.UserDAO;
import com.psbk.dikjar.model.Dosen;
import com.psbk.dikjar.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Apey
 *
 */

@Service("DosenService")
@Transactional(readOnly = true)
public class DosenService {

	@Autowired
	DosenDAO dosenDAO;
	
	@Autowired
	UserDAO userDAO;

	/**
	 * @return the dosenDAO
	 */
	public DosenDAO getDosenDAO() {
		return dosenDAO;
	}

	/**
	 * @param dosenDAO
	 *            the dosenDAO to set
	 */
	public void setDosenDAO(DosenDAO dosenDAO) {
		this.dosenDAO = dosenDAO;
	}

	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * 
	 * @param dosen
	 */
	@Transactional(readOnly = false)
	public void addDosen(Dosen dosen) {
		getDosenDAO().addDosen(dosen);
	}

	/**
	 * 
	 * @param dosen
	 */
	@Transactional(readOnly = false)
	public void updateDosen(Dosen dosen) {
		getDosenDAO().updateDosen(dosen);
	}

	/**
	 * 
	 * @param dosen
	 */
	@Transactional(readOnly = false)
	public void deleteDosen(Dosen dosen) {
		getDosenDAO().deleteDosen(dosen);
	}
	
	/**
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void addUser(Users user) {
		getUserDAO().addUser(user);
	}

	/**
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void updatetUser(Users user) {
		getUserDAO().updateUser(user);
	}

	/**
	 * 
	 * @param user
	 */
	@Transactional(readOnly = false)
	public void deleteUser(Users user) {
		getUserDAO().deleteUser(user);
	}

	/**
	 * 
	 * @return
	 */
	public List<Dosen> getDosens() {
		return getDosenDAO().getDosens();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Dosen getDosenById(String id) {
		return getDosenDAO().getDosenById(id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Users getUserById(String id) {
		return getUserDAO().getUsersByEmail(id);
	}
	
}
