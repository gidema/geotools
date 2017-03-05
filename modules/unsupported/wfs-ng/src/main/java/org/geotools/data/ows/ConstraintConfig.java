package org.geotools.data.ows;

/**
 * OWS Constraint meta data.
 * 
 * Specify the name and type of the constraint. 
 */
public class ConstraintConfig {
    private String name;
    private Class<?> type;

    public ConstraintConfig(String name, Class<?> type) {
        super();
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }
}