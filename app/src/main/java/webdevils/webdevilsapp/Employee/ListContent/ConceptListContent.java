package webdevils.webdevilsapp.Employee.ListContent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import common.Concept;
import server.Services;

/**
 * Kevin 03/04/2017
 */
public class ConceptListContent {

    /**
     * A map of Concepts, by Title
     */
    public static final Map<String, Concept> SUBMITTED_ITEMS_MAP = new HashMap<String, Concept>();
    public static final LinkedList<Concept> SUBMITTED_ITEMS = new LinkedList<Concept>();

    public static final Map<String, Concept> APPROVED_ITEMS_MAP = new HashMap<String, Concept>();
    public static final LinkedList<Concept> APPROVED_ITEMS = new LinkedList<Concept>();

    public static final Map<String, Concept> REJECTED_ITEMS_MAP = new HashMap<String, Concept>();
    public static final LinkedList<Concept> REJECTED_ITEMS = new LinkedList<Concept>();

    private static final int MAX_NUMBER_OF_CONCEPTS = 25;

    static Services services = new Services();
    static LinkedList<Concept> allConcepts = services.getAllConcepts();

    static {
        //load submitted
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

        //load approved
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

        //load rejected
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
