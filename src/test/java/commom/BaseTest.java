package commom;

import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class BaseTest{
    protected String URL;
    protected String PROPS_FILE = "insumos.properties";
    protected String SYS_WEB_DRIVER_PATH = "webdriver.chrome.driver";
    protected String driverPath;
    String exePah = System.getProperty("user.dir");
    String activeProfile;

    public BaseTest() {
        this.activeProfile = System.getProperty("activeProfile");
        String clientUrl = System.getProperty("webclientUrl");
        String driverName = System.getProperty("chromeDriverName");
        if (Objects.isNull(this.activeProfile)) {
            throw new RuntimeException("Selecciona un perfil disponible: 'Local' NO EXISTE, debes seleccionar otro.");
        }
        if (Objects.isNull(clientUrl) || Objects.isNull(driverName)) {
            throw new RuntimeException("Actualiza las dependencias del POM, luego haz clean, install y vuelve a correr el Test.");
        }
        this.URL = clientUrl;
        this.driverPath = String.format("%s/%s", exePah, driverName);

        System.out.println("Active profile: " + this.activeProfile);
        System.out.println("Driver path: " + this.driverPath);
        System.out.println("Client URL: " + this.URL);

        System.setProperty(SYS_WEB_DRIVER_PATH, driverPath);
    }

    public ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (activeProfile.contains("local")) {
            options.addArguments("start-maximized");
            options.addArguments("--incognito");
        } else {
            options.setBinary("/bin/google-chrome"); // Jenkins with tag/selector 'java' has this on path.
            options.setHeadless(true);
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        }
        return options;
    }

    public Properties getProperties() throws IOException {
        Properties props = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPS_FILE);
        props.load(inputStream);
        return props;
    }

}
