package org.geotools.data.wfs.internal;

public interface OperationsMetadata {

    boolean implementsTransactionalWFS();

    boolean implementsLockingWFS();

    boolean kvpEncoding();

    boolean xmlEncoding();

    boolean soapEncoding();

    boolean implementsInheritance();

    boolean implementsRemoteResolve();

    boolean implementsResultPaging();

    boolean implementsStandardJoins();

    boolean implementsTemporalJoins();

    boolean implementsFeatureVersioning();

    boolean manageStoredQueries();

    boolean pagingIsTransactionSafe();

}
