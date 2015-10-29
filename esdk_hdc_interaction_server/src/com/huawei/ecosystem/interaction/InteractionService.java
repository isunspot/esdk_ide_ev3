/*******************************************************************************
 * Copyright(c) 2005-2012 Huawei Tech. Co., Ltd.
 * All rights reserved.
 * 
 * Author: p00196421
 * Date  : 2015年9月29日
 *******************************************************************************/
package com.huawei.ecosystem.interaction;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.huawei.esdk.ev3.common.ChallengeResultCmd;

/**
 * <h6>交互服务</h6>
 */
@Path(value = "/services")
public class InteractionService
{
	private String serverIP = "192.168.1.2";
	
	private int serverPort = 9058;
    /**
     * 成功事件
     * @param host 
     *            Socket服务主机
     * @param port 
     *            Socket服务端口
     * @param location 
     *            成功的机位
     * @return {@link String}
     */
    @GET
    @Path("/success")
    @Produces(MediaType.TEXT_PLAIN)
    public String success(@QueryParam("host") String host,
            @QueryParam("port") int port,
            @QueryParam("location") String location)
    {
        ChallengeResultCmd msg = new ChallengeResultCmd();
        msg.setPosition(location);
        msg.setResult("Y"); //$NON-NLS-1$
        msg.setHandPhoneCmd("Y");
        return SocketService.getInstance().execute(serverIP, serverPort, msg);
    }
    
    /**
     * 失败事件
     * @param host 
     *            Socket服务主机
     * @param port 
     *            Socket服务端口
     * @param location 
     *            失败的机位
     * @return {@link String}
     */
    @GET
    @Path(value = "/fail")
    @Produces(MediaType.TEXT_PLAIN)
    public String fail(@QueryParam("host") String host,
            @QueryParam("port") int port,
            @QueryParam("location") String location)
    {
        ChallengeResultCmd msg = new ChallengeResultCmd();
        msg.setPosition(location);
        msg.setResult("N"); //$NON-NLS-1$
        msg.setHandPhoneCmd("Y");
        return SocketService.getInstance().execute(serverIP, serverPort, msg);
    }
}
