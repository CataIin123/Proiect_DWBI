package dwbi.proiect_dwbi;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class RideGlue extends Hook {
    static int elementCounter = 0;

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

    @Then("I should see the {string} create page")
    public void iShouldSeeTheCreatePage(String string) {
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

    @And("I should see the new ride on the ride page")
    public void iShouldSeeTheNewRideOnTheRidePage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("Topicware");
        expectedElements.add("1GTN1TEH3FZ321117");
        expectedElements.add("1");
        expectedElements.add("2022-01-01 00:00:00");
        expectedElements.add("test");
        expectedElements.add("0.0");
        while (true) {
            WebElement baseTable = getDriver().findElement(By.tagName("table"));
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
                WebElement nextPageElement = getDriver().findElement(By.linkText("Next"));
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

    @When("I click on the update button for the first element")
    public void iClickOnTheUpdateButtonForTheFirst() {
        WebElement baseTable = getDriver().findElement(By.tagName("table"));
        List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
        WebElement updateButton = rows.get(1).findElement(By.linkText("Update"));
        updateButton.click();
    }

    @Then("I should see the update {string} page")
    public void iSouldSeeTheUpdatePage(String string) {
        Assert.assertTrue(getDriver().getCurrentUrl().startsWith("http://localhost:8081/" + string + "s/update/"));
    }

    @Given("I am on the {string} update page")
    public void iAmOnTheUpdatePage(String string) {
        String pageName = string.toLowerCase(Locale.ROOT) + "s";
        String url = String.format("http://localhost:8081/%s/update/3", pageName);
        getDriver().get(url);
        String windowTitle = getDriver().getTitle();
        String correctWindowTitle = string.substring(0, 1).toUpperCase() + string.substring(1) + "s";
        Assert.assertEquals(windowTitle, correctWindowTitle);
    }

    @When("I input the updated ride data")
    public void iInputTheUpdatedRideData() {
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
        statusField.sendKeys("updated ride33");
        tarifField.sendKeys("0");
    }

    @When("I click on the delete button for the first element")
    public void iClickOnTheDeleteButtonForTheFirstElement() {
        WebElement navElement = getDriver().findElement(By.className("panel-footer")).findElement(By.tagName("nav"));
        String navText = navElement.getText();
        elementCounter = new Scanner(navText).useDelimiter("\\D+").nextInt();
        WebElement baseTable = getDriver().findElement(By.tagName("table"));
        List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
        WebElement deleteButton = rows.get(1).findElement(By.linkText("Delete"));
        deleteButton.click();
    }

    @Then("The {string} list should contain one less element")
    public void theListShouldContainOneLessElement(String string) {
        WebElement navElement = getDriver().findElement(By.className("panel-footer")).findElement(By.tagName("nav"));
        String navText = navElement.getText();
        int newElementCounter = new Scanner(navText).useDelimiter("\\D+").nextInt();
        Assert.assertEquals(newElementCounter, elementCounter - 1);
    }
}
