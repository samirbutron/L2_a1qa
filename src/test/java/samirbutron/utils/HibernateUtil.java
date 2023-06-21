package samirbutron.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import samirbutron.DAO.TestDAO;

public class HibernateUtil {

  private static final SessionFactory sessionFactory = new Configuration().configure()
      .buildSessionFactory();

  public static void createTestEntry(TestDAO testDAO) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
      session.save(testDAO);
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static TestDAO getTestDAOById(int id) {
    try (Session session = sessionFactory.openSession()) {
      String hql = "FROM TestDAO WHERE id = :id";
      Query<TestDAO> query = session.createQuery(hql, TestDAO.class);
      query.setParameter("id", id);
      return query.uniqueResult();
    } catch (Exception e) {
      return null;
    }
  }

  public static void updateTestEntry(TestDAO test) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
      session.update(test);
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void deleteTestDAOById(int id) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
      TestDAO testDAO = session.get(TestDAO.class, id);
      if (testDAO != null) {
        session.delete(testDAO);
      }
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static TestDAO getLastTestEntry() {
    try (Session session = sessionFactory.openSession()) {
      String hql = "FROM TestDAO ORDER BY id DESC";
      Query<TestDAO> query = session.createQuery(hql, TestDAO.class);
      return query.uniqueResult();
    } catch (Exception e) {
      return null;
    }
  }
}
