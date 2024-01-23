import PageObjects.AppointmentPage;
import PageObjects.LoginPage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Duration;

public class Appointment {
    private String username;
    private String password;
    private String appointment_url;
    private String comment;
    private String no_comment;
    private String facility;
    private String facility2;
    private String facility3;
    private String header;
    private String subheader;
    @BeforeMethod
    public void init() throws JsonSyntaxException, IOException, ParseException {
        String path = "src/main/resources/Login.json";
        String path_ = "src/main/resources/Appointment.json";
        JsonObject config = new Gson().fromJson(new String(Files.readAllBytes(Paths.get(path))), JsonObject.class);
        JsonObject config_ = new Gson().fromJson(new String(Files.readAllBytes(Paths.get(path_))), JsonObject.class);
        username = (String) config.get("username").getAsString();
        password = (String) config.get("password").getAsString();
        appointment_url = (String) config.get("appointment_url").getAsString();
        comment = (String) config_.get("comment").getAsString();
        no_comment = (String) config_.get("no_comment").getAsString();
        facility = (String) config_.get("facility").getAsString();
        facility2 = (String) config_.get("facility2").getAsString();
        facility3 = (String) config_.get("facility3").getAsString();
        header = (String) config_.get("header").getAsString();
        subheader = (String) config_.get("subheader").getAsString();
    }
    @Parameters({"URL"})
    @Test(description = "Booking without a comment")
    public void AppointmentCase1(String url){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);
        LoginPage landingPage = new LoginPage(driver);
        landingPage.AppLogin(username, password);
        Assert.assertEquals(driver.getCurrentUrl(), appointment_url);
        AppointmentPage appointmentPage =new AppointmentPage(driver);
        appointmentPage.BookAppointment(no_comment, facility, header, subheader);
        driver.close();
    }
    @Parameters({"URL"})
    @Test(description = "Booking with a comment and a different facility")
    public void AppointmentCase2(String url){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);
        LoginPage landingPage = new LoginPage(driver);
        landingPage.AppLogin(username, password);
        Assert.assertEquals(driver.getCurrentUrl(), appointment_url);
        AppointmentPage appointmentPage =new AppointmentPage(driver);
        appointmentPage.BookAppointment(comment, facility2, header, subheader);
        driver.close();
    }

    @Parameters({"URL"})
    @Test(description = "Submitting without filling the form")
    public void AppointmentCase3(String url){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);
        LoginPage landingPage = new LoginPage(driver);
        landingPage.AppLogin(username, password);
        Assert.assertEquals(driver.getCurrentUrl(), appointment_url);
        AppointmentPage appointmentPage =new AppointmentPage(driver);
        appointmentPage.BookAppointment2(comment, facility3, header, subheader);
        driver.close();
    }
}
