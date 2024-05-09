package org.xms.g.fitness;

public interface SensorsApi extends org.xms.g.utils.XInterface {
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> add(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.SensorRequest param1, org.xms.g.fitness.request.OnDataPointListener param2);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> add(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.SensorRequest param1, android.app.PendingIntent param2);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.DataSourcesResult> findDataSources(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.DataSourcesRequest param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> remove(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.OnDataPointListener param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> remove(org.xms.g.common.api.ExtensionApiClient param0, android.app.PendingIntent param1);
    
    default java.lang.Object getZInstanceSensorsApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default com.google.android.gms.fitness.SensorsApi getGInstanceSensorsApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default java.lang.Object getHInstanceSensorsApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.SensorsApi dynamicCast(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static class XImpl extends org.xms.g.utils.XObject implements org.xms.g.fitness.SensorsApi {
        
        public XImpl(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> add(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.SensorRequest param1, org.xms.g.fitness.request.OnDataPointListener param2) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> add(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.SensorRequest param1, android.app.PendingIntent param2) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.DataSourcesResult> findDataSources(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.DataSourcesRequest param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> remove(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.OnDataPointListener param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> remove(org.xms.g.common.api.ExtensionApiClient param0, android.app.PendingIntent param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
    }
}