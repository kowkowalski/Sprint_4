package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPageStepOne {

    private final WebDriver driver;

    public OrderPageStepOne(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы
    private final By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");
    private final By firstNameError = By.xpath("//div[text()='Введите имя']"); // локатор ошибки

    // Методы
    public void fillFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void fillAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void fillMetro(String metro) {
        driver.findElement(metroInput).sendKeys(metro);
    }

    public void fillPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public String getFirstNameError() {
        return driver.findElement(firstNameError).getText();
    }
}