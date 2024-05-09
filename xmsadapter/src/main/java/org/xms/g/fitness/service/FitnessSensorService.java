package org.xms.g.fitness.service;

public abstract class FitnessSensorService extends android.app.Service implements org.xms.g.utils.XGettable {
    public java.lang.Object gInstance;
    public java.lang.Object hInstance;
    
    public FitnessSensorService(org.xms.g.utils.XBox param0) {
        if (param0 == null) {
            return;
        }
        this.setGInstance(param0.getGInstance());
        this.setHInstance(param0.getHInstance());
    }
    
    public FitnessSensorService() {
    }
    
    public static java.lang.String getSERVICE_INTERFACE() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public android.os.IBinder onBind(android.content.Intent param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public void onCreate() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public abstract java.util.List<org.xms.g.fitness.data.DataSource> onFindDataSources(java.util.List<org.xms.g.fitness.data.DataType> param0);
    
    public abstract boolean onRegister(org.xms.g.fitness.service.FitnessSensorServiceRequest param0);
    
    public abstract boolean onUnregister(org.xms.g.fitness.data.DataSource param0);
    
    public void setGInstance(java.lang.Object param0) {
        this.gInstance = param0;
    }
    
    public void setHInstance(java.lang.Object param0) {
        this.hInstance = param0;
    }
    
    public java.lang.Object getGInstance() {
        return this.gInstance;
    }
    
    public java.lang.Object getHInstance() {
        return this.hInstance;
    }
    
    public static org.xms.g.fitness.service.FitnessSensorService dynamicCast(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static class XImpl extends org.xms.g.fitness.service.FitnessSensorService {
        
        public XImpl(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public java.util.List<org.xms.g.fitness.data.DataSource> onFindDataSources(java.util.List<org.xms.g.fitness.data.DataType> param0) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public boolean onRegister(org.xms.g.fitness.service.FitnessSensorServiceRequest param0) {
            throw new java.lang.RuntimeException("Not Supported");
        }
        
        public boolean onUnregister(org.xms.g.fitness.data.DataSource param0) {
            throw new java.lang.RuntimeException("Not Supported");
        }
    }
}