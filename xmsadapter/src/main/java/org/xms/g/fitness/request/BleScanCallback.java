package org.xms.g.fitness.request;

public abstract class BleScanCallback extends org.xms.g.utils.XObject {
    private boolean wrapper = true;
    
    public BleScanCallback(org.xms.g.utils.XBox param0) {
        super(param0);
        wrapper = true;
    }
    
    public BleScanCallback() {
        super(((org.xms.g.utils.XBox) null));
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            this.setHInstance(new HImpl());
        } else {
            this.setGInstance(new GImpl());
        }
        wrapper = false;
    }
    
    public abstract void onDeviceFound(org.xms.g.fitness.data.BleDevice param0);
    
    public abstract void onScanStopped();
    
    public static org.xms.g.fitness.request.BleScanCallback dynamicCast(java.lang.Object param0) {
        return ((org.xms.g.fitness.request.BleScanCallback) param0);
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        if (!(param0 instanceof org.xms.g.utils.XGettable)) {
            return false;
        }
        if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
            return ((org.xms.g.utils.XGettable) param0).getHInstance() instanceof com.huawei.hms.hihealth.options.BleScanCallback;
        } else {
            return ((org.xms.g.utils.XGettable) param0).getGInstance() instanceof com.google.android.gms.fitness.request.BleScanCallback;
        }
    }
    
    private class GImpl extends com.google.android.gms.fitness.request.BleScanCallback {
        
        public void onDeviceFound(com.google.android.gms.fitness.data.BleDevice param0) {
            org.xms.g.fitness.request.BleScanCallback.this.onDeviceFound(((param0) == null ? null : (new org.xms.g.fitness.data.BleDevice(new org.xms.g.utils.XBox(param0, null)))));
        }
        
        public void onScanStopped() {
            org.xms.g.fitness.request.BleScanCallback.this.onScanStopped();
        }
        
        public GImpl() {
            super();
        }
    }
    
    private class HImpl extends com.huawei.hms.hihealth.options.BleScanCallback {
        
        public void onDeviceDiscover(com.huawei.hms.hihealth.data.BleDeviceInfo param0) {
            org.xms.g.fitness.request.BleScanCallback.this.onDeviceFound(((param0) == null ? null : (new org.xms.g.fitness.data.BleDevice(new org.xms.g.utils.XBox(null, param0)))));
        }
        
        public void onScanEnd() {
            org.xms.g.fitness.request.BleScanCallback.this.onScanStopped();
        }
        
        public HImpl() {
            super();
        }
    }
    
    public static class XImpl extends org.xms.g.fitness.request.BleScanCallback {
        
        public XImpl(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public void onDeviceFound(org.xms.g.fitness.data.BleDevice param0) {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.BleScanCallback) this.getHInstance()).onDeviceDiscover(((com.huawei.hms.hihealth.data.BleDeviceInfo) ((param0) == null ? null : (param0.getHInstance()))))");
                ((com.huawei.hms.hihealth.options.BleScanCallback) this.getHInstance()).onDeviceDiscover(((com.huawei.hms.hihealth.data.BleDeviceInfo) ((param0) == null ? null : (param0.getHInstance()))));
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.BleScanCallback) this.getGInstance()).onDeviceFound(((com.google.android.gms.fitness.data.BleDevice) ((param0) == null ? null : (param0.getGInstance()))))");
                ((com.google.android.gms.fitness.request.BleScanCallback) this.getGInstance()).onDeviceFound(((com.google.android.gms.fitness.data.BleDevice) ((param0) == null ? null : (param0.getGInstance()))));
            }
        }
        
        public void onScanStopped() {
            if (org.xms.g.utils.GlobalEnvSetting.isHms()) {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.huawei.hms.hihealth.options.BleScanCallback) this.getHInstance()).onScanEnd()");
                ((com.huawei.hms.hihealth.options.BleScanCallback) this.getHInstance()).onScanEnd();
            } else {
                org.xms.g.utils.XmsLog.d("XMSRouter", "((com.google.android.gms.fitness.request.BleScanCallback) this.getGInstance()).onScanStopped()");
                ((com.google.android.gms.fitness.request.BleScanCallback) this.getGInstance()).onScanStopped();
            }
        }
    }
}