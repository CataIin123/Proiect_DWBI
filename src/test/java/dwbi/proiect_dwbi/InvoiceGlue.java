package dwbi.proiect_dwbi;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class InvoiceGlue {

    private Hook hook;


    public InvoiceGlue(Hook hook) {
        this.hook = hook;
    }


    @When("I input the invoice data")
    public void iInputTheClientData() {
        WebElement rideId = hook.getDriver().findElement(By.name("rideId"));
        WebElement invoiceDate = hook.getDriver().findElement(By.name("invoiceDate"));
        WebElement totalAmount = hook.getDriver().findElement(By.name("totalAmount"));
        WebElement status = hook.getDriver().findElement(By.name("status"));

        rideId.sendKeys("61");
        invoiceDate.sendKeys("10-Feb-2020");
        totalAmount.sendKeys("1");
        status.sendKeys("test");
    }

    @And("I should see the new invoice on the invoice page")
    public void iShouldSeeTheNewClientOnTheClientPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("61");
        expectedElements.add("2020-02-10 00:00:00");
        expectedElements.add("0.01");
        expectedElements.add("test");
        while (true) {
            WebElement baseTable = hook.getDriver().findElement(By.tagName("table"));
            List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
            for (int i = 0; i < rows.size(); i++) {
                try {
                    List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
                    List<String> actualElements = new ArrayList<>();
                    for (int j = 1; j < cells.size() - 2; j++) {
                        actualElements.add(cells.get(j).getText());
                    }
                    if (actualElements.equals(expectedElements)) {
                        Assert.assertArrayEquals(actualElements.toArray(), expectedElements.toArray());
                        return;
                    }
                } catch (Exception e) {
                    break;
                }
            }
            try {
                WebElement nextPageElement = hook.getDriver().findElement(By.linkText("Next"));
                if (nextPageElement.isEnabled()) {
                    nextPageElement.click();
                } else {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        Assert.fail("The resource cannot be found");
    }

    @When("I input the updated invoice data")
    public void iInputTheUpdatedClientData() {
        WebElement rideId = hook.getDriver().findElement(By.name("rideId"));
        WebElement invoiceDate = hook.getDriver().findElement(By.name("invoiceDate"));
        WebElement totalAmount = hook.getDriver().findElement(By.name("totalAmount"));
        WebElement status = hook.getDriver().findElement(By.name("status"));

        rideId.sendKeys("61");
        invoiceDate.sendKeys("10-Feb-2020");
        totalAmount.sendKeys("1");
        status.sendKeys("test updated");
    }

    @And("I should see the updated invoice on the invoice page")
    public void iShouldSeeTheUpdatedClientOnTheClientPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("61");
        expectedElements.add("2020-02-10 00:00:00");
        expectedElements.add("0.01");
        expectedElements.add("test updated");

        while (true) {
            WebElement baseTable = hook.getDriver().findElement(By.tagName("table"));
            List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
            for (int i = 0; i < rows.size(); i++) {
                try {
                    List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
                    List<String> actualElements = new ArrayList<>();
                    for (int j = 1; j < cells.size() - 2; j++) {
                        actualElements.add(cells.get(j).getText());
                    }
                    if (actualElements.equals(expectedElements)) {
                        Assert.assertArrayEquals(actualElements.toArray(), expectedElements.toArray());
                        return;
                    }
                } catch (Exception e) {
                    break;
                }
            }
            try {
                WebElement nextPageElement = hook.getDriver().findElement(By.linkText("Next"));
                if (nextPageElement.isEnabled()) {
                    nextPageElement.click();
                } else {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        Assert.fail("The resource cannot be found");
    }
}
