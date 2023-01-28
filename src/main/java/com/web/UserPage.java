package com.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class UserPage extends BaseTest {

    public static By addUserBtn=By.xpath("//button[contains(text(),'Add User')]");
    public static By fistNameTxt=By.name("FirstName");
    public static By lastNameTxt=By.name("LastName");
    public static By passwordTxt=By.name("Password");
    public static By companyRdo=By.xpath("//input[@name='optionsRadios'][@value='16']");
    public static By roleSelect=By.name("RoleId");
    public static By emailTxt=By.name("Email");
    public static By cellTxt=By.name("Mobilephone");
    public static By saveBtn=By.xpath("//button[text()='Save']");
    public static By searchTxt=By.xpath("//input[@ng-model='searchValue']");
    public static By tableRows=By.xpath("//table[@table-title='Smart Table example']//tr[@class='smart-table-data-row ng-scope']");
    public static By tableCells=By.xpath("//table[@table-title='Smart Table example']//tr[@class='smart-table-data-row ng-scope']//td[text()]");
    public static By userNameTxt=By.name("UserName");

    public void typeSearchTxt(String name)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchTxt));
        driver.findElement(searchTxt).sendKeys(name);
    }

    public void typeFirstName(String name)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fistNameTxt));
        driver.findElement(fistNameTxt).sendKeys(name);
    }

    public void typeUserName(String name)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameTxt));
        driver.findElement(userNameTxt).sendKeys(name);
    }

    public void typePassword(String name)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordTxt));
        driver.findElement(passwordTxt).sendKeys(name);
    }

    public void typeLastName(String name)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameTxt));
        driver.findElement(lastNameTxt).sendKeys(name);
    }

    public void typeEmail(String name)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailTxt));
        driver.findElement(emailTxt).sendKeys(name);
    }

    public void typeCell(String name)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cellTxt));
        driver.findElement(cellTxt).sendKeys(name);
    }

    public void clickOnCompanyRadio()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(companyRdo));
        driver.findElement(companyRdo).click();
    }

    public void clickOnAddUser()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addUserBtn));
        driver.findElement(addUserBtn).click();
    }

    public void selectByVisibleTextRole(String role)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(roleSelect));
        Select select=new Select(driver.findElement(roleSelect));
        select.selectByVisibleText(role);
    }

    public void clickOnSave()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveBtn));
        driver.findElement(saveBtn).click();
    }

    public void verifyCallOnTable(String name)
    {
        Assert.assertTrue(verifyCellValue(name),name+" is missing on table");
    }

    public boolean verifyCellValue(String cellValue)
    {
        boolean flag=false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));
        List<WebElement> rows=driver.findElements(tableRows);
        for (WebElement ele:rows
             ) {
            List<WebElement> cells=driver.findElements(tableCells);
            for (WebElement cell:cells
                 ) {
                if(cell.getText().equals(cellValue)) {
                    flag = true;
                    break;
                }

            }
            if(flag)
                break;;
        }
        return flag;
    }

}
