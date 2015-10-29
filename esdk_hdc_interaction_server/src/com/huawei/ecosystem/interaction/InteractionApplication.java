/*******************************************************************************
 * Copyright(c) 2005-2012 Huawei Tech. Co., Ltd.
 * All rights reserved.
 * 
 * Author: p00196421
 * Date  : 2015年9月29日
 *******************************************************************************/
package com.huawei.ecosystem.interaction;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * <h6>提供Interaciton交互服务的APP</h6>
 */
@ApplicationPath("/")
public class InteractionApplication extends Application
{
    @Override
    public java.util.Set<Class<?>> getClasses()
    {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(InteractionService.class);
        return classes;
    };
}
