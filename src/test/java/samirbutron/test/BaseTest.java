package samirbutron.test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

  private static Browser browser;

  @BeforeClass
  public static void setWebPage() {
    browser = AqualityServices.getBrowser();
  }

  @AfterClass
  public void tearDown() {
    browser.quit();
  }
}
