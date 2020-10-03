package automationtesting.ventanas;

import automationtesting.Utilidades;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;


import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static automationtesting.Constantes.URL_PRACTICA_VENTANAS;
import static automationtesting.Constantes.URL_PRUEBA_PRACTICE;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TestVentanas {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void prueba1Ventana(){
        driver.manage().window().maximize();
        driver.get(URL_PRACTICA_VENTANAS);
        Utilidades.esperar(30, driver);

        String ventanaInicial = driver.getWindowHandle();
        System.out.println("ID ventana " + ventanaInicial);
        System.out.println("Titulo ventana " + driver.getTitle());

        driver.findElement(By.xpath("//a//button[@class='btn btn-info']")).click();

        for(String manejadorVentana : driver.getWindowHandles()){
            if(!ventanaInicial.contentEquals(manejadorVentana)) {
                driver.switchTo().window(manejadorVentana);
                break;
            }
        }

        System.out.println("Titulo ventana 2: " + driver.getTitle());

        driver.close();

        driver.switchTo().window(ventanaInicial);

        System.out.println("Titulo ventana 1: " + driver.getTitle());

        assertEquals("Automation Demo Site", driver.findElement(By.tagName("h1")).getText());

    }

    @Test
    public void prueba2Ventana(){
        driver.manage().window().maximize();
        driver.get(URL_PRACTICA_VENTANAS);
        Utilidades.esperar(30, driver);

        String ventanaInicial = driver.getWindowHandle();
        System.out.println("ID ventana " + ventanaInicial);
        System.out.println("Titulo ventana " + driver.getTitle());

        driver.findElement(By.xpath("//a[@href='#Seperate']")).click();
        driver.findElement(By.xpath("//div//button[@class='btn btn-primary']")).click();

        for(String manejadorVentana : driver.getWindowHandles()){
            if(!ventanaInicial.contentEquals(manejadorVentana)) {
                driver.switchTo().window(manejadorVentana);
                break;
            }
        }
//Inicio de comandos en segunda ventana
        System.out.println("Titulo ventana 2: " + driver.getTitle());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 100)");
        Utilidades.esperar(2);
        js.executeScript("window.scrollBy(0, 100)");
        Utilidades.esperar(2);
        js.executeScript("window.scrollBy(0, 100)");
        Utilidades.esperar(2);
        js.executeScript("window.scrollBy(0, 100)");
        Utilidades.esperar(2);

        List<WebElement> contenedorLogos = driver.findElements(By.xpath("//div[@class='getting-started-topic']/h3"));

        for(int i=0; i<contenedorLogos.size(); i++){
            System.out.println("Elemento: " + contenedorLogos.get(i).getText());
        }

//Fin de comandos de segunda ventana
        driver.close();
        driver.switchTo().window(ventanaInicial);

        System.out.println("Titulo ventana 1: " + driver.getTitle());




        driver.findElement(By.xpath("//div//button[@class='btn btn-primary']")).click();

        for(String manejadorVentana : driver.getWindowHandles()){
            if(!ventanaInicial.contentEquals(manejadorVentana)) {
                driver.switchTo().window(manejadorVentana);
                break;
            }
        }
//Inicio de comandos en segunda ventana
        System.out.println("Titulo ventana 2: " + driver.getTitle());

        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0, 100)");
        Utilidades.esperar(2);
        js2.executeScript("window.scrollBy(0, 100)");
        Utilidades.esperar(2);
        js2.executeScript("window.scrollBy(0, 100)");
        Utilidades.esperar(2);
        js2.executeScript("window.scrollBy(0, 100)");
        Utilidades.esperar(2);

        List<WebElement> contenedorLogos2 = driver.findElements(By.xpath("//div[@class='getting-started-topic']/h3"));

        for(int i=0; i<contenedorLogos2.size(); i++){
            System.out.println("Elemento: " + contenedorLogos2.get(i).getText());
        }

//Fin de comandos de segunda ventana
        driver.close();
        driver.switchTo().window(ventanaInicial);



        assertEquals("Automation Demo Site", driver.findElement(By.tagName("h1")).getText());

    }

    @After
    public void close(){
        driver.quit();
    }

}
