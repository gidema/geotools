package org.geotools.data.wfs.internal.v2_0;

import static org.geotools.data.wfs.internal.WFSOperationsMetadata.register;

import java.util.HashMap;
import java.util.Map;

import org.geotools.data.ows.Constraint;
import org.geotools.data.ows.ConstraintConfig;
import org.geotools.data.ows.v11.ConstraintFactoryV11;
import org.geotools.data.wfs.internal.WFSOperationsMetadata;

import net.opengis.ows11.OperationsMetadataType;

public class WFSOperationsMetadata20 implements WFSOperationsMetadata {
    private static Map<String, ConstraintConfig> configMap;
    
    private Map<String, Constraint<?>> constraints;
    
    static {
        configMap = new HashMap<>();
        register(configMap, "ImplementsTransactionalWFS", Boolean.class);
        register(configMap, "ImplementsLockingWFS", Boolean.class);
        register(configMap, "KVPEncoding", Boolean.class);
        register(configMap, "XMLEncoding", Boolean.class);
        register(configMap, "SOAPEncoding", Boolean.class);
        register(configMap, "ImplementsInheritance", Boolean.class);
        register(configMap, "ImplementsRemoteResolve", Boolean.class);
        register(configMap, "ImplementsResultPaging", Boolean.class);
        register(configMap, "ImplementsStandardJoins", Boolean.class);
        register(configMap, "ImplementsSpatialJoins", Boolean.class);
        register(configMap, "ImplementsTemporalJoins", Boolean.class);
        register(configMap, "ImplementsFeatureVersioning", Boolean.class);
        register(configMap, "ManageStoredQueries", Boolean.class);
        register(configMap, "PagingIsTransactionSafe", Boolean.class);
    }
    
    public WFSOperationsMetadata20(OperationsMetadataType metadata) {
        this.constraints = ConstraintFactoryV11.parseConstraints(metadata, configMap);
    }

    public Constraint<Boolean> implementsTransactionalWFS() {
        return getBooleanConstraint("ImplementsTransactionalWFS");
    }

    public Constraint<Boolean> implementsLockingWFS() {
        return getBooleanConstraint("ImplementsLockingWFS");
    }

    public Constraint<Boolean> kvpEncoding() {
        return getBooleanConstraint("KVPEncoding");
    }

    public Constraint<Boolean> xmlEncoding() {
        return getBooleanConstraint("XMLEncoding");
    }

    public Constraint<Boolean> soapEncoding() {
        return getBooleanConstraint("SOAPEncoding");
    }

    public Constraint<Boolean> implementsInheritance() {
        return getBooleanConstraint("ImplementsInheritance");
    }

    public Constraint<Boolean> implementsRemoteResolve() {
        return getBooleanConstraint("ImplementsRemoteResolve");
    }

    public Constraint<Boolean> implementsResultPaging() {
        return getBooleanConstraint("ImplementsResultPaging");
    }

    public Constraint<Boolean> implementsStandardJoins() {
        return getBooleanConstraint("ImplementsStandardJoins");
    }

    public Constraint<Boolean> implementsSpatialJoins() {
        return getBooleanConstraint("ImplementsSpatialJoins");
    }

    public Constraint<Boolean> implementsTemporalJoins() {
        return getBooleanConstraint("ImplementsTemporalJoins");
    }

    public Constraint<Boolean> implementsFeatureVersioning() {
        return getBooleanConstraint("ImplementsFeatureVersioning");
    }

    public Constraint<Boolean> manageStoredQueries() {
        return getBooleanConstraint("ManageStoredQueries");
    }

    public Constraint<Boolean> pagingIsTransactionSafe() {
        return getBooleanConstraint("PagingIsTransactionSafe");
    }

    @Override
    public Constraint<?> getConstraint(String name) {
        return constraints.get(name);
    }

    @Override
    public <T> Constraint<T> getConstraint(String name, Class<T> type) {
        Constraint<?> constraint = constraints.get(name);
        if (constraint == null) return null;
        if (constraint.getType().isAssignableFrom(type)) {
            @SuppressWarnings("unchecked")
            Constraint<T> result = (Constraint<T>) constraint;
            return result;
        }
        return null;
    }

    @Override
    public Constraint<Boolean> getBooleanConstraint(String name) {
        return getConstraint(name, Boolean.class);
    }

    @Override
    public Constraint<String> getStringConstraint(String name) {
        return getConstraint(name, String.class);
    }
}