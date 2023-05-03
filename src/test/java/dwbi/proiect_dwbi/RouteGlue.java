package dwbi.proiect_dwbi;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RouteGlue {
    private Hook hook;

    public RouteGlue() {
    }

    public RouteGlue(Hook hook) {
        this.hook = hook;
    }

    @When("I input the route data")
    public void iInputTheRouteData() {
        WebElement departure = hook.getDriver().findElement(By.name("departure"));
        WebElement arrival = hook.getDriver().findElement(By.name("arrival"));
        WebElement distance = hook.getDriver().findElement(By.name("distance"));

        departure.sendKeys("1");
        arrival.sendKeys("1");
        distance.clear();
        distance.sendKeys("100");

    }

    @And("I should see the new route on the route page")
    public void iShouldSeeTheNewRouteOnTheRoutePage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("test");
        expectedElements.add("test");
        expectedElements.add("100");

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

    @When("I input the updated route data")
    public void iInputTheUpdatedRouteData() {
        WebElement departure = hook.getDriver().findElement(By.name("departure"));
        WebElement arrival = hook.getDriver().findElement(By.name("arrival"));
        WebElement distance = hook.getDriver().findElement(By.name("distance"));

        departure.sendKeys("1");
        arrival.sendKeys("1");
        distance.clear();
        distance.sendKeys("777");
    }

    @And("I should see the updated route on the route page")
    public void iShouldSeeTheUpdatedRouteOnTheRoutePage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("test");
        expectedElements.add("test");
        expectedElements.add("777");

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
