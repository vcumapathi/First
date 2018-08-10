package org.fst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LeaveMaster")
public class LeaveMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="leaveMasterId")
	private Integer leaveMasterId;
	
	@Column(name="reportingOne")
	private String reportingOne;
	
	@Column(name="reportingTwo")
	private String reportingTwo;
	
	@Column(name="fromDate")
	private Date fromDate;
	
	@Column(name="toDate")
	private Date toDate;
	
	@Column(name="emergencyNumber")
	private Long emergencyNumber;
	
	@Column(name="leaveType")
	private String leaveType;
	
	@Column(name="reason")
	private String reason;
	
	@Column(name="location")
	private String location;
	
	@Column(name="numberOfDays")
	private Integer numberOfDays;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;

	public Integer getLeaveMasterId() {
		return leaveMasterId;
	}

	public void setLeaveMasterId(Integer leaveMasterId) {
		this.leaveMasterId = leaveMasterId;
	}

	public String getReportingOne() {
		return reportingOne;
	}

	public void setReportingOne(String reportingOne) {
		this.reportingOne = reportingOne;
	}

	public String getReportingTwo() {
		return reportingTwo;
	}

	public void setReportingTwo(String reportingTwo) {
		this.reportingTwo = reportingTwo;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getEmergencyNumber() {
		return emergencyNumber;
	}

	public void setEmergencyNumber(Long emergencyNumber) {
		this.emergencyNumber = emergencyNumber;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
