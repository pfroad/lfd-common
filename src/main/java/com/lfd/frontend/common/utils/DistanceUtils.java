package com.lfd.frontend.common.utils;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 1/25/16.
 */
public class DistanceUtils {
    public static final double EARTH_RADUS = 6367000.0;
    /**
     * Haversine calculator
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    public static double distHaversineRAD(double lat1, double lon1, double lat2, double lon2) {
        double hsinX = Math.sin(toRadians(lon1 - lon2) * 0.5);
        double hsinY = Math.sin(toRadians(lat1 - lat2) * 0.5);
        double h = hsinY * hsinY +
                (Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2)) * hsinX * hsinX);
        return 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1 - h)) * EARTH_RADUS;
    }

    /**
     * meituan simple distance calculator
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double distanceSimplify(double lat1, double lng1, double lat2, double lng2) {
        double dx = lng1 - lng2;
        double dy = lat1 - lat2;
        double midY = (lat1 + lat2) / 2;

        double disX = EARTH_RADUS * Math.cos(toRadians(midY)) * toRadians(dx);
        double disY = EARTH_RADUS * toRadians(dy);

        return Math.sqrt(disX * disX + disY * disY);
    }

    /**
     * meituan simplest distance calculator, use multiply to replace trigonometrical function to reduce calculate times
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @param a
     * @return
     */
    public static double distanceSimplifyMore(double lat1, double lng1, double lat2, double lng2, double[] a) {
        //1) 计算三个参数
        double dx = lng1 - lng2; // 经度差值
        double dy = lat1 - lat2; // 纬度差值
        double b = (lat1 + lat2) / 2.0; // 平均纬度
        //2) 计算东西方向距离和南北方向距离(单位：米)，东西距离采用三阶多项式
        double Lx = (a[3] * b*b*b  + a[2]* b*b  +a[1] * b + a[0] ) * toRadians(dx) * 6367000.0; // 东西距离
        double Ly = EARTH_RADUS * toRadians(dy); // 南北距离
        //3) 用平面的矩形对角距离公式计算总距离
        return Math.sqrt(Lx * Lx + Ly * Ly);
    }

    public static double toRadians(double degree) {
        return degree / 360 * 2 * Math.PI;
    }

    public static double[] trainPolyFit(int degree, int Length){
        PolynomialCurveFitter polynomialCurveFitter = PolynomialCurveFitter.create(degree);
        double minLat = 10.0; //中国最低纬度
        double maxLat = 60.0; //中国最高纬度
        double interv = (maxLat - minLat) / (double)Length;
        List<WeightedObservedPoint> weightedObservedPoints = new ArrayList<WeightedObservedPoint>();
        for(int i = 0; i < Length; i++) {
            double x = minLat + (double)i*interv;
            WeightedObservedPoint weightedObservedPoint = new WeightedObservedPoint(1,  x, Math.cos(toRadians(x)));
            weightedObservedPoints.add(weightedObservedPoint);
        }
        return polynomialCurveFitter.fit(weightedObservedPoints);
    }

    public static void main(String[] args) {
//        double x = 66;
//        for (int i = 0; i < 6; i++) {
//            double radian = toRadians(x);
//            double[] fit = trainPolyFit(3, (int) (10 * Math.pow(10, i)));
//            System.out.println(Math.cos(radian) - (fit[3] * radian*radian*radian  + fit[2]* radian*radian +fit[1] * radian + fit[0]));
//        }
        System.out.println(distHaversineRAD(23.12248, 113.320519, 23.122, 113.320));
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10000000; i++) {
//            distHaversineRAD(39.26, 115.25, 41.04, 117.30);
//        }
//        System.out.println("distHaversineRAD:" + (System.currentTimeMillis() - start));
//        start = System.currentTimeMillis();
//        for (int i = 0; i < 10000000; i++) {
//            distanceSimplify(39.26, 115.25, 41.04, 117.30);
//        }
//        System.out.println("distanceSimplify:" + (System.currentTimeMillis() - start));
//        double[] fits = trainPolyFit(3, 1000);
//        start = System.currentTimeMillis();
//        for (int i = 0; i < 10000000; i++) {
//            distanceSimplifyMore(39.26, 115.25, 41.04, 117.30, fits);
//        }
//        System.out.println("distanceSimplifyMore:" + (System.currentTimeMillis() - start));
    }
}
