package com.Bergie.pageobjects;

import com.Bergie.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Jonathan on 1/12/2016.
 */
public class EmailViewPage {
    public String getEmailSubjectText(WebDriver driver) {
        return WebUtil.getElementText(driver, By.cssSelector("h2[class='hP']"));
    }

    public String getEmailBodyText(WebDriver driver) {
        return WebUtil.getElementText(driver, By.cssSelector("div[class='a3s'] div[dir='ltr']"));
    }
}
