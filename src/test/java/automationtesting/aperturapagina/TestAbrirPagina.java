package automationtesting.aperturapagina;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAbrirPagina {

    public static WebDriver driver;

    @Before
    public void setUp(){
        this.driver = new ChromeDriver();
    }

    @Test
    public void abrirPagina(){
        driver.manage().window().maximize();
        driver.get("http://practice.automationtesting.in/");
    }

    @After
    public void close(){
        driver.quit();
    }

}
