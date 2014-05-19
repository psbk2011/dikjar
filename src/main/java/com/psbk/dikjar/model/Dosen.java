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
@Table(name = "tbl_dosen")
public class Dosen {

	private String id_dosen;
	private String nama_dosen;
	private String jabatan;
	private String email;
	private String telepon;

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
	 * @return the nama_dosen
	 */
	@Column(name = "nama_dosen", nullable = false)
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
	@Column(name = "jabatan", nullable = false)
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
	@Column(name = "email", nullable = false)
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
	 * @param telepon the telepon to set
	 */
	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("id_dosen : ").append(getId_dosen());
		stringBuffer.append(", nama_dosen : ").append(getNama_dosen());
		stringBuffer.append(", jabatan : ").append(getJabatan());
		stringBuffer.append(", email : ").append(getEmail());
		stringBuffer.append(", telepon : ").append(getTelepon());
		
		
		return stringBuffer.toString();
	}
	

}
