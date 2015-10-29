package com.huawei.esdk.ev3;

import java.util.HashMap;
import java.util.Map;

import com.huawei.esdk.ev3.bean.RGBBean;

public class RobotConstantsConf {
	//======================================================================
	//以下配置为轨道颜色基本值
	//======================================================================
	// 轨道颜色值
	public static int COLOR_VALUE_TRACK = 10;//强度
	public static int COLOR_VALUE_TRACK_ID = 7;//Color ID
	public static RGBBean COLOR_VALUE_TRACK_RGB = new RGBBean(0.341f, 0.174f, 0.063f);//RGB

	// 轨道边缘颜色值,(轨道左边边的颜色，顺时针)
	public static int COLOR_VALUE_TRACK_LEFT_SIDE = 100;
	public static int COLOR_VALUE_TRACK_LEFT_SIDE_ID = 6;
	public static RGBBean COLOR_VALUE_TRACK_LEFT_SIDE_RGB = new RGBBean(0.383f, 0.361f, 0.247f);
	
	// 轨道边缘颜色值,(轨道右边的颜色,顺时针)
	public static int COLOR_VALUE_TRACK_RIGHT_SIDE = 3;
	public static int COLOR_VALUE_TRACK_RIGHT_SIDE_ID = 3;
	public static RGBBean COLOR_VALUE_TRACK_RIGHT_SIDE_RGB = new RGBBean(0.378f, 0.244f, 0.072f);
		
	//======================================================================
	//以下配置为巡线需要
	//======================================================================
	/*
	 * 1 - 根据强度
	 * 2 - 根据ColorID
	 * 3 - 根据RGB
	 * 以上 3种power gain的值为固定值，是三段式的 (在轨道中间走)
	 * 
	 * 4位PID方式，是沿着交界线走
	 * 4 - 根据强度获取不断变化的turn值
	 */
	public static int POWER_GAIN_METHOD = 4;
	
	/* 1有效 */
	public static int COLOR_DIFF = 10; //根据强度
	//* 3有效 */
	public static float RGB_DIFF_RANGE = 0.09f;
	//1,2,3有效
	public static int POWER_GAIN = 240;
	
	//TP决定了机器人沿着线向前移动的速度有多快， 1~4有效
	public static int PID_MODE_TP = 360;
	//KP决定了当机器人渐渐离开线的边缘时，控制器让机器人返回线的边缘的速度有多快， 1~4有效
	public static float PID_MODE_KP = 4.5f;
	//KI:同P的部分一样，我们对integral乘以一个比例常数，这是另一个K。因为这个比例常数与积分部分有关，所以我们称其为Ki
	public static float PID_MODE_KI = 0;
	//KD:
	public static float PID_MODE_KD = 0;
	
	public static int MAX_TURN = 120;
	
	/* 4有效 */
	//恒量,根据传感器读取
	public static int PID_MODE_OFFSET = 55;//补偿量,（黑色和白色光电传感器读值的平均数）黑色7，白色91
	
	//======================================================================
	//以下配置为寻找颜色点所需
	//======================================================================
	public static float RGB_DIFF_RANGE_COLOR = 0.07f;
	
	public static float RGB_DIFF_RANGE_COLOR_PER = 0.3f;//30%

	public static Map<String, String> POS_COLOR_MAPPING = new HashMap<String, String>();

	public static Map<String, String> POS_COLOR_MAPPING_RGB = new HashMap<String, String>();

	public static int[] SECONDS_COLORS = new int[] { 0, 1 };

	static {
		//红色： 393,66,49  0.430,0.052,0.045 0.393,0.068,0.048
		//黄色：370,288,72 0.366,0.300,0.070 0.330,0.380,0.093
		//蓝色：50,60,122 0.050,0.060,0.122 0.039,0.081,0.158
		//绿色：150,252,78		0.158,0.252,0.078 0.132,0.330,0.10
		POS_COLOR_MAPPING_RGB.put("1", "0.393,0.068,0.048|0.039,0.081,0.158");//红蓝
		POS_COLOR_MAPPING_RGB.put("2", "0.393,0.068,0.048|0.132,0.330,0.10");//红绿
		POS_COLOR_MAPPING_RGB.put("3", "0.393,0.068,0.048|0.330,0.380,0.093");//红黄
		POS_COLOR_MAPPING_RGB.put("4", "0.039,0.081,0.158|0.132,0.330,0.10");//蓝绿
		POS_COLOR_MAPPING_RGB.put("5", "0.039,0.081,0.158|0.330,0.380,0.093");//蓝黄
		POS_COLOR_MAPPING_RGB.put("6", "0.132,0.330,0.10|0.330,0.380,0.093");//绿黄
		POS_COLOR_MAPPING_RGB.put("7", "0.039,0.081,0.158|0.393,0.068,0.048");//蓝红
		POS_COLOR_MAPPING_RGB.put("8", "0.132,0.330,0.10|0.393,0.068,0.048");//绿红
		POS_COLOR_MAPPING_RGB.put("9", "0.330,0.380,0.093|0.393,0.068,0.048");//黄红
		POS_COLOR_MAPPING_RGB.put("10", "0.132,0.330,0.10|0.039,0.081,0.158");//绿蓝
		POS_COLOR_MAPPING_RGB.put("11", "0.330,0.380,0.093|0.039,0.081,0.158");//黄蓝
		POS_COLOR_MAPPING_RGB.put("12", "0.330,0.380,0.093|0.132,0.330,0.10");//黄绿
		
		POS_COLOR_MAPPING.put("1", "2");
		POS_COLOR_MAPPING.put("2", "2|0");
		POS_COLOR_MAPPING.put("3", "2|1");
		POS_COLOR_MAPPING.put("4", "");
		POS_COLOR_MAPPING.put("5", "");
		POS_COLOR_MAPPING.put("6", "");
		POS_COLOR_MAPPING.put("7", "");
		POS_COLOR_MAPPING.put("8", "");
		POS_COLOR_MAPPING.put("9", "");
		POS_COLOR_MAPPING.put("10", "");
		POS_COLOR_MAPPING.put("11", "");
		POS_COLOR_MAPPING.put("12", "");
	}
}
