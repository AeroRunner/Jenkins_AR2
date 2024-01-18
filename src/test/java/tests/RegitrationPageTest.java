package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.ResultModal;
import utils.UserData;

import static io.qameta.allure.Allure.step;

@DisplayName("Registration Page Tests")
public class RegitrationPageTest extends BaseTest {
    RegistrationPage regPage = new RegistrationPage();
    ResultModal resModal = new ResultModal();
    UserData data = new UserData();

    @DisplayName("Full Registration Page Test: Filing and Check Result Form")
    @Test
    @Tag("fallMozilla")
    public void filingAndCheckResultRegistrationPageTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open Registration Page", () -> {
            regPage.openFormPage();
        });
        step("Remove Banner", () -> {
            regPage.removeBanner();
        });
        step("Enter Name 'Dmitrii'", () -> {
            regPage.setFirstName(data.name);
        });
        step("Enter LastName 'Elizarov'", () -> {
            regPage.setLastName(data.lastName);
        });
        step("Enter Email 'airplay1x6@gmail.com'", () -> {
            regPage.setEmail(data.email);
        });
        step("Choise Gender", () -> {
            regPage.choiceGender(data.gender);
        });
        step("Enter Phone Number '8888888888'", () -> {
            regPage.setUserNumber(data.number);
        });
        step("Enter Date of Birth(day(29),month(December),year(1999))", () -> {
            regPage.choiceDateOfBirth(data.birthDay, data.birthMonth, data.birthYear);
        });
        step("Choise Subject 'Arts'", () -> {
            regPage.setSubj(data.arts);
        });
        step("Enter Hobby 'Sports", () -> {
            regPage.choiceHobby(data.sports);
        });
        step("Upload test image (testimage.png)", () -> {
            regPage.uploadImage();
        });
        step("Enter current address 'Krajiska 87'", () -> {
            regPage.setCurrAddress(data.currAddress);
        });
        step("Choise State (Uttar Pradesh) and City (Agra)", () -> {
            regPage.choiceStateCity(data.state, data.city);
        });
        step("Click button 'Submit", () -> {
            regPage.clickSubmit();
        });
        step("Check modal results after click 'Submit'", () -> {
            resModal.verifyModalAppeared();
            resModal.checkResult(resModal.graphName, resModal.fullName);
            resModal.checkResult(resModal.graphEmail, data.email);
            resModal.checkResult(resModal.graphGender, data.gender);
            resModal.checkResult(resModal.graphMobile, data.number);
            resModal.checkResult(resModal.graphBorn, resModal.bornDate);
            resModal.checkResult(resModal.graphSubj, data.arts);
            resModal.checkResult(resModal.graphHobby, data.sports);
            resModal.checkResult(resModal.graphPicture, data.testImg);
            resModal.checkResult(resModal.graphAddress, data.currAddress);
            resModal.checkResult(resModal.graphStateCity, resModal.stateAndCity);
        });

    }

    @DisplayName("Registration Page only required fields test")
    @Test
    @Tag("smoke")
    public void filingRequiredFieldsRegistrPage() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open Registration Page", () -> {
            regPage.openFormPage();
        });
        step("Remove Banner", () -> {
            regPage.removeBanner();
        });
        step("Enter Name 'Dmitrii'", () -> {
            regPage.setFirstName(data.name);
        });
        step("Enter LastName 'Elizarov'", () -> {
            regPage.setLastName(data.lastName);
        });
        step("Choise Gender", () -> {
            regPage.choiceGender(data.gender);
        });
        step("Enter Phone Number '8888888888'", () -> {
            regPage.setUserNumber(data.number);
        });
        step("Click button 'Submit", () -> {
            regPage.clickSubmit();
        });
        step("Check modal results after click 'Submit'", () -> {
            resModal.verifyModalAppeared();
            resModal.checkResult(resModal.graphName, resModal.fullName);
            resModal.checkResult(resModal.graphGender, data.gender);
            resModal.checkResult(resModal.graphMobile, data.number);
        });


    }

    @DisplayName("Registration Page Negative Tests: " +
    "The modal window should not be displayed until the required fields are filled in ")
    @Test
    @Tag("smoke")
    public void requiredFieldsNegativeTests() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open Registration Page", () -> {
            regPage.openFormPage();
        });
        step("Remove Banner", () -> {
            regPage.removeBanner();
        });
        step("Enter Name 'Dmitrii'", () -> {
            regPage.setFirstName(data.name);
        });
        step("Click button 'Submit", () -> {
            regPage.clickSubmit();
        });
        step("Modal window shouldn't appear", ()->{
            regPage.checkTableHidden();
        } );
    }
}
