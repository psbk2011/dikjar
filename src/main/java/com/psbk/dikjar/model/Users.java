/**
 * 
 */
package com.psbk.dikjar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Apey
 *
 */

@Entity
@Table(name = "tbl_user")
public class Users {

	private String id_dosen;
	private String username;
	private String password;
	private String hak_akses;

	/**
	 * @return the id_dosen
	 */
	@Id
	@Column(name = "id_dosen", unique = true, nullable = false)
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
	 * @return the username
	 */
	@Column(name = "username", unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the hak_akses
	 */
	@Column(name="hak_akses", nullable = false)
	public String getHak_akses() {
		return hak_akses;
	}

	/**
	 * @param hak_akses
	 *            the hak_akses to set
	 */
	public void setHak_akses(String hak_akses) {
		this.hak_akses = hak_akses;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("id_dosen : ").append(getId_dosen());
		stringBuffer.append(", password : ").append(getPassword());
		stringBuffer.append(", hak_akses : ").append(getHak_akses());
		
		return stringBuffer.toString();
	}

}
