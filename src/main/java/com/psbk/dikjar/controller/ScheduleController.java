/**
 * 
 */
package com.psbk.dikjar.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ViewScoped;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.psbk.dikjar.model.Schedule;
import com.psbk.dikjar.service.ScheduleService;

/**
 * @author Apey
 *
 */
@ManagedBean
@ViewScoped
public class ScheduleController implements Serializable {

	/*
	 * Agenda Controller
	 */
	private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;

	private ScheduleEvent event = new DefaultScheduleEvent();

	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();
		try {
			for (Schedule schedule : getScheduleService()
					.getSchedules()) {
				
				/*
				 * For Start Event
				 */
				String tahun = schedule.getTangal_mulai().substring(0,
						4);
				String bulan = schedule.getTangal_mulai().substring(5,
						7);
				int tanggal = Integer.parseInt(schedule.getTangal_mulai().substring(
						8, 10))+1;
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				String dateInString = tanggal + "-" + bulan + "-" + tahun;
				Date dateMulai = formatter.parse(dateInString);
				
				String tahunSelesai = schedule.getTanggal_selesai().substring(0,
						4);
				String bulanSelesai = schedule.getTanggal_selesai().substring(5,
						7);
				int tanggalSelesai = Integer.parseInt(schedule.getTanggal_selesai().substring(
						8, 10))+1;
				dateInString = tanggalSelesai + "-" + bulanSelesai + "-" + tahunSelesai;
				Date dateSelesai = formatter.parse(dateInString);
				
				eventModel.addEvent(new DefaultScheduleEvent(schedule.getNama_agenda(),
						dateMulai, dateSelesai));
				event.setId(schedule.getId_agenda());
				System.out.println(event.getId());
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @return the eventModel
	 */
	public ScheduleModel getEventModel() {
		return eventModel;
	}

	/**
	 * @param eventModel the eventModel to set
	 */
	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	/**
	 * @return the event
	 */
	public ScheduleEvent getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}
	
	/**
	 * 
	 * @param selectEvent
	 */
	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
	}
	
	/**
	 * 
	 * @param selectEvent
	 */
	public void onEventSelect(SelectEvent selectEvent) {
		String id = event.getId();
		event = (ScheduleEvent) selectEvent.getObject();
		event.setId(id);
	}
	
	/**
	 * 
	 * @param event
	 */
	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event moved", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	/**
	 * 
	 * @param event
	 */
	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event resized", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}
	
	/**
	 * 
	 * @param actionEvent
	 */
	@PostUpdate
	public void addEvent(ActionEvent actionEvent) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(event.getId());
		Schedule schedule = new Schedule();
		if (event.getId() == null){
			eventModel.addEvent(event);
			schedule.setId_agenda(eventModel.getEvents().get(eventModel.getEvents().size()-1).getId());
			schedule.setNama_agenda(eventModel.getEvents().get(eventModel.getEvents().size()-1).getTitle());
			schedule.setTangal_mulai(sdf.format(eventModel.getEvents().get(eventModel.getEvents().size()-1).getStartDate()));
			schedule.setTanggal_selesai(sdf.format(eventModel.getEvents().get(eventModel.getEvents().size()-1).getEndDate()));
			schedule.setKeterangan(eventModel.getEvents().get(eventModel.getEvents().size()-1).getDescription());
			getScheduleService().addShedule(schedule);
		}
		else{
			schedule.setId_agenda(event.getId());
			schedule.setNama_agenda(event.getTitle());
			schedule.setTangal_mulai(sdf.format(event.getStartDate()));
			schedule.setTanggal_selesai(sdf.format(event.getEndDate()));
			schedule.setKeterangan(event.getDescription());
			getScheduleService().updateShedule(schedule);
//			eventModel.updateEvent(event);
		}
//		event = new DefaultScheduleEvent();
	}
	
	/**
	 * 
	 * @param message
	 */
	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/*
	 * Spring
	 */

	@ManagedProperty(value = "#{ScheduleService}")
	ScheduleService scheduleService;

	List<Schedule> scheduleList;

	private String id_agenda;
	private String nama_agenda;
	private String tangal_mulai;
	private String tanggal_selesai;
	private String keterangan;

	/**
	 * @return the scheduleService
	 */
	public ScheduleService getScheduleService() {
		return scheduleService;
	}

	/**
	 * @param scheduleService
	 *            the scheduleService to set
	 */
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	/**
	 * @return the scheduleList
	 */
	public List<Schedule> getScheduleList() {
		scheduleList = new ArrayList<Schedule>();
		scheduleList.addAll(getScheduleService().getSchedules());
		return scheduleList;
	}

	/**
	 * @param scheduleList
	 *            the scheduleList to set
	 */
	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	/**
	 * @return the id_agenda
	 */
	public String getId_agenda() {
		return id_agenda;
	}

	/**
	 * @param id_agenda
	 *            the id_agenda to set
	 */
	public void setId_agenda(String id_agenda) {
		this.id_agenda = id_agenda;
	}

	/**
	 * @return the nama_agenda
	 */
	public String getNama_agenda() {
		return nama_agenda;
	}

	/**
	 * @param nama_agenda
	 *            the nama_agenda to set
	 */
	public void setNama_agenda(String nama_agenda) {
		this.nama_agenda = nama_agenda;
	}

	/**
	 * @return the tangal_mulai
	 */
	public String getTangal_mulai() {
		return tangal_mulai;
	}

	/**
	 * @param tangal_mulai
	 *            the tangal_mulai to set
	 */
	public void setTangal_mulai(String tangal_mulai) {
		this.tangal_mulai = tangal_mulai;
	}

	/**
	 * @return the tanggal_selesai
	 */
	public String getTanggal_selesai() {
		return tanggal_selesai;
	}

	/**
	 * @param tanggal_selesai
	 *            the tanggal_selesai to set
	 */
	public void setTanggal_selesai(String tanggal_selesai) {
		this.tanggal_selesai = tanggal_selesai;
	}

	/**
	 * @return the keterangan
	 */
	public String getKeterangan() {
		return keterangan;
	}

	/**
	 * @param keterangan
	 *            the keterangan to set
	 */
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
}
