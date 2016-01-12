package com.Bergie.pageobjects;

import com.Bergie.util.WebUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Jonathan on 1/12/2016.
 */
public class EmailViewPage {
    public String getEmailSubjectText(WebDriver driver) {
        WebElement subjectArea = WebUtil.waitAndFindCss(driver, "h2[class='hP']");
        return subjectArea.getText();
    }

    public String getEmailBodyText(WebDriver driver) {
        WebElement bodyArea = WebUtil.waitAndFindCss(driver, "div[class='a3s'] div[dir='ltr']");
        return bodyArea.getText();
    }
}
