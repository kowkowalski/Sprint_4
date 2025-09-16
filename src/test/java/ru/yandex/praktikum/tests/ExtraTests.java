package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.driver.DriverFactory;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPageStepOne;
import ru.yandex.praktikum.page.OrderStatusPage;

import static org.junit.Assert.assertEquals;

public class ExtraTests {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = DriverFactory.createChrome();
        mainPage = new MainPage(driver);
        driver.get(BASE_URL);
        mainPage.acceptCookies();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Проверка логотипа Самокат
    @Test
    public void scooterLogoRedirectsToMainPage() {
        mainPage.clickScooterLogo();
        assertEquals(BASE_URL, driver.getCurrentUrl());
    }

    // Проверка логотипа Яндекс
    @Test
    public void yandexLogoOpensInNewTab() {
        String originalWindow = driver.getWindowHandle();
        mainPage.clickYandexLogo();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        assertEquals("https://yandex.ru/", driver.getCurrentUrl());
        driver.switchTo().window(originalWindow);
    }

    // Проверка ошибки для поля "Имя"
    @Test
    public void firstNameFieldCannotBeEmpty() {
        mainPage.clickTopOrderButton();
        OrderPageStepOne stepOne = new OrderPageStepOne(driver);

        stepOne.fillLastName("Ковалев");
        stepOne.fillAddress("ул. Ленина, 5");
        stepOne.fillMetro("Сокол");
        stepOne.fillPhone("+79999999999");
        stepOne.clickNextButton();

        String errorText = stepOne.getFirstNameError();
        assertEquals("Введите имя", errorText);
    }

    // Проверка неверного номера заказа
    @Test
    public void invalidOrderNumberShowsError() {
        OrderStatusPage status = new OrderStatusPage(driver);
        status.open();
        status.findOrder("999999");
        assertEquals("Заказ не найден", status.getNotFoundText());
    }
}