package com.huawei.esdk.ev3.common;

@SuppressWarnings("javadoc")
public class ChallengeResultResponseCmd implements NetMessage
{
    
    /**
     * UID
     */
    private static final long serialVersionUID = -5074523970871139151L;
    
    /*
     * 挑战者所在位置
     */
    private String position;
    
    /**
     * 任务挑战结果
     * 1、机器人回归成功：Y
     * 2、机器人尚未回归成功或者回归失败：N
     */
    private String result;
    
    public String getPosition()
    {
        return position;
    }
    
    public void setPosition(String position)
    {
        this.position = position;
    }
    
    public String getResult()
    {
        return result;
    }
    
    public void setResult(String result)
    {
        this.result = result;
    }
}
