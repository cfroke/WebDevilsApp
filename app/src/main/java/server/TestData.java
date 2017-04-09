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
        User dan = services.createMemberUser("dan","dan");

        //Concept Data

        //Bob test data
        Concept bobConcept = services.createConcept(bob, "New Investment Product", "I think it would be awesome to have " +
                "this awesome investment product available to trade.", product, "");
        services.giveConceptStars(bobConcept, 10);

        Concept bobConcept2 = services.createConcept(bob,"Later hours","It would be great if the bank was open later on " +
                "saturdays.",service, "jane");
        bobConcept2.setStatusToApproved();
        services.makeConceptSticky(bobConcept2);
        services.giveConceptStars(bobConcept2, 2 );
        services.saveConcept(bobConcept2);

        Concept bobConcept3 = services.createConcept(bob,"Personal Advisor","It would be great if I had access to a" +
                " personal financial advisor to make a .",service, "");
        bobConcept3.setStatusToApproved();
        services.giveConceptStars(bobConcept3, 4);
        services.saveConcept(bobConcept3);

        //Jane test data
        Concept janeConcept = services.createConcept(jane,"Mortgage Rates","I would love to get a mortgage through you, but" +
                "xyz competitor is offering lower rates",improvement, "bob");
        services.giveConceptStars(janeConcept, 3);

        Concept janeConcept2 = services.createConcept(jane,"Insurance Product","I think it would be fantastic to have this" +
                "insurance product available for purchase.",product, "");
        janeConcept2.setStatusToApproved();
        services.giveConceptStars(janeConcept2, 7);
        services.saveConcept(janeConcept2);

        //Dan test data
        Concept danConcept = services.createConcept(dan,"Lower Rate Credit Card","I would love to have a credit card with" +
                        " a lower interest rate.",improvement, "bob");
        danConcept.setStatusToApproved();
        services.giveConceptStars(danConcept, 8);
        services.makeConceptSticky(danConcept);
        services.saveConcept(danConcept);

        Concept danConccept2 = services.createConcept(dan,"Shorter Lines","I think it would be awesome if there were no lines" +
                " at the bank when I'm trying to deposit checks.",service, "");
        danConccept2.setStatusToApproved();
        services.giveConceptStars(danConccept2, 1);
        services.saveConcept(danConccept2);


        //Employee Data
        User empUser = services.createEmployeeUser("employee","pass123");
        User carl = services.createEmployeeUser("carl","carl");
        User sue = services.createEmployeeUser("sue","sue");
    }

}
