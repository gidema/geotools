package org.geotools.data.wfs.internal;

import java.util.Map;

import org.geotools.data.ows.Constraint;
import org.geotools.data.ows.ConstraintConfig;

public interface WFSOperationsMetadata {
    
    /**
     * Get a constraint by name.
     * 
     * @param name
     * @return The constraint, or null if no constraint with this name exists
     */
    public Constraint<?> getConstraint(String name);
    
    /**
     * Get a constraint by name and type.
     * 
     * @param name
     * @param type
     * @return The constraint, or null if no constraint with this name exists
     */
    public <T> Constraint<T> getConstraint(String name, Class<T> type);
    
    /**
     * Get a constraint of type Boolean by name.
     * 
     * @param name
     * @return The constraint, or null if no constraint with this name exists
     */
     public Constraint<Boolean> getBooleanConstraint(String name);

     /**
      * Get a constraint of type String by name.
      * 
      * @param name
      * @return The constraint, or null if no constraint with this name exists
      */
      public Constraint<String> getStringConstraint(String name);
      
      public static void register(Map<String, ConstraintConfig> config,
              String name, Class<?> type) {
          config.put(name, new ConstraintConfig(name, type));
      }
}