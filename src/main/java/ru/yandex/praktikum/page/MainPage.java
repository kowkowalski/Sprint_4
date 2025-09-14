package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локаторы (camelCase, private final)
    private final By orderButtonTop = By.xpath("(//button[text()='Заказать'])[1]");
    private final By orderButtonBottom = By.xpath("(//button[text()='Заказать'])[2]");
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By scooterLogo = By.className("Header_LogoScooter__3lsAR");
    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    private By faqQuestion(int index) {
        return By.id("accordion__heading-" + index);
    }

    private By faqAnswer(int index) {
        return By.id("accordion__panel-" + index);
    }

    // Методы
    public void acceptCookies() {
        if (!driver.findElements(cookieButton).isEmpty()) {
            driver.findElement(cookieButton).click();
        }
    }

    public void clickTopOrderButton() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickBottomOrderButton() {
        driver.findElement(orderButtonBottom).click();
    }

    public void clickFaqQuestion(int index) {
        driver.findElement(faqQuestion(index)).click();
    }

    public String getFaqAnswerText(int index) {
        return driver.findElement(faqAnswer(index)).getText();
    }

    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }
}