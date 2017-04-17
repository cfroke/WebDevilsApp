/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp.Employee.ListContent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import common.Concept;
import server.Services;

/**
 * This is a content list manager for this Employee views. Depending on the purpose of the view,
 * different sets of concepts are need for display
 */
public class ConceptListContent {

    /**
     * Maps of Concepts, by Title
     */
    public static final Map<String, Concept> SUBMITTED_ITEMS_MAP = new HashMap<String, Concept>();
    public static final LinkedList<Concept> SUBMITTED_ITEMS = new LinkedList<Concept>();

    public static final Map<String, Concept> APPROVED_ITEMS_MAP = new HashMap<String, Concept>();
    public static final LinkedList<Concept> APPROVED_ITEMS = new LinkedList<Concept>();

    public static final Map<String, Concept> REJECTED_ITEMS_MAP = new HashMap<String, Concept>();
    public static final LinkedList<Concept> REJECTED_ITEMS = new LinkedList<Concept>();

    /**
     * This is for limiting the number of items in a view so the system won't get bogged down with
     * too many items in a given list. Another form of content management will be needed if large
     * data sets are used.
     */
    private static final int MAX_NUMBER_OF_CONCEPTS = 25;

    /**
     * Retrieves all concepts from the server via the services class
     */
    static Services services = new Services();
    static LinkedList<Concept> allConcepts = services.getAllConcepts();

    static {

        //loads submitted concepts
        int count = 0;
        for(Concept concept : allConcepts){
            if(count < MAX_NUMBER_OF_CONCEPTS){
                if(concept.isSubmitted()){
                    SUBMITTED_ITEMS.add(concept);
                    SUBMITTED_ITEMS_MAP.put(concept.getTitle(), concept);
                }
            }else{
                break;
            }
            count++;
        }

        //loads approved concepts
        count = 0;
        for(Concept concept : allConcepts){
            if(count < MAX_NUMBER_OF_CONCEPTS){
                if(concept.isApproved()){
                    APPROVED_ITEMS.add(concept);
                    APPROVED_ITEMS_MAP.put(concept.getTitle(), concept);
                }
            }else{
                break;
            }
            count++;
        }

        //loads rejected concepts
        count = 0;
        for(Concept concept : allConcepts){
            if(count < MAX_NUMBER_OF_CONCEPTS){
                if(concept.isRejected()){
                    REJECTED_ITEMS.add(concept);
                    REJECTED_ITEMS_MAP.put(concept.getTitle(), concept);
                }
            }else{
                break;
            }
            count++;
        }
    }
}
