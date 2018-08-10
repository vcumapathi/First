package org.fst.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Employee", uniqueConstraints ={
		@UniqueConstraint(columnNames="employeeId")
})
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="employeeId", unique = true, nullable = false)
	private Integer employeeId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="dateOfBirth")
	private Date dateOfBirth;
	
	@Column(name="mobileNumber")
	private Long mobileNumber;
	
	@Column(name="alternateNumber")
	private Long alternateNumber;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="email")
	private String email;
	
	@Column(name="bloodGrop")
	private String bloodGroup;
	
	@NotNull
	@Column(name="password")
	private String password;
	
	@NotNull
	@Column(name="confirmPassword")
	private String confirmPassword;
		
	@OneToOne
    @JoinColumn(name = "address_id")
    //@RestResource(path = "employeeAddress", rel="address")
    private Address address;
	
	@OneToOne
	@JoinColumn(name="leaves")
	private Leaves leaves;
	
	@Fetch(value = FetchMode.SELECT)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeId")
	@JsonIgnore 
	private List<LeaveMaster> leaveMasterList ;
	
	@ManyToOne
	@JoinColumn(name = "managerId")
	private Manager manager;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getAlternateNumber() {
		return alternateNumber;
	}

	public void setAlternateNumber(Long alternateNumber) {
		this.alternateNumber = alternateNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<LeaveMaster> getLeaveMasterList() {
		return leaveMasterList;
	}

	public void setLeaveMasterList(List<LeaveMaster> leaveMasterList) {
		this.leaveMasterList = leaveMasterList;
	}

	public void setLeaves(Leaves leaves) {
		this.leaves = leaves;
	}

	public Leaves getLeaves() {
		return leaves;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	
	
}
