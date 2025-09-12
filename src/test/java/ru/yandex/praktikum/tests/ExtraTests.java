package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.driver.DriverFactory;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPageStepOne;

import static org.junit.Assert.assertEquals;

public class ExtraTests {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = DriverFactory.createChrome();
        mainPage = new MainPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage.acceptCookies();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //Проверка логотипа Самокат
    @Test
    public void scooterLogoRedirectsToMainPage() {
        mainPage.clickScooterLogo();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://qa-scooter.praktikum-services.ru/", currentUrl);
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

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://yandex.ru/", currentUrl);


        driver.switchTo().window(originalWindow);
    }

    //Проверка ошибки для поля "Имя"
    @Test
    public void firstNameFieldCannotBeEmpty() {
        mainPage.clickTopOrderButton();

        OrderPageStepOne stepOne = new OrderPageStepOne(driver);
        stepOne.fillLastName("Ковалев");
        stepOne.fillAddress("ул. Ленина, 5");
        stepOne.fillMetro("Сокол");
        stepOne.fillPhone("+79999999999");
        stepOne.clickNextButton();

        String errorText = driver.findElement(By.xpath("//div[text()='Введите имя']")).getText();
        assertEquals("Введите имя", errorText);
    }

    //Проверка неверного номера заказа
    @Test
    public void invalidOrderNumberShowsError() {
        driver.get("https://qa-scooter.praktikum-services.ru/track-order");

        driver.findElement(By.xpath("//input[@placeholder='Введите номер заказа']")).sendKeys("999999");
        driver.findElement(By.xpath("//button[text()='Проверить']")).click();

        String errorText = driver.findElement(By.xpath("//div[contains(text(),'Заказ не найден')]")).getText();
        assertEquals("Заказ не найден", errorText);
    }
}