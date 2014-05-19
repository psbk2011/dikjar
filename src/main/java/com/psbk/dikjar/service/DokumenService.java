package com.psbk.dikjar.service;

import java.util.List;

import com.psbk.dikjar.dao.DokumenDAO;
import com.psbk.dikjar.model.Dokumen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * Dokumen Service
 */

@Service("DokumenService")
@Transactional(readOnly = true)
public class DokumenService {

	@Autowired
	DokumenDAO dokumenDAO;

	public DokumenDAO getDokumenDAO() {
		return dokumenDAO;
	}

	public void setDokumenDAO(DokumenDAO dokumenDAO) {
		this.dokumenDAO = dokumenDAO;
	}
	
	@Transactional(readOnly = false)
	public void addDokumen(Dokumen dokumen){
		getDokumenDAO().addDokumen(dokumen);
	}
	
	@Transactional(readOnly = false)
	public void updateDokumen(Dokumen dokumen){
		getDokumenDAO().updateDokumen(dokumen);
	}
	
	@Transactional(readOnly = false)
	public void deleteDokumen(Dokumen dokumen){
		getDokumenDAO().deleteDokumen(dokumen);
	}
	
	public Dokumen getDokumenById(String id){
		return getDokumenDAO().getDokumenById(id);
	}
	
	public List<Dokumen> getDokumens(){
		return getDokumenDAO().getDokumens();
	}
	
	public String maxID(){
		return getDokumenDAO().getMaxDokumenId();
	}
	
}
