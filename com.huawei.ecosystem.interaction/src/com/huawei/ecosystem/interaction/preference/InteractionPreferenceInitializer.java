package com.huawei.ecosystem.interaction.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.huawei.ecosystem.interaction.Activator;

/**
 * <h6>首选项初始化</h6>
 */
public class InteractionPreferenceInitializer extends
        AbstractPreferenceInitializer
{
    //    /**
    //     * 超时时间
    //     */
    //    public static final String TIME_OUT = "interaction.timeout"; //$NON-NLS-1$
    //    
    //    /**
    //     * 超时时间
    //     */
    //    public static final String SUCC_MARK = "interaction.succ"; //$NON-NLS-1$
    //    
    //    /**
    //     * 超时时间
    //     */
    //    public static final String CONSOLE_TYPES = "interaction.consoles"; //$NON-NLS-1$
    //    
    /**
     * 成功事件
     */
    public static final String SUCCESS_SERVICE = "interaction.services.success"; //$NON-NLS-1$
    
    /**
     * 失败事件
     */
    public static final String FAIL_SERVICE = "interaction.services.fail"; //$NON-NLS-1$
    
    //    
    //    /**
    //     * 超时事件
    //     */
    //    public static final String TIMEOUT_SERVICE = "interaction.services.timeout"; //$NON-NLS-1$
    
    /**
     * 位置
     */
    public static final String SERVER_HOST = "server_host"; //$NON-NLS-1$
    
    /**
     * 位置
     */
    public static final String SERVER_PORT = "server_port"; //$NON-NLS-1$
    
    /**
     * 位置
     */
    public static final String LOCATION = "location"; //$NON-NLS-1$
    
    @Override
    public void initializeDefaultPreferences()
    {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        //        store.setDefault(TIME_OUT, 10);
        //        store.setDefault(SUCC_MARK, "Demo has been done successfully"); //$NON-NLS-1$
        //        store.setDefault(CONSOLE_TYPES,
        //                "org.eclipse.debug.ui.ProcessConsoleType"); //$NON-NLS-1$
        store.setDefault(SUCCESS_SERVICE,
                "http://localhost:8081/interaction/services/success"); //$NON-NLS-1$
        store.setDefault(FAIL_SERVICE,
                "http://localhost:8081/interaction/services/fail"); //$NON-NLS-1$
        //        store.setDefault(TIMEOUT_SERVICE,
        //                "http://localhost:8081/interaction/services/timeout"); //$NON-NLS-1$
        store.setDefault(SERVER_HOST, "172.0.0.1"); //$NON-NLS-1$
        store.setDefault(SERVER_PORT, "8080"); //$NON-NLS-1$
        store.setDefault(LOCATION, 1);
    }
    
}
