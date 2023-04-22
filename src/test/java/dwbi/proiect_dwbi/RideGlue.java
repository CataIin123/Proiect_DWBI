package dwbi.proiect_dwbi;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class RideGlue extends Hook {
    HashMap<String, String> pages;

    @When("I am on the {string} page")
    public void iAmOnPage(String string) {
        String pageName = string.toLowerCase(Locale.ROOT) + "s";
        String url = String.format("http://localhost:8081/%s", pageName);
        getDriver().get(url);
        String windowTitle = getDriver().getTitle();
        String correctWindowTitle = string.substring(0, 1).toUpperCase() + string.substring(1) + "s";
        Assert.assertEquals(windowTitle, correctWindowTitle);
    }

    @Then("I should see a list with all the {string}")
    public void iShouldSeeAlist(String string) {
        WebElement baseTable = getDriver().findElement(By.tagName("table"));
        List<WebElement> rows = baseTable.findElements(By.tagName("td"));
        Assert.assertFalse(rows.isEmpty());
    }

    @When("I click on the {string} create button")
    public void iClickOnTheCreateButton(String string) {
        WebElement createButton = getDriver().findElement(By.className("create-" + string + "-button"));
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("create-" + string + "-button")));
        createButton.click();
    }

    @Then("I sould see the {string} create page")
    public void iSouldSeeTheCreatePage(String string) {
        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8081/" + string + "s/create?");
    }


    @Given("I am on the {string} create page")
    public void iAmOnTheCreatePage(String string) {
        String pageName = string.toLowerCase(Locale.ROOT) + "s";
        String url = String.format("http://localhost:8081/%s/create?", pageName);
        getDriver().get(url);
        String windowTitle = getDriver().getTitle();
        String correctWindowTitle = string.substring(0, 1).toUpperCase() + string.substring(1) + "s";
        Assert.assertEquals(windowTitle, correctWindowTitle);
    }

    @When("I input the ride data")
    public void iInputTheRideData() {
        WebElement clientField = getDriver().findElement(By.name("client"));
        WebElement carField = getDriver().findElement(By.name("car"));
        WebElement routeField = getDriver().findElement(By.name("route"));
        WebElement rideDateField = getDriver().findElement(By.name("rideDate"));
        WebElement statusField = getDriver().findElement(By.name("status"));
        WebElement tarifField = getDriver().findElement(By.name("tarif"));

        clientField.sendKeys("1");
        carField.sendKeys("1");
        routeField.sendKeys("1");
        rideDateField.sendKeys("01-Jan-22");
        statusField.sendKeys("test");
        tarifField.sendKeys("0");
    }

    @When("I click on the save button")
    public void iClickOnTheSaveButton() {
        WebElement submitButton = getDriver().findElement(By.name("submit"));
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("submit")));
        submitButton.click();
    }

    @And("I should see the new ride on the Ride page")
    public void iShouldSeeTheNewRideOnTheRidePage() {
        WebElement baseTable = getDriver().findElement(By.tagName("table"));
        List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("1");
        expectedElements.add("1");
        expectedElements.add("1");
        expectedElements.add("01-Jan-22");
        expectedElements.add("test");
        expectedElements.add("0");
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            List<String> actualElements =  new ArrayList<>();
            for (int j = 0; j < cells.size()-2; j++) {
                actualElements.add(cells.get(j).getText());
            }
            if (actualElements.equals(expectedElements)){
                Assert.assertArrayEquals(actualElements.toArray(), expectedElements.toArray());
            }
        }
    }
}
