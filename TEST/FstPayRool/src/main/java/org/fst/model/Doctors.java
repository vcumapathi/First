package org.fst.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Doctors")
public class Doctors {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="doctorId", unique = true, nullable = false)
	private Long doctorId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="mobileNumber")
	private Long mobileNumber;
	
	@Column(name="image",columnDefinition="mediumblob")
	private byte[] image;
	
	@Column(name="address")
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	@JsonIgnore
	private Category category;
	
	@Transient
	private long cId;

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	public long getcId() {
		return cId;
	}

	public void setcId(long cId) {
		this.cId = cId;
	}
	
}
