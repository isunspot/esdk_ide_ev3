/*******************************************************************************
 * Copyright(c) 2005-2012 Huawei Tech. Co., Ltd.
 * All rights reserved.
 * 
 * Author: p00196421
 * Date  : 2015年10月14日
 *******************************************************************************/
package com.huawei.ecosystem.interaction.socket;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import com.huawei.ecosystem.interaction.Activator;

/**
 * Socket管理
 */
public class SocketInstance
{
    /**
     * 返回实例
     * @return {@link SocketInstance}
     */
    public static SocketInstance getInstance()
    {
        return instance;
    }
    
    private static SocketInstance instance = new SocketInstance();
    
    private Job job;
    
    private SocketInstance()
    {
    }
    
    /**
     * 执行任务
     * @param runnable
     *            执行的任务
     */
    public void execute(Job runnable)
    {
        if (null != job)
        {
            MessageDialog.openWarning(Display.getDefault().getActiveShell(),
                    "Warning", //$NON-NLS-1$
                    "waiting last task to finished."); //$NON-NLS-1$
            Activator.getDefault()
                    .getLog()
                    .log(new Status(IStatus.WARNING, Activator.PLUGIN_ID,
                            "Result task is running.")); //$NON-NLS-1$
            return;
        }
        job = runnable;
        job.addJobChangeListener(new JobChangeAdapter()
        {
            /* （此注释不是Javadoc注释）
             * @see org.eclipse.core.runtime.jobs.JobChangeAdapter#done(org.eclipse.core.runtime.jobs.IJobChangeEvent)
             */
            @Override
            public void done(IJobChangeEvent event)
            {
                super.done(event);
                job = null;
            }
        });
        job.schedule();
    }
}
