/**
 * 
 */
package com.psbk.dikjar.controller;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.psbk.dikjar.model.Dosen;
import com.psbk.dikjar.model.Users;
import com.psbk.dikjar.service.DosenService;

/**
 * @author Apey
 *
 */

@ManagedBean
@RequestScoped
public class DosenController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{DosenService}")
	DosenService dosenService;

	List<Dosen> dosenList;
	
	List<Dosen> d;
	
	

	/**
	 * @return the d
	 */
	public List<Dosen> getD() {
		return d;
	}

	/**
	 * @param d the d to set
	 */
	public void setD(List<Dosen> d) {
		this.d = d;
	}


	private String id_dosen;
	private String nama_dosen;
	private String jabatan;
	private String email;
	private String telepon;

	/**
	 * @return the dosenService
	 */
	public DosenService getDosenService() {
		return dosenService;
	}

	/**
	 * @param dosenService
	 *            the dosenService to set
	 */
	public void setDosenService(DosenService dosenService) {
		this.dosenService = dosenService;
	}

	/**
	 * @return the dosenList
	 */
	public List<Dosen> getDosenList() {
		dosenList = new ArrayList<Dosen>();
		dosenList.addAll(getDosenService().getDosens());
		return dosenList;
	}

	/**
	 * @param dosenList
	 *            the dosenList to set
	 */
	public void setDosenList(List<Dosen> dosenList) {
		this.dosenList = dosenList;
	}

	/**
	 * @return the id_dosen
	 */
	public String getId_dosen() {
		return id_dosen;
	}

	/**
	 * @param id_dosen
	 *            the id_dosen to set
	 */
	public void setId_dosen(String id_dosen) {
		this.id_dosen = id_dosen;
	}

	/**
	 * @return the nama_dosen
	 */
	public String getNama_dosen() {
		return nama_dosen;
	}

	/**
	 * @param nama_dosen
	 *            the nama_dosen to set
	 */
	public void setNama_dosen(String nama_dosen) {
		this.nama_dosen = nama_dosen;
	}

	/**
	 * @return the jabatan
	 */
	public String getJabatan() {
		return jabatan;
	}

	/**
	 * @param jabatan
	 *            the jabatan to set
	 */
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telepon
	 */
	public String getTelepon() {
		return telepon;
	}

	/**
	 * @param telepon
	 *            the telepon to set
	 */
	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}

	
	/**
	 * Logic
	 */

	private static int _status = 0;
	private Dosen dosen;
	private Users users = new Users();
	
	private String password;
	private String hak_akses;
	
	/**
	 * @return the dosen
	 */
	public Dosen getDosen() {
		return dosen;
	}

	/**
	 * @param dosen the dosen to set
	 */
	public void setDosen(Dosen dosen) {
		this.dosen = dosen;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the hak_akses
	 */
	public String getHak_akses() {
		return hak_akses;
	}

	/**
	 * @param hak_akses the hak_akses to set
	 */
	public void setHak_akses(String hak_akses) {
		this.hak_akses = hak_akses;
	}
	
	public String getMD5(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException(
					"String to encript cannot be null or zero length");
		}
		MessageDigest digester = null;
		try {
			digester = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		digester.update(str.getBytes());
		byte[] hash = digester.digest();
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {
				hexString.append("0").append(
						Integer.toHexString((0xFF & hash[i])));
			} else {
				hexString.append(Integer.toHexString(0xFF & hash[i]));
			}
		}
		return hexString.toString();
	}

	public void actionDosen(){
		Dosen dosen = new Dosen();
		Users user = new Users();
		
		dosen.setId_dosen(getId_dosen());
		dosen.setNama_dosen(getNama_dosen());
		dosen.setJabatan(getJabatan());
		dosen.setEmail(getEmail());
		dosen.setTelepon(getTelepon());
		
		user.setId_dosen(getId_dosen());
		user.setUsername(getEmail());
		user.setPassword(getMD5(getPassword()));
		user.setHak_akses(getHak_akses());
		
		try {
			if (_status == 0) {
				getDosenService().addDosen(dosen);
				getDosenService().addUser(user);
				FacesMessage msg = new FacesMessage("Succesful",
						getId_dosen() + " Berhasil diinputkan");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				resetField();
			}else{
				getDosenService().updateDosen(dosen);
				getDosenService().updatetUser(user);
				FacesMessage msg = new FacesMessage("Succesful",
						getId_dosen() + " Berhasil diedit");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				resetField();
				_status = 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void resetField() {
		this.setId_dosen("");
		this.setNama_dosen("");
		this.setJabatan("");
		this.setTelepon("");
		this.setEmail("");
		this.setHak_akses("");
		this.setPassword("");
	}
	
	public void editDosen(String id){
		_status = 1;
		System.out.println(id);
		dosen = getDosenService().getDosenById(id);
		System.out.println(dosen.getEmail());
//		users = getDosenService().getUserById(id);
//		
		setId_dosen(dosen.getId_dosen());
		setNama_dosen(dosen.getNama_dosen());
		setJabatan(dosen.getJabatan());
		setTelepon(dosen.getTelepon());
		setEmail(dosen.getEmail());
//		setHak_akses(user.getHak_akses());
	}
	
	public void deleteDosen(Dosen dosen){		
		getDosenService().deleteDosen(dosen);
		
		FacesMessage msg = new FacesMessage("Success delete pengguna "+dosen.getId_dosen());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
