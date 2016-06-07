package Monopoly.Logic;

import java.util.*;

/**
 * GroupProperty Class
 */
public class GroupProperty {
	
	private int id;
	private String color;
	private int totalProperty;

	/**
	 * Constructor of GroupProperty
     * @param id of gGroup
     * @param color of group
     * @param totalProperty per group
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