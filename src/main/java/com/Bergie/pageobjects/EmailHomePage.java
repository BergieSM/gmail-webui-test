package com.Bergie.pageobjects;

import com.Bergie.util.WebUtil;
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
    public static SignInPage signOut(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
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

    public void clickComposeButton(WebDriver driver) {
        WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        composeButton.click();

    }

    public void fillInRecipient(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement toTextArea = WebUtil.waitAndFindCss(driver, "textarea[name='to']");   //driver.findElement(By.cssSelector("textarea[name='to']"));
        toTextArea.clear();
        toTextArea.sendKeys(s);
    }

    public void fillInSubject(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement subjectTextArea = WebUtil.waitAndFindCss(driver, "input[name='subjectbox']");    //driver.findElement(By.cssSelector("input[name='subjectbox']"));
        subjectTextArea.clear();
        subjectTextArea.sendKeys(s);
    }

    public void fillInEmailBody(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement emailBodyTextEntry = WebUtil.waitAndFindCss(driver, "div[aria-label='Message Body']");   //driver.findElement(By.cssSelector("div[aria-label='Message Body']"));
        //apparently, "!" doesn't work with sendkeys very well.  See https://code.google.com/p/selenium/issues/detail?id=6411 for updated info.
        //System.out.println("body: "+body);    //debug
        //System.out.println("body as char array: "+body.toCharArray());    //debug
        emailBodyTextEntry.clear();
        emailBodyTextEntry.sendKeys(s);
    }

    public void clickSendEmail(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement emailSendButton = WebUtil.waitAndFindCss(driver, "div[aria-label*='Send'][aria-label*='Enter']");    //driver.findElement(By.cssSelector("div[aria-label*='Send'][aria-label*='Enter']"));
        emailSendButton.click();
    }

    public void clickInboxWithNewEmail(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(s)));
        //WebElement inboxLinkage =
        driver.findElement(By.partialLinkText(s)).click();
        //inboxLinkage.click();

    }

    public EmailViewPage clickNewEmail(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='y6'] span[id] b")));
        WebElement newEmail = WebUtil.waitAndFindCss(driver, "div[class='y6'] span[id] b"); //driver.findElement(By.cssSelector("div[class='y6'] span[id] b"));
        newEmail.click();

        //returning new email page
        return PageFactory.initElements(driver, EmailViewPage.class);
    }
}
