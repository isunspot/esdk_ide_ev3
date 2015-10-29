/*******************************************************************************
 * Copyright(c) 2005-2012 Huawei Tech. Co., Ltd.
 * All rights reserved.
 * 
 * Author: p00196421
 * Date  : 2015年10月14日
 *******************************************************************************/
package com.huawei.ecosystem.interaction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import com.huawei.esdk.ev3.common.ChallengeResultCmd;
import com.huawei.esdk.ev3.common.ChallengeResultResponseCmd;
import com.huawei.esdk.ev3.common.NetMessage;

/**
 * <h6>连接Socket服务</h6>
 */
public class SocketService
{
    /**
     * 返回实例
     * @return {@link SocketService}
     */
    public static SocketService getInstance()
    {
        return instance;
    }
    
    private static SocketService instance = new SocketService();
    
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    
    private SocketService()
    {
    }
    
    /**
     * 发送Socket信息
     * @param host 
     *            Socket服务主机
     * @param port 
     *            Socket服务端口
     * @param msg 
     *            Socket通信对象
     * @return {@link String}
     */
    public String execute(final String host, final int port,
            final NetMessage msg)
    {
        ChallengeRunnable task = new ChallengeRunnable(host, port, msg);
//        Future<String> future = executor.submit(task);
        executor.submit(task);
//        try
//        {
//            return future.get();
//        }
//        catch (Exception e)
//        {
//            return null;
//        }
        return "";
    }
    
    private static class ChallengeRunnable implements Runnable //Callable<String>
    {
        private final String host;
        
        private final int port;
        
        private final NetMessage msg;
        
        public ChallengeRunnable(final String host, final int port,
                final NetMessage msg)
        {
            this.host = host;
            this.port = port;
            this.msg = msg;
        }
        
        /* （此注释不是Javadoc注释）
         * @see java.util.concurrent.Callable#call()
         */
//        @Override
        public void run() //throws Exception
        {
            Socket socket = null;
            ObjectOutputStream oOut = null;
            ObjectInputStream oIn = null;
            try
            {
                socket = new Socket(host, port);
                // 请求成功或者失败
                OutputStream outStream = socket.getOutputStream();
                oOut = new ObjectOutputStream(outStream);
                oOut.writeObject(msg);
                oOut.flush();
                
                socket.setSoTimeout(1000 * 60 * 5);
                // 等待服务器回应
                oIn = new ObjectInputStream(socket.getInputStream());
                Object response = null;
                while (null != (response = oIn.readObject()))
                {
                    if (response instanceof ChallengeResultResponseCmd)
                    {
                        String position = ((ChallengeResultResponseCmd)response).getPosition();
                        if (msg instanceof ChallengeResultCmd)
                        {
                            if (position.equals(((ChallengeResultCmd)msg).getPosition()))
                            {
//                                String result = ((ChallengeResultResponseCmd)response).getResult();
//                                return result;
                            }
                        }
                    }
                }
            }
            catch (Throwable e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (null != oOut)
                {
                    try
                    {
                        oOut.close();
                    }
                    catch (IOException e)
                    {
                    }
                }
                if (null != oIn)
                {
                    try
                    {
                        oIn.close();
                    }
                    catch (IOException e)
                    {
                    }
                }
                if (null != socket)
                {
                    try
                    {
                        socket.close();
                    }
                    catch (IOException e)
                    {
                    }
                }
            }
//            return null;
        }
    }
}
