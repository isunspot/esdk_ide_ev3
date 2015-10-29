/*******************************************************************************
 * Copyright(c) 2005-2012 Huawei Tech. Co., Ltd.
 * All rights reserved.
 * 
 * Author: p00196421
 * Date  : 2015年9月25日
 *******************************************************************************/
package com.huawei.ecosystem.interaction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * <h6>监控控制台输出，并调用服务器服务</h6>
 */
public class Interaction
{
    //    private static final Interaction instance = new Interaction();
    //    
    //    private static final IPreferenceStore STORE = Activator.getDefault()
    //            .getPreferenceStore();
    //    
    //    private final Timer timer = new Timer();
    //    
    //    private boolean running = false;
    //    
    //    private final ListenerList listener = new ListenerList();
    //    
    //    private TimerTask task;
    //    
    //    private Interaction()
    //    {
    //    }
    //    
    //    /* （此注释不是Javadoc注释）
    //     * @see java.lang.Runnable#run()
    //     */
    //    @Override
    //    public void run()
    //    {
    //        setRunning(true);
    //        int timeout = STORE.getInt(InteractionPreferenceInitializer.TIME_OUT);
    //        task = new TimerTask()
    //        {
    //            
    //            @Override
    //            public void run()
    //            {
    //                // 任务启动时，说明已经超时
    //                startChecking(true);
    //            }
    //        };
    //        timer.schedule(task, timeout * 60 * 1000);
    //    }
    //    
    //    private synchronized void setRunning(boolean running)
    //    {
    //        boolean old = this.running;
    //        this.running = running;
    //        firePropertyChanged("running", old, running); //$NON-NLS-1$
    //    }
    //    
    //    private void firePropertyChanged(String property, Object oldValue,
    //            Object newValue)
    //    {
    //        if (listener.isEmpty())
    //        {
    //            return;
    //        }
    //        Object[] listeners = listener.getListeners();
    //        PropertyChangeEvent event = new PropertyChangeEvent(this, property,
    //                oldValue, newValue);
    //        for (Object l : listeners)
    //        {
    //            if (l instanceof IPropertyChangeListener)
    //            {
    //                ((IPropertyChangeListener)l).propertyChange(event);
    //            }
    //        }
    //    }
    //    
    //    private synchronized void startChecking(final boolean timeout)
    //    {
    //        if (!isRunning())
    //        {
    //            return;
    //        }
    //        new Thread(new Runnable()
    //        {
    //            
    //            @Override
    //            public void run()
    //            {
    //                checkConsole(timeout);
    //            }
    //        }).start();
    //    }
    //    
    //    private synchronized void checkConsole(final boolean timeout)
    //    {
    //        if (!isRunning())
    //        {
    //            return;
    //        }
    //        try
    //        {
    //            IConsoleManager manager = ConsolePlugin.getDefault()
    //                    .getConsoleManager();
    //            IConsole[] consoles = manager.getConsoles();
    //            if (null == consoles || 0 == consoles.length)
    //            {
    //                exit(timeout);
    //                return;
    //            }
    //            List<String> typeList = parseConsoleTypes();
    //            for (IConsole console : consoles)
    //            {
    //                if (typeList.contains(console.getType())
    //                        && console instanceof TextConsole)
    //                {
    //                    IDocument document = ((TextConsole)console).getDocument();
    //                    int lines = document.getNumberOfLines();
    //                    if (0 == lines)
    //                    {
    //                        exit(timeout);
    //                        return;
    //                    }
    //                    
    //                    String mark = getSuccessMark((TextConsole)console);
    //                    
    //                    int index = 0;
    //                    while (index < lines)
    //                    {
    //                        try
    //                        {
    //                            IRegion info = document.getLineInformation(index);
    //                            String line = document.get(info.getOffset(),
    //                                    info.getLength());
    //                            
    //                            if (line.endsWith(mark)
    //                                    || line.endsWith(mark + ".")) //$NON-NLS-1$
    //                            {
    //                                success();
    //                                return;
    //                            }
    //                        }
    //                        catch (BadLocationException e)
    //                        {
    //                            
    //                        }
    //                        index++;
    //                    }
    //                }
    //            }
    //            exit(timeout);
    //        }
    //        finally
    //        {
    //            if (null != task)
    //            {
    //                task.cancel();
    //                timer.purge();
    //            }
    //            setRunning(false);
    //        }
    //    }
    //    
    //    /**
    //     * @param console  
    //     * @return {@link String}
    //     */
    //    private String getSuccessMark(TextConsole console)
    //    {
    //        String mark = STORE.getString(InteractionPreferenceInitializer.SUCC_MARK);
    //        return mark;
    //    }
    //    
    //    private void exit(boolean timeout)
    //    {
    //        if (timeout)
    //        {
    //            timeout();
    //            return;
    //        }
    //        fail();
    //    }
    //    
    //    /**
    //     * 添加属性变更监听器
    //     * @param listener
    //     *            监听器
    //     */
    //    public void addPropertyChangeListener(IPropertyChangeListener listener)
    //    {
    //        this.listener.add(listener);
    //    }
    //    
    //    /**
    //     * 删除属性变更监听器
    //     * @param listener
    //     *            监听器
    //     */
    //    public void removePropertyChangeListener(IPropertyChangeListener listener)
    //    {
    //        this.listener.remove(listener);
    //    }
    //    
    //    private void success()
    //    {
    //        String result = sendGet(STORE.getString(InteractionPreferenceInitializer.SUCCESS_SERVICE));
    //        Activator.getDefault()
    //                .getLog()
    //                .log(new Status(IStatus.INFO, Activator.PLUGIN_ID,
    //                        "call success service result:" + result)); //$NON-NLS-1$
    //    }
    //    
    //    private void fail()
    //    {
    //        String result = sendGet(STORE.getString(InteractionPreferenceInitializer.FAIL_SERVICE));
    //        Activator.getDefault()
    //                .getLog()
    //                .log(new Status(IStatus.INFO, Activator.PLUGIN_ID,
    //                        "call fail service result:" + result)); //$NON-NLS-1$
    //    }
    //    
    //    private void timeout()
    //    {
    //        String result = sendGet(STORE.getString(InteractionPreferenceInitializer.TIMEOUT_SERVICE));
    //        Activator.getDefault()
    //                .getLog()
    //                .log(new Status(IStatus.INFO, Activator.PLUGIN_ID,
    //                        "call timeout service result:" + result)); //$NON-NLS-1$
    //    }
    //    
    /**
     * 发送Get请求
     * @param url
     *            服务地址
     * @return {@link String}
     */
    public static String sendGet(String url)
    {
        String result = ""; //$NON-NLS-1$
        BufferedReader in = null;
        try
        {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*"); //$NON-NLS-1$ //$NON-NLS-2$
            connection.setRequestProperty("connection", "Keep-Alive"); //$NON-NLS-1$ //$NON-NLS-2$
            connection.setRequestProperty("user-agent", //$NON-NLS-1$
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"); //$NON-NLS-1$
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        }
        catch (Exception e)
        {
            Activator.getDefault()
                    .getLog()
                    .log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                            e.getLocalizedMessage(), e));
        }
        // 使用finally块来关闭输入流
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception e)
            {
                Activator.getDefault()
                        .getLog()
                        .log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                                e.getLocalizedMessage(), e));
            }
        }
        return result;
    }
    
    //    /**
    //     * 返回实例
    //     * @return Interaction
    //     */
    //    public static Interaction getInstance()
    //    {
    //        return instance;
    //    }
    //    
    //    /**
    //     * 启动交互机制
    //     */
    //    public static void startup()
    //    {
    //        if (isRunning())
    //        {
    //            MessageDialog.openInformation(Display.getDefault().getActiveShell(),
    //                    Messages.WARNING,
    //                    Messages.Interaction_IS_RUNNING);
    //            return;
    //        }
    //        try
    //        {
    //            instance.run();
    //        }
    //        catch (IllegalThreadStateException e)
    //        {
    //            MessageDialog.openError(Display.getDefault().getActiveShell(),
    //                    Messages.Error,
    //                    e.getLocalizedMessage());
    //        }
    //    }
    //    
    //    /**
    //     * 提交任务
    //     */
    //    public static void commit()
    //    {
    //        if (!isRunning())
    //        {
    //            MessageDialog.openInformation(Display.getDefault().getActiveShell(),
    //                    Messages.WARNING,
    //                    Messages.Interaction_STARTUP_FIRST);
    //            return;
    //        }
    //        instance.startChecking(false);
    //    }
    //    
    //    /**
    //     * 是否在运行
    //     * @return boolean
    //     */
    //    public static boolean isRunning()
    //    {
    //        return instance.running;
    //    }
    //    
    //    /**
    //     * 解析Console Types
    //     * @return List<String>
    //     */
    //    public static List<String> parseConsoleTypes()
    //    {
    //        String types = STORE.getString(InteractionPreferenceInitializer.CONSOLE_TYPES);
    //        String[] typeArray = types.split(","); //$NON-NLS-1$
    //        List<String> typeList = Arrays.asList(typeArray);
    //        return typeList;
    //    }
}
