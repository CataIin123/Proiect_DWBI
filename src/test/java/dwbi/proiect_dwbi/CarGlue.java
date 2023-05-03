package dwbi.proiect_dwbi;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CarGlue {

    private Hook hook;

    public CarGlue() {
    }

    public CarGlue(Hook hook) {
        this.hook = hook;
    }

    @When("I input the car data")
    public void iInputTheCarData() {
        WebElement carNumber = hook.getDriver().findElement(By.name("carNumber"));
        WebElement transportCapacity = hook.getDriver().findElement(By.name("transportCapacity"));
        WebElement carType = hook.getDriver().findElement(By.name("carType"));

        carNumber.sendKeys("1GYS4GEF0BR677777");
        transportCapacity.sendKeys("28256");
        carType.sendKeys("electric");
    }

    @And("I should see the new car on the car page")
    public void iShouldSeeTheNewCarOnTheCarPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("1GYS4GEF0BR677777");
        expectedElements.add("28256");
        expectedElements.add("electric");
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

    @When("I input the updated car data")
    public void iInputTheUpdatedCarData() {
        WebElement carNumber = hook.getDriver().findElement(By.name("carNumber"));
        WebElement transportCapacity = hook.getDriver().findElement(By.name("transportCapacity"));
        WebElement carType = hook.getDriver().findElement(By.name("carType"));

        carNumber.sendKeys("1GYS4GEF0BR677777");
        transportCapacity.sendKeys("28256");
        carType.sendKeys("updated");
    }

    @And("I should see the updated car on the car page")
    public void iShouldSeeTheUpdatedCarOnTheCarPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("1GYS4GEF0BR677777");
        expectedElements.add("28256");
        expectedElements.add("updated");
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
