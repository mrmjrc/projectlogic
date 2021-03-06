package cn.mrmj.utils;

/**
 * create by: mrmj
 * description: 通过经纬度获取两点之间的距离
 * create time: 2019/11/21 14:30
 */
public class LocationUtils {
    private static double EARTH_RADIUS = 6371.393;
    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    /**
     * create by: mrmj
     * description: 计算两个经纬度之间的距离
     *      *      * @param lat1
     *      *      * @param lng1
     *      *      * @param lat2
     *      *      * @param lng2
     * create time: 2019/11/21 14:31
     */
    public static double GetDistance(double lat1, double lng1, double lat2, double lng2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)* Math.cos(radLat2)* Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000);
        return s;
    }

    public static void main(String[] args) {
        System.out.println(LocationUtils.GetDistance(30.067212742816693,119.91573000000002,30.0001,120.124192));
    }
}
