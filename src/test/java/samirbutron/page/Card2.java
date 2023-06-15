package samirbutron.page;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.forms.Form;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import samirbutron.utils.RandomUtils;
import samirbutron.utils.RobotUtil;

public class Card2 extends Form {

  public Card2() {
    super(By.xpath("//div[@class='avatar-and-interests__form']"), "Card 2 form");
  }

  private static final ISettingsFile testConfig = new JsonSettingsFile("testconfig.json");
  private static final String IMAGE_PATH = testConfig.getValue("/imagePath").toString();
  private final IButton upload = AqualityServices.getElementFactory()
      .getButton(By.xpath("//a[@class='avatar-and-interests__upload-button']"), "Upload button");
  private final ICheckBox checkBox = AqualityServices.getElementFactory()
      .getCheckBox(By.xpath("//div[@class='avatar-and-interests__interests-list']"), "Checkbox");
  private final static String selectAll = testConfig.getValue("/selectAllCBElement").toString();
  private final static String unselectAll = testConfig.getValue("/selectAllCBElement").toString();
  private static final String CHECKBOX_ELEMENTS_LOCATION = "//label[@class='checkbox__label']";

  private final IButton next = AqualityServices.getElementFactory()
      .getButton(By.xpath("//button[@name='button' and contains(text(), 'Next')]"), "Next button");

  public void pickCheckboxes() {
    List<ICheckBox> checkboxes = checkBox.findChildElements(By.xpath(CHECKBOX_ELEMENTS_LOCATION),
        "Checkbox element", ElementType.CHECKBOX);
    List<ICheckBox> newList = checkboxes.stream()
        .peek(element -> {
          if (element.getAttribute("for").equals(unselectAll)) {
            element.click();
          }
        })
        .filter(element -> !element.getAttribute("for").equals(selectAll) && !element.getAttribute(
            "for").equals(unselectAll)).toList();

    Set<Integer> randomNumbers = RandomUtils.generateThreeRandomNumbers(newList.size());
    for (Integer num : randomNumbers) {
      newList.get(num).check();
    }

  }

  public void uploadImage() {
    upload.clickAndWait();
    RobotUtil.delay(3000);
    String currentDirectoryPath = Paths.get("").toAbsolutePath().toString();
    String relativeImagePath = Paths.get(IMAGE_PATH).toString();
    String absoluteImagePathString = currentDirectoryPath + relativeImagePath;
    RobotUtil.robotType(absoluteImagePathString);
  }

  public void clickNext() {
    next.click();
  }
}