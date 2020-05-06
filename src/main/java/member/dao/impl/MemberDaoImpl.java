package member.dao.impl;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.management.ListenerNotFoundException;
import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import forum.model.Launch_activityBean;
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

	// user忘記密碼時,依照他註冊時所填的email,更新其密碼
	// 成功回傳1
	@Override
	public int updatePassword(String email, String newPW) {
		int result = 0;
		String hql = "UPDATE MemberBean m SET m.m_password = :pw WHERE m.m_mail = :email";
		Session session = factory.getCurrentSession();
		result = session.createQuery(hql).setParameter("pw", newPW).setParameter("email", email).executeUpdate();

		return result;
	}

	// 確認email是否存在
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

//	//變更密碼
//	@Override
//	public int changePassword(MemberBean mb, String newPW) {
//		int result = 0;
//		String hql = "UPDATE MemberBean m SET m.m_password = :newPW WHERE m.m_id = :m_id";
//		Session session = factory.getCurrentSession();
//		result = session.createQuery(hql).setParameter("newPW", newPW).setParameter("m_id", mb.getM_id()).executeUpdate();
//		
//		return result;
//	}

	// 變更密碼
	@Override
	public void changePassword(MemberBean mb) {
		MemberBean member = null;
		Session session = factory.getCurrentSession();
		member = (MemberBean) session.get(MemberBean.class, mb.getM_id());
		member.setM_password(mb.getM_password());

	}

	@Override
	public int updateM_img(MemberBean mb, Blob m_img) {
		int result = 0;
		String hql = "UPDATE MemberBean m SET m.m_img = :m_img WHERE m.m_id = :m_id";
		Session session = factory.getCurrentSession();
		result = session.createQuery(hql).setParameter("m_img", m_img).setParameter("m_id", mb.getM_id())
				.executeUpdate();

		return result;
	}

	@Override
	public void updateMember(MemberBean mb) {
		MemberBean member = null;
		Session session = factory.getCurrentSession();
		member = (MemberBean) session.get(MemberBean.class, mb.getM_id());
		member.setNickname(mb.getNickname());
		member.setIncome(mb.getIncome());
		member.setEducation(mb.getEducation());
		member.setCity(mb.getCity());

	}

	@Override
	public void addMyActivity(Launch_activityBean article_Id, String m_id) {
		MemberBean mbean;
		Launch_activityBean launchbean;

		Session session = factory.getCurrentSession();
		// mbean 是 以MemberBean的m_id 為參考依據(裡面包含Birthday city 等MemberBean的屬性)
		mbean = session.get(MemberBean.class, m_id);
		// launchbean 是 以Launch_activityBean的article_Id 為參考依據(裡面包含article_content
		// article_title等Launch_activityBean的屬性)

		launchbean = session.get(Launch_activityBean.class, article_Id.getArticle_Id());
		// mbean呼叫getLaunch_activityBean()方法 ，將呼叫的物件 存入變數launchset 的Set集合
		Set<Launch_activityBean> launchset = mbean.getLaunch_activityBean();

		// Set集合型態的launchset物件，將以Launch_activityBean 的 article_Id 為參考的物件launchbean
		// 加進去(launchset物件內)
		launchset.add(launchbean);
		// 將launchbean物件放入mbean物件內
		mbean.setLaunch_activityBean(launchset);

		// launchbean呼叫.getMembers()方法 ，將呼叫的參數 存入物件members 的Set集合
		Set<MemberBean> members = launchbean.getMembers();

		// Set集合型態的members物件，將以MemberBean 的 m_id 為參考的物件mbean 加進去( members物件內)
		members.add(mbean);
		// 將members物件放入launchbean物件內
		launchbean.setMembers(members);
	}

	@SuppressWarnings("null")
	@Override
	public List<MemberBean> getActivityPerson(int article_Id) {


		MemberBean mbean = null;
		List<MemberBean> joinActivityMember = new ArrayList<MemberBean>();
		Session session = factory.getCurrentSession();
		
		Launch_activityBean launch_activityBean = (Launch_activityBean) session.load(Launch_activityBean.class,article_Id);

		for (Iterator<?> iter = launch_activityBean.getMembers().iterator(); iter.hasNext();) {

			mbean = (MemberBean) iter.next();
			joinActivityMember.add(mbean);     
			System.out.println("joinActivityMember =" + joinActivityMember);
		}
		
		
		return joinActivityMember;  //List<String>
	}

	@Override
	public void leaveMyActivity(Launch_activityBean article_Id, String m_id) {
		MemberBean mbean;
		Launch_activityBean launchbean;

		Session session = factory.getCurrentSession();
		// mbean 是 以MemberBean的m_id 為參考依據(裡面包含Birthday city 等MemberBean的屬性)
		mbean = session.get(MemberBean.class, m_id);
		// launchbean 是 以Launch_activityBean的article_Id 為參考依據(裡面包含article_content
		// article_title等Launch_activityBean的屬性)

		launchbean = session.get(Launch_activityBean.class, article_Id.getArticle_Id());
		
		Set<Launch_activityBean> launchset =mbean.getLaunch_activityBean();
		launchset.remove(launchbean);
		
		
		Set<MemberBean> members = launchbean.getMembers();
		
		members.remove(mbean);
		launchbean.setMembers(members);
		
	}
	


}
