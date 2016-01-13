import com.Bergie.categories.Critical;
import com.Bergie.categories.Major;
import com.Bergie.pageobjects.EmailHomePage;
import com.Bergie.pageobjects.EmailViewPage;
import com.Bergie.pageobjects.SignInPage;
import com.Bergie.util.WebUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Bergie on 1/7/2016.
 * Following tutorial at:
 * https://www.udemy.com/webdriver-test-automation-framework-step-by-step
 */
public class GmailSignInTest {
    String pass;
    WebDriver driver;

    @Before
    public void Buildup() {
        System.out.println(System.getProperties().getProperty("BSMpass"));
        pass = System.getProperty("BSMpass");
        //pass = JOptionPane.showInputDialog(JOptionPane.getRootFrame(),"Enter the gmail password", null, JOptionPane.PLAIN_MESSAGE);
        driver = new FirefoxDriver();
    }

    @Category({Critical.class})
    @Test
    public void gmailLoginShouldBeSuccessful() {
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

    @Category({Major.class})
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