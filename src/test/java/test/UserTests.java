package test;

import com.web.BaseTest;
import com.web.UserPage;
import com.web.Utility;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {

    UserPage userPage=new UserPage();

    @Test(description = "Create User and verify the Users")
    public void createAndVerifyUser() {

        String userName="User_"+ Utility.getCurrentTimeStamp();
        String fistName="Sai_"+Utility.getCurrentTimeStamp();
        String lastName="krishna_"+Utility.getCurrentTimeStamp();
        userPage.clickOnAddUser();
        userPage.typeFirstName(fistName);
        userPage.typeLastName(lastName);

        userPage.typeUserName(userName);
        userPage.typePassword("saiKrishna#123");
        userPage.clickOnCompanyRadio();
        userPage.selectByVisibleTextRole("Customer");
        userPage.typeEmail("sai@testgmail.com");
        userPage.typeCell("1234567890");
        userPage.clickOnSave();

        //Verification of created user
        userPage.typeSearchTxt(userName);
        userPage.verifyCallOnTable(fistName);
        userPage.verifyCallOnTable(lastName);
        userPage.verifyCallOnTable(userName);
    }

}
