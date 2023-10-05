package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.Photographer;

import java.util.List;
import java.util.Random;

public class ResultsPage {

    private WebDriver driver;

    @FindBy(css = "ol[class*='ui-search-layout'] li")
    private List<WebElement> results;

    private By price = By.cssSelector("div[class*='ui-search-price--size-medium'] span[class='price-tag-text-sr-only']");

    private By name = By.tagName("h2");

    private Photographer photographer;

    public ResultsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        photographer = new Photographer(driver);
    }

    @Step("Obteniendo item aleatorio")
    public WebElement getRandomResult() {
        photographer.takePhoto();
        Random rand = new Random();
        WebElement randomElement = results.get(rand.nextInt(results.size()));
        return randomElement;
    }

    @Step("Seleccionando item aleatorio")
    public ResultModel clickOnRandomItem() {
        WebElement randomElement = getRandomResult();

        ResultModel expectedResultModel = new ResultModel();
        expectedResultModel.setName(randomElement.findElement(name).getText());
        expectedResultModel.setPrice(randomElement.findElement(price).getText());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", randomElement);
        randomElement.findElement(name).click();
        return expectedResultModel;

    }

}