package cn.mrmj.utils;

import java.math.BigDecimal;


/**
 * create by: mrmj
 * description: 计算价格等等的计算器
 * create time: 2019/11/12 18:39
 */
public class CalculateUtils {

	/**
	 * create by: mrmj
	 * description: 加法
	 * 大数的精确计算，需要用 bigdecimal
	 * create time: 2019/11/12 18:39
	 */
	public static double add(double var1, double var2) {
		BigDecimal b1 = new BigDecimal(Double.toString(var1));
		BigDecimal b2 = new BigDecimal(Double.toString(var2));
		return b1.add(b2).doubleValue();
	}


	/**
	 * create by: mrmj
	 * description: 减法的计算
	 * create time: 2019/11/12 18:43
	 */
	public static double sub(double var1, double var2) {
		BigDecimal b1 = new BigDecimal(Double.toString(var1));
		BigDecimal b2 = new BigDecimal(Double.toString(var2));
		return b1.subtract(b2).doubleValue();
	}


	/**
	 * create by: mrmj
	 * description: 乘法
	 * create time: 2019/11/12 18:44
	 */
	public static double mul(double var1, double var2) {
		BigDecimal b1 = new BigDecimal(Double.toString(var1));
		BigDecimal b2 = new BigDecimal(Double.toString(var2));
		return b1.multiply(b2).doubleValue();
	}



	/**
	 * create by: mrmj
	 * description: 除法
	 * create time: 2019/11/12 18:44
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or ");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


	/**
	 * create by: mrmj
	 * description: 四舍五入取舍数字，scale为精确的位数
	 * create time: 2019/11/12 18:45
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


}
