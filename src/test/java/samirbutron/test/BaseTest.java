package samirbutron.test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

  private final ISettingsFile testConfig = new JsonSettingsFile("testconfig.json");
  private final String url = testConfig.getValue("/url").toString();
  private Browser browser;

  @BeforeClass
  public void setWebPage() {
    browser = AqualityServices.getBrowser();
    //Navigate to home page
    browser.goTo(url);
  }

  @AfterClass
  public void tearDown() {
    browser.quit();
  }
}
