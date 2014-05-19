/**
 * 
 */
package com.psbk.dikjar.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.psbk.dikjar.model.Dosen;
import com.psbk.dikjar.model.Schedule;

/**
 * @author Apey
 *
 */
@Repository
public class ScheduleDAO {

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
	 * @param Schedule
	 */
	public void addSchedule(Schedule schedule) {
		getSessionFactory().getCurrentSession().save(schedule);
	}

	/**
	 * 
	 * @param Schedule
	 */
	public void updateSchedule(Schedule schedule) {
		getSessionFactory().getCurrentSession().update(schedule);
	}

	/**
	 * 
	 * @param Schedule
	 */
	public void deleteSchedule(Schedule schedule) {
		getSessionFactory().getCurrentSession().delete(schedule);
	}
	
	/**
	 * Query hibernate
	 */
	
	/**
	 * 
	 * @return
	 */
	public List<Schedule> getSchedules(){
		List list = getSessionFactory().getCurrentSession().createQuery("from Schedule").list();
		return list;
	}
	
	public Schedule getScheduleById(String id){
		List list = getSessionFactory().getCurrentSession().createQuery("from Schedule where id_agenda=?").setParameter(0, id).list();
		return (Schedule) list.get(0);
	}
}
