/**
 * 
 */
package com.psbk.dikjar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psbk.dikjar.dao.ScheduleDAO;
import com.psbk.dikjar.model.Schedule;

/**
 * @author Apey
 *
 */
@Service("ScheduleService")
@Transactional(readOnly = true)
public class ScheduleService {

	@Autowired
	ScheduleDAO scheduleDAO;

	/**
	 * @return the scheduleDAO
	 */
	public ScheduleDAO getScheduleDAO() {
		return scheduleDAO;
	}

	/**
	 * @param scheduleDAO the scheduleDAO to set
	 */
	public void setScheduleDAO(ScheduleDAO scheduleDAO) {
		this.scheduleDAO = scheduleDAO;
	}
	
	@Transactional(readOnly = false)
	public void addShedule(Schedule schedule){
		getScheduleDAO().addSchedule(schedule);
	}
	
	@Transactional(readOnly = false)
	public void updateShedule(Schedule schedule){
		getScheduleDAO().updateSchedule(schedule);
	}
	
	@Transactional(readOnly = false)
	public void deleteShedule(Schedule schedule){
		getScheduleDAO().deleteSchedule(schedule);
	}
	
	public List<Schedule> getSchedules(){
		return getScheduleDAO().getSchedules();
	}
	
	public Schedule getScheduleById(String id){
		return getScheduleDAO().getScheduleById(id);
	}
	
}
