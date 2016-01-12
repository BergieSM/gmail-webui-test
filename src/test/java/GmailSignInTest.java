import com.Bergie.pageobjects.EmailHomePage;
import com.Bergie.pageobjects.EmailViewPage;
import com.Bergie.pageobjects.SignInPage;
import com.Bergie.util.WebUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

/**
 * Created by Bergie on 1/7/2016.
 * Following tutorial at:
 * https://www.udemy.com/webdriver-test-automation-framework-step-by-step
 * Issue encountered during development:
 * https://code.google.com/p/selenium/issues/detail?id=6411
 */
public class GmailSignInTest {
    final String pass = JOptionPane.showInputDialog(JOptionPane.getRootFrame(),
            "Enter the gmail password", null, JOptionPane.PLAIN_MESSAGE);
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver, 30);

    @Test
    public void gmailLoginShAutoQARulesouldBeSuccessful() {
        //  Go to Gmail
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

        //  Fill in Username
        SignInPage.fillInUsername(driver, "bergiesm@gmail.com");

        //  Click Next
        SignInPage.clickNext(driver);

        //  Fill in Password
        SignInPage.fillInPassword(driver, pass);

        // Uncheck 'Stay Signed In'
        SignInPage.uncheckStaySignedIn(driver);

        //  Click Sign In
        EmailHomePage emailHomePage = SignInPage.clickSignIn(driver);

        //  Verify Sign In
        Assert.assertTrue("Inbox should exist if signed in", EmailHomePage.doesInboxExist(driver));

        //  Sign Out
        signInPage = EmailHomePage.signOut(driver);

        //  Verify Sign Out
        Assert.assertTrue("Should find email field if signed out", signInPage.isEmailDisplayed(driver));
    }

    @Test
    //This test assumes that the gmail email list has no unread messages in it to begin with.  Check before first run.
    public void gmailSendAndReceiveEmailTest() {
        //All from previous example test
        //  Go to Gmail
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

        //  Fill in Username
        SignInPage.fillInUsername(driver, "bergiesm@gmail.com");

        //  Click Next
        SignInPage.clickNext(driver);

        //  Fill in Password
        SignInPage.fillInPassword(driver, pass);

        // Uncheck 'Stay Signed In'
        SignInPage.uncheckStaySignedIn(driver);

        //  Click Sign In
        EmailHomePage emailHomePage = SignInPage.clickSignIn(driver);

        //  Verify Sign In
        Assert.assertTrue("Inbox should exist if signed in", EmailHomePage.doesInboxExist(driver));

        //New content here
        //  Click Compose
        emailHomePage.clickComposeButton(driver);

        //  Fill in Recipient
        emailHomePage.fillInRecipient(driver, "BergieSM@Gmail.com");

        //  Fill in Subject
        final String subject = "Gmail Send Email Test";
        emailHomePage.fillInSubject(driver, subject);

        //  Fill in Email Body
        final String body = "Hello Email World";
        emailHomePage.fillInEmailBody(driver, body);

        //  Click Send
        emailHomePage.clickSendEmail(driver);

        //  Click Inbox Again
        emailHomePage.clickInboxWithNewEmail(driver, "Inbox (1)");

        //  Click Email
        EmailViewPage emailViewPage = emailHomePage.clickNewEmail(driver);

        //  Verify the email subject and body are both correct
        String actualSubjectText = emailViewPage.getEmailSubjectText(driver);
        Assert.assertEquals("Subject should be the same", subject, actualSubjectText);

        String actualBodyText = emailViewPage.getEmailBodyText(driver);
        Assert.assertEquals("Email Body Text should be the same", body, actualBodyText);

        //  Sign out
        EmailHomePage.signOut(driver);
    }

    @After
    public void Cleanup() {
        driver.quit();
    }
}

//bergiesm@gmail.com
//
//136.206.107.93
//HEANET HEAnet Limited,IE
//Just stop.