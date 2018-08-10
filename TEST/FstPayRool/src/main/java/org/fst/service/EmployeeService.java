package org.fst.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.fst.model.Category;
import org.fst.model.Doctors;
import org.fst.model.Employee;
import org.fst.model.LeaveMaster;
import org.fst.model.Leaves;


public interface EmployeeService {

	Map<String, String> saveEmployee(Employee employee, HttpSession session);

	Employee employeeLogin(Employee employee, HttpSession session);

	List<Employee> getEmployees();

	Map<String, String> assignLeavesToEmployee(Leaves leaves, HttpSession session);

	Leaves getEmployeeLeaves(HttpSession session);

	Map<String, String> saveLeaveMaster(LeaveMaster leaveMaster, HttpSession session);

	Map<String, String> saveCat(Category category, HttpSession session);

	List<Category> getCategoryList();

	Map<String, String> saveDoctors(Doctors doctors);

	List<Doctors> getDoctorsList(Long categoryId);
	
}
