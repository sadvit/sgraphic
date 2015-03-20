package com.sadvit.io.reader;

import com.sadvit.math.Point3;
import com.sadvit.math.Triangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WavefrontReader {

    public static List<Triangle> readTriangles(String path) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(path));
            List<Triangle> triangles = new ArrayList<>(5000);
            List<Point3> points = new ArrayList<>();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] arr = line.split(" ");
                switch (arr[0]) {
                    case "v":
                        points.add(v(arr[1], arr[2], arr[3]));
                        break;
                    case "f":
                        triangles.add(f(arr[1], arr[2], arr[3], points));
                        break;
                    default:
                        break;
                }
            }
            return triangles;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Point3 v(String x, String y, String z) {
        return Point3.parsePoint(x, y, z);
    }

    private static Triangle f(String n1, String n2, String n3, List<Point3> points) {
        Point3 p1 = points.get(Integer.parseInt(n1.split("/")[0]) - 1);
        Point3 p2 = points.get(Integer.parseInt(n2.split("/")[0]) - 1);
        Point3 p3 = points.get(Integer.parseInt(n3.split("/")[0]) - 1);
        return new Triangle(p1, p2, p3);
    }

}
