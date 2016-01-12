package com.Bergie.pageobjects;

import com.Bergie.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Jonathan on 1/12/2016.
 */
public class EmailHomePage {
    public static SignInPage signOut(WebDriver driver) {
        WebUtil.click(driver, By.cssSelector(".gb_Za.gbii"));
        WebUtil.click(driver, By.id("gb_71"));
        WebUtil.waitForElementVisible(driver, By.id("Email"));
        return PageFactory.initElements(driver, SignInPage.class);
    }

    public static boolean doesInboxExist(WebDriver driver) {
        return WebUtil.doesElementExist(driver, By.partialLinkText("Inbox"));
    }

    public void clickComposeButton(WebDriver driver) {
        WebUtil.click(driver, By.cssSelector("div[role='button'][gh='cm']"));
    }

    public void fillInRecipient(WebDriver driver, String s) {
        WebUtil.waitForElementVisible(driver, By.cssSelector("textarea[name='to']"));
        WebUtil.clearAndSendKeys(driver, By.cssSelector("textarea[name='to']"), s);
    }

    public void fillInSubject(WebDriver driver, String s) {
        WebUtil.clearAndSendKeys(driver, By.cssSelector("input[name='subjectbox']"), s);
    }

    public void fillInEmailBody(WebDriver driver, String s) {
        WebUtil.clearAndSendKeys(driver, By.cssSelector("div[aria-label='Message Body']"), s);
    }

    public void clickSendEmail(WebDriver driver) {
        WebUtil.click(driver, By.cssSelector("div[aria-label*='Send'][aria-label*='Enter']"));
    }

    public void clickInboxWithNewEmail(WebDriver driver, String s) {
        WebUtil.click(driver, By.partialLinkText(s));
    }

    public EmailViewPage clickNewEmail(WebDriver driver) {
        WebUtil.click(driver, By.cssSelector("div[class='y6'] span[id] b"));
        return PageFactory.initElements(driver, EmailViewPage.class);
    }
}
