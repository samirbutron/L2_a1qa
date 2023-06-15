package samirbutron.test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

  private final ISettingsFile testConfig = new JsonSettingsFile("testconfig.json");
  private final ISettingsFile config = new JsonSettingsFile("config.json");
  private final String url = config.getValue("/url").toString();
  private Browser browser;

  @BeforeMethod
  public void setWebPage() {
    browser = AqualityServices.getBrowser();
    //Navigate to home page
    browser.goTo(url);
  }

  @AfterMethod
  public void tearDown() {
    browser.quit();
  }
}
