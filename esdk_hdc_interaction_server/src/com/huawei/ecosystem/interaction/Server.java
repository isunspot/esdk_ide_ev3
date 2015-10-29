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
import java.net.ServerSocket;
import java.net.Socket;

import com.huawei.esdk.ev3.common.ChallengeResultCmd;
import com.huawei.esdk.ev3.common.ChallengeResultResponseCmd;

/**
 * TODO 添加类注释
 */
public class Server
{
    
    /**
     * TODO 添加方法注释
     * @param args
     *            参数
     * @throws IOException
     *            异常
     */
    public static void main(String args[]) throws IOException
    {
        //为了简单起见，所有的异常信息都往外抛  
        int port = 8899;
        //定义一个ServerSocket监听在端口8899上  
        ServerSocket server = new ServerSocket(port);
        while (true)
        {
            //server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的  
            final Socket socket = server.accept();
            new Thread(new Runnable()
            {
                
                @Override
                public void run()
                {
                    try
                    {
                        //跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。  
                        ObjectInputStream reader = new ObjectInputStream(
                                socket.getInputStream());
                        String position = "-1"; //$NON-NLS-1$
                        try
                        {
                            Object object = reader.readObject();
                            if (object instanceof ChallengeResultCmd)
                            {
                                position = ((ChallengeResultCmd)object).getPosition();
                                System.out.println(object);
                                System.out.println(((ChallengeResultCmd)object).getResult());
                            }
                        }
                        catch (ClassNotFoundException e)
                        {
                            e.printStackTrace();
                        }
                        
                        if (socket.isConnected())
                        {
                            ObjectOutputStream writer = new ObjectOutputStream(
                                    socket.getOutputStream());
                            ChallengeResultResponseCmd obj = new ChallengeResultResponseCmd();
                            obj.setPosition(position);
                            obj.setResult("Y"); //$NON-NLS-1$
                            writer.writeObject(obj);
                            writer.flush();
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            socket.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            
        }
    }
    
}
