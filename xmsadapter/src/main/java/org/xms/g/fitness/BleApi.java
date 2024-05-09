package org.xms.g.fitness;

public interface BleApi extends org.xms.g.utils.XInterface {
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> claimBleDevice(org.xms.g.common.api.ExtensionApiClient param0, java.lang.String param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> claimBleDevice(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.BleDevice param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.BleDevicesResult> listClaimedBleDevices(org.xms.g.common.api.ExtensionApiClient param0);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> startBleScan(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.StartBleScanRequest param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> stopBleScan(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.BleScanCallback param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unclaimBleDevice(org.xms.g.common.api.ExtensionApiClient param0, java.lang.String param1);
    
    public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unclaimBleDevice(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.BleDevice param1);
    
    default java.lang.Object getZInstanceBleApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default com.google.android.gms.fitness.BleApi getGInstanceBleApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default java.lang.Object getHInstanceBleApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.BleApi dynamicCast(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static class XImpl extends org.xms.g.utils.XObject implements org.xms.g.fitness.BleApi {
        
        public XImpl(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> claimBleDevice(org.xms.g.common.api.ExtensionApiClient param0, java.lang.String param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> claimBleDevice(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.BleDevice param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.BleDevicesResult> listClaimedBleDevices(org.xms.g.common.api.ExtensionApiClient param0) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> startBleScan(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.StartBleScanRequest param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> stopBleScan(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.BleScanCallback param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unclaimBleDevice(org.xms.g.common.api.ExtensionApiClient param0, java.lang.String param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.common.api.Status> unclaimBleDevice(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.data.BleDevice param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
    }
}