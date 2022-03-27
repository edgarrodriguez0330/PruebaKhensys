package main;

import Commom.Login;
import Commom.PruebaProps;
import commom.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PruebaTest extends BaseTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver(chromeOptions());
        driver.get(URL);

    }

    @AfterTest
    public void tearDown() {
        //driver.quit();
    }

    @Test
   public void navigateToPage() throws IOException {
        PruebaProps props = new PruebaProps(getProperties());
        Login login = new Login(driver, props);

        Prueba prueba = new Prueba(driver,props);
        login.userLogin();
        prueba.nuevaSolicitud();
        prueba.formularioPaso2();
        prueba.formularioPaso3();
        prueba.formularioPaso4();
        prueba.formularioPaso5();
    }
}