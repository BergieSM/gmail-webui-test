package com.Bergie.util;

import com.Bergie.pageobjects.SignInPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Jonathan on 1/11/2016.
 */
public class WebUtil {
    public static SignInPage goToSignInPage(WebDriver driver) {
        driver.get("http://gmail.com");
        return PageFactory.initElements(driver, SignInPage.class);
    }

    public static WebElement waitAndFindCss(WebDriver driver, String cssString) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssString)));
        return driver.findElement(By.cssSelector(cssString));
    }

    public static WebElement waitAndFindID(WebDriver driver, String idString) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(idString)));
        return driver.findElement(By.id(idString));
    }
}
