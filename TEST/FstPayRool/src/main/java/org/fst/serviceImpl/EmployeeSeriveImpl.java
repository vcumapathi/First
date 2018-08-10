package org.fst.serviceImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.fst.model.Category;
import org.fst.model.Doctors;
import org.fst.model.Employee;
import org.fst.model.LeaveMaster;
import org.fst.model.Leaves;
import org.fst.repository.EmployeeRepository;
import org.fst.service.EmployeeService;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSeriveImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	private static final Logger logger = Logger.getLogger(EmployeeSeriveImpl.class);
	public static final String tsPw ="fstum";

	public Map<String,String> saveEmployee(Employee employee,HttpSession session){
		String saltedPassword = tsPw + employee.getPassword();
		String hashPassword = generateHash(saltedPassword);
		
		String cf = tsPw+employee.getConfirmPassword();
		String hsConPw = generateHash(cf);
		boolean b = hashPassword.equals(hsConPw);
		if(b){
			employee.setPassword(hashPassword);
			employee.setConfirmPassword(hsConPw);
		}
		Map<String,String> saveObj=employeeRepository.saveEmployee(employee);
		return saveObj;
	}

	public Employee employeeLogin(Employee employee, HttpSession session) {
		String saltedPassword = tsPw + employee.getPassword();
		String hashPassword = generateHash(saltedPassword);
		employee.setPassword(hashPassword);
		Employee empObj = employeeRepository.employeeLogin(employee);
		if(empObj !=null){
			session.setAttribute("employeeObj", empObj);
			Employee emp =(Employee) session.getAttribute("employeeObj");
			logger.info("employee login getAttribute "+ emp);
			
		}
		return empObj;
	}

	public List<Employee> getEmployees() {
		List<Employee> empList = employeeRepository.getEmployees();
		return empList;
	}

	public Map<String, String> assignLeavesToEmployee(Leaves leaves, HttpSession session) {
		Date assignLeavesDate = new Date();
		leaves.setDate(assignLeavesDate);
		Employee emp = (Employee) session.getAttribute("employeeObj");
		Integer managerId = emp.getEmployeeId();
		leaves.setManagerId(managerId);
		Map<String,String> result = employeeRepository.assignLeaves(leaves);
		return result;
	}

	public Leaves getEmployeeLeaves(HttpSession session) {
		Employee emp = (Employee) session.getAttribute("employeeObj");
		Integer employeeId = emp.getEmployeeId();
		Leaves leavesObj = employeeRepository.getEmployeeLeaves(employeeId);
		return leavesObj;
	}

	public Map<String, String> saveLeaveMaster(LeaveMaster leaveMaster, HttpSession session) {
		Employee employee =(Employee) session.getAttribute("employeeObj");
		DateTime fromDate = new DateTime(leaveMaster.getFromDate());
		DateTime toDate = new DateTime(leaveMaster.getToDate());
		Period diff = new Period(fromDate,toDate);
		int diffbwtwoDates = diff.getDays();
		int test = 1;
		int numberOfDays = test + diffbwtwoDates;
		leaveMaster.setNumberOfDays(numberOfDays);
		leaveMaster.setEmployee(employee);
		logger.info(" diff b/w two dates : " + numberOfDays);
		Map<String,String> result = employeeRepository.saveLeaveMaster(leaveMaster);
		return result;
	}
	
	public static String generateHash(String input){
		StringBuilder stringBuilder = new StringBuilder();
		try{
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes =  messageDigest.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f','x','y','z' };
			for (int i = 0; i < hashBytes.length; ++i) {
				byte b = hashBytes[i];
				stringBuilder.append(digits[(b & 0xf0) >> 4]);
				stringBuilder.append(digits[b & 0x0f]);
			}
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			
		}
		return stringBuilder.toString();
	}

	public Map<String, String> saveCat(Category category, HttpSession session) {

		Map<String,String> saveObj=employeeRepository.saveCat(category);
		return saveObj;
	}

	public List<Category> getCategoryList() {
		List<Category> catList = employeeRepository.getCategories();
		return catList;
	}

	public Map<String, String> saveDoctors(Doctors doctors) {
		Category cat=employeeRepository.getCategoryObject(doctors.getcId());
		doctors.setCategory(cat);
		Map<String,String> saveObj=employeeRepository.saveDoctors(doctors);
		return saveObj;
	}

	public List<Doctors> getDoctorsList(Long categoryId) {
		List<Doctors> docList = employeeRepository.getDoctorsList(categoryId);
		return docList;
	}
	
}


