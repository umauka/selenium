package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class AppointmentPage {
    WebDriver driver;
    public AppointmentPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "combo_facility")WebElement facilityList;
    @FindBy(css = "h2")WebElement formHeader;
    @FindBy(id = "chk_hospotal_readmission")WebElement readmissionChk;
    @FindBy(id = "radio_program_medicare")WebElement medicarePrg;
    @FindBy(id = "radio_program_medicaid")WebElement medicaidPrg;
    @FindBy(id = "radio_program_none")WebElement noProgram;
    @FindBy(id = "txt_visit_date")WebElement dateToday;
    @FindBy(id = "txt_comment")WebElement commentBox;
    @FindBy(id = "btn-book-appointment")WebElement submitBtn;
    @FindBy(css = "p")WebElement subheader;
    @FindBy(id = "facility")WebElement facility;
    @FindBy(id = "hospital_readmission")WebElement readmission;
    @FindBy(id = "program")WebElement program;
    @FindBy(id = "visit_date")WebElement visitDate;
    @FindBy(id = "comment")WebElement comment;
    @FindBy(css = ".datepicker.datepicker-dropdown.dropdown-menu")WebElement calendar;
    public void BookAppointment(String comment_, String facility_, String header_, String subheader_){
        facilityList.click();
        Select dropDown = new Select(facilityList);
        dropDown.selectByValue(facility_);
        formHeader.click();
        medicaidPrg.click();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(formatter);
        dateToday.sendKeys(formattedDate);
        commentBox.sendKeys(comment_);
        submitBtn.click();
        Assert.assertEquals(formHeader.getText(), header_);
        Assert.assertEquals(subheader.getText(), subheader_);
        Assert.assertEquals(facility.getText(), facility_);
        Assert.assertEquals(comment.getText(), comment_);
    }

    public void BookAppointment2(String comment_, String facility_, String header_, String subheader_){
        submitBtn.click();
        Assert.assertTrue(calendar.isDisplayed());
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(formatter);
        dateToday.sendKeys(formattedDate);
        commentBox.sendKeys(comment_);
        submitBtn.click();
        Assert.assertEquals(formHeader.getText(), header_);
        Assert.assertEquals(subheader.getText(), subheader_);
        Assert.assertEquals(facility.getText(), facility_);
        Assert.assertEquals(comment.getText(), comment_);
    }
}
