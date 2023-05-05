package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementHelper;

public class BasketPage {
    ElementHelper elementHelper;

    By title = By.className("android.widget.TextView");

    public BasketPage(WebDriver driver) {
        this.elementHelper = new ElementHelper(driver);
    }

    public String getTitle() {
        return elementHelper.getText(title);
    }


}
