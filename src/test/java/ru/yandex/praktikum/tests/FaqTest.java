package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.driver.DriverFactory;
import ru.yandex.praktikum.page.MainPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FaqTest {

    private WebDriver driver;
    private MainPage mainPage;
    private final int index;
    private final String expectedAnswer;

    public FaqTest(int index, String expectedAnswer) {
        this.index = index;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters(name = "FAQ index {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышки и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
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
    public void faqItemShouldOpenAnswer() {
        mainPage.clickFaqQuestion(index);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElementLocated(
                        org.openqa.selenium.By.id("accordion__panel-" + index),
                        expectedAnswer
                ));

        String actualAnswer = mainPage.getFaqAnswerText(index);
        assertEquals("FAQ answer mismatch for index " + index, expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}