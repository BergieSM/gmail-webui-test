import com.thoughtworks.selenium.webdriven.commands.IsVisible;
import org.apache.commons.collections.Predicate;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Bergie on 1/7/2016.
 * Following tutorial at:
 * https://www.udemy.com/webdriver-test-automation-framework-step-by-step
 */
public class GmailSignInTest {
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver,30);

    @Test
    public void gmailLoginShouldBeSuccessful(){
        //WebDriverWait wait = new WebDriverWait(driver,30);
        //  Go to Gmail
        driver.get("http://gmail.com");
        //  Fill in Username
        WebElement usernameTextBox = driver.findElement(By.id("Email"));
        usernameTextBox.clear();
        usernameTextBox.sendKeys("bergiesm@gmail.com");
            //  Click Next
            WebElement usernameNextButton = driver.findElement(By.id("next"));
            usernameNextButton.click();
        //  Fill in Password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
        WebElement passwordTextBox = driver.findElement(By.id("Passwd"));
        passwordTextBox.clear();
        passwordTextBox.sendKeys("b3rgB#RG##");
            // Uncheck 'Stay Signed In'
            WebElement staySignedInCheckbox = driver.findElement(By.id("PersistentCookie"));
            String staySignedInCheckedAttribute = staySignedInCheckbox.getAttribute("checked");
            //System.out.println("staySignedInCheckedAttribute = "+staySignedInCheckedAttribute);   DEBUG
            //System.out.println(staySignedInCheckedAttribute); DEBUG
            if(staySignedInCheckedAttribute.equals("true")){
                staySignedInCheckbox.click();
                //staySignedInCheckedAttribute =  staySignedInCheckbox.getAttribute("checked"); DEBUG
                //System.out.println("staySignedInCheckedAttribute after click = "+staySignedInCheckedAttribute);   DEBUG
            }
        //  Click Sign In
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();
        //  Verify Sign In
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        Assert.assertTrue("Inbox should exist",driver.findElement(By.partialLinkText("Inbox")).getSize().getWidth()>0);
        //  Sign Out
        WebElement profilePicture = driver.findElement(By.cssSelector(".gb_Za.gbii"));
        profilePicture.click();
        WebElement signOutLink = driver.findElement(By.id("gb_71"));
        signOutLink.click();
        //  Verify Sign Out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        Assert.assertTrue("Should find email field if signed out",driver.findElement(By.id("Email")).isDisplayed());
    }

    @Test
    public void gmailSendAndReceiveEmailTest(){
    //All from previous example test
        //  Go to Gmail
        driver.get("http://gmail.com");
        //  Fill in Username
        WebElement usernameTextBox = driver.findElement(By.id("Email"));
        usernameTextBox.clear();
        usernameTextBox.sendKeys("bergiesm@gmail.com");
        //  Click Next
        WebElement usernameNextButton = driver.findElement(By.id("next"));
        usernameNextButton.click();
        //  Fill in Password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
        WebElement passwordTextBox = driver.findElement(By.id("Passwd"));
        passwordTextBox.clear();
        passwordTextBox.sendKeys("b3rgB#RG##");
        // Uncheck 'Stay Signed In'
        WebElement staySignedInCheckbox = driver.findElement(By.id("PersistentCookie"));
        String staySignedInCheckedAttribute = staySignedInCheckbox.getAttribute("checked");
        //System.out.println("staySignedInCheckedAttribute = "+staySignedInCheckedAttribute);   DEBUG
        //System.out.println(staySignedInCheckedAttribute); DEBUG
        if(staySignedInCheckedAttribute.equals("true")){
            staySignedInCheckbox.click();
            //staySignedInCheckedAttribute =  staySignedInCheckbox.getAttribute("checked"); DEBUG
            //System.out.println("staySignedInCheckedAttribute after click = "+staySignedInCheckedAttribute);   DEBUG
        }
        //  Click Sign In
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();
        //  Verify Sign In
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        Assert.assertTrue("Inbox should exist",driver.findElement(By.partialLinkText("Inbox")).getSize().getWidth()>0);
    //New content here
        //  Click Compose
        WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        composeButton.click();
        //  Fill in Recipient
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(":cw")));
        WebElement emailRecipientTextArea = driver.findElement(By.cssSelector("textarea[id=':cw']"));
        emailRecipientTextArea.clear();
        emailRecipientTextArea.sendKeys("BergieSM@Gmail.com");
        //  Fill in Subject
        WebElement emailSubjectTextEntry = driver.findElement(By.cssSelector("textarea[id=':db']"));
        emailSubjectTextEntry.clear();
        emailSubjectTextEntry.sendKeys("SUBJECT HERE");
        //  Fill in Email Body
        WebElement emailBodyTextEntry = driver.findElement(By.cssSelector("textarea[id=':gy']"));
        emailBodyTextEntry.clear();
        emailBodyTextEntry.sendKeys("EMAIL BODY HERE");
        //  Click Send
        WebElement emailSendButton = driver.findElement(By.cssSelector("div[role='button'][id=':dl']"));
        emailSendButton.click();
        //  Click Inbox Again
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        driver.findElement(By.partialLinkText("Inbox")).click();
        //  Click Email

        //  Verify the email subject and body are both correct
        //  Sign out
    }

    @After
    public void Cleanup(){
        driver.quit();
    }
}
//bergiesm@gmail.com
//b3rgB#RG##