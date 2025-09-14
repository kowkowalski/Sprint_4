package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationModal {

    private final WebDriver driver;

    public OrderConfirmationModal(WebDriver driver) {
        this.driver = driver;
    }

    // Более точный локатор заголовка модалки, который содержит текст "Заказ оформлен"
    private final By confirmationTitle = By.xpath("//div[contains(@class,'Order_ModalHeader') and contains(., 'Заказ оформлен')]");
    private final By orderNumber = By.xpath("//div[contains(@class,'Order_Text') and contains(., 'Заказ')]/following-sibling::div");

    public String getConfirmationTitle() {
        return driver.findElement(confirmationTitle).getText();
    }

    public boolean isConfirmationVisible() {
        return !driver.findElements(confirmationTitle).isEmpty();
    }

    public String getOrderNumber() {
        if (!driver.findElements(orderNumber).isEmpty()) {
            return driver.findElement(orderNumber).getText();
        }
        return "";
    }
}