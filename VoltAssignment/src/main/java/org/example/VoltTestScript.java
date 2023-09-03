package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class VoltTestScript {
    public static void main(String[] args) throws InterruptedException {
        String[][] usernamePassword = {{"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"}};
        System.out.println("Please enter Username and Password: ");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        String password = sc.nextLine();
        String[][] compareDetails = {{username,password}}; // Initialize with the same size
        boolean isAuthenticated = false;
        // Loop through each element of usernamePassword and compare with compareDetails
        for (int i = 0; i < usernamePassword.length; i++) {
            if (username.equals(usernamePassword[i][0]) && password.equals(usernamePassword[i][1])) {
                isAuthenticated = true;
                break; // Exit the loop if a match is found
            }
        }
        //Initializing ChromeDriver
        System.setProperty("webdriver.chrome.driver","/Users/hungerbox/IdeaProjects/VoltAssignment/src/resources/Files/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (isAuthenticated){
            driver.get("https://www.saucedemo.com/");
            WebElement sauceUsername = driver.findElement(By.id("user-name"));
            WebElement saucePassword = driver.findElement(By.id("password"));
            WebElement sauceLoginButton = driver.findElement(By.id("login-button"));
            sauceUsername.sendKeys(compareDetails[0][0]);
            saucePassword.sendKeys(compareDetails[0][1]);
            sauceLoginButton.click();
        } else {
            System.out.println("Username and Password are not valid");
        }
        Thread.sleep(5000);

        WebElement addToCart = driver.findElement(By.xpath("//div[@class='pricebar']/button"));

        System.out.println(addToCart.getText());
        //Add to Cart Functionality
        addToCart.click();
        //Remove Functionality
        WebElement remove = driver.findElement(By.xpath("//div[@class='pricebar']/*[text()='Remove']"));
        System.out.println(remove.getText());
        remove.click();
        //Add to Cart and Go to cart
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pricebar']/button")));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement addToCart2 = driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        System.out.println(addToCart2.getText());
        addToCart2.click();
        WebElement cart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        cart.click();
        //Click on checkout and go to checkout 1 page
        WebElement checkout1 = driver.findElement(By.xpath("//*[@id='checkout']"));
        checkout1.click();
        //Enter address details
        WebElement firstName = driver.findElement(By.xpath("//*[@id='first-name']"));
        firstName.sendKeys("Devadutta");
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement lastName = driver.findElement(By.xpath("//*[@id='last-name']"));
        lastName.sendKeys("Mohapatra");
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement postalAddress = driver.findElement(By.xpath("//*[@id='postal-code']"));
        postalAddress.sendKeys("560102");
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement continueCheckout = driver.findElement(By.xpath("//*[@id='continue']"));
        continueCheckout.click();
        //Find the finish button and click on it
        WebElement finish = driver.findElement(By.xpath("//*[@id='finish']"));
        System.out.println(finish.getText());
        finish.click();
        WebElement successfulOrderMessage = driver.findElement(By.xpath("//div[@id='checkout_complete_container']"));
        System.out.println(successfulOrderMessage.getText());
        driver.close();
    }
}