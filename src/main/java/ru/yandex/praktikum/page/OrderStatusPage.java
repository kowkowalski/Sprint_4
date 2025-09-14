package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderStatusPage {

    private final WebDriver driver;
    private final By orderInput = By.xpath("//input[@placeholder='Введите номер заказа']");
    private final By checkButton = By.xpath("//button[text()='Проверить']");
    private final By notFoundMessage = By.xpath("//div[contains(text(),'Заказ не найден')]");

    public OrderStatusPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/track-order");
    }

    public void findOrder(String orderNumber) {
        driver.findElement(orderInput).sendKeys(orderNumber);
        driver.findElement(checkButton).click();
    }

    public String getNotFoundText() {
        if (!driver.findElements(notFoundMessage).isEmpty()) {
            return driver.findElement(notFoundMessage).getText();
        }
        return "";
    }
}