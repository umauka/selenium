import PageObjects.LoginPage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Duration;

public class Login {
    private String username;
    private String password;
    private String false_username;
    private String false_password;
    private String page_title;
    private String login_error;
    private String appointment_url;

    @BeforeMethod
    public void init() throws JsonSyntaxException, IOException, ParseException {
        String path = "src/main/resources/Login.json";
        JsonObject config = new Gson().fromJson(new String(Files.readAllBytes(Paths.get(path))), JsonObject.class);
        username = (String) config.get("username").getAsString();
        password = (String) config.get("password").getAsString();
        false_username = (String) config.get("false_username").getAsString();
        false_password = (String) config.get("false_password").getAsString();
        page_title = (String) config.get("page_title").getAsString();
        login_error = (String) config.get("login_error").getAsString();
        appointment_url = (String) config.get("appointment_url").getAsString();
    }

    @Parameters({"URL"})
    @Test(description = "Check Url Validity")
    public void CheckUrl(String url){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);
        Assert.assertEquals(driver.getTitle(), page_title);
        driver.close();
    }

    @Parameters({"URL"})
    @Test(description = "Wrong Username")
    public void LoginCase1(String url){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);
        LoginPage landingPage = new LoginPage(driver);
        landingPage.AppLogin(false_username, password);
        landingPage.loginErr(login_error);
        driver.close();
    }

    @Parameters({"URL"})
    @Test(description = "Wrong Password")
    public void LoginCase2(String url){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);
        LoginPage landingPage = new LoginPage(driver);
        landingPage.AppLogin(username, false_password);
        landingPage.loginErr(login_error);
        driver.close();
    }

    @Parameters({"URL"})
    @Test(description = "Valid Credentials")
    public void LoginCase3(String url){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);
        LoginPage landingPage = new LoginPage(driver);
        landingPage.AppLogin(username, password);
        Assert.assertEquals(driver.getCurrentUrl(), appointment_url);
        driver.close();
    }

}
