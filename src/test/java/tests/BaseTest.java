package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static io.qameta.allure.Allure.step;


public class BaseTest {
    @DisplayName("Set settings before start tests ")
    @BeforeAll
    public static void setUP() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step(" Set remote browser", () -> {
            Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        });
        step("Set timeout(10000)", () -> {

            Configuration.timeout = 10000;
        });
        step("Set baseUrl - https://demoqa.com", () -> {
            Configuration.baseUrl = "https://demoqa.com";
        });
        step("Set browsersize(1920x1080)", () -> {
            Configuration.browserSize = "1920x1080";
        });

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

    }
    @DisplayName("After every test")
    @AfterEach
    void afterEveryTest() {
        Attach.screenshotAs("Last Screen");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        step("Close Browser", () -> {
            Selenide.closeWebDriver();
        });
    }
}
