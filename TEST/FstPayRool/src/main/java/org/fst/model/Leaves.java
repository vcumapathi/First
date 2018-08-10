package org.fst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Leaves")
public class Leaves {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="leavesId")
	private Integer leavesId;
	
	@OneToOne(mappedBy = "leaves")
    private Employee employee;
	
	@Column(name="pl")
	private String pl;
	
	@Column(name="sl")
	private String sl;
	
	@Column(name="apl")
	private String apl;
	
	@Column(name="asl")
	private String asl;
	
	@Column(name="status")
	private String status;
	
	@Column(name="employeeId")
	private Integer employeeId;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="managerId")
	private Integer managerId;
	
	@OneToOne(mappedBy = "leaves")
	private Manager manager;

	public Integer getLeavesId() {
		return leavesId;
	}

	public void setLeavesId(Integer leavesId) {
		this.leavesId = leavesId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getPl() {
		return pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}

	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getApl() {
		return apl;
	}

	public void setApl(String apl) {
		this.apl = apl;
	}

	public String getAsl() {
		return asl;
	}

	public void setAsl(String asl) {
		this.asl = asl;
	}
	
	
}
