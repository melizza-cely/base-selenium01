package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Photographer;

public class HomePage {

    @FindBy(id = "cb1-edit")
    private WebElement searchBox;

    private Photographer photographer;
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void searchFor(String item) {
        photographer.takePhoto();
        searchBox.sendKeys(item);
        searchBox.sendKeys(Keys.ENTER);
    }
}