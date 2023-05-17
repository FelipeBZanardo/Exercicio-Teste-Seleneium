import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest2 {
    private static WebDriver webDriver;
    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://igorsmasc.github.io/fomulario_cadastro_selenium/");
    }

    @AfterEach
    void tearDown() {
        //webDriver.quit();
    }

    @Test
    void devePreencherOFormularioCorretamente() {
        webDriver.findElement(By.id("nome")).sendKeys("Nome");
        webDriver.findElement(By.id("sobrenome")).sendKeys("Sobrenome");
        webDriver.findElement(By.id("masculino")).click();
        webDriver.findElement(By.id("java")).click();
        webDriver.findElement(By.id("javascript")).click();
        WebElement areaInteresse = webDriver.findElement(By.id("area-interesse"));
        Select selectAreaInteresse = new Select(areaInteresse);
        selectAreaInteresse.selectByValue("Devops");
        selectAreaInteresse.selectByValue("Frontend");

        WebElement motivacao = webDriver.findElement(By.id("motivacao"));
        Select selectMotivacao = new Select(motivacao);
        selectMotivacao.selectByValue("Devops");
        selectMotivacao.selectByValue("Drontend");

        webDriver.findElement(By.id("porque")).sendKeys("Porque sim!");

        //webDriver.findElement(By.xpath("/html/body/div[1]/form/div[8]/button[2]")).click();
        webDriver.findElement(By.cssSelector(".btn-primary")).submit();
        webDriver.switchTo().alert().accept();

        String nome = webDriver.findElement(By.xpath("//tr/td[1]")).getText();
        assertEquals("Nome", nome);
        String sobrenome = webDriver.findElement(By.xpath("//tr/td[2]")).getText();
        assertEquals("Sobrenome", sobrenome);
    }

    @Test
    void deveVerificarSeTodosOsCamposEstaoPreenchidos() {
        webDriver.findElement(By.id("nome")).submit();
        String textoAlerta = webDriver.switchTo().alert().getText();
        assertEquals("Por favor, preencha todos os campos.", textoAlerta);
    }
}
