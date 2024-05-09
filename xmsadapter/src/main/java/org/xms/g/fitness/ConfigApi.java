package org.xms.g.fitness;

public interface ConfigApi extends org.xms.g.utils.XInterface {
    
    public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.DataTypeResult> createCustomDataType(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.DataTypeCreateRequest param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> disableFit(org.xms.g.common.api.ExtensionApiClient param0);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.DataTypeResult> readDataType(org.xms.g.common.api.ExtensionApiClient param0, java.lang.String param1);
    
    default java.lang.Object getZInstanceConfigApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default com.google.android.gms.fitness.ConfigApi getGInstanceConfigApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default java.lang.Object getHInstanceConfigApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.ConfigApi dynamicCast(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static class XImpl extends org.xms.g.utils.XObject implements org.xms.g.fitness.ConfigApi {
        
        public XImpl(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.DataTypeResult> createCustomDataType(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.DataTypeCreateRequest param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> disableFit(org.xms.g.common.api.ExtensionApiClient param0) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.DataTypeResult> readDataType(org.xms.g.common.api.ExtensionApiClient param0, java.lang.String param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
    }
}