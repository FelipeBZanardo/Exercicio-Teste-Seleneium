import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {
    private static WebDriver webDriver;
    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://chercher.tech/practice/dynamic-table");
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @Test
    void exercicioXPathBlueberryTest() {
        WebElement btnBlueberry = webDriver.findElement(By.xpath("//*[@id=\"blue\"]"));
        btnBlueberry.click();
        WebElement h1Blueberry = webDriver.findElement(By.xpath("//*[@id=\"output\"]"));
        assertEquals("Blueberry" , h1Blueberry.getText());
    }

    @Test
    void exercicioXPathSelectFacebookTest() {
        WebElement checkboxFacebook = webDriver
                .findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/table/tbody/tr[2]/td[1]/input"));
        checkboxFacebook.click();
        assertTrue(checkboxFacebook.isSelected());
    }

    @Test
    void exercicioXPathAllSelectsTest() {
        List<WebElement> allSelects = webDriver
                .findElements(By.xpath("/html/body/div[1]/div/div/div/div[1]/table/tbody/tr[*]/td[1]/input"));
        allSelects.forEach(WebElement::click);
        allSelects.forEach(select -> assertTrue(select.isSelected()));
    }
}
