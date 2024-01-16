package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest extends TestBase {
    @Test
    public void verifyPost() {
        //    1. Verify the if the total record is 25
//            2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupticohaero libero.”
//                      3. Check the single user_id in the Array list (5914064)
//                      4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
//                      5. Verify the body of userid = 5914202 is equal “Cum adulatio aranea. Concido quibusdam aliquid. Admitto a vergo. Voro tabella carcer. Ater testimonium canis. Odit vel aureus. Sed speculum volup. Vel suppono corrumpo. Officiis suggero laboriosam. Sui defendo celer. Paens acceptus fuga. Ustulo et neque. Deorsum cenaculum aspicio. Consuasor curriculum pauper. Vitiosus suppono atrox. Adultus coruscus aestas. Curatio deserunt cresco."
        given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/public/v2/posts")
                .then()
                .statusCode(200)
                .body(
                        "id", hasSize(25),
                        "find{it.id == 93997}.title", equalTo("Demitto conqueror atavus argumentum corrupticohaero libero."),
                        "user_id", hasItem(5914064),
                        "user_id", hasItems(5914243, 5914202, 5914199),
                        "find{it.user_id == 5914065}.body", equalTo("Caritas vomer sublime. Tumultus nisi damno. Ulterius campana virgo. Argentum amaritudo comitatus. Temeritas theatrum cito. Chirographum adamo aqua. Canis adsidue fuga. Stipes canonicus accusamus. Clamo subiungo soleo. Video adeptio ater. Surculus modi delinquo. Tendo claustrum aestivus. Sulum ut vilitas. Ante vulgo depereo.")
                );
    }
}
