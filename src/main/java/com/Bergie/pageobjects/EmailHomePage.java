package com.Bergie.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Jonathan on 1/12/2016.
 */
public class EmailHomePage {
    public static SignInPage signOut(WebDriver driver, WebDriverWait wait) {
        WebElement profilePicture = driver.findElement(By.cssSelector(".gb_Za.gbii"));
        profilePicture.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gb_71")));
        WebElement signOutLink = driver.findElement(By.id("gb_71"));
        signOutLink.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        return PageFactory.initElements(driver, SignInPage.class);
    }

    public static boolean doesInboxExist(WebDriver driver) {
        return driver.findElement(By.partialLinkText("Inbox")).getSize().getWidth() > 0;
    }
}
