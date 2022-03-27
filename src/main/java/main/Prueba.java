package main;

import Commom.BasePage;
import Commom.PruebaProps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Prueba extends BasePage {
    PruebaProps props;
    @FindBy(xpath = "//label[contains(text(), 'Solicitud')]")
    WebElement btnNuevaSolicitud;
    @FindBy(id = "btagreements")
    WebElement aceptar;
    @FindBy(id = "btUserAgreement")
    WebElement aceptarLastPage;
    @FindBy(xpath = "//span[contains(text(), 'CONTINUAR')]")
    WebElement continuarSiguienteFormulario;
    @FindBy(id = "mentorFullName")
    WebElement nombre;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep2_mentorJobPosition")
    WebElement puesto;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep2_mentorWorkplace")
    WebElement lugarTrabajo;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep2_mentorPhoneNumber")
    WebElement telefono;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep2_mentorCellphone")
    WebElement celular;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep2_mentorEmail")
    WebElement correo;
    @FindBy(xpath = "//input[contains(@id, 'MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep3_requestTypeInvestigation')]")
    WebElement checkRealizarInvestigacion;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep3_schoolDependencies")
    WebElement proveaDetalle;
    @FindBy(id = "lbOrganizationName")
    WebElement checkInstitucionTrabaja;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep3_OrganizationNameSpecification")
    WebElement inputinstitucionTrabaja;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep4_researchType")
    WebElement tituloInestigacion;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep4_s2objective")
    WebElement objetivo;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep4_s2Methodology")
    WebElement resumenMetodoligia;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep4_s2Sample")
    WebElement muestra;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep5_ccpiApprovedTheachers")
    WebElement checkCartaConsentimiento;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep5_investigationImportanceDescription")
    WebElement inputImportanciaUtilidad;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep5_agreedTerms")
    WebElement checkTerminosCondiciones;
    @FindBy(id = "MainContent_ucClaimContainer_fvClaimContainer_Wizard1_ClaimStep5_btnEnviar")
    WebElement btnEnviarFormulario;
    @FindBy(id = "PageFunctionsContent_ucRightFunctionBox_lvwFunctions_lblFunctionLabel_0")
    WebElement btnGuardar;


    public Prueba(WebDriver driver, PruebaProps props) {
        setDriver(driver);
        this.props = props;
        PageFactory.initElements(driver, this);
    }


    public void nuevaSolicitud() {
        try {
            if (btnNuevaSolicitud.isEnabled()) {
                clickOnElem(btnNuevaSolicitud);
                waitForPageLoaded();
                if (aceptar.isDisplayed()) {
                    clickOnElem(aceptar);
                    waitForPageLoaded();
                    if (continuarSiguienteFormulario.isDisplayed()) {
                        clickOnElem(continuarSiguienteFormulario);
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail("Test de Nueva Solicitud Falló");
            e.printStackTrace();
        }

    }

    public void formularioPaso2() {
        waitForPageLoaded();
        String NombreInsumo = props.getNombre();
        String PuestoInsumo = props.getPuesto();
        String LugarTrabajoInsumo = props.getLugarTrabajo();
        String TelefonoInsumo = props.getTelefono();
        String CelularInsumo = props.getCelular();
        String CorreoInsumo = props.getCorreo();
        try {
            waitForPageLoaded();
            if (nombre.isDisplayed()) {
                sendText(nombre, NombreInsumo);
                sendText(puesto, PuestoInsumo);
                sendText(lugarTrabajo, LugarTrabajoInsumo);
                sendText(telefono, TelefonoInsumo);
                sendText(celular, CelularInsumo);
                sendText(correo, CorreoInsumo);
                if (continuarSiguienteFormulario.isDisplayed()) {
                    clickOnElem(continuarSiguienteFormulario);
                    System.out.println("Se completó el Formulario 2 Exitosamente");
                }
            }

        } catch (Exception e) {
            Assert.fail("Test de Formulario 2 Falló");
            e.printStackTrace();
        }
    }

    public void formularioPaso3() {
        try {
            waitForPageLoaded();
            String ProveaDetalleInsumo = props.getProveaDetalle();
            String InstitucionTrabaja = props.getInstitucionTrabaja();
            waitForPageLoaded();
            if (checkRealizarInvestigacion.isDisplayed()) {
                clickOnElem(checkRealizarInvestigacion);
                waitForPageLoaded();
                if (proveaDetalle.isDisplayed()) {
                    sendText(proveaDetalle, ProveaDetalleInsumo);
                    waitForPageLoaded();
                    if (checkInstitucionTrabaja.isDisplayed()) {
                        clickOnElem(checkInstitucionTrabaja);
                        waitForPageLoaded();
                        sendText(inputinstitucionTrabaja, InstitucionTrabaja);
                        System.out.println("Se completó el Formulario 3 Exitosamente");
                        clickOnElem(continuarSiguienteFormulario);
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail("Test de Formulario 3 Falló");
            e.printStackTrace();
        }
    }

    public void formularioPaso4() {
        try {
            waitForPageLoaded();
            String TituloInvestigacionInsumo = props.getTituloInvestigacion();
            String ObjetivoInsumo = props.getObjetivo();
            String ResumenMetodologiaInsumo = props.getResumenMetodologia();
            String MuestraInsumo = props.getMuestra();
            waitForPageLoaded();
            if (tituloInestigacion.isDisplayed()) {
                sendText(tituloInestigacion, TituloInvestigacionInsumo);
                sendText(objetivo, ObjetivoInsumo);
                sendText(resumenMetodoligia, ResumenMetodologiaInsumo);
                sendText(muestra, MuestraInsumo);
                System.out.println("Se completó el Formulario 4 Exitosamente");
                clickOnElem(continuarSiguienteFormulario);
            }

        } catch (Exception e) {
            Assert.fail("Test de Formulario 4 Falló");
            e.printStackTrace();
        }
    }

    public void formularioPaso5() {
        try {
            waitForPageLoaded();
            String ImportanciaUtilidadInsumo = props.getImportanciaUtilidad();
            if(checkCartaConsentimiento.isDisplayed()){
                clickOnElem(checkCartaConsentimiento);
                sendText(inputImportanciaUtilidad, ImportanciaUtilidadInsumo);
                clickOnElem(checkTerminosCondiciones);
                clickOnElem(btnEnviarFormulario);
                waitForPageLoaded();
                clickOnElem(aceptarLastPage);
                waitForPageLoaded();
                clickOnElem(btnEnviarFormulario);
                System.out.println("Se completó el Formulario 5 Exitosamente");
                waitFor(btnGuardar);
                if(btnGuardar.isDisplayed()){
                    System.out.println("Prueba Finalizada");
                }
            }

        } catch (Exception e) {
            Assert.fail("Test de Formulario 5 Falló");
            e.printStackTrace();
        }
    }


}
