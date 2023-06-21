package samirbutron.test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import samirbutron.DAO.TestDAO;
import samirbutron.PO.Page;
import samirbutron.utils.HibernateUtil;
import samirbutron.utils.RandomUtils;

public class DatabaseTest extends BaseTest {

  private static final ISettingsFile config = new JsonSettingsFile("config.json");
  private static final String url = config.getValue("/url").toString();
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static final Page page = new Page();

  private static String startTime;

  @BeforeMethod
  void testStart() {
    startTime = dateFormat.format(new Date());
    setWebPage();
  }

  @AfterMethod
  public void testEnd(ITestResult result) {
    String classPath = result.getTestClass().getName();
    String testName = result.getMethod().getMethodName();
    String testPath = result.getMethod().getQualifiedName();
    String methodName = classPath + "#" + testPath;
    int projectID = 6;
    int sessionId = 20;
    String endTime = dateFormat.format(new Date());
    String env = "SamirDesktop";
    String browser = AqualityServices.getBrowser().getBrowserName().name().toLowerCase();
    TestDAO testResult = new TestDAO(0, testName, result.getStatus(), methodName, projectID,
        sessionId, startTime, endTime, env, browser, null);
    HibernateUtil.createTestEntry(testResult);
    Assert.assertEquals(HibernateUtil.getLastTestEntry(), testResult, "Last entry is different");
    tearDown();
  }

  @Test
  public void test1() {
    AqualityServices.getBrowser().goTo(url);
    String actualUrl = AqualityServices.getBrowser().getCurrentUrl();
    String expectedUrl = config.getValue("/url").toString();
    Assert.assertEquals(actualUrl, expectedUrl, "Url is different");
    Assert.assertTrue(page.searchBarIsPresent(), "Search bar is not present");
    Assert.assertTrue(page.logoIsPresent(), "Text in search bar is different");
  }

  @Test
  public void test2() {
    List<Integer> idValues = RandomUtils.generateRandomRepeatingDigitList(300);
    List<TestDAO> listTests = new ArrayList<>();
    for (Integer i : idValues) {
      listTests.add(HibernateUtil.getTestDAOById(i));
    }
    if (!listTests.isEmpty()) {
      listTests.removeIf(Objects::isNull);
      for (TestDAO test : listTests) {
        test.setStatus_id(3);
        test.setName("Modified");
      }
      for (TestDAO test : listTests) {
        HibernateUtil.updateTestEntry(test);
      }
      for (TestDAO test : listTests) {
        HibernateUtil.deleteTestDAOById(test.getId());
      }
      for (TestDAO test : listTests) {
        Assert.assertNull(HibernateUtil.getTestDAOById(test.getId()));
      }
    }
  }
}
