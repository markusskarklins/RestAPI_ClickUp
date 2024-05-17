package com.apitest;

import io.cucumber.java.en.*;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class StepDefinitions {

    private String spaceId;
    private static String folderId;
    private String listId;
    private String taskId;
    private String apiKey = "pk_74411390_GQEQLKC3PJ3JBV21VPVVIDJBDEPP2GJ5";
    private String baseUrl = "https://api.clickup.com/api/v2/";

    public static String getFolderId() {
        return folderId;
    }

    @Given("I have a ClickUp Space with ID {string}")
    public void clickupId(String spaceId) {
        this.spaceId = spaceId;
    }

    @When("I create a new Folder named {string}")
    public void newFolder(String folderName) {
        JSONObject body = new JSONObject();
        body.put("name", folderName);

        folderId = given()
            .header("Authorization", apiKey)
            .contentType("application/json")
            .body(body.toJSONString())
            .when()
            .post(baseUrl + "space/" + spaceId + "/folder")
            .then()
            .statusCode(200)
            .extract()
            .path("id");
    }

    @Then("I verify that the Folder named {string} is created successfully")
    public void correctFolderName(String folderName) {
        given()
            .header("Authorization", apiKey)
            .when()
            .get(baseUrl + "folder/" + folderId)
            .then()
            .statusCode(200)
            .body("name", equalTo(folderName));
    }

    @When("I create a new List named {string} in the Folder {string}")
    public void newListInFolder(String listName, String folderName) {
        JSONObject body = new JSONObject();
        body.put("name", listName);

        listId = given()
            .header("Authorization", apiKey)
            .contentType("application/json")
            .body(body.toJSONString())
            .when()
            .post(baseUrl + "folder/" + folderId + "/list")
            .then()
            .statusCode(200)
            .extract()
            .path("id");
    }

    @Then("I verify that the List named {string} is created successfully")
    public void correctListName(String listName) {
        given()
            .header("Authorization", apiKey)
            .when()
            .get(baseUrl + "list/" + listId)
            .then()
            .statusCode(200)
            .body("name", equalTo(listName));
    }

    @When("I create a new Task in the List {string}")
    public void newTaskInList(String listName) {
        JSONObject body = new JSONObject();
        body.put("name", "TestTask-" + System.currentTimeMillis());

        taskId = given()
            .header("Authorization", apiKey)
            .contentType("application/json")
            .body(body.toJSONString())
            .when()
            .post(baseUrl + "list/" + listId + "/task")
            .then()
            .statusCode(200)
            .extract()
            .path("id");
    }

    @Then("I verify that the Task is created successfully")
    public void taskIsCreated() {
        given()
            .header("Authorization", apiKey)
            .when()
            .get(baseUrl + "task/" + taskId)
            .then()
            .statusCode(200)
            .body("id", equalTo(taskId));
    }

    @When("I remove the Task from the List {string}")
    public void removeTaskFromList(String listName) {
        given()
            .header("Authorization", apiKey)
            .when()
            .delete(baseUrl + "task/" + taskId)
            .then()
            .statusCode(anyOf(is(200), is(204)));
    }

    @Then("I verify that the Task is removed successfully")
    public void taskIsRemoved() {
        given()
            .header("Authorization", apiKey)
            .when()
            .get(baseUrl + "task/" + taskId)
            .then()
            .statusCode(404);
    }
}
