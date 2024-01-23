package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "menu-toggle")WebElement menuBtn;
    @FindBy(linkText = "Login")WebElement loginMenu;
    @FindBy(css = "h2")WebElement loginHeader;
    @FindBy(id = "txt-username")WebElement username;
    @FindBy(id = "txt-password")WebElement password;
    @FindBy(id = "btn-login")WebElement loginBtn;
    @FindBy(css = ".lead.text-danger")WebElement errLogin;


    public void AppLogin(String user_name, String pass_word){
        menuBtn.click();
        loginMenu.click();
        Assert.assertTrue(loginHeader.isDisplayed());
        username.sendKeys(user_name);
        password.sendKeys(pass_word);
        loginBtn.click();
    }

    public void loginErr(String err){
        Assert.assertTrue(errLogin.isDisplayed());
        Assert.assertEquals(errLogin.getText(), err);
    }

}