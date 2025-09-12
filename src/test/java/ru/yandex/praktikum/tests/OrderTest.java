package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.driver.DriverFactory;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderConfirmationModal;
import ru.yandex.praktikum.page.OrderPageStepOne;
import ru.yandex.praktikum.page.OrderPageStepTwo;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private MainPage mainPage;
    private OrderPageStepOne stepOne;
    private OrderPageStepTwo stepTwo;
    private OrderConfirmationModal confirmation;

    // Параметры теста
    @Parameterized.Parameter(0)
    public String firstName;
    @Parameterized.Parameter(1)
    public String lastName;
    @Parameterized.Parameter(2)
    public String address;
    @Parameterized.Parameter(3)
    public String metro;
    @Parameterized.Parameter(4)
    public String phone;
    @Parameterized.Parameter(5)
    public String date;
    @Parameterized.Parameter(6)
    public String rentPeriod;
    @Parameterized.Parameter(7)
    public String color;
    @Parameterized.Parameter(8)
    public String comment;


    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Ковалев", "Кирилл", "ул. Ленина, 5", "Сокол", "+79999999999", "10.09.2025", "сутки", "black", "Позвоните за час"},
                {"Марина", "Петрова", "ул. Гагарина, 3", "Тимирязевская", "+79533452389", "12.09.2025", "двое суток", "grey", "Не звонить"}
        });
    }

    @Before
    public void setUp() {
        driver = DriverFactory.createChrome();
        mainPage = new MainPage(driver);
        stepOne = new OrderPageStepOne(driver);
        stepTwo = new OrderPageStepTwo(driver);
        confirmation = new OrderConfirmationModal(driver);

        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage.acceptCookies();
    }


    private void makeOrder(String buttonPosition) throws InterruptedException {
        if (buttonPosition.equalsIgnoreCase("top")) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }


        stepOne.fillFirstName(firstName);
        stepOne.fillLastName(lastName);
        stepOne.fillAddress(address);
        stepOne.fillMetro(metro);
        stepOne.fillPhone(phone);
        stepOne.clickNextButton();


        stepTwo.fillDate(date);
        stepTwo.selectRentPeriod(rentPeriod);
        if (color.equalsIgnoreCase("black")) {
            stepTwo.selectBlackColor();
        } else {
            stepTwo.selectGreyColor();
        }
        stepTwo.fillComment(comment);
        stepTwo.clickOrderButton();
        stepTwo.confirmOrder();


        String title = confirmation.getConfirmationTitle();
        assertEquals("Заказ оформлен", title);
    }

    @Test
    public void orderScooterTopButton() throws InterruptedException {
        makeOrder("top");
    }

    @Test
    public void orderScooterBottomButton() throws InterruptedException {
        makeOrder("bottom");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}