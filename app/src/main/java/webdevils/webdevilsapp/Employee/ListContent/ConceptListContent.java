package webdevils.webdevilsapp.Employee.ListContent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import common.Concept;
import server.Services;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ConceptListContent {

    /**
     * A map of Concepts, by Title
     */
    public static final Map<String, Concept> ITEMS_MAP = new HashMap<String, Concept>();
    public static final LinkedList<Concept> ITEMS = new LinkedList<Concept>();
    private static final int MAX_NUMBER_OF_CONCEPTS = 25;
    static Services services = new Services();
    static LinkedList<Concept> unReviewedConcepts = services.getUnreviewedConcepts();

    static {
        // Add some sample items.
        int count = 0;
        for(Concept concept : unReviewedConcepts){
            if(count < MAX_NUMBER_OF_CONCEPTS){
                addItem(concept);
            }else{
                break;
            }
            count++;
        }
        for (int i = 1; i <= MAX_NUMBER_OF_CONCEPTS; i++) {

            //grab items from server

//            addItem(concept);
        }
    }

    private static void addItem(Concept concept) {
        ITEMS.add(concept);
        ITEMS_MAP.put(concept.getTitle(), concept);
    }



}
