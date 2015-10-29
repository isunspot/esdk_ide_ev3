/*******************************************************************************
 * Copyright(c) 2005-2012 Huawei Tech. Co., Ltd.
 * All rights reserved.
 * 
 * Author: p00196421
 * Date  : 2015年9月29日
 *******************************************************************************/
package com.huawei.ecosystem.interaction.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

import com.huawei.ecosystem.interaction.Activator;
import com.huawei.ecosystem.interaction.Interaction;
import com.huawei.ecosystem.interaction.preference.InteractionPreferenceInitializer;
import com.huawei.ecosystem.interaction.socket.SocketInstance;

/**
 * <h6>交互处理器</h6>
 */
public abstract class InteractionHandler extends AbstractHandler
{
    /* （此注释不是Javadoc注释）
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException
    {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        final String host = store.getString(InteractionPreferenceInitializer.SERVER_HOST);
        final int port = store.getInt(InteractionPreferenceInitializer.SERVER_PORT);
        final String location = store.getString(InteractionPreferenceInitializer.LOCATION);
        Job runnable = new Job("Challenge Job") //$NON-NLS-1$
        {
            
            @Override
            protected IStatus run(IProgressMonitor monitor)
            {
                monitor.beginTask("", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
                try
                {
                    System.out.println(Interaction.sendGet(doExecute(host,
                            port,
                            location)));
                }
                finally
                {
                    monitor.done();
                }
                return Status.OK_STATUS;
            }
        };
        SocketInstance.getInstance().execute(runnable);
        return null;
    }
    
    protected abstract String doExecute(String host, int port, String location);
}
