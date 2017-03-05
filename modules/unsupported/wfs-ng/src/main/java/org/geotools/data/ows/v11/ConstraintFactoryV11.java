package org.geotools.data.ows.v11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.geotools.data.ows.Constraint;
import org.geotools.data.ows.ConstraintConfig;
import org.geotools.xml.EMFUtils;

import net.opengis.ows11.AllowedValuesType;
import net.opengis.ows11.DomainType;
import net.opengis.ows11.OperationsMetadataType;
import net.opengis.ows11.ValueType;

/**
 * ConstraintFactory implementation for OWS Constraint this is
 * using the net.opengis.ows11 package.
 * 
 */
public class ConstraintFactoryV11 {

    private static <T> T parseValue(String s, Class<T> type) {
        if (type.isAssignableFrom(String.class)) {
            @SuppressWarnings("unchecked")
            T result = (T)s;
            return result;
        }
        if (type.isAssignableFrom(Boolean.class)) {
            return type.cast(Boolean.parseBoolean(s));
        }
        return null;
    }
    
    public static Map<String, Constraint<?>> parseConstraints(
            OperationsMetadataType metadata, Map<String, ConstraintConfig> configMap) {
        EList<?> constraintEList = (EList<?>) EMFUtils.get(metadata, "constraint");
        Map<String, Constraint<?>> constraints = new HashMap<>();
        @SuppressWarnings("unchecked")
        Iterator<DomainType> it = (Iterator<DomainType>) constraintEList.iterator();
        while (it.hasNext()) {
            DomainType domainType = it.next();
            String name = domainType.getName();
            ConstraintConfig config = configMap.get(name);
            if (config != null) {
                Constraint<?> constraint = createConstraint(domainType, config.getType());
                constraints.put(name, constraint);
            }
        }
        return constraints;
    }

    public static <T> Constraint<T> createConstraint(EObject value, Class<T> type) {
        assert value instanceof DomainType;
        DomainType domainType = (DomainType) value;
        boolean noValues = domainType.getNoValues() != null;
        boolean anyValue = domainType.getAnyValue() != null;
        ValueType valueType = domainType.getDefaultValue();
        T defaultValue = null;
        if (valueType != null) {
            defaultValue = parseValue(valueType.getValue(), type);
        }
        List<T> allowedValues;
        AllowedValuesType allowedType = domainType.getAllowedValues();
        if (allowedType == null) {
            allowedValues = Collections.emptyList();
        }
        else {
            EList<?> allowed = allowedType.getValue();
            allowedValues = new ArrayList<>(allowed.size());
            for (Object o : allowed) {
                o.toString();
            }
        }
        return new ConstraintImpl<>(type, defaultValue, allowedValues, noValues, anyValue);
    }

    public static class ConstraintImpl<T> implements Constraint<T> {
        private final Class<T> type;
        private final T defaultValue;
        private final List<T> allowedValues;
        private final boolean noValues;
        private final boolean anyValue;
        
        public ConstraintImpl(Class<T> type, T defaultValue, List<T> allowedValues,
                boolean noValues, boolean anyValue) {
            super();
            this.defaultValue = defaultValue;
            this.allowedValues = allowedValues;
            this.noValues = noValues;
            this.anyValue = anyValue;
            this.type = type;
        }

        @Override
        public T getDefaultValue() {
            return defaultValue;
        }

        @Override
        public List<T> getAllowedValues() {
            return allowedValues;
        }

        @Override
        public boolean isNoValues() {
            return noValues;
        }

        @Override
        public boolean isAnyValue() {
            return anyValue;
        }

        @Override
        public Class<T> getType() {
            return type;
        }
    }
}