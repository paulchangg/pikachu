package member.service.impl;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import forum.model.Launch_activityBean;
import init.HibernateUtils;
import init.SendEmail;
import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.model.MemberBean;
import member.service.MemberService;

public class MemberServiceImpl implements MemberService {

	MemberDao dao;
	SessionFactory factory;

	public MemberServiceImpl() {
		this.dao = new MemberDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public int saveMember(MemberBean mb) {
		int count = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.saveMember(mb);
			count++;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return count;
	}

	@Override
	public boolean idExists(String id) {
		boolean result;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			result = dao.idExists(id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return result;
	}

	@Override
	public MemberBean queryMember(String id) {
		MemberBean mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			mb = dao.queryMember(id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return mb;
	}

	@Override
	public MemberBean checkIdPassword(String userId, String password) {
		MemberBean mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			mb = dao.checkIdPassword(userId, password);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return mb;
	}

	@Override
	public int updatePassword(String email, String newPW) {
		int r = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			r = dao.updatePassword(email, newPW);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}

		return r;
	}

	@Override
	public boolean emailExists(String email) {
		boolean result;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			result = dao.emailExists(email);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return result;
	}

//	@Override
//	public boolean sendMail(String email, String newPW) {
//		boolean r = false;
//		String host = "smtp.gmail.com";
//		int port = 587;
//		String from = "ntutjava013.2@gmail.com";
//		String to = email;
//		final String username = "ntutjava013.2@gmail.com";
//		final String password = "Do!ng123";
//
//		Properties props = new Properties();
//		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.port", port);
//		javax.mail.Session session = javax.mail.Session.getInstance(props, new Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
//		});
//
//		try {
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(from));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//			message.setSubject("PIKACHU : update passowrd successfully");
//			message.setText("This is your new password : [ " + newPW + " ] !!!");
//
//			Transport transport = session.getTransport("smtp");
//			transport.connect(host, port, username, password);
//
//			Transport.send(message);
//
//			System.out.println("寄送email結束.");
//			r = true;
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//		
//		return r;
//	}
	
	@Override
	public void sendMail(String email, String newPW) {
		SendEmail se = new SendEmail(email, newPW);
		se.start();			
	}



	@Override
	public void changePassword(MemberBean mb) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			dao.changePassword(mb);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	@Override
	public int updateM_img(MemberBean mb, Blob m_img) {
		int r = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			r = dao.updateM_img(mb, m_img);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}

		return r;
	}

	@Override
	public void updateMember(MemberBean mb) {
		Session session = factory.getCurrentSession();
		Transaction tx =null;
		try {
			tx = session.beginTransaction();
            dao.updateMember(mb);
			tx.commit();
		} catch(Exception e){
			if (tx != null) tx.rollback();
			throw new RuntimeException(e);
		}
		return;
		
	}
	@Override
	public void addMyActivity(Launch_activityBean article_Id, String m_id) {
		
		Session session = factory.getCurrentSession();
		Transaction tx =null;
		try {
			tx = session.beginTransaction();
			dao.addMyActivity(article_Id, m_id);
			tx.commit();
		} catch(Exception e){
			if (tx != null) tx.rollback();
			throw new RuntimeException(e);
		}
		return;
		
		
		
	}

	@Override
	public List<MemberBean> getActivityPerson(int article_Id) {
		List<MemberBean> joinActivityMember = null;
		Session session = factory.getCurrentSession();
		Transaction tx =null;
		try {
			tx = session.beginTransaction();
			joinActivityMember=	dao.getActivityPerson(article_Id);
			tx.commit();
		} catch(Exception e){
			if (tx != null) tx.rollback();
			throw new RuntimeException(e);
		}
		return joinActivityMember;
		
		
	}
	@Override
	public void leaveMyActivity(Launch_activityBean article_Id, String m_id) {
		
		
		Session session = factory.getCurrentSession();
		Transaction tx =null;
		try {
			tx = session.beginTransaction();
			dao.leaveMyActivity(article_Id, m_id);
			tx.commit();
		} catch(Exception e){
			if (tx != null) tx.rollback();
			throw new RuntimeException(e);
		}
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
