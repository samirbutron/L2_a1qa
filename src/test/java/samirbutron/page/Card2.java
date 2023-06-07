package samirbutron.page;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import samirbutron.utils.RandomUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Card2 extends Form {

    private Logger logger = AqualityServices.getLogger();
    public Card2() {
        super(By.className("avatar-and-interests__form"), "Card 2 form");
    }

    private final IButton upload = AqualityServices.getElementFactory().getButton(By.className("avatar-and-interests__upload-button"), "Upload button");
    private final ILink userPhoto = AqualityServices.getElementFactory().getLink(By.cssSelector("background-image"), "User photo");

    private final ICheckBox checkBox = AqualityServices.getElementFactory().getCheckBox(By.className("avatar-and-interests__interests-list"), "Checkbox");
    private static final String CHECKBOX_ELEMENTS_LOCATION = "//input[@type='checkbox']";

    public void pickCheckboxes(){
        List<ICheckBox> checkboxes = checkBox.findChildElements(By.className(CHECKBOX_ELEMENTS_LOCATION), "Checkbox element", ElementType.CHECKBOX);
        String selectAll = "Select all"; //Checkbox to be removed
        String unselectAll = "Unselect all"; //Checkbox to be removed
        List<ICheckBox> newList = checkboxes.stream()
                .peek(element -> {
                    if (element.getText().equals(unselectAll)) {
                        element.click();
                    }
                })
                .filter(element -> !element.getText().equals(selectAll) && !element.getText().equals(unselectAll))
                .collect(Collectors.toList());
        Set<Integer> randomNumbers = RandomUtils.generateThreeRandomNumbers(newList.size());
        for (Integer num : randomNumbers){
            newList.get(num).click();
        }

    }

    public boolean uploadImage(){
        upload.click();
        //Figure how to upload image
        return userPhoto.state().isDisplayed();

    }
}