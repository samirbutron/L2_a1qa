package samirbutron.page;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import samirbutron.utils.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Card1 extends Form {

    public Card1() {
        super(By.className("login-form__container"), "Card 1 form");
    }

    private final ITextBox password = AqualityServices.getElementFactory().getTextBox(By.xpath("//div[@class='login-form__field-row']//child::input"), "Password text box");
    private final ITextBox email = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[@placeholder = 'Your email']"), "Email text box");
    private final  ITextBox server = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[@placeholder = 'Domain']"), "Mail server text box");
    private final IButton dropDownButton = AqualityServices.getElementFactory().getButton(By.className("dropdown__opener"), "Dropdown opener");
    private final IComboBox domainList = AqualityServices.getElementFactory().getComboBox(By.className("dropdown__list"), "Domain");
    private static final String COMBOBOX_CHILD_LOCATION = "dropdown__list-item";
    private final ILabel terms = getElementFactory().getLabel(By.className("checkbox__box"), "Terms check box");
    private final IButton next = getElementFactory().getButton(By.className("button--secondary"), "Next button");

    //I'm sure this is not a correct Element Interface for this element...
    private final ILabel helpForm = AqualityServices.getElementFactory().getLabel(By.xpath("help-form"), "Help form");
    private final IButton hideHelp = AqualityServices.getElementFactory().getButton(By.xpath("//span[@class='highlight']//parent::button"), "'Send to bottom'");

    //I'm sure this is not a correct Element Interface for this element...
    private final ILabel cookiesBanner = AqualityServices.getElementFactory().getLabel(By.className("cookies"), "Cookies banner");
    private final IButton acceptCookie = AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(text(), 'Not really, no')]"), "'No, not really' button");
    private final ILabel timer = getElementFactory().getLabel(By.cssSelector(".timer"), "timer");

    public void fillForm (String password, String email, String server){
        this.password.clearAndType(password);
        this.email.clearAndType(email);
        this.server.clearAndType(server);
        dropDownButton.click();
        List<IComboBox> comboElements = domainList.findChildElements(By.className(COMBOBOX_CHILD_LOCATION), ElementType.COMBOBOX);
        //Needed to remove the 'other' element, because there is always a small chance that it will be picked, and it ruins the test
        List<IComboBox> cleanComboElements = comboElements.stream().filter(element -> !element.getText().equals("other") )
                .collect(Collectors.toList());

        IComboBox randomDomain = cleanComboElements.get(RandomUtils.generateRandomNumber(cleanComboElements.size()));
        randomDomain.click();
    }
    public void acceptTerms(){
        terms.click();
    }
    public void sendForm() {
        next.click();
    }

    //Maybe this method could be separated into two methods waitForHelp() and hideHelp()
    public boolean hideHelp() {
         if(helpForm.state().waitForDisplayed()){
             hideHelp.click();
             return true;
         }
         return false;
    }
    public boolean helpFormIsHidden(){
        return helpForm.state().isDisplayed() ? false : true;
    }

    //Maybe this method could be separated into two methods waitForCookies() and acceptCookies()
    public void acceptCookies() {
        cookiesBanner.state().waitForDisplayed();
        acceptCookie.click();
    }

    public boolean cookiesIsHidden(){
        return cookiesBanner.state().isDisplayed() ? false : true;
    }
    public String getTimerStart() {
        String timerString = timer.getText();
        return timerString.substring(3,8);
    }

}
