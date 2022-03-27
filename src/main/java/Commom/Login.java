package Commom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Login  extends  BasePage{

    PruebaProps props;
    @FindBy(xpath = "//div[@class = 'logo']")
    WebElement pageAuth;
    @FindBy(id = "txtUsername")
    WebElement userName;
    @FindBy(id = "txtPassword")
    WebElement userPass;
    @FindBy(id = "btnEnter")
    WebElement loginBtn;
    @FindBy(xpath="//h1[contains(text(), 'Hola')]")
    WebElement afterLogin;


    public Login(WebDriver driver, PruebaProps props){
        setDriver(driver);
        this.props = props;
        PageFactory.initElements(driver, this);
    }

    public void userLogin(){
        waitForPageLoaded();
        String UserLogin = props.getUser();
        String PassLogin = props.getPassword();
        try{
            if(pageAuth.isDisplayed()){
                if(userName.isEnabled()){
                    sendText(userName, UserLogin);
                    sendText(userPass, PassLogin);
                    clickOnElem(loginBtn);
                    waitForPageLoaded();
                    if(afterLogin.isDisplayed()){
                        System.out.println("Login Exitoso");
                    }

                }

            }
        } catch (Exception e){
            Assert.fail("Test de Login Falló");
            e.printStackTrace();
        }
    }
}
