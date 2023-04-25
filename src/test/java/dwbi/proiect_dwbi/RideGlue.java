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

public class RideGlue extends SetupGlue {

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
}
