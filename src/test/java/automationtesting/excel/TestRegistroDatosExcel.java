package automationtesting.excel;

import automationtesting.Utilidades;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static automationtesting.Constantes.*;
import static automationtesting.Utilidades.esperar;
import static org.junit.Assert.assertEquals;

public class TestRegistroDatosExcel {

    public static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void leerDatosExcel() throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String rutaArchivo = "src/test/resources/datadriven/registroLibros.xlsx";
        FileInputStream archivo = new FileInputStream(rutaArchivo);
        Workbook libro = new XSSFWorkbook(archivo); //Toma el libro del archivo
        Sheet hoja = libro.getSheetAt(0); //Toma la primera hoja teniendo en cuenta que es a nivel de vector (0 -> hoja1)

        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_PRACTICE);
        Utilidades.esperar(10);
        js.executeScript("window.scrollBy(0,1000)"); //Desplazamiento vertical
        //js.executeScript("window.scrollBy(1000,0)"); //Desplazamiento horizontalmente

        List<WebElement> listaTitulos = driver.findElements(By.xpath("//ul[@id='main-nav']/li"));

        for(int i=0; i<listaTitulos.size(); i++){
            Row fila = hoja.createRow(i);
            Cell celda = fila.createCell(0);
            celda.removeCellComment();
            celda.setCellValue(listaTitulos.get(i).getText());
        }

        FileOutputStream salidaArchivo = new FileOutputStream(rutaArchivo);
        libro.write(salidaArchivo);
        salidaArchivo.close();

        Utilidades.esperar(5);


    }


    @Test
    public void leerHobbiesExcel() throws IOException {
        String rutaArchivo = "src/test/resources/datadriven/datos.xlsx";
        FileInputStream archivo = new FileInputStream(rutaArchivo);
        XSSFWorkbook libro = new XSSFWorkbook(archivo); //Toma el libro del archivo
        XSSFSheet hoja = libro.getSheetAt(0); //Toma la primera hoja teniendo en cuenta que es a nivel de vector (0 -> hoja1)
        Row fila = hoja.getRow(1); //Parte desde la primera fila en el archivo de excel (0)

        driver.manage().window().maximize();
        driver.get(URL_PRUEBA_DEMO_REGISTRO);

        List<WebElement> contenedorCheckbox = driver.findElements(By.xpath("//div[@class='col-md-4 col-xs-4 col-sm-4']/div"));
        String opciones = String.valueOf(fila.getCell(0));

        List<String> listaOpciones = new ArrayList<>();
        listaOpciones.addAll(Arrays.asList(opciones.split(",")));
        for(int i=0; i<contenedorCheckbox.size(); i++){
            for(int j=0; j<listaOpciones.size(); j++) {
                if (contenedorCheckbox.get(i).getText().equals(listaOpciones.get(j))) {
                    driver.findElement(By.xpath("//div[@class='col-md-4 col-xs-4 col-sm-4']/div[" + (i + 1) + "]/input")).click();
                    break;
                }
            }
        }

        esperar(5);
        archivo.close();
    }

    @After
    public void close(){
        driver.quit();
    }

}
