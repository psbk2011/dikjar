package com.psbk.dikjar.dao;

import java.util.List;

import com.psbk.dikjar.model.Dokumen;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * Dokumen DAO
 * 
 */

@Repository
public class DokumenDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addDokumen(Dokumen dokumen){
		getSessionFactory().getCurrentSession().save(dokumen);
	}
	
	public void updateDokumen(Dokumen dokumen){
		getSessionFactory().getCurrentSession().update(dokumen);
	}
	
	public void deleteDokumen(Dokumen dokumen){
		getSessionFactory().getCurrentSession().delete(dokumen);
	}
	
	public Dokumen getDokumenById(String id){
		List list = getSessionFactory().getCurrentSession().createQuery("from Dokumen where id_dokumen=?").setParameter(0, id).list();
		return (Dokumen)list.get(0);
	}
	
	public List<Dokumen> getDokumens(){
		List list = getSessionFactory().getCurrentSession().createQuery("from Dokumen").list();
		return list;
	}
	
	public String getMaxDokumenId(){
		Criteria crit = getSessionFactory().getCurrentSession().createCriteria(Dokumen.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.max("id_dokumen"));
		crit.setProjection(projList);
		List results = crit.list();
		
		return results.get(0).toString();
	}
}
