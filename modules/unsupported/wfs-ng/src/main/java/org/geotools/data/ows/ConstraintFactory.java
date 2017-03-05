package org.geotools.data.ows;

import org.eclipse.emf.ecore.EObject;

/**
 * Create an OWS constraint object.
 */
public interface ConstraintFactory {
    public <T> Constraint<T> create(EObject domainType, Class<T> type);
}