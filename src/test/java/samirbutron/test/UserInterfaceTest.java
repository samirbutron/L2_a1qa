package samirbutron.test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import samirbutron.page.Card1;
import samirbutron.page.Card2;
import samirbutron.page.Card3;
import samirbutron.page.MainPage;
import samirbutron.utils.RandomUtils;

public class UserInterfaceTest {

  private Browser browser;
  private ISettingsFile testConfig = new JsonSettingsFile("testconfig.json");
  private String url = testConfig.getValue("/url").toString();
  private MainPage mainPage = new MainPage();
  private Card1 card1 = new Card1();
  private Card2 card2 = new Card2();
  private Card3 card3 = new Card3();

  @BeforeMethod
  public void testStart() {
    browser = AqualityServices.getBrowser();
    //Navigate to home page
    browser.goTo(url);
  }

  @AfterMethod
  public void testEnd() {
    browser.quit();
  }

  @Test
  public void test1() {
    //Assert Welcome page is open
    Assert.assertTrue(mainPage.state().waitForDisplayed());
    //Click the link (in text 'Please click HERE to GO to the next page') to navigate the next page.
    mainPage.clickLink();
    //Assert the '1' card is open
    Assert.assertTrue(card1.state().waitForDisplayed());

    //Input random valid password, email, accept the terms of use and click "next" button.
    String email = RandomUtils.generateString();
    String password = RandomUtils.generatePassword(email);
    String server = RandomUtils.generateString();
    card1.fillForm(password,email,server);
    card1.acceptTerms();
    card1.clickNext();
    //Assert the '2' card is open
    Assert.assertTrue(card2.state().waitForDisplayed());
    //Choose 3 random interests, upload image, click "Next" button.
    card2.pickCheckboxes();
    //Assert the '3' card is open
    Assert.assertTrue(card3.state().waitForDisplayed());
  }

  @Test
  public void test2() {
    //Assert Welcome page is open
    Assert.assertTrue(mainPage.state().waitForDisplayed());
    mainPage.clickLink();
    //Hide help form
    card1.hideHelp();
    //Assert Form content is hidden
    Assert.assertTrue(card1.helpFormIsHidden());

  }

  @Test
  public void test3() {
    //Assert Welcome page is open
    Assert.assertTrue(mainPage.state().waitForDisplayed());
    mainPage.clickLink();
    //Accept cookies
    card1.acceptCookies();
    //Assert Form is closed
    Assert.assertTrue(card1.cookiesIsHidden());
  }

  @Test
  public void test4() {
    //Assert Welcome page is open
    Assert.assertTrue(mainPage.state().waitForDisplayed());
    mainPage.clickLink();
    //Validate that timer starts from "00:00"
    card1.getTimerStart();
    Assert.assertEquals(card1.getTimerStart(), testConfig.getValue("/expectedTime") );

  }
}
