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

    @Parameterized.Parameter(0)
    public String buttonPosition;
    @Parameterized.Parameter(1)
    public String firstName;
    @Parameterized.Parameter(2)
    public String lastName;
    @Parameterized.Parameter(3)
    public String address;
    @Parameterized.Parameter(4)
    public String metro;
    @Parameterized.Parameter(5)
    public String phone;
    @Parameterized.Parameter(6)
    public String date;
    @Parameterized.Parameter(7)
    public String rentPeriod;
    @Parameterized.Parameter(8)
    public String color;
    @Parameterized.Parameter(9)
    public String comment;

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"top", "Кирилл", "Ковалев", "ул. Ленина, 1", "Сокол", "+79991112233", "10.09.2025", "сутки", "black", "Позвоните за час"},
                {"bottom", "Мария", "Петрова", "ул. Гагарина, 5", "Тимирязевская", "+79992223344", "12.09.2025", "двое суток", "grey", "Не звонить"}
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

    private void makeOrder() {
        if ("top".equalsIgnoreCase(buttonPosition)) {
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
        if ("black".equalsIgnoreCase(color)) stepTwo.selectBlackColor();
        else stepTwo.selectGreyColor();
        stepTwo.fillComment(comment);
        stepTwo.clickOrderButton();
        stepTwo.confirmOrder();
    }

    @Test
    public void orderFlow() {
        makeOrder();
        assertEquals("Заказ оформлен", confirmation.getConfirmationTitle());
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}