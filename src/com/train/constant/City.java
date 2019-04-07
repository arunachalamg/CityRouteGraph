package com.train.constant;

/**
 * Define all cities which are linked with rail routes
 *  
 * @author Arun G
 *
 */
public enum City {
	A("a"),B("b"),C("c"),D("d"),E("e");
	
	private final String value;

	City(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
   }
