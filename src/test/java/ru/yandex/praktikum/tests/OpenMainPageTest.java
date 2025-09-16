package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.driver.DriverFactory;

import static org.junit.Assert.assertTrue;

public class OpenMainPageTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.createChrome();
    }

    @Test
    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        String title = driver.getTitle();
        assertTrue("Страница не открылась", title != null && !title.isEmpty());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}