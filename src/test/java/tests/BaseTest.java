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

        step("Setup configurations before all tests", () -> {
            Configuration.remote
                    = System.getProperty("selenoid", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true));
            Configuration.browserSize = System.getProperty("size", "1920x1080");
            Configuration.baseUrl = "https://demoqa.com";
            Configuration.pageLoadStrategy = "eager";
            Configuration.timeout = 20000;
            Configuration.browserVersion = System.getProperty("version", "100");
            Configuration.webdriverLogsEnabled = true;
            Configuration.browser = System.getProperty("browser", "chrome");
            Configuration.browserCapabilities = capabilities;
        });
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
