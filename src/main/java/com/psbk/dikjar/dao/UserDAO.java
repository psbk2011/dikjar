/**
 * 
 */
package com.psbk.dikjar.dao;

import java.util.List;

import com.psbk.dikjar.model.Users;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Apey
 *
 */

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 
	 * @param users
	 */
	public void addUser(Users users){
		getSessionFactory().getCurrentSession().save(users);
	}
	
	/**
	 * 
	 * @param users
	 */
	public void updateUser(Users users){
		getSessionFactory().getCurrentSession().update(users);
	}
	
	/**
	 * 
	 * @param users
	 */
	public void deleteUser(Users users){
		getSessionFactory().getCurrentSession().delete(users);
	}
	
	
	/**
	 * Query hibernate
	 */
	
	/**
	 * 
	 * @param id_dosen
	 * @return
	 */
	public Users getUsersByEmail(String id_dosen){
		List list = getSessionFactory().getCurrentSession().createQuery("form Users where id_dosen=?").setParameter(0, id_dosen).list();
		return (Users)list.get(0);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Users> getUsers(){
		List list = getSessionFactory().getCurrentSession().createQuery("from Users").list();
		return list;
	}
}
