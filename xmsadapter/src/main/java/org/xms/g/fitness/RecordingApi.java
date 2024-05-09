package org.xms.g.fitness;

public interface RecordingApi extends org.xms.g.utils.XInterface {
    
    public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.ListSubscriptionsResult> listSubscriptions(org.xms.g.common.api.ExtensionApiClient param0);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.ListSubscriptionsResult> listSubscriptions(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataType param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> subscribe(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataType param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> subscribe(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataSource param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unsubscribe(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataSource param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unsubscribe(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataType param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unsubscribe(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.Subscription param1);
    
    default java.lang.Object getZInstanceRecordingApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default com.google.android.gms.fitness.RecordingApi getGInstanceRecordingApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default java.lang.Object getHInstanceRecordingApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.RecordingApi dynamicCast(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static class XImpl extends org.xms.g.utils.XObject implements org.xms.g.fitness.RecordingApi {
        
        public XImpl(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.ListSubscriptionsResult> listSubscriptions(org.xms.g.common.api.ExtensionApiClient param0) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.ListSubscriptionsResult> listSubscriptions(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataType param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> subscribe(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataType param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> subscribe(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataSource param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unsubscribe(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataSource param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unsubscribe(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.DataType param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unsubscribe(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.Subscription param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
    }
}