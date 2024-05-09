package org.xms.g.fitness.service;

public interface SensorEventDispatcher extends org.xms.g.utils.XInterface {
    
    public void publish(org.xms.g.fitness.data.DataPoint param0) throws android.os.RemoteException;
    
    public void publish(java.util.List<org.xms.g.fitness.data.DataPoint> param0) throws android.os.RemoteException;
    
    default java.lang.Object getZInstanceSensorEventDispatcher() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default com.google.android.gms.fitness.service.SensorEventDispatcher getGInstanceSensorEventDispatcher() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default java.lang.Object getHInstanceSensorEventDispatcher() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.service.SensorEventDispatcher dynamicCast(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static class XImpl extends org.xms.g.utils.XObject implements org.xms.g.fitness.service.SensorEventDispatcher {
        
        public XImpl(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public void publish(org.xms.g.fitness.data.DataPoint param0) throws android.os.RemoteException {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public void publish(java.util.List<org.xms.g.fitness.data.DataPoint> param0) throws android.os.RemoteException {
            throw new java.lang.RuntimeException("Not Supported");
        }
    }
}