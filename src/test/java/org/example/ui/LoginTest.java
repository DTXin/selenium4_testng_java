package org.example.ui;

import com.google.gson.JsonObject;
import org.example.Config;
import org.example.fw_ui.base.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify user login successfully")
    public void TC01_LoginSuccessfully() {
        JsonObject obj = getCommon().ReadJsonWithGson(Config.DATATEST_FOLDER + Config.USER);
        JsonObject users = obj.getAsJsonObject("standard_user");

        getLoginPage().login(users.get("username").getAsString(), users.get("password").getAsString());
        getProductPage().verify_ProductPage_IsDisplayed();
    }

    @Test(description = "Verify user login unsuccessfully")
    public void TC02_LoginUnSuccessfully() {
        JsonObject obj = getCommon().ReadJsonWithGson(Config.DATATEST_FOLDER + Config.USER);
        JsonObject users = obj.getAsJsonObject("invalid_user");

        getLoginPage().login(users.get("username").getAsString(), users.get("password").getAsString());
        getLoginPage().verify_ErrorMessage_InvalidUser();
    }

    @Test(description = "Verify locked user cannot login")
    public void TC03_LockedUserCannotLogin() {
        JsonObject obj = getCommon().ReadJsonWithGson(Config.DATATEST_FOLDER + Config.USER);
        JsonObject users = obj.getAsJsonObject("locked_user");

        getLoginPage().login(users.get("username").getAsString(), users.get("password").getAsString());
        getLoginPage().verify_ErrorMessage_LockedUser();
    }
}