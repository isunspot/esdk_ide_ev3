package com.huawei.esdk.ev3;

import com.huawei.esdk.ev3.service.ColorService;


public class HDCIDERobotAssistant extends RobotConstantsConf {
	
	public static int calculateTurn(boolean isClockwise,
			int light, int colorID, float[] colorRGB) {
		if (1 == POWER_GAIN_METHOD) {
			return calTurnByLight(isClockwise, light);
		} else if (2 == POWER_GAIN_METHOD) {
			return calTurnByColorID(isClockwise, colorID);
		}  else if (3 == POWER_GAIN_METHOD) {
			return calTurnByRGB(isClockwise, colorRGB);
		} else {
			return 0;
		}
	}
	
	private static int calTurnByLight(boolean isClockwise,
			int light) {
		int turn = 0;
		if (isClockwise) {
			 if (light - ColorService.COLOR_VALUE_TRACK_LEFT_SIDE < -COLOR_DIFF) {
				 turn = -POWER_GAIN;
			 } else {
				 turn = 0;
			 }
		 } else {
			 if (light - ColorService.COLOR_VALUE_TRACK_RIGHT_SIDE > COLOR_DIFF) {
				 turn = POWER_GAIN;
			 }
			 else {
				 turn = 0;
			 }
		 }
		
		return turn;
	}

	private static int calTurnByColorID(boolean isClockwise, int colorID){
		int turn = 0;
		if (isClockwise) {
			if (ColorService.colorMatched(colorID, ColorService.COLOR_VALUE_TRACK_LEFT_SIDE_ID)) {
				turn = -POWER_GAIN;
			} else if (ColorService.colorMatched(colorID, ColorService.COLOR_VALUE_TRACK_RIGHT_SIDE_ID)) {
				turn = POWER_GAIN;
			} else {
				turn = 0;
			}
		} else {
			//TODO
			turn = 0;
		}
		
		return turn;
	}
	
	private static int calTurnByRGB(boolean isClockwise, float[] colorRGB) {
		int turn = 0;
		if (isClockwise){
			if (ColorService.colorMatchRGB4Moving(colorRGB, ColorService.COLOR_VALUE_TRACK_LEFT_SIDE_RGB)) {
				turn = -POWER_GAIN;
			} else if (ColorService.colorMatchRGB4Moving(colorRGB, ColorService.COLOR_VALUE_TRACK_RIGHT_SIDE_RGB)) {
				turn = POWER_GAIN;
			} else {
				turn = 0;
			}
		} else {
			if (ColorService.colorMatchRGB4Moving(colorRGB, ColorService.COLOR_VALUE_TRACK_LEFT_SIDE_RGB)) {
				turn = POWER_GAIN;
			} else if (ColorService.colorMatchRGB4Moving(colorRGB, ColorService.COLOR_VALUE_TRACK_RIGHT_SIDE_RGB)) {
				turn = -POWER_GAIN;
			} else {
				turn = 0;
			}
		}
		
		return turn;
	}
}