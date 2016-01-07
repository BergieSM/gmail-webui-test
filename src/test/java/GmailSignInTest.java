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

    @Test
    public void gmailLoginShouldBeSuccessful(){
        WebDriverWait wait = new WebDriverWait(driver,30);
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

    @After
    public void Cleanup(){
        driver.quit();
    }
}
//bergiesm@gmail.com
//b3rgB#RG##