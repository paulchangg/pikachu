package member.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import init.HibernateUtils;
import member.dao.MemberDao;
import member.model.MemberBean;


public class MemberDaoImpl implements MemberDao {

	SessionFactory factory;

	public MemberDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	// 儲存MemberBean物件，將參數mb新增到Member表格內。
	@Override
	public int saveMember(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(mb);
		n++;
		return n;
	}

	// 判斷參數id(會員帳號)是否已經被現有客戶使用，如果是，傳回true，表示此id不能使用，
	// 否則傳回false，表示此id可使用。
	@Override
	public boolean idExists(String id) {
		boolean exist = false;
		String hql = "FROM MemberBean m WHERE m.m_id = :id";
		Session session = factory.getCurrentSession();

		try {
			session.createQuery(hql).setParameter("id", id).getSingleResult();
			exist = true;
		} catch (NonUniqueResultException n) {
			exist = true;
		} catch (NoResultException e) {
			exist = false;
		}

		return exist;
	}

	// 由參數 id (會員帳號) 到Member表格中 取得某個會員的所有資料，傳回值為一個MemberBean物件，
	// 如果找不到對應的會員資料，傳回值為null。
	@SuppressWarnings("unchecked")
	@Override
	public MemberBean queryMember(String id) {
		MemberBean mb = null;
		String hql = "FROM MemberBean m WHERE m.m_id = :id";
		Session session = factory.getCurrentSession();
		List<MemberBean> beans = null;

		beans = session.createQuery(hql).setParameter("id", id).getResultList();
		if (beans.size() > 0) {
			mb = beans.get(0);
		}

		return mb;
	}

	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	@SuppressWarnings("unchecked")
	@Override
	public MemberBean checkIdPassword(String userId, String password) {
		MemberBean mb = null;
		List<MemberBean> beans = null;
		String hql = "FROM MemberBean m WHERE m.m_id = :id and m.m_password = :pswd";
		Session session = factory.getCurrentSession();
		beans = session.createQuery(hql).setParameter("id", userId).setParameter("pswd", password).getResultList();

		if (beans.size() > 0) {
			mb = beans.get(0);
		}

		return mb;
	}

	//user忘記密碼時,依照他註冊時所填的email,更新其密碼
	// 成功回傳1
	@Override
	public int updatePassword(String email, String newPW) {
		int result = 0;
		String hql = "UPDATE MemberBean m SET m.m_password = :pw WHERE m.m_mail = :email";
		Session session = factory.getCurrentSession();
		result = session.createQuery(hql).setParameter("pw", newPW).setParameter("email", email).executeUpdate();
		
		return result;
	}
	//確認email是否存在
	@Override
	public boolean emailExists(String email) {
		boolean exist = false;
		String hql = "FROM MemberBean m WHERE m.m_mail = :email";
		Session session = factory.getCurrentSession();

		try {
			session.createQuery(hql).setParameter("email", email).getSingleResult();
			exist = true;
		} catch (NonUniqueResultException n) {
			exist = true;
		} catch (NoResultException e) {
			exist = false;
		}

		return exist;
	}
	//變更暱稱
	@Override
	public int updateNickname(MemberBean mb, String nickname) {
		int result = 0;
		String hql = "UPDATE MemberBean m SET m.nickname = :nickname WHERE m.m_id = :m_id";
		Session session = factory.getCurrentSession();
		result = session.createQuery(hql).setParameter("nickname", nickname).setParameter("m_id", mb.getM_id()).executeUpdate();
		
		return result;
	}
	//變更年收入
	@Override
	public int updateIncome(MemberBean mb, String income) {
		int result = 0;
		String hql = "UPDATE MemberBean m SET m.income = :income WHERE m.m_id = :m_id";
		Session session = factory.getCurrentSession();
		result = session.createQuery(hql).setParameter("income", income).setParameter("m_id", mb.getM_id()).executeUpdate();
		
		return result;
	}
	//變更居住地
	@Override
	public int updateCity(MemberBean mb, String city) {
		int result = 0;
		String hql = "UPDATE MemberBean m SET m.city = :city WHERE m.m_id = :m_id";
		Session session = factory.getCurrentSession();
		result = session.createQuery(hql).setParameter("city", city).setParameter("m_id", mb.getM_id()).executeUpdate();
		
		return result;
	}
	//變更學歷
	@Override
	public int updateEducation(MemberBean mb, String education) {
		int result = 0;
		String hql = "UPDATE MemberBean m SET m.education = :education WHERE m.m_id = :m_id";
		Session session = factory.getCurrentSession();
		result = session.createQuery(hql).setParameter("education", education).setParameter("m_id", mb.getM_id()).executeUpdate();
		
		return result;
	}
	//變更密碼
	@Override
	public int changePassword(MemberBean mb, String newPW) {
		int result = 0;
		String hql = "UPDATE MemberBean m SET m.m_password = :newPW WHERE m.m_id = :m_id";
		Session session = factory.getCurrentSession();
		result = session.createQuery(hql).setParameter("newPW", newPW).setParameter("m_id", mb.getM_id()).executeUpdate();
		
		return result;
	}

}
