package com.psbk.dikjar.dao;

import java.util.List;

import com.psbk.dikjar.model.BeritaAcara;
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
public class BeritaAcaraDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addDokumen(BeritaAcara dokumen){
		getSessionFactory().getCurrentSession().save(dokumen);
	}
	
	public void updateDokumen(BeritaAcara dokumen){
		getSessionFactory().getCurrentSession().update(dokumen);
	}
	
	public void deleteDokumen(BeritaAcara dokumen){
		getSessionFactory().getCurrentSession().delete(dokumen);
	}
	
	public BeritaAcara getDokumenById(String id){
		List list = getSessionFactory().getCurrentSession().createQuery("from BeritaAcara where id_dokumen=?").setParameter(0, id).list();
		return (BeritaAcara)list.get(0);
	}
	
	public List<BeritaAcara> getDokumens(){
		List list = getSessionFactory().getCurrentSession().createQuery("from BeritaAcara").list();
		return list;
	}
	
	public String getMaxDokumenId(){
		Criteria crit = getSessionFactory().getCurrentSession().createCriteria(BeritaAcara.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.max("id_dokumen"));
		crit.setProjection(projList);
		List results = crit.list();
		
		return results.get(0).toString();
	}
}
