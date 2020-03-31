package member.service.impl;

import java.sql.Blob;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
	public int updateNickname(MemberBean mb, String nickname) {
		int r = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			r = dao.updateNickname(mb, nickname);
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
	public int updateIncome(MemberBean mb, String income) {
		int r = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			r = dao.updateIncome(mb, income);
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
	public int updateCity(MemberBean mb, String city) {
		int r = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			r = dao.updateCity(mb, city);
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
	public int updateEducation(MemberBean mb, String education) {
		int r = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			r = dao.updateEducation(mb, education);
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
	public int changePassword(MemberBean mb, String newPW) {
		int r = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			r = dao.changePassword(mb, newPW);
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

}
