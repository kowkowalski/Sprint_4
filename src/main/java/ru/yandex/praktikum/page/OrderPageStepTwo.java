package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPageStepTwo {

    private final WebDriver driver;

    public OrderPageStepTwo(WebDriver driver) {
        this.driver = driver;
    }

    private final By DATE_INPUT = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By RENT_PERIOD_DROPDOWN = By.className("Dropdown-placeholder");
    private final By BLACK_COLOR_CHECKBOX = By.id("black");
    private final By GREY_COLOR_CHECKBOX = By.id("grey");
    private final By COMMENT_INPUT = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By ORDER_BUTTON = By.xpath("//button[text()='Заказать']");
    private final By YES_BUTTON = By.xpath("//button[text()='Да']");

    private By rentPeriodOption(String text) {
        return By.xpath("//div[@class='Dropdown-option' and text()='" + text + "']");
    }

    public void fillDate(String date) {
        driver.findElement(DATE_INPUT).sendKeys(date);
    }

    public void selectRentPeriod(String period) {
        driver.findElement(RENT_PERIOD_DROPDOWN).click();
        driver.findElement(rentPeriodOption(period)).click();
    }

    public void selectBlackColor() {
        driver.findElement(BLACK_COLOR_CHECKBOX).click();
    }

    public void selectGreyColor() {
        driver.findElement(GREY_COLOR_CHECKBOX).click();
    }

    public void fillComment(String comment) {
        driver.findElement(COMMENT_INPUT).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(ORDER_BUTTON).click();
    }

    public void confirmOrder() {
        driver.findElement(YES_BUTTON).click();
    }
}