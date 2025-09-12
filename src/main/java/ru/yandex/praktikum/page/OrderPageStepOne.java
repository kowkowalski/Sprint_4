package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPageStepOne {

    private final WebDriver driver;

    public OrderPageStepOne(WebDriver driver) {
        this.driver = driver;
    }

    private final By FIRST_NAME_INPUT = By.xpath("//input[@placeholder='* Имя']");
    private final By LAST_NAME_INPUT = By.xpath("//input[@placeholder='* Фамилия']");
    private final By ADDRESS_INPUT = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By METRO_INPUT = By.xpath("//input[@placeholder='* Станция метро']");
    private final By PHONE_INPUT = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By NEXT_BUTTON = By.xpath("//button[text()='Далее']");

    // Методы
    public void fillFirstName(String firstName) {
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
    }

    public void fillAddress(String address) {
        driver.findElement(ADDRESS_INPUT).sendKeys(address);
    }

    public void fillMetro(String metro) {
        driver.findElement(METRO_INPUT).sendKeys(metro);
    }

    public void fillPhone(String phone) {
        driver.findElement(PHONE_INPUT).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }
}