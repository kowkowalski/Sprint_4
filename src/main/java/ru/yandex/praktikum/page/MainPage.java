package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы
    private final By ORDER_BUTTON_TOP = By.xpath("//button[text()='Заказать'][1]");
    private final By ORDER_BUTTON_BOTTOM = By.xpath("//button[text()='Заказать'][2]");
    private final By COOKIE_BUTTON = By.id("rcc-confirm-button");
    private final By SCOOTER_LOGO = By.className("Header_LogoScooter__3lsAR");
    private final By YANDEX_LOGO = By.className("Header_LogoYandex__3TSOI");

    private By faqQuestion(int index) {
        return By.id("accordion__heading-" + index);
    }

    private By faqAnswer(int index) {
        return By.id("accordion__panel-" + index);
    }

    // Методы
    public void acceptCookies() {
        driver.findElement(COOKIE_BUTTON).click();
    }

    public void clickTopOrderButton() {
        driver.findElement(ORDER_BUTTON_TOP).click();
    }

    public void clickBottomOrderButton() {
        driver.findElement(ORDER_BUTTON_BOTTOM).click();
    }

    public void clickFaqQuestion(int index) {
        driver.findElement(faqQuestion(index)).click();
    }

    public String getFaqAnswerText(int index) {
        return driver.findElement(faqAnswer(index)).getText();
    }

    public void clickScooterLogo() {
        driver.findElement(SCOOTER_LOGO).click();
    }

    public void clickYandexLogo() {
        driver.findElement(YANDEX_LOGO).click();
    }
}