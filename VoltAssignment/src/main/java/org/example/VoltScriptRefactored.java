package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class VoltScriptRefactored {
    public static void main(String[] args) throws InterruptedException {
        // Define valid username and password combinations
        String[][] usernamePassword = {
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"}
        };

        // Get user input for username and password
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Username: ");
        String username = sc.nextLine();
        System.out.println("Please enter Password: ");
        String password = sc.nextLine();
        sc.close();

        // Check if the entered username and password are valid
        boolean isAuthenticated = false;
        for (String[] credentials : usernamePassword) {
            if (username.equals(credentials[0]) && password.equals(credentials[1])) {
                isAuthenticated = true;
                break;
            }
        }

        // Initialize ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/Users/hungerbox/IdeaProjects/VoltAssignment/src/resources/Files/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        if (isAuthenticated) {
            // Navigate to the login page
            driver.get("https://www.saucedemo.com/");

            // Locate web elements for login
            WebElement sauceUsername = driver.findElement(By.id("user-name"));
            WebElement saucePassword = driver.findElement(By.id("password"));
            WebElement sauceLoginButton = driver.findElement(By.id("login-button"));

            // Enter credentials and click login
            sauceUsername.sendKeys(username);
            saucePassword.sendKeys(password);
            sauceLoginButton.click();
        } else {
            System.out.println("Username and Password are not valid");
            driver.quit();
            return;
        }

        // Sleep for 5 seconds to ensure the page is loaded
        Thread.sleep(5000);

        // Find and interact with cart elements
        WebElement addToCart = driver.findElement(By.xpath("//div[@class='pricebar']/button"));
        System.out.println(addToCart.getText());
        addToCart.click();

        WebElement remove = driver.findElement(By.xpath("//div[@class='pricebar']/*[text()='Remove']"));
        System.out.println(remove.getText());
        remove.click();

        // Continue shopping and add another item to the cart
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement addToCart2 = driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        System.out.println(addToCart2.getText());
        addToCart2.click();

        // Go to the cart
        WebElement cart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        System.out.println(cart.getText());
        cart.click();

        // Click on checkout and go to checkout 1 page
        WebElement checkout1 = driver.findElement(By.xpath("//*[@id='checkout']"));
        System.out.println(checkout1.getText());
        checkout1.click();

        // Enter address details
        WebElement firstName = driver.findElement(By.xpath("//*[@id='first-name']"));
        firstName.sendKeys("Devadutta");

        WebElement lastName = driver.findElement(By.xpath("//*[@id='last-name']"));
        lastName.sendKeys("Mohapatra");

        WebElement postalAddress = driver.findElement(By.xpath("//*[@id='postal-code']"));
        postalAddress.sendKeys("560102");

        WebElement continueCheckout = driver.findElement(By.xpath("//*[@id='continue']"));
        continueCheckout.click();

        // Find the finish button and click on it
        WebElement finish = driver.findElement(By.xpath("//*[@id='finish']"));
        System.out.println(finish.getText());
        finish.click();

        // Display successful order message
        WebElement successfulOrderMessage = driver.findElement(By.xpath("//div[@id='checkout_complete_container']"));
        System.out.println(successfulOrderMessage.getText());

        // Close the browser
        driver.quit();
    }
}
