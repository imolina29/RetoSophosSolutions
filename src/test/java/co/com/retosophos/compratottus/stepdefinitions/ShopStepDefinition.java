package co.com.retosophos.compratottus.stepdefinitions;

import static co.com.retosophos.compratottus.questions.VerifyProduct.verifyProduct;

import org.openqa.selenium.WebDriver;

import static co.com.retosophos.compratottus.tasks.OpenTheBrowser.openTheBrowser;
import static co.com.retosophos.compratottus.tasks.SearchProduct.searchProduct;
import static org.hamcrest.CoreMatchers.equalTo;

import co.com.retosophos.compratottus.userinterface.Tottuspages;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class ShopStepDefinition {

    @Managed(driver = "chrome")
    private WebDriver herBrowser;

    private Actor user = Actor.named("user");
    private Tottuspages tottuspages;

    @Before
    public void setUp() {
        user.can(BrowseTheWeb.with(herBrowser));
    }

    @Given("^the user is on the tottus page$")
    public void theUserIsOnTheTottusPage() {
        user.wasAbleTo(openTheBrowser(tottuspages));
    }

    @When("^the user search and add a product as (.*)$")
    public void theUserSearchAndAddAProduct(String nameProduct) {
        user.attemptsTo(searchProduct(nameProduct));
    }

    @Then("^the user check my products in the shopping cart$")
    public void theUserCheckMyProductsInTheShoppingCart() {
        user.should(seeThat(verifyProduct(), equalTo(true)));
    }

}
