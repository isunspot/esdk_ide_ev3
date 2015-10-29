/*******************************************************************************
 * Copyright(c) 2005-2012 Huawei Tech. Co., Ltd.
 * All rights reserved.
 * 
 * Author: p00196421
 * Date  : 2015年9月25日
 *******************************************************************************/
package com.huawei.ecosystem.interaction.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * TODO 添加类注释
 */
@SuppressWarnings("javadoc")
public class Messages extends NLS
{
    private static final String BUNDLE_NAME = "com.huawei.ecosystem.interaction.i18n.messages"; //$NON-NLS-1$
    
    public static String Error;

    public static String Interaction_IS_RUNNING;

    public static String Interaction_STARTUP_FIRST;

    public static String InteractionPreferencePage_Location;

    public static String InteractionPreferencePage_Server_IP;

    public static String InteractionPreferencePage_Server_Port;

    public static String InteractionPreferencePage_Service_Fail;

    public static String InteractionPreferencePage_Service_Succ;

    public static String InteractionPreferencePage_Service_Timeout;

    public static String InteractionPreferencePage_Services;

    public static String InteractionPreferencePage_Succ_Mark;

    public static String InteractionPreferencePage_Timeout;

    public static String WARNING;
    
    static
    {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
    
    private Messages()
    {
    }
}
