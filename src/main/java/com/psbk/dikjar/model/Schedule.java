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
@Table(name = "tbl_agenda")
public class Schedule {

	private String id_agenda;
	private String nama_agenda;
	private String tangal_mulai;
	private String tanggal_selesai;
	private String keterangan;

	/**
	 * @return the id_agenda
	 */
	@Id
	@Column(name = "id_agenda", unique = true, nullable = false)
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
	@Column(name = "nama_agenda", nullable = false)
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
	@Column(name = "tanggal_mulai", nullable = false)
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
	@Column(name = "tanggal_selesai", nullable = false)
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
	@Column(name = "keterangan", nullable = false)
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
