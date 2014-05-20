package com.psbk.dikjar.service;

import java.util.List;

import com.psbk.dikjar.dao.BeritaAcaraDAO;
import com.psbk.dikjar.dao.DokumenDAO;
import com.psbk.dikjar.model.BeritaAcara;
import com.psbk.dikjar.model.Dokumen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * Dokumen Service
 */

@Service("BeritaAcaraService")
@Transactional(readOnly = true)
public class BeritaAcaraService {

	@Autowired
	BeritaAcaraDAO dokumenDAO;

	public BeritaAcaraDAO getDokumenDAO() {
		return dokumenDAO;
	}

	public void setDokumenDAO(BeritaAcaraDAO BeritaAcaraDAO) {
		this.dokumenDAO = BeritaAcaraDAO;
	}
	
	@Transactional(readOnly = false)
	public void addDokumen(BeritaAcara dokumen){
		getDokumenDAO().addDokumen(dokumen);
	}
	
	@Transactional(readOnly = false)
	public void updateDokumen(BeritaAcara dokumen){
		getDokumenDAO().updateDokumen(dokumen);
	}
	
	@Transactional(readOnly = false)
	public void deleteDokumen(BeritaAcara dokumen){
		getDokumenDAO().deleteDokumen(dokumen);
	}
	
	public BeritaAcara getDokumenById(String id){
		return getDokumenDAO().getDokumenById(id);
	}
	
	public List<BeritaAcara> getDokumens(){
		return getDokumenDAO().getDokumens();
	}
	
	public String maxID(){
		return getDokumenDAO().getMaxDokumenId();
	}
	
}
