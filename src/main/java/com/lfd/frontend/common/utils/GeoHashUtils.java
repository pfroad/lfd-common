package com.lfd.frontend.common.utils;

/**
 * geo hash calculator
 geohash长度	Lat位数	Lng位数	Lat误差	Lng误差	km误差
 1	2	3	±23	±23	±2500
 2	5	5	± 2.8	±5.6	±630
 3	7	8	± 0.70	± 0.7	±78
 4	10	10	± 0.087	± 0.18	±20
 5	12	13	± 0.022	± 0.022	±2.4
 6	15	15	± 0.0027	± 0.0055	±0.61
 7	17	18	±0.00068	±0.00068	±0.076
 8	20	20	±0.000086	±0.000172	±0.01911
 9	22	23	±0.000021	±0.000021	±0.00478
 10	25	25	±0.00000268	±0.00000536	±0.0005971
 11	27	28	±0.00000067	±0.00000067	±0.0001492
 12	30	30	±0.00000008	±0.00000017	±0.0000186
 *
 * Created by Ryan on 4/15/2016.
 */
public class GeoHashUtils {
    public static final String[] ENCODE_TABLE = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "b", "c", "d", "e", "f", "g", "h", "j", "k", "m",
            "n", "p", "q", "r", "s", "t", "u", "v", "w", "x",
            "y", "z"
    };

    public static final int SEPARATE_LEN = 5;

    public static final int DEFAULT_GEO_HASH_LENGTH = 8;

    public static byte[] parse(double start, double end, double geoVal, int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            double diff = end - start;
            if (geoVal < start + diff / 2.0) {
                bytes[i] = 0;
                end = start + diff / 2.0;
            } else {
                bytes[i] = 1;
                start = start + diff / 2.0;
            }
        }
        return bytes;
    }

    public static byte[] encodeLatitude(double latitude, int length) {
        int start = -90;
        int end = 90;

       return parse(start, end, latitude, length);
    }

    public static byte[] encodeLongitude(double longitude, int length) {
        int start = -180;
        int end = 180;

        return parse(start, end, longitude, length);
    }

    public static String encodeToString(byte[] bytes) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < bytes.length; i += SEPARATE_LEN) {
            int codeIndex = 0;
            for (int j = 0; j < SEPARATE_LEN; j++) {
                codeIndex += byteVal(bytes[i + j], SEPARATE_LEN - 1 - j);
            }
            str.append(ENCODE_TABLE[codeIndex]);
        }

        return str.toString();
    }

    public static int byteVal(int b, int pow) {
        return b == 0 ? 0 : (int) Math.pow(2, pow);
    }

    public static String encode(double latitude, double longitude, int length) {
        int byteLength = length * SEPARATE_LEN;

        int latBitLength = byteLength / 2;
        byte[] latBytes = encodeLatitude(latitude, latBitLength);
        byte[] longBytes = encodeLongitude(longitude, byteLength - latBitLength);

        byte[] bytes = new byte[byteLength];
        for (int i = 0; i < latBitLength; i++) {
            bytes[2 * i] = longBytes[i];
            bytes[2 * i + 1] = latBytes[i];
        }

        if (byteLength % 2 > 0) {
            bytes[byteLength - 1] = longBytes[latBitLength];
        }

        return encodeToString(bytes);
    }

    public static String encode(double latitude, double longitude) {
        return encode(latitude, longitude, DEFAULT_GEO_HASH_LENGTH);
    }

    public static void main(String[] args){
        byte[] bytes = {1, 1, 1, 0, 0, 1, 1,
                1, 0, 1, 0, 0, 1, 0,
                0, 0, 1, 1, 1, 1, 0, 0,
                0, 0, 0, 0, 1, 1, 0,
                1, 0, 1, 0, 1, 1, 0,
                0, 0, 0, 1};

//        System.out.println(encodeToString(bytes));
        System.out.println(encode(23.103277, 113.313423, 8));
        System.out.println(encode(23.104205, 113.313383, 8).substring(0, 4));
    }
}
