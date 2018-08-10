package org.fst.repository;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseRepository {
		  @Resource(name="sessionFactory")
		  protected SessionFactory sessionFactory;   

		  protected Session getSession(){
		         return sessionFactory.openSession();
		  }     

		  protected SessionFactory getSessionFactory() {
		  	return sessionFactory;
		  }

		public void setSessionFactory(SessionFactory sessionFactory) {
		  this.sessionFactory = sessionFactory;
		}
}
