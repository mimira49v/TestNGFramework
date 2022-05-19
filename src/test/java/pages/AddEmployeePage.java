package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class AddEmployeePage extends CommonMethods {

    @FindBy(id="firstName")
    public WebElement firstNameField;

    @FindBy(id="middleName")
    public WebElement middleNameField;

    @FindBy(id="lastName")
    public WebElement lastNameField;

    @FindBy(id="btnSave")
    public WebElement saveButton;

    @FindBy(id="employeeId")
    public WebElement employeeID;

    @FindBy (xpath="//div[@id='profile-pic']/h1")
    public WebElement addedEmployeeVerification;

    @FindBy(id="photofile")
    public WebElement uploadPhoto;

    @FindBy(id="chkLogin")
    public WebElement checkBox;

    @FindBy(id="user_name")
    public WebElement createUsername;

    @FindBy(id="user_password")
    public WebElement createPassword;

    @FindBy(id="re_password")
    public WebElement confirmPassword;

    @FindBy (xpath = "//*[@id='resultTable']/tbody/tr/td[2]")
    public List<WebElement> rowData;

    public AddEmployeePage(){
        PageFactory.initElements(driver, this);
    }

}