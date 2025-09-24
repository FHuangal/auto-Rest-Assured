package com.nttdata.testing.stepDefinitions;

import com.nttdata.testing.questions.ResponseCode;
import com.nttdata.testing.tasks.GetAllPets;
import com.nttdata.testing.tasks.PostPet;
import com.nttdata.testing.tasks.PutPet;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class PetStoreStepDefinitions {

    public static Logger LOGGER = LoggerFactory.getLogger(PetStoreStepDefinitions.class);

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el {actor} establece el endpoint  de petStore")
    public void elActorEstableceElEndpointDePetStore(Actor actor) {
        actor.whoCan(CallAnApi.at("https://petstore.swagger.io/v2"));
    }

    @When("el {actor} realiza una solicitud GET")
    public void elActorRealizaUnaSolicitudGET(Actor actor) {
        actor.attemptsTo(GetAllPets.fromEndpoint("/pet/findByStatus?status=available"));
    }

    @Then("el codigo de respuesta debe ser {int}")
    public void elCodigoDeRespuestaDebeSer(int responseCode) {
        theActorInTheSpotlight().should(seeThat("El código de respuesta", ResponseCode.getStatus(), equalTo(responseCode)));
    }

    @When("^el actor crea un pet con el (.*) (.*) (.*) (.*) (.*) (.*) (.*) (.*)$")
    public void elActorCreaUnPetConEl(String pet_id, String category_id, String category_name, String pet_name, String photoUrls, String tag_id, String tag_name, String status) {
        theActorInTheSpotlight().attemptsTo(PostPet.fromPage(pet_id,category_id,category_name,pet_name,photoUrls,tag_id,tag_name,status));
    }

    @When("^el actor actualiza un Pet con los datos (.*) (.*) (.*) (.*) (.*) (.*) (.*)$")
    public void elActorActualizaUnPetConLosDatos(String category_id, String category_name, String pet_name, String photoUrls, String tag_id, String tag_name, String status) {
        theActorInTheSpotlight().attemptsTo();
    }
}
