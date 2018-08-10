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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Manager")
public class Manager {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer managerId;

	@Column(name="mName")
	private String mName;
	
	@Column(name="email")
	private String email;

	@Column(name="mPassword")
	private String mPassword;
	
	@OneToOne
	@JoinColumn(name="leaves")
	private Leaves leaves;
	
	@Fetch(value = FetchMode.SELECT)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "managerId")
	@JsonIgnore
	private List<Employee> employees;

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonIgnore
	public String getmPassword() {
		return mPassword;
	}
	
	@JsonProperty
	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	
}
