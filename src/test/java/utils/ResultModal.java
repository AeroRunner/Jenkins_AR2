package utils;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultModal {
    UserData userData = new UserData();
    private SelenideElement
            seeModalWindow = $(".modal-dialog"),
            tableParagraph = $(".table-responsive");


    public String graphName = "Student Name";
    public String graphEmail = "Student Email";
    public String graphGender = "Gender";
    public String graphMobile = "Mobile";
    public String graphBorn = "Date of Birth";
    public String graphSubj = "Subjects";
    public String graphHobby = "Hobbies";
    public String graphPicture = "Picture";
    public String graphAddress = "Address";
    public String graphStateCity = "State and City";
    public String fullName = userData.name + " " + userData.lastName;
    public String bornDate = userData.birthDay + " " + userData.birthMonth + "," + userData.birthYear;
    public String stateAndCity = userData.state + " " + userData.city;

    public ResultModal verifyModalAppeared() {
        seeModalWindow.should(appear);

        return this;
    }

    public ResultModal checkResult(String paragraph, String text1) {
        tableParagraph.$(byText(paragraph)).parent().shouldHave(text(text1));
        return this;
    }
}
