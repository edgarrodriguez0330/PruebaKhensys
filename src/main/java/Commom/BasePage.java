package Commom;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BasePage {

    protected WebDriver driver;


    Random random = new Random();

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnElem(WebElement element) {
        element.click();
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = x -> ((JavascriptExecutor) Objects.requireNonNull(x)).executeScript("return document.readyState").toString().equals("complete");
        try {
            waitSeconds(1);
            WebDriverWait wait = new WebDriverWait(driver, 5); // Big timeout, due to issues in OpenShift
            wait.until(expectation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Send Text
     *
     * @param element of web
     * @param text    sender
     */
    public void sendText(WebElement element, String text) {
        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(text);
    }


    /**
     * Take the Screen shot of tests
     *
     * @param driver  webdriver
     * @param scrName screenShot name
     */
    public void takeScreenShot(WebDriver driver, String scrName) {
        File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrShot, new File("C:\\PAFScreenshot\\" + scrName + ".png"));
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * Gives you a randomName, and avoid many strings, and same names
     *
     * @return random string
     */
    public String randomName() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public void waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // 90 seconds due to issues in OpenShift services...
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void waitMillis(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public Map<String, List<WebElement>> getTableStructure(String parentId) {
        return getTable(parentId);
    }

    public Map<String, List<WebElement>> getTableStructure() {
        return getTable(null);
    }

    public Map<String, List<WebElement>> getTable(String parentId) {
        if (Objects.nonNull(parentId)) {
            parentId = String.format("#%s ", parentId);
        } else {
            parentId = "";
        }
        Map<String, List<WebElement>> table = new HashMap<>();
        WebElement baseTable = driver.findElement(By.cssSelector(parentId + ".ant-table-content"));
        List<WebElement> tableHeaders = baseTable.findElements(By.cssSelector(".ant-table-thead .ant-table-cell"));
        List<WebElement> tableRows = baseTable.findElements(By.cssSelector(".ant-table-tbody tr"));
        for (int rowIdx = 0; rowIdx < tableRows.size(); rowIdx++) {
            List<WebElement> tableRowContents = tableRows.get(rowIdx).findElements(By.className("ant-table-cell"));
            if (tableRowContents.isEmpty()) {
                // Nothing detected... Maybe the row is an aria-hidden
                continue;
            }
            for (int headerIdx = 0; headerIdx < tableHeaders.size(); headerIdx++) {
                WebElement header = tableHeaders.get(headerIdx);
                List<WebElement> currentContent = table.get(header.getText());
                if (Objects.isNull(currentContent)) {
                    currentContent = new ArrayList<>();
                }
                currentContent.add(tableRowContents.get(headerIdx));
                table.put(header.getText(), currentContent);
            }
        }
        return table;
    }


    private WebElement getTableActions(String header, String value, String actionColumnHeader, String parentId) {
        if (Objects.isNull(actionColumnHeader)) {
            actionColumnHeader = "Acción";
        }
        Map<String, List<WebElement>> listTable = getTableStructure(parentId);
        int idx = getIndexOfRow(listTable.get(header), value);
        return listTable.get(actionColumnHeader).get(idx);
    }

    public WebElement clickDeleteOnTable(String header, String value) {
        return clickDeleteOnTable(header, value, null, null);
    }

    public WebElement clickDeleteOnTableWithParent(String header, String value, String parentId) {
        return clickDeleteOnTable(header, value, null, parentId);
    }

    public WebElement clickDeleteOnTable(String header, String value, String actionColumnHeader, String parentId) {
        WebElement createdListActions = getTableActions(header, value, actionColumnHeader, parentId);
        WebElement deleteBtn = createdListActions.findElement(By.id("delete"));
        waitFor(deleteBtn);
        clickOnElem(deleteBtn);
        return deleteBtn;
    }

    public WebElement clickEditOnTable(String header, String value) {
        return clickEditOnTable(header, value, null);
    }

    public WebElement clickEditOnTable(String header, String value, String actionColumnHeader) {
        WebElement createdListActions = getTableActions(header, value, actionColumnHeader, null);
        WebElement editBtn = createdListActions.findElement(By.id("edit"));
        waitFor(editBtn);
        clickOnElem(editBtn);
        return editBtn;
    }

    public int getIndexOfRow(List<WebElement> rows, String lookForValue) {
        List<WebElement> row = rows.stream()
                .filter(we -> we.getText().equalsIgnoreCase(lookForValue))
                .collect(Collectors.toList());
        return rows.indexOf(row.get(0));
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void scrollByVisibleElement(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitSeconds(2);
        js.executeScript("window.scrollBy(0,10000)");
        //js.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public boolean verifyOptionInDropDown(String optionLabel) {
        boolean result = false;
        WebElement baseDropdown = driver.findElement(By.cssSelector(".rc-virtual-list-holder-inner"));
        List<WebElement> options = baseDropdown.findElements(By.cssSelector(".rc-virtual-list-holder-inner .ant-select-item"));
        for(WebElement option : options) {
            if (option.getAttribute("title").equals(optionLabel)) {
                result =true;
                break;
            }
        }
        return result;
    }
}
