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
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id 
     * @return
     */
    public int getTotalProperty(int id) {
        return totalProperty;
    }

}