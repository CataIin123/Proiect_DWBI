package dwbi.proiect_dwbi;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;


public class SharedGlue {
    private Hook hook;
    static int elementCounter = 0;
    static int idToUpdate = 0;


    public SharedGlue() {
    }

    public SharedGlue(Hook hook) {
        this.hook = hook;
    }

    @Given("I am on the {string} page")
    public void iAmOnPage(String string) {
        String pageName = string.toLowerCase(Locale.ROOT) + "s";
        String url = String.format("http://localhost:8081/%s", pageName);
        hook.initializeDriver().get(url);
        String windowTitle = hook.getDriver().getTitle();
        String correctWindowTitle = string.substring(0, 1).toUpperCase() + string.substring(1) + "s";
        Assert.assertEquals(windowTitle, correctWindowTitle);
    }

    @Given("I am on the {string} create page")
    public void iAmOnTheCreatePage(String string) {
        String pageName = string.toLowerCase(Locale.ROOT) + "s";
        String url = String.format("http://localhost:8081/%s/create?", pageName);
        hook.getDriver().get(url);
        String windowTitle = hook.getDriver().getTitle();
        String correctWindowTitle = string.substring(0, 1).toUpperCase() + string.substring(1) + "s";
        Assert.assertEquals(windowTitle, correctWindowTitle);
    }

    @Given("I am on the {string} update page")
    public void iAmOnTheUpdatePage(String string) {
        String pageName = string.toLowerCase(Locale.ROOT) + "s";
        String url = String.format("http://localhost:8081/%s/update/" + idToUpdate, pageName);
        hook.getDriver().get(url);
        String windowTitle = hook.getDriver().getTitle();
        String correctWindowTitle = string.substring(0, 1).toUpperCase() + string.substring(1) + "s";
        Assert.assertEquals(windowTitle, correctWindowTitle);
    }

    @When("I click on the {string} create button")
    public void iClickOnTheCreateButton(String string) {
        WebElement createButton = hook.getDriver().findElement(By.className("create-" + string + "-button"));
        WebDriverWait wait = new WebDriverWait(hook.getDriver(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("create-" + string + "-button")));
        createButton.click();
    }

    @When("I click on the save button")
    public void iClickOnTheSaveButton() {
        WebElement submitButton = hook.getDriver().findElement(By.name("submit"));
        WebDriverWait wait = new WebDriverWait(hook.getDriver(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("submit")));
        submitButton.click();
    }

    @When("I click on the update button for the first element")
    public void iClickOnTheUpdateButtonForTheFirst() {
        WebElement baseTable = hook.getDriver().findElement(By.tagName("table"));
        List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
        WebElement updateButton = rows.get(1).findElement(By.linkText("Update"));
        try {
            List<WebElement> cells = rows.get(1).findElements(By.tagName("td"));
            idToUpdate = Integer.parseInt(cells.get(0).getText());
        } catch (Exception e) {
            Assert.fail("The resource cannot be found");
        }
        updateButton.click();
    }

    @When("I click on the update button for the last element")
    public void iClickOnTheUpdateButtonForTheLast() {
        try {
            WebElement lastPageElement = hook.getDriver().findElement(By.linkText("Last"));
            if (lastPageElement.isEnabled()) {
                lastPageElement.click();
            }
        } catch (Exception ignored) {}
        WebElement baseTable = hook.getDriver().findElement(By.tagName("table"));
        List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
        WebElement lastRow = rows.get(rows.size() - 1);
        WebElement updateButton = lastRow.findElement(By.linkText("Update"));
        try {
            List<WebElement> cells = rows.get(1).findElements(By.tagName("td"));
            idToUpdate = Integer.parseInt(cells.get(0).getText());
        } catch (Exception e) {
            Assert.fail("The resource cannot be found");
        }
        updateButton.click();
    }


    @When("I click on the delete button for the {string} element")
    public void iClickOnTheDeleteButtonForTheElement(String string) {
        WebElement navElement = hook.getDriver().findElement(By.className("panel-footer")).findElement(By.tagName("nav"));
        String navText = navElement.getText();
        elementCounter = new Scanner(navText).useDelimiter("\\D+").nextInt();

        if (Objects.equals(string, "last")) {
            try {
                WebElement lastPageElement = hook.getDriver().findElement(By.linkText("Last"));
                if (lastPageElement.isEnabled()) {
                    lastPageElement.click();
                }
            } catch (Exception ignored) {}
            WebElement baseTable = hook.getDriver().findElement(By.tagName("table"));
            List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
            WebElement lastRow = rows.get(rows.size() - 1);
            WebElement updateButton = lastRow.findElement(By.linkText("Delete"));
            updateButton.click();
        } else {
            int id = 1;
            if (Objects.equals(string, "second")) {
                id = 2;
            }
            WebElement baseTable = hook.getDriver().findElement(By.tagName("table"));
            List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
            WebElement deleteButton = rows.get(id).findElement(By.linkText("Delete"));
            deleteButton.click();
        }
    }

    @Then("I should see a list with all the {string}")
    public void iShouldSeeAlist(String string) {
        WebElement baseTable = hook.getDriver().findElement(By.tagName("table"));
        List<WebElement> rows = baseTable.findElements(By.tagName("td"));
        Assert.assertFalse(rows.isEmpty());
    }

    @Then("I should see the {string} create page")
    public void iShouldSeeTheCreatePage(String string) {
        String url = hook.getDriver().getCurrentUrl();
        String expectedSuffix = "http://localhost:8081/" + string + "s/create";
        Assert.assertTrue(url.startsWith(expectedSuffix));
    }

    @Then("I should see the update {string} page")
    public void iSouldSeeTheUpdatePage(String string) {
        Assert.assertTrue(hook.getDriver().getCurrentUrl().startsWith("http://localhost:8081/" + string + "s/update/"));
    }

    @Then("The {string} list should contain one less element")
    public void theListShouldContainOneLessElement(String string) {
        WebElement navElement = hook.getDriver().findElement(By.className("panel-footer")).findElement(By.tagName("nav"));
        String navText = navElement.getText();
        int newElementCounter = new Scanner(navText).useDelimiter("\\D+").nextInt();
        Assert.assertEquals(newElementCounter, elementCounter - 1);
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        WebElement errorMessage = hook.getDriver().findElement(By.className("error-message"));
        Assert.assertTrue(errorMessage.getText().contains("cannot be deleted because"));
    }
}
