package pages;

import com.codeborne.selenide.SelenideElement;
import components.CalendarComponents;

import java.io.File;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjInput = $("#subjectsInput"),
            hobbyInput = $("#hobbiesWrapper"),
            uploadFile = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            wrapperStateCity = $("#stateCity-wrapper"),
            addState = $("#state"),
            addCity = $("#city"),
            addSubmit = $("#submit"),
            tableClass = $(".table-responsive");

    CalendarComponents calendarComponent = new CalendarComponents();

    public RegistrationPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage openFormPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage choiceGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage choiceDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubj(String subj1) {
        subjInput.setValue(subj1).pressEnter();
        return this;
    }

    public RegistrationPage choiceHobby(String hobby1) {
        hobbyInput.$(byText(hobby1)).click();
        return this;
    }

    public RegistrationPage uploadImage() {
        File image = new File("src/test/resources/testimage.png");
        uploadFile.uploadFile(image);
        return this;
    }

    public RegistrationPage setCurrAddress(String curAddress) {
        addressInput.setValue(curAddress);
        return this;
    }

    public RegistrationPage choiceStateCity(String state, String city) {
        addState.click();
        wrapperStateCity.$(byText(state)).click();
        addCity.click();
        wrapperStateCity.$(byText(city)).click();
        return this;
    }

    public RegistrationPage clickSubmit() {
        addSubmit.click();
        return this;
    }

    public RegistrationPage checkTableHidden() {
        tableClass.shouldBe(hidden);
        return this;
    }
}
