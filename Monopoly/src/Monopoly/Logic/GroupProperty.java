package Monopoly.Logic;

import java.util.*;

/**
 * 
 */
public class GroupProperty {
	
    public int id;
    public String color;
    public int totalProperty;

	/**
	 * Constructor of GroupProperty
     * @param id 
     * @param color 
     * @param totalProperty
     */
    public GroupProperty(int id, String color, int totalProperty) {
        this.id = id;
        this.color = color;
        this.totalProperty = totalProperty;
    }

    /**
     * Get the id of the GroupProperty
     * @return id of GroupProperty
     */
    public int getId() {
        return id;
    }

    /**
     * Get the number of total properties that a group have.
     * @return total of properties
     */
    public int getTotalProperty() {
        return totalProperty;
    }

}