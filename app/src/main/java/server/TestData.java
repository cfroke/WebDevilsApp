package server;

import common.Concept;
import common.User;

/**
 * Created by Kevin on 2/19/2017.
 */

public class TestData {

    Services services = new Services();
    private static TestData singleton = new TestData();
    private String product = "Product";
    private String service = "Service";
    private String improvement = "Improvement";

    private TestData() {
        initializeTestData();
    }

    public static TestData getInstance() {
        return singleton;
    }

    private void initializeTestData() {

        //Member Data
        User testUser = services.createMemberUser("user","password");
        User bob = services.createMemberUser("bob","bob");
        User jane = services.createMemberUser("jane","jane");
        User casey = services.createMemberUser("casey","casey");

        //Concept Data
        Concept bobConcept = services.createConcept(bob, "New Investment Product", "I think it would be awesome to have " +
                "this awesome investment product available to trade.", product, "");
        services.upVoteConcept(bobConcept);

        Concept bobConcept2 = services.createConcept(bob,"Later hours","It would be great if the bank was open later on " +
                "saturdays.",service, "jane");
        bobConcept2.setStatusToApproved();
        services.saveConcept(bobConcept2);
        services.upVoteConcept(bobConcept2);

        Concept bobConcept3 = services.createConcept(bob,"Make Money on Suggestions","It would be great we got paid for our suggestions.",service, "");
        bobConcept3.setStatusToRejected();
        services.saveConcept(bobConcept3);

        Concept janeConcept = services.createConcept(jane,"Mortgage Rates","I would love to get a mortgage through you, but" +
                "xyz competitor is offering lower rates",improvement, "bob");
        services.upVoteConcept(janeConcept);
        services.upVoteConcept(janeConcept);

        Concept janeConcept2 = services.createConcept(jane,"Insurance Product","I think it would be fantastic to have this" +
                "insurance product available for purchase.",product, "");
        janeConcept2.setStatusToApproved();
        services.downVoteConcept(janeConcept2);
        services.upVoteConcept(janeConcept2);
        services.upVoteConcept(janeConcept2);
        services.upVoteConcept(janeConcept2);
        services.upVoteConcept(janeConcept2);
        services.upVoteConcept(janeConcept2);
        services.upVoteConcept(janeConcept2);
        services.upVoteConcept(janeConcept2);
        services.upVoteConcept(janeConcept2);

        for(int i = 1 ; i < 3 ; i++){
            services.createConcept(jane, "Jane's Title " + i , "Jane's Description " + i
                    , product, "");
        }

        //Employee Data
        User empUser = services.createEmployeeUser("employee","pass123");
        User carl = services.createEmployeeUser("carl","carl");
        User sue = services.createEmployeeUser("sue","sue");
    }

}
