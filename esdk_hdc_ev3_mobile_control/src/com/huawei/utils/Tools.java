package com.huawei.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Pattern;

import android.util.Log;


public class Tools
{

    /**
     * 字符串转换为数字。 <br>
     * 如果抛出NumberFormatException异常，则返回-1.
     * 
     */
    public static int parseInt(String source)
    {
        try
        {
            int target = Integer.parseInt(source);
            return target;
        }
        catch (NumberFormatException e)
        {
            return -1;
        }
    }

    public static boolean isEmpty(String string)
    {
        if (null == string || "".equals(string))
        {
            return true;
        }

        return false;
    }

    public static boolean checkIP(String ip)
    {
        if (null == ip)
        {
            return false;
        }
        String regex = "^(2[0-4]\\d|25[0-5]|1\\d{2}|[1-9]\\d|[1-9])\\."
                + "(2[0-4]\\d|25[0-5]|1\\d{2}|[1-9]\\d|\\d)\\."
                + "(2[0-4]\\d|25[0-5]|1\\d{2}|[1-9]\\d|\\d)\\."
                + "(2[0-4]\\d|25[0-5]|1\\d{2}|[1-9]\\d|\\d)$";

        return Pattern.matches(regex, ip);
    }

    public static boolean checkPort(int port)
    {
        if (1 < port && port < 65535)
        {
            return true;
        }

        return false;
    }


    public static String getLocalIpAddress()
    {
        String ip = "";
        try
        {
            Enumeration<NetworkInterface> networkInfo = NetworkInterface
                    .getNetworkInterfaces();
            NetworkInterface intf = null;
            Enumeration<InetAddress> intfAddress = null;
            InetAddress inetAddress = null;
            if (networkInfo == null)
            {
                Log.e("getLocalIpAddress",
                        "get LocalIp address Error , return null value ");
                return "";
            }
            for (Enumeration<NetworkInterface> en = networkInfo; en
                    .hasMoreElements();)
            {
                intf = en.nextElement();
                intfAddress = intf.getInetAddresses();
                for (Enumeration<InetAddress> enumIpAddr = intfAddress; enumIpAddr
                        .hasMoreElements();)
                {
                    inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress())
                    {
                        ip = inetAddress.getHostAddress();
                        if (isIPV4Addr(ip))
                        {
                            Log.i("getLocalIpAddress", "ip is " + ip);
                            return ip;
                        }
                    }
                }
            }
        }
        catch (SocketException e)
        {
            Log.e("getLocalIpAddress", "SocketException | " + e.toString());
        }
        return ip;
    }

    /**
     * 判断是否是ipv4地址
     * @param ipAddr
     * @return
     */
    public static boolean isIPV4Addr(String ipAddr)
    {
        Pattern p = Pattern
                .compile("^((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}"
                        + "(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])$");
        return p.matcher(ipAddr).matches();

    }

}
