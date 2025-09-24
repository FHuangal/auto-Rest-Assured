package com.nttdata.testing.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutPet implements Task {
    private final String category_id, category_name, pet_name, photoUrls, tag_id, tag_name, status;
    private final String pet_id;


    public PutPet(String category_id, String category_name, String pet_name, String photoUrls, String tag_id, String tag_name, String status) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.pet_name = pet_name;
        this.photoUrls = photoUrls;
        this.tag_id = tag_id;
        this.tag_name = tag_name;
        this.status = status;
        this.pet_id = OnStage.theActorInTheSpotlight().recall("pet_id");
    }

    public static Performable fromPage(String category_id, String category_name, String pet_name, String photoUrls, String tag_id, String tag_name, String status){
        return instrumented(PutPet.class,category_id,category_name,pet_name,photoUrls,tag_id,tag_name,status);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Put.to("/pet")
                .with(requestSpecification -> requestSpecification
                        .contentType(ContentType.JSON)
                        .body("{\"id\":" + pet_id + ","
                                + "\"category\":" + "{"
                                + "\"id\":" + category_id + ","
                                + "\"name\":" + category_name + "},"
                                + "\"name\":" + pet_name + ","
                                + "\"photoUrls\":" + "[" + photoUrls + "],"
                                + "\"tags\":" + "[{"
                                + "\"id\":" + tag_id + ","
                                + "\"name\":" + tag_name + "}],"
                                + "\"status\":" + status + "}")
                        .log().all()));
    }
}
