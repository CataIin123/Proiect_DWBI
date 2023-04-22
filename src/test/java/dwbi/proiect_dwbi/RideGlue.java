package dwbi.proiect_dwbi;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RideGlue extends Hook {

    @When("I open the Ride page")
    public void i_open_the_Ride_page() {
        getDriver().get("http://localhost:8081/invoices");
    }

    @Then("I should see a list with all the rides")
    public void i_should_see_a_list_with_all_the_rides() {
        getDriver().get("http://localhost:8081/invoices");
    }

    @Given("I click on the create button")
    public void i_click_on_the_create_button() throws Throwable {
        getDriver().get("http://localhost:8081/invoices");
    }

}
