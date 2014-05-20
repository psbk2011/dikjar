package com.psbk.dikjar.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.support.ServletContextPropertySource;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.context.support.ServletContextResourceLoader;

import com.psbk.dikjar.model.BeritaAcara;
import com.psbk.dikjar.model.Dokumen;
import com.psbk.dikjar.service.BeritaAcaraService;
import com.psbk.dikjar.service.DokumenService;

@ManagedBean
@RequestScoped
public class BeritaAcaraBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// private static final String SUCCESS = "view";
	// private static final String ERROR = "error";

	@ManagedProperty(value = "#{BeritaAcaraService}")
	BeritaAcaraService dokumenService;

	List<BeritaAcara> dokumenList;

	List<BeritaAcara> listSearch;

	/**
	 * @return the dokumenList
	 */
	public List<BeritaAcara> getDokumenList() {
		dokumenList = new ArrayList<>();
		dokumenList.addAll(getDokumenService().getDokumens());
		return dokumenList;
	}

	/**
	 * @param dokumenList
	 *            the dokumenList to set
	 */
	public void setDokumenList(List<BeritaAcara> dokumenList) {
		this.dokumenList = dokumenList;
	}

	/**
	 * @return the listSearch
	 */
	public List<BeritaAcara> getListSearch() {
		return listSearch;
	}

	/**
	 * @param listSearch
	 *            the listSearch to set
	 */
	public void setListSearch(List<BeritaAcara> listSearch) {
		this.listSearch = listSearch;
	}

	private String id_dokumen;
	private String nama_dokumen;
	private UploadedFile uploadedFile;

	private static int _status = 0;

	/**
	 * @return the dokumenService
	 */
	public BeritaAcaraService getDokumenService() {
		return dokumenService;
	}

	/**
	 * @param dokumenService
	 *            the dokumenService to set
	 */
	public void setDokumenService(BeritaAcaraService dokumenService) {
		this.dokumenService = dokumenService;
	}

	/**
	 * @return the id_dokumen
	 */
	public String getId_dokumen() {
		if (_status == 0) {
			autoId_Dokumen();
		}
		return id_dokumen;
	}

	/**
	 * @param id_dokumen
	 *            the id_dokumen to set
	 */
	public void setId_dokumen(String id_dokumen) {
		this.id_dokumen = id_dokumen;
	}

	/**
	 * @return the nama_dokumen
	 */
	public String getNama_dokumen() {
		return nama_dokumen;
	}

	/**
	 * @param nama_dokumen
	 *            the nama_dokumen to set
	 */
	public void setNama_dokumen(String nama_dokumen) {
		this.nama_dokumen = nama_dokumen;
	}

	/**
	 * @return the uploadedFile
	 */
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	/**
	 * @param uploadedFile
	 *            the uploadedFile to set
	 */
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}


	private BeritaAcara dokumen = new BeritaAcara();

	/**
	 * @return the dokumen
	 */
	public BeritaAcara getDokumen() {
		return dokumen;
	}

	/**
	 * @param dokumen
	 *            the dokumen to set
	 */
	public void setDokumen(BeritaAcara dokumen) {
		this.dokumen = dokumen;
	}

	public void autoId_Dokumen() {
		int i = 0;
		String id_dokumen_tem = getDokumenService().maxID();
		String angka = id_dokumen_tem.substring(id_dokumen_tem.length() - 2,
				id_dokumen_tem.length());
		int convert_kodebrg = Integer.valueOf(angka);
		StringBuffer kalimat = new StringBuffer();
		kalimat.append("00");
		i = i + convert_kodebrg + 1;

		if (i <= 9) {
			kalimat.append("0");
		}
		if (i <= 99) {
			kalimat.delete(0, 2);
		}
		this.id_dokumen = "BA-" + kalimat + i;
	}

	public void actionDokume() {
		BeritaAcara d = new BeritaAcara();

		d.setId_dokumen(getId_dokumen());
		d.setNama_dokumen(getNama_dokumen());
		d.setNama_file(getUploadedFile().getFileName());
		
		try {
			if (_status == 0) {
				handleFileUpload();
				getDokumenService().addDokumen(d);
				FacesMessage msg = new FacesMessage("Succesful",
						d.getId_dokumen() + " Berhasil diinputkan");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				resetField();
			} else {
				handleFileUpload();
				System.out.println(getId_dokumen());
				getDokumenService().updateDokumen(d);
				FacesMessage msg = new FacesMessage("Succesful",
						d.getId_dokumen() + " Berhasil diedit");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				resetField();
				_status = 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void resetField() {
		this.setId_dokumen("");
		this.setNama_dokumen("");
		this.setUploadedFile(null);
	}
	
	public void editDokumen(BeritaAcara d) {
		_status = 1;
		d = getDokumenService().getDokumenById(d.getId_dokumen());
		
		setId_dokumen(d.getId_dokumen());
		setNama_dokumen(d.getNama_dokumen());
	}
	
	public void deleteDokumen(BeritaAcara d){
		getDokumenService().deleteDokumen(d);
		FacesMessage msg = new FacesMessage("Success delete dokumen "+d.getId_dokumen());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void handleFileUpload() {
		try {
			File targetFolder = new File(
					"C:/Xampp/tomcat/webapps/dikjar/assets/resources");
			InputStream inputStream = getUploadedFile().getInputstream();
			OutputStream out = new FileOutputStream(new File(targetFolder,
					getUploadedFile().getFileName()));
			int read = 0;
			byte[] bytes = new byte[1024000];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
