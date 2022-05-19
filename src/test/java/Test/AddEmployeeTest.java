package Test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends CommonMethods {

    @Test
    //adding a single employee
    public void addEmployee() {
        login.LoginMethod(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));
        click(employeeSearchPage.pimOption);
        click(employeeSearchPage.addEmployeeOption);
        sendText(addEmployeePage.firstNameField, "Mmiimire");
        sendText(addEmployeePage.middleNameField, "SSSSHahaha");
        sendText(addEmployeePage.lastNameField, "NIkolajeci");
        String emplID = addEmployeePage.employeeID.getAttribute("value");
        click(addEmployeePage.saveButton);

        click((employeeSearchPage.pimOption));
        click(employeeSearchPage.empListOption);
        sendText(employeeSearchPage.searchID, emplID);
        click(employeeSearchPage.searchButton);

        List<WebElement> rowData = addEmployeePage.rowData;
        for (WebElement data : rowData) {
            String actualID = data.getText();
            System.out.println(emplID + " " + actualID);
            Assert.assertEquals(emplID, actualID);
        }
    }
    @Test
    public void addMultipleEmployee() throws InterruptedException {
//              read the employee data from Excel file
//              assert that u have successfully added the employee

        login.LoginMethod(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));
        click(dash.pimOption);
        click(dash.addEmployeeOption);

        List<Map<String, String>> employeeNames = ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH, "Sheet1");
        Iterator<Map<String, String>> itr = employeeNames.iterator();
        while (itr.hasNext()) {
            //it returns a key and value for an employee
            Map<String, String> mapNewEmp = itr.next();
            //filling up all the fields with data coming from Excel file
            sendText(addEmployeePage.firstNameField, mapNewEmp.get("FirstName"));
            sendText(addEmployeePage.middleNameField, mapNewEmp.get("MiddleName"));
            sendText(addEmployeePage.lastNameField, mapNewEmp.get("LastName"));

            //it will fetch the employee id from attribute
            String empIDValue = addEmployeePage.employeeID.getAttribute("value");

            //uploading the photo
            sendText(addEmployeePage.uploadPhoto, mapNewEmp.get("Photograph"));

            //clicking on a checkbox
            if (!addEmployeePage.checkBox.isSelected()) {
                click(addEmployeePage.checkBox);
            }

            //filling up other fields
            sendText(addEmployeePage.createUsername, mapNewEmp.get("Username"));
            sendText(addEmployeePage.createPassword, mapNewEmp.get("Password"));
            sendText(addEmployeePage.confirmPassword, mapNewEmp.get("Password"));

            click(addEmployeePage.saveButton);

            Thread.sleep(2000);
            //verifying the employee
            click(employeeSearchPage.empListOption);
            sendText(employeeSearchPage.searchID, empIDValue);
            click(employeeSearchPage.searchButton);

            List<WebElement> rowData = addEmployeePage.rowData;
            for (WebElement data : rowData) {
                String actualID = data.getText();
                Assert.assertEquals(actualID, empIDValue);
            }
            click(employeeSearchPage.addEmployeeOption);
        }
    }
}
