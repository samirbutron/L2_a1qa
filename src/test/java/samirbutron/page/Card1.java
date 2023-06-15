package samirbutron.page;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import java.util.List;
import org.openqa.selenium.By;
import samirbutron.utils.RandomUtils;

public class Card1 extends Form {

  public Card1() {
    super(By.className("login-form__container"), "Card 1 form");
  }

  private final ITextBox password = AqualityServices.getElementFactory()
      .getTextBox(By.xpath("//div[@class='login-form__field-row']//input"),
          "Password text box");
  private final ITextBox email = AqualityServices.getElementFactory()
      .getTextBox(By.xpath("//input[@placeholder = 'Your email']"), "Email text box");
  private final ITextBox server = AqualityServices.getElementFactory()
      .getTextBox(By.xpath("//input[@placeholder = 'Domain']"), "Mail server text box");
  private final IButton dropDownButton = AqualityServices.getElementFactory()
      .getButton(By.xpath("//div[@class='dropdown__opener']"), "Dropdown opener");
  private final IComboBox domainList = AqualityServices.getElementFactory()
      .getComboBox(By.xpath("//div[@class='dropdown__list']"), "Domain");
  private static final String COMBOBOX_CHILD_LOCATION = "dropdown__list-item";
  private final ILabel terms = getElementFactory().getLabel(
      By.xpath("//span[@class='checkbox__box']"),
      "Terms check box");
  private final IButton next = getElementFactory().getButton(
      By.xpath("//a[@class='button--secondary']"),
      "Next button");

  private final ILabel helpForm = AqualityServices.getElementFactory()
      .getLabel(By.xpath("help-form"), "Help form");
  private final IButton hideHelp = AqualityServices.getElementFactory()
      .getButton(By.xpath("//span[@class='highlight']//parent::button"), "'Send to bottom'");

  //I'm sure this is not a correct Element Interface for this element...
  private final ILabel cookiesBanner = AqualityServices.getElementFactory()
      .getLabel(By.className("cookies"), "Cookies banner");
  private final IButton acceptCookie = AqualityServices.getElementFactory()
      .getButton(By.xpath("//button[contains(text(), 'Not really, no')]"),
          "'No, not really' button");
  private final ILabel timer = getElementFactory().getLabel(By.cssSelector(".timer"), "timer");

  public void fillForm(String password, String email, String server) {
    this.password.clearAndType(password);
    this.email.clearAndType(email);
    this.server.clearAndType(server);
    dropDownButton.click();
    //TODO Couldn't declare it as PO field, there seems to be a trouble, need to find a way to lazy instantiate them
    List<IComboBox> comboElements = domainList.findChildElements(
        By.className(COMBOBOX_CHILD_LOCATION), ElementType.COMBOBOX);
    //Needed to remove the 'other' element, because there is always a small chance that it will be picked, and it ruins the test
    List<IComboBox> cleanComboElements = comboElements.stream()
        .filter(element -> !element.getText().equals("other")).toList();

    IComboBox randomDomain = cleanComboElements.get(
        RandomUtils.generateRandomNumber(cleanComboElements.size()));
    randomDomain.click();
  }

  public void acceptTerms() {
    terms.click();
  }

  public void clickNext() {
    next.click();
  }

  //Maybe this method could be separated into two methods waitForHelp() and hideHelp()
  public void hideHelp() {
    hideHelp.click();
  }

  public boolean helpFormIsHidden() {
    return !helpForm.state().isDisplayed();
  }

  //Maybe this method could be separated into two methods waitForCookies() and acceptCookies()
  public void acceptCookies() {
    acceptCookie.click();
  }

  public boolean cookiesIsHidden() {
    return !cookiesBanner.state().isDisplayed();
  }

  public String getTimerStart() {
    String timerString = timer.getText();
    return timerString.substring(3, 8);
  }
}
