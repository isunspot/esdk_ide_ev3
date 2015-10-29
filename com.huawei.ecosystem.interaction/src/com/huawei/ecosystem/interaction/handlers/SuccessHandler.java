package com.huawei.ecosystem.interaction.handlers;

import com.huawei.ecosystem.interaction.Activator;
import com.huawei.ecosystem.interaction.preference.InteractionPreferenceInitializer;

/**
 * <h6>启动任务</h6>
 */
public class SuccessHandler extends InteractionHandler
{
    /**
     * 构造函数 
     */
    public SuccessHandler()
    {
    }
    
    /* （此注释不是Javadoc注释）
     * @see com.huawei.ecosystem.interaction.handlers.InteractionHandler#doExecute(java.lang.String, int, java.lang.String)
     */
    @Override
    protected String doExecute(String host, int port, String location)
    {
        String service = Activator.getDefault()
                .getPreferenceStore()
                .getString(InteractionPreferenceInitializer.SUCCESS_SERVICE);
        service += "?host=" + host + "&port=" + port + "&location=" + location; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return service;
    }
}
