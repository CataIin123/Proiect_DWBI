package dwbi.proiect_dwbi;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DistrictGlue {

    private Hook hook;

    public DistrictGlue() {
    }

    public DistrictGlue(Hook hook) {
        this.hook = hook;
    }

    @When("I input the district data")
    public void iInputTheDistrictData() {
        WebElement districtName = hook.getDriver().findElement(By.name("districtName"));
        WebElement regionId = hook.getDriver().findElement(By.name("regionId"));

        districtName.sendKeys("Test district");
        regionId.sendKeys("1");
    }

    @When("I input the updated district data")
    public void iInputTheUpdatedDistrictData() {
        WebElement districtName = hook.getDriver().findElement(By.name("districtName"));
        WebElement regionId = hook.getDriver().findElement(By.name("regionId"));

        districtName.sendKeys("Updated test district");
        regionId.sendKeys("4");
    }

    @When("I click on the delete button for the updated district")
    public void iClickOnTheDeleteButtonForTheDistrict() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("Updated test district");
        expectedElements.add("Vest");

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

    @Then("I should see the new district on the district page")
    public void iShouldSeeTheNewDistrictOnTheDistrictPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("Test district");
        expectedElements.add("Centru");

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

    @Then("I should see the updated district on the district page")
    public void iShouldSeeTheUpdatedDistrictOnTheDistrictPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("Updated test district");
        expectedElements.add("Vest");

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
