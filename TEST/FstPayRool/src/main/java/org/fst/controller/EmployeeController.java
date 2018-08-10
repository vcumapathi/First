package org.fst.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.fst.model.Category;
import org.fst.model.Doctors;
import org.fst.model.Employee;
import org.fst.model.LeaveMaster;
import org.fst.model.Leaves;
import org.fst.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	private static final Logger logger = Logger.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="/employee", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> saveEmployeeDetails(@RequestBody Employee employee,HttpSession session){
		
		logger.info("employee object: "+employee.getName());
		Map<String,String> mapObj =employeeService.saveEmployee(employee,session);
		
		return mapObj;
	}
	
	@RequestMapping(value="/login", method= RequestMethod.POST)
	@ResponseBody
	public Employee employeeLogin(@RequestBody Employee employee,HttpSession session){
		Employee employeeObj = employeeService.employeeLogin(employee,session);
		return employeeObj;
	}
	
	@RequestMapping(value="/employees", method=RequestMethod.GET)
	@ResponseBody
	public List<Employee> getEmployees(){
		List<Employee> employeeList = employeeService.getEmployees();
		return employeeList;
	}

	@RequestMapping(value="/assignLeaves", method= RequestMethod.POST)
	@ResponseBody
	public Map<String,String> assignLeaves(@RequestBody Leaves leaves, HttpSession session){
		Map<String,String> result = employeeService.assignLeavesToEmployee(leaves,session);
		return result;
	}
	
	@RequestMapping(value="/employeeLeaves", method = RequestMethod.GET)
	@ResponseBody
	public Leaves getEmployeeLeaves(HttpSession session){
		Leaves leavesObj = employeeService.getEmployeeLeaves(session);
		return leavesObj;
	}
	
	@RequestMapping(value="/saveLeaveMaster", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> saveLeaveMasterDetails(@RequestBody LeaveMaster leaveMaster, HttpSession session){
		Map<String,String> mapObj = employeeService.saveLeaveMaster(leaveMaster,session);
		return mapObj;
	}
	
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> saveCatDetails(@RequestBody Category category,HttpSession session){
		
		logger.info("category object: "+category.getName());
		Map<String,String> mapObj =employeeService.saveCat(category,session);
		
		return mapObj;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.GET)
	@ResponseBody
	public List<Category> getCategories(){
		List<Category> catList = employeeService.getCategoryList();
		return catList;
	}
	
	@RequestMapping(value="/saveDoc",consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> saveDocDetails(@RequestParam(value="file",required = false) MultipartFile file,
			@RequestPart("doctor")  Doctors doctors){
			try {
				byte[] mediaBytes = file.getBytes();
				doctors.setImage(mediaBytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		Map<String,String> mapObj =employeeService.saveDoctors(doctors);
		return mapObj;
	}
	
	@RequestMapping(value = "/doctors/{categoryId}",method =RequestMethod.GET)
	@ResponseBody
	public List<Doctors> getDocList(@PathVariable("categoryId") Long categoryId) {
		List<Doctors> getImages = employeeService.getDoctorsList(categoryId);
		return getImages;
		
	}
}
