package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.driver.DriverFactory;
import ru.yandex.praktikum.page.MainPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FaqTest {

    private WebDriver driver;
    private MainPage mainPage;
    private final int index;

    public FaqTest(int index) {
        this.index = index;
    }

    @Parameterized.Parameters(name = "FAQ index {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}
        });
    }

    @Before
    public void setUp() {
        driver = DriverFactory.createChrome();
        mainPage = new MainPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage.acceptCookies();
    }

    @Test
    public void faqItemShouldOpenAnswer() throws InterruptedException {
        mainPage.clickFaqQuestion(index);

        String answer = mainPage.getFaqAnswerText(index);
        assertTrue("Answer for FAQ index " + index + " should not be empty", answer != null && !answer.trim().isEmpty());
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
