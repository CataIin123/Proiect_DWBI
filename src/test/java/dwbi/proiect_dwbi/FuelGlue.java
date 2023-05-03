package dwbi.proiect_dwbi;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FuelGlue {

    private Hook hook;

    public FuelGlue() {
    }

    public FuelGlue(Hook hook) {
        this.hook = hook;
    }

    @When("I input the fuel data")
    public void iInputThefuelData() {
        WebElement carNumber = hook.getDriver().findElement(By.name("carNumber"));
        WebElement resupplyDate = hook.getDriver().findElement(By.name("resupplyDate"));
        WebElement quantity = hook.getDriver().findElement(By.name("quantity"));
        WebElement unitPrice = hook.getDriver().findElement(By.name("unitPrice"));

        carNumber.sendKeys("WBAPH73599A509841");
        resupplyDate.sendKeys("30-Jan-2020");
        quantity.clear();
        quantity.sendKeys("16");
        unitPrice.clear();
        unitPrice.sendKeys("12");
        int x = 1;
    }

    @And("I should see the new fuel on the fuel page")
    public void iShouldSeeTheNewfuelOnThefuelPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("WBAPH73599A509841");
        expectedElements.add("2020-01-30 00:00:00");
        expectedElements.add("16");
        expectedElements.add("12.0");

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

    @When("I input the updated fuel data")
    public void iInputTheUpdatedfuelData() {
        WebElement carNumber = hook.getDriver().findElement(By.name("carNumber"));
        WebElement resupplyDate = hook.getDriver().findElement(By.name("resupplyDate"));
        WebElement quantity = hook.getDriver().findElement(By.name("quantity"));
        WebElement unitPrice = hook.getDriver().findElement(By.name("unitPrice"));


        carNumber.sendKeys("WBAPH73599A509841");
        resupplyDate.sendKeys("30-Jan-2020");
        quantity.clear();
        quantity.sendKeys("777");
        unitPrice.clear();
        unitPrice.sendKeys("1");

    }

    @And("I should see the updated fuel on the fuel page")
    public void iShouldSeeTheUpdatedfuelOnThefuelPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("WBAPH73599A509841");
        expectedElements.add("2020-01-30 00:00:00");
        expectedElements.add("777");
        expectedElements.add("1.0");

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
