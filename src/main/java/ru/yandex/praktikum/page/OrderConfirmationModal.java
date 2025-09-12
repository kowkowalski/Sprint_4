package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationModal {

    private final WebDriver driver;

    public OrderConfirmationModal(WebDriver driver) {
        this.driver = driver;
    }

    private final By CONFIRMATION_TITLE = By.className("Order_ModalHeader__3FDaJ");
    private final By STATUS_BUTTON = By.xpath("//button[text()='Посмотреть статус']");

    public String getConfirmationTitle() {
        return driver.findElement(CONFIRMATION_TITLE).getText();
    }

    public void clickStatusButton() {
        driver.findElement(STATUS_BUTTON).click();
    }
}