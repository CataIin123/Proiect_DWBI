package dwbi.proiect_dwbi;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TownGlue {

    private Hook hook;

    public TownGlue() {
    }

    public TownGlue(Hook hook) {
        this.hook = hook;
    }

    @When("I input the town data")
    public void iInputTheTownData() {
        WebElement townName = hook.getDriver().findElement(By.name("townName"));
        WebElement districtId = hook.getDriver().findElement(By.name("districtId"));

        townName.sendKeys("Test town");
        districtId.sendKeys("1");
    }

    @When("I input the updated town data")
    public void iInputTheUpdatedTownData() {
        WebElement townName = hook.getDriver().findElement(By.name("townName"));
        WebElement districtId = hook.getDriver().findElement(By.name("districtId"));

        townName.sendKeys("Updated test town");
        districtId.sendKeys("2");
    }

    @When("I click on the delete button for the updated town")
    public void iClickOnTheDeleteButtonForTheTown() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("Updated test town");
        expectedElements.add("Arad");


        WebElement lastPageElement = hook.getDriver().findElement(By.linkText("Last"));
        if (lastPageElement.isEnabled()) {
            lastPageElement.click();
        }

        while (true) {
            WebElement baseTable = hook.getDriver().findElement(By.tagName("table"));
            List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
            for (WebElement row : rows) {
                try {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
                    List<String> actualElements = new ArrayList<>();
                    for (int j = 1; j < cells.size() - 2; j++) {
                        actualElements.add(cells.get(j).getText());
                    }
                    if (actualElements.equals(expectedElements)) {
                        WebElement deleteButton = row.findElement(By.linkText("Delete"));
                        deleteButton.click();
                        return;
                    }
                } catch (Exception e) {
                    break;
                }
            }
            try {
                WebElement nextPageElement = hook.getDriver().findElement(By.linkText("Previous"));
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

    @Then("I should see the new town on the town page")
    public void iShouldSeeTheNewTownOnTheTownPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("Test town");
        expectedElements.add("Alba");

        WebElement lastPageElement = hook.getDriver().findElement(By.linkText("Last"));
        if (lastPageElement.isEnabled()) {
            lastPageElement.click();
        }

        while (true) {
            WebElement baseTable = hook.getDriver().findElement(By.tagName("table"));
            List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
            for (WebElement row : rows) {
                try {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
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
                WebElement nextPageElement = hook.getDriver().findElement(By.linkText("Previous"));
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

    @Then("I should see the updated town on the town page")
    public void iShouldSeeTheUpdatedTownOnTheTownPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("Updated test town");
        expectedElements.add("Arad");

        WebElement lastPageElement = hook.getDriver().findElement(By.linkText("Last"));
        if (lastPageElement.isEnabled()) {
            lastPageElement.click();
        }

        while (true) {
            WebElement baseTable = hook.getDriver().findElement(By.tagName("table"));
            List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
            for (WebElement row : rows) {
                try {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
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
                WebElement nextPageElement = hook.getDriver().findElement(By.linkText("Previous"));
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
