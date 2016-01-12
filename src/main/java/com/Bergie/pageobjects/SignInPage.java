package com.Bergie.pageobjects;

import com.Bergie.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Jonathan on 1/11/2016.
 */
public class SignInPage {
    public static void fillInUsername(WebDriver driver, String s) {
        WebUtil.clearAndSendKeys(driver, By.id("Email"), s);
    }

    public static void clickNext(WebDriver driver) {
        WebUtil.click(driver, By.id("next"));
    }

    public static void fillInPassword(WebDriver driver, String pass) {
        WebUtil.clearAndSendKeys(driver, By.id("Passwd"), pass);
    }

    public static void uncheckStaySignedIn(WebDriver driver) {
        WebElement staySignedInCheckbox = driver.findElement(By.id("PersistentCookie"));
        String staySignedInCheckedAttribute = staySignedInCheckbox.getAttribute("checked");
        if (staySignedInCheckedAttribute.equals("true")) {
            WebUtil.click(driver, By.id("PersistentCookie"));
        }
    }

    public static EmailHomePage clickSignIn(WebDriver driver) {
        WebUtil.click(driver, By.id("signIn"));
        WebUtil.waitForElementVisible(driver, By.partialLinkText("Inbox"));
        return PageFactory.initElements(driver, EmailHomePage.class);
    }

    public boolean isEmailDisplayed(WebDriver driver) {
        return WebUtil.doesElementExist(driver, By.id("Email"));
    }
}
