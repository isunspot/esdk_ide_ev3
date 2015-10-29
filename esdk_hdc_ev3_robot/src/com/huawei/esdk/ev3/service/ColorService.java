package com.huawei.esdk.ev3.service;

import com.huawei.esdk.ev3.RobotConstantsConf;
import com.huawei.esdk.ev3.bean.RGBBean;

public class ColorService extends RobotConstantsConf {

	public static boolean colorMatched(int colorId, int baseLineColor) {
		return colorId == baseLineColor;
	}

	public static boolean colorMatchRGB4Moving(float[] color, RGBBean baseLineColor) {
		if (Math.abs(color[0] - baseLineColor.getrValue()) < RGB_DIFF_RANGE
				&& Math.abs(color[1] - baseLineColor.getgValue()) < RGB_DIFF_RANGE
				&& Math.abs(color[2] - baseLineColor.getbValue()) < RGB_DIFF_RANGE) {
			return true;
		}
		
		return false;
	}
	
	public static boolean colorMatchRGB(float[] color, RGBBean baseLineColor) {
		if (Math.abs(color[0] - baseLineColor.getrValue()) < RGB_DIFF_RANGE_COLOR
				&& Math.abs(color[1] - baseLineColor.getgValue())  < RGB_DIFF_RANGE_COLOR
				&& Math.abs(color[2] - baseLineColor.getbValue()) < RGB_DIFF_RANGE_COLOR) {
			return true;
		}
		
		return false;
	}

	public static boolean containedIn2ndColors(int colorId) {
		for (int item : SECONDS_COLORS) {
			if (colorId == item) {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		Object re = retrievePosColorsRGB(2);
		System.out.println(re);
	}
	
	public static RGBBean[] retrievePosColorsRGB(int position) {
		String temp = POS_COLOR_MAPPING_RGB.get(String.valueOf(position));
		if (null == temp) {
			temp = "0";
		}
		String[] tempArray = temp.split("\\|");

		RGBBean[] result = new RGBBean[tempArray.length];

		String[] rgbValueArray;
		RGBBean bean;
		for (int i = 0; i < tempArray.length; i++) {
			rgbValueArray = tempArray[i].split(",");
			bean = new RGBBean();
			bean.setrValue(Float.parseFloat(rgbValueArray[0]));
			bean.setgValue(Float.parseFloat(rgbValueArray[1]));
			bean.setbValue(Float.parseFloat(rgbValueArray[2]));

			result[i] = bean;
		}

		return result;
	}

	public static int[] retrievePosColors(int position) {
		String[] posColorValues = getPosColor(position);
		int[] result = new int[posColorValues.length];
		int index = 0;
		for (String item : posColorValues) {
			result[index++] = Integer.parseInt(item);
		}

		return result;
	}

	private static String[] getPosColor(int position) {
		String temp = POS_COLOR_MAPPING.get(String.valueOf(position));
		if (null == temp) {
			temp = "0";
		}

		return temp.split("\\|");
	}
}
