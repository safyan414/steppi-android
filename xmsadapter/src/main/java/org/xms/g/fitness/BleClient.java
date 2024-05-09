package org.xms.g.fitness;

public class BleClient extends org.xms.g.common.api.ExtensionApi {
    
    public BleClient(org.xms.g.utils.XBox param0) {
        super(param0);
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> claimBleDevice(java.lang.String param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.BleController) this.getHInstance()).saveDevice(param0)");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.BleController) this.getHInstance()).saveDevice(param0);
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.BleClient) this.getGInstance()).claimBleDevice(param0)");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.BleClient) this.getGInstance()).claimBleDevice(param0);
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> claimBleDevice(org.xms.g.fitness.data.BleDevice param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.BleController) this.getHInstance()).saveDevice(((com.huawei.hms.hihealth.data.BleDeviceInfo) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.BleController) this.getHInstance()).saveDevice(((com.huawei.hms.hihealth.data.BleDeviceInfo) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.BleClient) this.getGInstance()).claimBleDevice(((com.google.android.gms.fitness.data.BleDevice) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.BleClient) this.getGInstance()).claimBleDevice(((com.google.android.gms.fitness.data.BleDevice) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.util.List<org.xms.g.fitness.data.BleDevice>> listClaimedBleDevices() {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.BleController) this.getHInstance()).getSavedDevices()");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.BleController) this.getHInstance()).getSavedDevices();
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.BleClient) this.getGInstance()).listClaimedBleDevices()");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.BleClient) this.getGInstance()).listClaimedBleDevices();
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> startBleScan(java.util.List<org.xms.g.fitness.data.DataType> param0, int param1, org.xms.g.fitness.request.BleScanCallback param2) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.BleController) this.getHInstance()).beginScan(((java.util.List) org.xms.g.utils.Utils.mapList2GH(param0, true)), param1, ((com.huawei.hms.hihealth.options.BleScanCallback) ((param2) == null ? null : (param2.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.BleController) this.getHInstance()).beginScan(((java.util.List) org.xms.g.utils.Utils.mapList2GH(param0, true)), param1, ((com.huawei.hms.hihealth.options.BleScanCallback) ((param2) == null ? null : (param2.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.BleClient) this.getGInstance()).startBleScan(((java.util.List) org.xms.g.utils.Utils.mapList2GH(param0, false)), param1, ((com.google.android.gms.fitness.request.BleScanCallback) ((param2) == null ? null : (param2.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.BleClient) this.getGInstance()).startBleScan(((java.util.List) org.xms.g.utils.Utils.mapList2GH(param0, false)), param1, ((com.google.android.gms.fitness.request.BleScanCallback) ((param2) == null ? null : (param2.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Boolean> stopBleScan(org.xms.g.fitness.request.BleScanCallback param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.BleController) this.getHInstance()).endScan(((com.huawei.hms.hihealth.options.BleScanCallback) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.BleController) this.getHInstance()).endScan(((com.huawei.hms.hihealth.options.BleScanCallback) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.BleClient) this.getGInstance()).stopBleScan(((com.google.android.gms.fitness.request.BleScanCallback) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.BleClient) this.getGInstance()).stopBleScan(((com.google.android.gms.fitness.request.BleScanCallback) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> unclaimBleDevice(java.lang.String param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.BleController) this.getHInstance()).deleteDevice(param0)");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.BleController) this.getHInstance()).deleteDevice(param0);
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.BleClient) this.getGInstance()).unclaimBleDevice(param0)");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.BleClient) this.getGInstance()).unclaimBleDevice(param0);
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public org.xms.g.tasks.Task<java.lang.Void> unclaimBleDevice(org.xms.g.fitness.data.BleDevice param0) {
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.BleController) this.getHInstance()).deleteDevice(((com.huawei.hms.hihealth.data.BleDeviceInfo) ((param0) == null ? null : (param0.getHInstance()))))");
            com.huawei.hmf.tasks.Task hReturn = ((com.huawei.hms.hihealth.BleController) this.getHInstance()).deleteDevice(((com.huawei.hms.hihealth.data.BleDeviceInfo) ((param0) == null ? null : (param0.getHInstance()))));
            return ((hReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(null, hReturn))));
        } else {
            org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.BleClient) this.getGInstance()).unclaimBleDevice(((com.google.android.gms.fitness.data.BleDevice) ((param0) == null ? null : (param0.getGInstance()))))");
            com.google.android.gms.tasks.Task gReturn = ((com.google.android.gms.fitness.BleClient) this.getGInstance()).unclaimBleDevice(((com.google.android.gms.fitness.data.BleDevice) ((param0) == null ? null : (param0.getGInstance()))));
            return ((gReturn) == null ? null : (new org.xms.g.tasks.Task.XImpl(new org.xms.g.utils.XBox(gReturn, null))));
        }
    }
    
    public java.lang.Object getApiKey() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.BleClient dynamicCast(java.lang.Object param0) {
        if (param0 instanceof org.xms.g.fitness.BleClient) {
            return ((org.xms.g.fitness.BleClient) param0);
        }
        if (param0 instanceof org.xms.g.utils.XGettable) {
            com.google.android.gms.fitness.BleClient gReturn = ((com.google.android.gms.fitness.BleClient) ((org.xms.g.utils.XGettable) param0).getGInstance());
            com.huawei.hms.hihealth.BleController hReturn = ((com.huawei.hms.hihealth.BleController) ((org.xms.g.utils.XGettable) param0).getHInstance());
            return new org.xms.g.fitness.BleClient(new org.xms.g.utils.XBox(gReturn, hReturn));
        }
        return ((org.xms.g.fitness.BleClient) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.BleController;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.BleClient;
        }
    }
}