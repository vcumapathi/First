package org.fst.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.fst.common.ApplicationConstants;
import org.fst.model.Category;
import org.fst.model.Doctors;
import org.fst.model.Employee;
import org.fst.model.LeaveMaster;
import org.fst.model.Leaves;
import org.fst.model.Manager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class EmployeeRepository extends BaseRepository {

	private static final Logger logger = Logger.getLogger(EmployeeRepository.class);

	public Map<String, String> saveEmployee(Employee employee) {
		Map<String, String> mapObj = new HashMap<String, String>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Employee employeeObj = null;
		try {
			employeeObj = new Employee();
			employeeObj.setName(employee.getName());
			employeeObj.setMobileNumber(employee.getMobileNumber());
			employeeObj.setAlternateNumber(employee.getAlternateNumber());
			employeeObj.setBloodGroup(employee.getBloodGroup());
			employeeObj.setPassword(employee.getPassword());
			employeeObj.setConfirmPassword(employee.getConfirmPassword());
			session.saveOrUpdate(employeeObj);
			transaction.commit();
			logger.info("Employee saved successfully, employee Details=" + employeeObj);
			mapObj.put(ApplicationConstants.STATUS, ApplicationConstants.SAVED_SUCCESS);
			mapObj.put(ApplicationConstants.DESCRIPTION, ApplicationConstants.DATA_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			mapObj.put(ApplicationConstants.STATUS, ApplicationConstants.FAILED);
			mapObj.put(ApplicationConstants.DESCRIPTION, ApplicationConstants.ERROR);
			transaction.rollback();
			logger.info("Exception ", e);
		} finally {
			session.clear();
			session.close();
			employeeObj = null;
		}

		return mapObj;
	}

	public Employee employeeLogin(Employee employee) {
		Employee employeeObj = new Employee();
		Session session = getSession();
		Criteria critera = session.createCriteria(Employee.class);
		critera.add(Restrictions.eq("mobileNumber", employee.getMobileNumber()));
		critera.add(Restrictions.eq("password", employee.getPassword()));
		Object obj = critera.uniqueResult();
		System.out.println("object :"+obj);
		employeeObj =(Employee) obj;
		if(employeeObj !=null){
			logger.info("mobile number :" + employeeObj.getName());
			return employeeObj;
		}else{
			System.out.println("error...");
		}
		return employeeObj;
	}

	public List<Employee> getEmployees() {
		Session session = getSession();
		Criteria cr = session.createCriteria(Employee.class)
				.setProjection(Projections.projectionList().add(Projections.property("employeeId"), "employeeId")
						.add(Projections.property("name"), "name"))
				.setResultTransformer(Transformers.aliasToBean(Employee.class));

		List<Employee> employees = cr.list();
		return employees;
	}

	public Map<String, String> assignLeaves(Leaves leaves) {

		Map<String, String> mapObj = new HashMap<String, String>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Leaves leavesObj = null;
		try {
			leavesObj = new Leaves();
			leavesObj.setDate(leaves.getDate());
			leavesObj.setSl(leaves.getSl());
			leavesObj.setPl(leaves.getPl());
			leavesObj.setApl(leaves.getPl());
			leavesObj.setAsl(leaves.getSl());
			leavesObj.setManagerId(leaves.getManagerId());
			leavesObj.setEmployeeId(leaves.getEmployeeId());
			session.saveOrUpdate(leavesObj);
			transaction.commit();
			logger.info("Leaves saved successfully, Leaves Details =" + leavesObj);
			mapObj.put(ApplicationConstants.STATUS, ApplicationConstants.SAVED_SUCCESS);
			mapObj.put(ApplicationConstants.DESCRIPTION, ApplicationConstants.DATA_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			mapObj.put(ApplicationConstants.STATUS, ApplicationConstants.FAILED);
			mapObj.put(ApplicationConstants.DESCRIPTION, ApplicationConstants.ERROR);
			transaction.rollback();
			logger.info("Exception ", e);
		} finally {
			session.clear();
			session.close();
			leavesObj = null;
		}

		return mapObj;

	}

	public Leaves getEmployeeLeaves(Integer employeeId) {
		Leaves leavesObj = null;
		Session session = getSession();
		try{
			leavesObj = new Leaves();
			Criteria cr = session.createCriteria(Leaves.class);
			cr.add(Restrictions.eq("employeeId", employeeId));
			List<Leaves> result = cr.list();
			leavesObj = result.get(0);
			if(leavesObj != null){
				int managerid = leavesObj.getManagerId();
				Manager managerObj = new Manager();
				Criteria cr2 = session.createCriteria(Manager.class);
				cr2.add(Restrictions.eq("managerId", managerid));
				
				List<Manager> manager = cr2.list();
				managerObj = manager.get(0);
				if(managerObj.getManagerId() != null){
					leavesObj.setManager(managerObj);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return leavesObj;
	}

	public Map<String, String> saveLeaveMaster(LeaveMaster leaveMaster) {


		Map<String, String> mapObj = new HashMap<String, String>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		LeaveMaster leaveMasterObj = null;
		try {
			leaveMasterObj = new LeaveMaster();
			leaveMasterObj.setFromDate(leaveMaster.getFromDate());
			leaveMasterObj.setToDate(leaveMaster.getToDate());
			leaveMasterObj.setLeaveType(leaveMaster.getLeaveType());
			leaveMasterObj.setLocation(leaveMaster.getLocation());
			leaveMasterObj.setReason(leaveMaster.getReason());
			leaveMasterObj.setEmployee(leaveMaster.getEmployee());
			leaveMasterObj.setEmergencyNumber(leaveMaster.getEmergencyNumber());
			leaveMasterObj.setReportingOne(leaveMaster.getReportingOne());
			leaveMasterObj.setNumberOfDays(leaveMaster.getNumberOfDays());
			session.saveOrUpdate(leaveMasterObj);
			transaction.commit();
			logger.info("Leaves saved successfully, LeaveMaster Details =" + leaveMasterObj);
			mapObj.put(ApplicationConstants.STATUS, ApplicationConstants.SAVED_SUCCESS);
			mapObj.put(ApplicationConstants.DESCRIPTION, ApplicationConstants.DATA_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			mapObj.put(ApplicationConstants.STATUS, ApplicationConstants.FAILED);
			mapObj.put(ApplicationConstants.DESCRIPTION, ApplicationConstants.ERROR);
			transaction.rollback();
			logger.info("Exception ", e);
		} finally {
			session.clear();
			session.close();
			leaveMasterObj = null;
		}

		return mapObj;

	
	}

	public Map<String, String> saveCat(Category category) {

		Map<String, String> mapObj = new HashMap<String, String>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Category catObj = null;
		try {
			catObj = new Category();
			catObj.setName(category.getName());
		
			session.saveOrUpdate(catObj);
			transaction.commit();
			logger.info("Category saved successfully, catObj Details=" + catObj);
			mapObj.put(ApplicationConstants.STATUS, ApplicationConstants.SAVED_SUCCESS);
			mapObj.put(ApplicationConstants.DESCRIPTION, ApplicationConstants.DATA_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			mapObj.put(ApplicationConstants.STATUS, ApplicationConstants.FAILED);
			mapObj.put(ApplicationConstants.DESCRIPTION, ApplicationConstants.ERROR);
			transaction.rollback();
			logger.info("Exception ", e);
		} finally {
			session.clear();
			session.close();
			catObj = null;
		}
		return mapObj;
	
	}

	public List<Category> getCategories() {
		Session session = getSession();
		Criteria cr = session.createCriteria(Category.class)
				.setProjection(Projections.projectionList().add(Projections.property("categoryId"), "categoryId")
						.add(Projections.property("name"), "name"))
				.setResultTransformer(Transformers.aliasToBean(Category.class));

		List<Category> categories = cr.list();
		return categories;
	
	}

	public Map<String, String> saveDoctors(Doctors doctors) {

		Map<String, String> mapObj = new HashMap<String, String>();
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Doctors docObject = null;
		try {
			docObject = new Doctors();
			docObject.setName(doctors.getName());
			docObject.setCategory(doctors.getCategory());
			docObject.setImage(doctors.getImage());
			docObject.setMobileNumber(doctors.getMobileNumber());
			docObject.setAddress(doctors.getAddress());
			session.saveOrUpdate(docObject);
			transaction.commit();
			logger.info("Doctor saved successfully, docObject Details=" + docObject);
			mapObj.put(ApplicationConstants.STATUS, ApplicationConstants.SAVED_SUCCESS);
			mapObj.put(ApplicationConstants.DESCRIPTION, ApplicationConstants.DATA_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			mapObj.put(ApplicationConstants.STATUS, ApplicationConstants.FAILED);
			mapObj.put(ApplicationConstants.DESCRIPTION, ApplicationConstants.ERROR);
			transaction.rollback();
			logger.info("Exception ", e);
		} finally {
			session.clear();
			session.close();
			docObject = null;
		}
		return mapObj;
	
	
	}
	
	public Category getCategoryObject(long l) {
		Category categoryObj = null;
		Session session = getSession();
		try{
			categoryObj = new Category();
			Criteria cr = session.createCriteria(Category.class);
			cr.add(Restrictions.eq("categoryId", l));
			List<Category> result = cr.list();
			categoryObj = result.get(0);
			session.clear();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return categoryObj;
	}

	public List<Doctors> getDoctorsList(Long categoryId) {
		String getCategory ="From Doctors where categoryId =:categoryId";
		Query queryObj = sessionFactory.getCurrentSession().createQuery(getCategory);
		queryObj.setParameter("categoryId",categoryId);
		List<Doctors> getRecords =queryObj.list();
		return getRecords;
	}

}
