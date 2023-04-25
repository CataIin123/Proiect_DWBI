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
import java.util.*;

public class SharedGlue extends SetupGlue{
    static int elementCounter = 0;

    @Given("I am on the {string} page")
    public void iAmOnPage(String string) {
        String pageName = string.toLowerCase(Locale.ROOT) + "s";
        String url = String.format("http://localhost:8081/%s", pageName);
        getDriver().get(url);
        String windowTitle = getDriver().getTitle();
        String correctWindowTitle = string.substring(0, 1).toUpperCase() + string.substring(1) + "s";
        Assert.assertEquals(windowTitle, correctWindowTitle);
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

    @Given("I am on the {string} update page")
    public void iAmOnTheUpdatePage(String string) {
        String pageName = string.toLowerCase(Locale.ROOT) + "s";
        String url = String.format("http://localhost:8081/%s/update/3", pageName);
        getDriver().get(url);
        String windowTitle = getDriver().getTitle();
        String correctWindowTitle = string.substring(0, 1).toUpperCase() + string.substring(1) + "s";
        Assert.assertEquals(windowTitle, correctWindowTitle);
    }


    @When("I click on the {string} create button")
    public void iClickOnTheCreateButton(String string) {
        WebElement createButton = getDriver().findElement(By.className("create-" + string + "-button"));
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("create-" + string + "-button")));
        createButton.click();
    }

    @When("I click on the save button")
    public void iClickOnTheSaveButton() {
        WebElement submitButton = getDriver().findElement(By.name("submit"));
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("submit")));
        submitButton.click();
    }

    @When("I click on the update button for the first element")
    public void iClickOnTheUpdateButtonForTheFirst() {
        WebElement baseTable = getDriver().findElement(By.tagName("table"));
        List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
        WebElement updateButton = rows.get(1).findElement(By.linkText("Update"));
        updateButton.click();
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

    @Then("I should see a list with all the {string}")
    public void iShouldSeeAlist(String string) {
        WebElement baseTable = getDriver().findElement(By.tagName("table"));
        List<WebElement> rows = baseTable.findElements(By.tagName("td"));
        Assert.assertFalse(rows.isEmpty());
    }

    @Then("I should see the {string} create page")
    public void iShouldSeeTheCreatePage(String string) {
        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8081/" + string + "s/create?");
    }

    @Then("I should see the update {string} page")
    public void iSouldSeeTheUpdatePage(String string) {
        Assert.assertTrue(getDriver().getCurrentUrl().startsWith("http://localhost:8081/" + string + "s/update/"));
    }

    @Then("The {string} list should contain one less element")
    public void theListShouldContainOneLessElement(String string) {
        WebElement navElement = getDriver().findElement(By.className("panel-footer")).findElement(By.tagName("nav"));
        String navText = navElement.getText();
        int newElementCounter = new Scanner(navText).useDelimiter("\\D+").nextInt();
        Assert.assertEquals(newElementCounter, elementCounter - 1);
    }
}
