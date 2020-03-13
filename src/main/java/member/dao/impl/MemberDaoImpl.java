package member.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import init.HibernateUtils;
import member.dao.MemberDao;
import member.model.memberBean;
import net.bytebuddy.asm.Advice.ArgumentHandler.Factory;

public class MemberDaoImpl implements MemberDao{
	
	SessionFactory factory; 

	public MemberDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void saveMember(memberBean mb) {
		Session session = factory.getCurrentSession();
		session.save(mb);
	}

}
