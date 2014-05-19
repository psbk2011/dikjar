/**
 * 
 */
package com.psbk.dikjar.dao;

import java.util.List;

import com.psbk.dikjar.model.Dosen;
import com.psbk.dikjar.model.Users;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Apey
 *
 */
@Repository
public class DosenDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 * @param Dosen
	 */
	public void addDosen(Dosen dosen) {
		getSessionFactory().getCurrentSession().save(dosen);
	}

	/**
	 * 
	 * @param Dosens
	 */
	public void updateDosen(Dosen dosen) {
		getSessionFactory().getCurrentSession().update(dosen);
	}

	/**
	 * 
	 * @param Dosens
	 */
	public void deleteDosen(Dosen dosen) {
		getSessionFactory().getCurrentSession().delete(dosen);
	}

	/**
	 * Query hibernate
	 */
	
	/**
	 * 
	 * @return
	 */
	public List<Dosen> getDosens(){
		List list = getSessionFactory().getCurrentSession().createQuery("from Dosen").list();
		return list;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Dosen getDosenById(String id){
		List list = getSessionFactory().getCurrentSession().createQuery("from Dosen where id_dosen=?").setParameter(0, id).list();
		return (Dosen)list.get(0);
	}
	

}
