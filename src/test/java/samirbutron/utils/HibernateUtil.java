package samirbutron.utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import samirbutron.DAO.TestDAO;

public class HibernateUtil {

  private static final SessionFactory sessionFactory = new Configuration().configure()
      .buildSessionFactory();
  private static final ISettingsFile testConfig = new JsonSettingsFile("testconfig.json");

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
      String hql = testConfig.getValue("/hqlGetByIdQuery").toString();
      Query<TestDAO> query = session.createQuery(hql, TestDAO.class);
      query.setParameter("id", id);
      return query.uniqueResult();
    } catch (Exception e) {
      return null;
    }
  }

  public static List<TestDAO> getTestDAOFirst10RepeatedDigits() {
    try (Session session = sessionFactory.openSession()) {
      String sql = testConfig.getValue("/sqlGetRepeatingDigits").toString();
      return session.createNativeQuery(sql, TestDAO.class).getResultList();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static List<TestDAO> getTestDaoLast10Entries() {
    try (Session session = sessionFactory.openSession()) {
      String hql = testConfig.getValue("/hqlOrderDescById").toString();
      Query<TestDAO> query = session.createQuery(hql, TestDAO.class);
      query.setMaxResults(10);
      return query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
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
      String sql = testConfig.getValue("/sqlGetLastEntry").toString();
      return session.createNativeQuery(sql, TestDAO.class).getSingleResult();
    } catch (Exception e) {
      return null;
    }
  }
}
