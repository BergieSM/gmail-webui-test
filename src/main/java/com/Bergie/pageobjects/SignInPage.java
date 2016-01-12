package com.Bergie.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Jonathan on 1/11/2016.
 */
public class SignInPage {
    public static void fillInUsername(WebDriver driver, String s) {
        WebElement usernameTextBox = driver.findElement(By.id("Email"));
        usernameTextBox.clear();
        usernameTextBox.sendKeys(s);
    }

    public static void clickNext(WebDriver driver) {
        WebElement usernameNextButton = driver.findElement(By.id("next"));
        usernameNextButton.click();
    }

    public static void fillInPassword(WebDriver driver, WebDriverWait wait, String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
        WebElement passwordTextBox = driver.findElement(By.id("Passwd"));
        passwordTextBox.clear();
        passwordTextBox.sendKeys(pass);
    }

    public static void uncheckStaySignedIn(WebDriver driver, WebDriverWait wait) {
        WebElement staySignedInCheckbox = driver.findElement(By.id("PersistentCookie"));
        String staySignedInCheckedAttribute = staySignedInCheckbox.getAttribute("checked");
        //System.out.println("staySignedInCheckedAttribute = "+staySignedInCheckedAttribute);   DEBUG
        //System.out.println(staySignedInCheckedAttribute); DEBUG
        if (staySignedInCheckedAttribute.equals("true")) {
            staySignedInCheckbox.click();
            //staySignedInCheckedAttribute =  staySignedInCheckbox.getAttribute("checked"); DEBUG
            //System.out.println("staySignedInCheckedAttribute after click = "+staySignedInCheckedAttribute);   DEBUG
        }

    }

    public static EmailHomePage clickSignIn(WebDriver driver, WebDriverWait wait) {
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        return PageFactory.initElements(driver, EmailHomePage.class);
    }

    public boolean isEmailDisplayed(WebDriver driver) {
        return driver.findElement(By.id("Email")).isDisplayed();
    }
}
