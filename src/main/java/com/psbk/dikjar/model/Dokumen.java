package com.psbk.dikjar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Dokumen Entity
 * 
 */

@Entity
@Table(name = "tbl_dokumen")
public class Dokumen {

	private String id_dokumen;
	private String nama_dokumen;
	private String nama_file;
	private String jenis_dokumen;

	@Id
	@Column(name = "id_dokumen", unique = true, nullable = false)
	public String getId_dokumen() {
		return id_dokumen;
	}

	public void setId_dokumen(String id_dokumen) {
		this.id_dokumen = id_dokumen;
	}

	@Column(name = "nama_dokumen", nullable = false)
	public String getNama_dokumen() {
		return nama_dokumen;
	}

	public void setNama_dokumen(String nama_dokumen) {
		this.nama_dokumen = nama_dokumen;
	}

	@Column(name = "nama_file", nullable = false)
	public String getNama_file() {
		return nama_file;
	}

	public void setNama_file(String nama_file) {
		this.nama_file = nama_file;
	}

	@Column(name = "jenis_dokumen", nullable = false)
	public String getJenis_dokumen() {
		return jenis_dokumen;
	}

	public void setJenis_dokumen(String jenis_dokumen) {
		this.jenis_dokumen = jenis_dokumen;
	}

	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("id_dokumen : ").append(getId_dokumen());
		strBuff.append(", nama_dokumen : ").append(getNama_dokumen());
		strBuff.append(", nama_file : ").append(getNama_file());
		strBuff.append(", jenis_dokumen : ").append(getJenis_dokumen());
		return strBuff.toString();
	}
}
