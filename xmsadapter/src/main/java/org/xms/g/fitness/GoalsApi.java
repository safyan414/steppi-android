package org.xms.g.fitness;

public interface GoalsApi extends org.xms.g.utils.XInterface {
    
    public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.GoalsResult> readCurrentGoals(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.GoalsReadRequest param1);
    
    default java.lang.Object getZInstanceGoalsApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default com.google.android.gms.fitness.GoalsApi getGInstanceGoalsApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    default java.lang.Object getHInstanceGoalsApi() {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static org.xms.g.fitness.GoalsApi dynamicCast(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static boolean isInstance(java.lang.Object param0) {
        throw new java.lang.RuntimeException("Not Supported");
    }
    
    public static class XImpl extends org.xms.g.utils.XObject implements org.xms.g.fitness.GoalsApi {
        
        public XImpl(org.xms.g.utils.XBox param0) {
            super(param0);
        }
        
        public org.xms.g.common.api.PendingResult<org.xms.g.fitness.result.GoalsResult> readCurrentGoals(org.xms.g.common.api.ExtensionApiClient param0, org.xms.g.fitness.request.GoalsReadRequest param1) {
            throw new java.lang.RuntimeException("Not Supported");
        }
    }
}