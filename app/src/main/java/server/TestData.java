/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package server;

import common.Concept;
import common.User;

/**
 * This class is used to initialize test data each time the app is started. The exact same data
 * set will be available each time the app is started
 */
public class TestData {

    private final Services services = new Services();
    private static final TestData singleton = new TestData();
    private static final String product = "Product";
    private static final String service = "Service";
    private static final String improvement = "Improvement";

    /**
     * Constructor -- loads test data (must be private)
     */
    private TestData() {
        initializeTestData();
    }

    /**
     * singleton pattern used to insure that test data is only generated once.
     * (constructor must be private)
     */
    public static TestData getInstance() {
        return singleton;
    }

    private void initializeTestData() {

        //Member Data
        User testUser = services.createMemberUser("user","password");
        User bob = services.createMemberUser("bob","bob");
        User jane = services.createMemberUser("jane","jane");
        User Casey = services.createMemberUser("casey","casey");
        User dan = services.createMemberUser("dan","dan");
        User KyleCarey = services.createMemberUser("kyle","kyle");
        User Kevin = services.createMemberUser("kevin","kevin");
        User Wing = services.createMemberUser("wing","wing");
        User user2 = services.createMemberUser("user2","user2");
        User user3 = services.createMemberUser("user3","user3");
        User user4 = services.createMemberUser("user4","user4");
        User user5 = services.createMemberUser("user5","user5");

        //Concept Data

        //Bob test data
        Concept bobConcept = services.createConcept(bob, "New Investment Product", "I think it would be awesome to have " +
                "this awesome investment product available to trade.", product, "");

        Concept bobConcept2 = services.createConcept(bob,"Later hours","It would be great if the bank was open later on " +
                "saturdays.", service, "jane");
        bobConcept2.setStatusToApproved();
        services.makeConceptSticky(bobConcept2);
        services.giveConceptStars(bobConcept2, 2 );
        services.saveConcept(bobConcept2);


        Concept bobConcept3 = services.createConcept(bob,"Personal Adviser","It would be great if I had access to a" +
                " personal financial adviser.", service, "");
        bobConcept3.setStatusToApproved();
        services.giveConceptStars(bobConcept3, 4);
        services.saveConcept(bobConcept3);

        //Jane test data
        Concept janeConcept = services.createConcept(jane,"Mortgage Rates","I would love to get a mortgage through you, but " +
                "xyz competitor is offering lower rates.", improvement, "bob");

        Concept janeConcept2 = services.createConcept(jane,"Insurance Product","I think it would be fantastic to have this " +
                "insurance product available for purchase.", product, "");
        janeConcept2.setStatusToApproved();
        services.giveConceptStars(janeConcept2, 7);
        services.saveConcept(janeConcept2);

        //Dan test data
        Concept danConcept = services.createConcept(dan,"Lower Rate Credit Card","I would love to have a credit card with" +
                        " a lower interest rate.", improvement, "bob");
        danConcept.setStatusToApproved();
        services.giveConceptStars(danConcept, 8);
        services.makeConceptSticky(danConcept);
        services.saveConcept(danConcept);

        Concept danConccept2 = services.createConcept(dan,"Shorter Lines","I think it would be awesome if there were no lines" +
                " at the bank when I'm trying to deposit checks.", service, "");
        danConccept2.setStatusToApproved();
        services.giveConceptStars(danConccept2, 1);
        services.saveConcept(danConccept2);


        //Employee Data
        User empUser = services.createEmployeeUser("employee","pass123");
        User carl = services.createEmployeeUser("carl","carl");
        User sue = services.createEmployeeUser("sue","sue");

    }

}
