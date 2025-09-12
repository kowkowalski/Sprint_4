package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.driver.DriverFactory;
import ru.yandex.praktikum.page.MainPage;



public class FaqTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = DriverFactory.createChrome();
        mainPage = new MainPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage.acceptCookies();
    }

    @Test
    public void faqQuestionsShouldOpenAnswers() throws InterruptedException {

        for (int i = 0; i <= 7; i++) {
            mainPage.clickFaqQuestion(i);

            Thread.sleep(500);
            String answerText = mainPage.getFaqAnswerText(i);

            assert answerText != null && !answerText.isEmpty() : "Ответ для вопроса " + i + " пустой";
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}