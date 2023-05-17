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
        chromeOptions.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://chercher.tech/practice/dynamic-table");
    }

    @AfterEach
    void tearDown() {
        //webDriver.quit();
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
        List<WebElement> checkboxes = webDriver
                .findElements(By.xpath("/html/body/div[1]/div/div/div/div[1]/table/tbody/tr[*]/td[1]/input"));
        List<WebElement> campos = webDriver
                .findElements(By.xpath("/html/body/div[1]/div/div/div/div[1]/table/tbody/tr[*]/td[2]"));
        /*int posicao = 0;
        WebElement checkbox = null;
        for (int i = 0; i < campos.size(); i++) {
            if (campos.get(i).getText().equals("facebook.com")) {
                checkboxes.get(i).click();
                checkbox = checkboxes.get(i);
                posicao = i;
            }
        }
        assertTrue(checkbox.isSelected());*/
        WebElement webElement = campos.stream().filter(campo -> campo.getText().equals("facebook.com"))
                .findFirst().get();
        int posicao = campos.indexOf(webElement);
        checkboxes.get(posicao).click();
        assertTrue(checkboxes.get(posicao).isSelected());
    }

    @Test
    void exercicioXPathAllSelectsTest() {
        List<WebElement> allSelects = webDriver
                .findElements(By.xpath("/html/body/div[1]/div/div/div/div[1]/table/tbody/tr[*]/td[1]/input"));
        allSelects.forEach(WebElement::click);
        allSelects.forEach(select -> assertTrue(select.isSelected()));
    }


}
