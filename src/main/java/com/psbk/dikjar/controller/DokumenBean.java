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

import com.psbk.dikjar.model.Dokumen;
import com.psbk.dikjar.service.DokumenService;

@ManagedBean
@RequestScoped
public class DokumenBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// private static final String SUCCESS = "view";
	// private static final String ERROR = "error";

	@ManagedProperty(value = "#{DokumenService}")
	DokumenService dokumenService;

	List<Dokumen> dokumenList;

	List<Dokumen> listSearch;

	private String id_dokumen;
	private String nama_dokumen;
	private UploadedFile uploadedFile;
	private String jenis_dokumen;

	private Dokumen DOKUMEN = new Dokumen();
	private static int STATUS = 0;

	public void setId_Dokumen() {
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
		this.id_dokumen = "DK-" + kalimat + i;
	}

	public DokumenService getDokumenService() {
		return dokumenService;
	}

	/**
	 * @return the listSearch
	 */
	public List<Dokumen> getListSearch() {
		return listSearch;
	}

	/**
	 * @param listSearch
	 *            the listSearch to set
	 */
	public void setListSearch(List<Dokumen> listSearch) {
		this.listSearch = listSearch;
	}

	public void setDokumenService(DokumenService dokumenService) {
		this.dokumenService = dokumenService;
	}

	public List<Dokumen> getDokumenList() {
		dokumenList = new ArrayList<Dokumen>();
		dokumenList.addAll(getDokumenService().getDokumens());
		return dokumenList;
	}

	public void setDokumenList(List<Dokumen> dokumenList) {
		this.dokumenList = dokumenList;
	}

	public String getId_dokumen() {
		if (STATUS == 0) {
			setId_Dokumen();
		}
		return id_dokumen;
	}

	public void setId_dokumen(String id_dokumen) {
		this.id_dokumen = id_dokumen;
	}

	public String getNama_dokumen() {
		return nama_dokumen;
	}

	public void setNama_dokumen(String nama_dokumen) {
		this.nama_dokumen = nama_dokumen;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getJenis_dokumen() {
		return jenis_dokumen;
	}

	public void setJenis_dokumen(String jenis_dokumen) {
		this.jenis_dokumen = jenis_dokumen;
	}

	public void addDokumen() {
		try {
			if (getUploadedFile() != null) {
				Dokumen dokumen = new Dokumen();
				dokumen.setId_dokumen(getId_dokumen());
				dokumen.setNama_dokumen(getNama_dokumen());
				dokumen.setNama_file(getUploadedFile().getFileName());
				dokumen.setJenis_dokumen(getJenis_dokumen());
				handleFileUpload();

				if (STATUS == 0) {
					getDokumenService().addDokumen(dokumen);
				} else {
					getDokumenService().updateDokumen(dokumen);
					STATUS = 0;
				}
				FacesMessage msg = new FacesMessage("Succesful",
						getNama_dokumen() + " is uploaded.");
				FacesContext.getCurrentInstance().addMessage(null, msg);

				reset();

				// return SUCCESS;
			} else {
				FacesMessage msg = new FacesMessage("Error");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (DataAccessException e) {
			// TODO: handle exception
			e.printStackTrace();
			FacesMessage msg = new FacesMessage("Error");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		// return ERROR;

	}

	/**
	 * @return the dOKUMEN
	 */
	public Dokumen getDOKUMEN() {
		return DOKUMEN;
	}

	/**
	 * @param dOKUMEN
	 *            the dOKUMEN to set
	 */
	public void setDOKUMEN(Dokumen dOKUMEN) {
		DOKUMEN = dOKUMEN;
	}

	public void editDokumen(Dokumen id_dokumen) {
		STATUS = 1;
		id_dokumen = getDokumenService().getDokumenById(id_dokumen.getId_dokumen());
		setId_dokumen(id_dokumen.getId_dokumen());
		setNama_dokumen(id_dokumen.getNama_dokumen());
		setJenis_dokumen(id_dokumen.getJenis_dokumen());
	}

	public void deleteDokumen(Dokumen id_dokumen) {
		getDokumenService().deleteDokumen(id_dokumen);
		FacesMessage msg = new FacesMessage("Success delete");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void reset() {
		this.setNama_dokumen("");
		this.setUploadedFile(null);
		this.setJenis_dokumen("");
	}

	public void handleFileUpload() {
		try {
			File targetFolder = new File(
					"C:/Xampp/tomcat/webapps/dikjar_TIF/assets/resources");
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
