package member.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import init.HibernateUtils;
import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.model.memberBean;
import member.service.MemberService;

public class MemberServiceImpl implements MemberService{
	
	MemberDao dao;
	SessionFactory factory;
	
	public MemberServiceImpl() {
		this.dao = new MemberDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void saveMember(memberBean mb) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.saveMember(mb);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

}
