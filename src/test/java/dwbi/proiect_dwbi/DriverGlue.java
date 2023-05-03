package dwbi.proiect_dwbi;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DriverGlue {

    private Hook hook;

    public DriverGlue() {
    }

    public DriverGlue(Hook hook) {
        this.hook = hook;
    }

    @When("I input the driver data")
    public void iInputTheDriverData() {
        WebElement firstName = hook.getDriver().findElement(By.name("firstName"));
        WebElement lastName = hook.getDriver().findElement(By.name("lastName"));
        WebElement birthDay = hook.getDriver().findElement(By.name("birthDay"));
        WebElement cnp = hook.getDriver().findElement(By.name("cnp"));
        WebElement phone = hook.getDriver().findElement(By.name("phone"));
        WebElement address = hook.getDriver().findElement(By.name("address"));
        WebElement licenceIssueDate = hook.getDriver().findElement(By.name("licenceIssueDate"));
        WebElement licenceTimeoutDate = hook.getDriver().findElement(By.name("licenceTimeoutDate"));

        firstName.sendKeys("Rawley");
        lastName.sendKeys("Fealey");
        birthDay.sendKeys("30-Jan-1980");
        cnp.sendKeys("1949138290043");
        phone.sendKeys("463-303-1248");
        address.sendKeys("0963 Longview Pass");
        licenceIssueDate.sendKeys("30-Jan-2020");
        licenceTimeoutDate.sendKeys("30-Jan-2022");
        int x = 1;
    }

    @And("I should see the new driver on the driver page")
    public void iShouldSeeTheNewDriverOnTheDriverPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("Rawley");
        expectedElements.add("Fealey");
        expectedElements.add("1980-01-30 00:00:00");
        expectedElements.add("1949138290043");
        expectedElements.add("463-303-1248");
        expectedElements.add("0963 Longview Pass");
        expectedElements.add("2020-01-30 00:00:00");
        expectedElements.add("2022-01-30 00:00:00");
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

    @When("I input the updated driver data")
    public void iInputTheUpdatedDriverData() {
        WebElement firstName = hook.getDriver().findElement(By.name("firstName"));
        WebElement lastName = hook.getDriver().findElement(By.name("lastName"));
        WebElement birthDay = hook.getDriver().findElement(By.name("birthDay"));
        WebElement cnp = hook.getDriver().findElement(By.name("cnp"));
        WebElement phone = hook.getDriver().findElement(By.name("phone"));
        WebElement address = hook.getDriver().findElement(By.name("address"));
        WebElement licenceIssueDate = hook.getDriver().findElement(By.name("licenceIssueDate"));
        WebElement licenceTimeoutDate = hook.getDriver().findElement(By.name("licenceTimeoutDate"));

        firstName.sendKeys("updated");
        lastName.sendKeys("updated");
        birthDay.sendKeys("30-Jan-1980");
        cnp.sendKeys("1949138290043");
        phone.sendKeys("463-303-1248");
        address.sendKeys("0963 Longview Pass");
        licenceIssueDate.sendKeys("30-Jan-2020");
        licenceTimeoutDate.sendKeys("30-Jan-2022");
    }

    @And("I should see the updated driver on the driver page")
    public void iShouldSeeTheUpdatedDriverOnTheDriverPage() {
        List<String> expectedElements = new ArrayList<>();
        expectedElements.add("updated");
        expectedElements.add("updated");
        expectedElements.add("1980-01-30 00:00:00");
        expectedElements.add("1949138290043");
        expectedElements.add("463-303-1248");
        expectedElements.add("0963 Longview Pass");
        expectedElements.add("2020-01-30 00:00:00");
        expectedElements.add("2022-01-30 00:00:00");
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
