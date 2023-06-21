package samirbutron.PO;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

public class Page {

  private final ITextBox searchBar = AqualityServices.getElementFactory()
      .getTextBox(By.className("gLFyf"), "Search bar");
  private final ILabel logo = AqualityServices.getElementFactory()
      .getLabel(By.className("lnXdpd"), "Logo");

  public boolean searchBarIsPresent() {
    return searchBar.state().isDisplayed();
  }

  public boolean logoIsPresent() {
    return logo.state().isDisplayed();
  }
}
