package dwbi.proiect_dwbi;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import java.util.*;

public class ClientGlue {

    private Hook hook;

    public ClientGlue() {
    }

    public ClientGlue(Hook hook) {
        this.hook = hook;
    }

    @When("I input the client data")
    public void iInputTheClientData() {
        WebElement clientName = hook.getDriver().findElement(By.name("clientName"));
        WebElement cui = hook.getDriver().findElement(By.name("cui"));
        WebElement invoiceAddress = hook.getDriver().findElement(By.name("invoiceAddress"));
        WebElement deliveryAddress = hook.getDriver().findElement(By.name("deliveryAddress"));
        WebElement contactPerson = hook.getDriver().findElement(By.name("contactPerson"));
        WebElement phone = hook.getDriver().findElement(By.name("phone"));

        clientName.sendKeys("test client");
        cui.sendKeys("12345");
        invoiceAddress.sendKeys("test");
        deliveryAddress.sendKeys("test");
        contactPerson.sendKeys("contact");
        phone.sendKeys("1234235");
    }

    @And("I should see the new client on the client page")
    public void iShouldSeeTheNewClientOnTheClientPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("test client");
        expectedElements.add("12345");
        expectedElements.add("test");
        expectedElements.add("test");
        expectedElements.add("contact");
        expectedElements.add("1234235");
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

    @When("I input the updated client data")
    public void iInputTheUpdatedClientData() {
        WebElement clientName = hook.getDriver().findElement(By.name("clientName"));
        WebElement cui = hook.getDriver().findElement(By.name("cui"));
        WebElement invoiceAddress = hook.getDriver().findElement(By.name("invoiceAddress"));
        WebElement deliveryAddress = hook.getDriver().findElement(By.name("deliveryAddress"));
        WebElement contactPerson = hook.getDriver().findElement(By.name("contactPerson"));
        WebElement phone = hook.getDriver().findElement(By.name("phone"));

        clientName.sendKeys("test client updated");
        cui.sendKeys("12345");
        invoiceAddress.sendKeys("test updated");
        deliveryAddress.sendKeys("test updated");
        contactPerson.sendKeys("contact");
        phone.sendKeys("1234235");
    }

    @And("I should see the updated client on the client page")
    public void iShouldSeeTheUpdatedClientOnTheClientPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("test client updated");
        expectedElements.add("12345");
        expectedElements.add("test updated");
        expectedElements.add("test updated");
        expectedElements.add("contact");
        expectedElements.add("1234235");
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

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        try {
            WebElement errorMessage = hook.getDriver().findElement(By.className("error-message"));
            Assert.assertEquals("The client cannot be deleted because he booked a ride", errorMessage.getText());
        } catch (Exception e) {
        }
    }
}
