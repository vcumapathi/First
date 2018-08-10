package org.fst.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "categoryId", nullable = false)
	private Long categoryId;
	
	@Column(name="name")
	private String name;
	
	@Fetch(value = FetchMode.SELECT)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId")
	@JsonIgnore
	private List<Doctors> doctorList;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Doctors> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(List<Doctors> doctorList) {
		this.doctorList = doctorList;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + ", doctorList=" + doctorList + "]";
	}
	
	
}
