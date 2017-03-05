package org.geotools.data.ows;

import java.util.List;

/**
 * OWS Constraint
 * 
 * @param <T> Constraint type
 */
public interface Constraint<T> {
    public T getDefaultValue();

    public List<T> getAllowedValues();

    public boolean isNoValues();

    public boolean isAnyValue();

    public Class<T> getType();
}