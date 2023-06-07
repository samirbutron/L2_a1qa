package samirbutron.page;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {

    public MainPage() {
        super(By.className("start__button"), "Main page");
    }

    private final ILink iLink = AqualityServices.getElementFactory().getLink(By.className("start__link"), "Link from 'HERE' text");

    public void clickLink (){
        iLink.click();
    }

}
