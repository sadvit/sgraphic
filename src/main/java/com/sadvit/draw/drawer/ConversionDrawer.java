package com.sadvit.draw.drawer;

import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;
import javafx.scene.paint.Color;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ConversionDrawer implements Drawer {

    private static double a = 0.5;

    private static double b = 0.5;

    private static double f = 0.5;

    public static final RealMatrix SCALE = MatrixUtils.createRealMatrix(new double[][]{
            {a, 0, 0},
            {0, b, 0},
            {0, 0, 1}
    });

    public static final RealMatrix MOVE = MatrixUtils.createRealMatrix(new double[][]{
            {1, 0, a},
            {0, 1, b},
            {0, 0, 1}
    });

    public static final RealMatrix ROTATE = MatrixUtils.createRealMatrix(new double[][]{
            { cos(f), sin(f), 0},
            {-sin(f), cos(f), 0},
            {0      , 0     , 1}
    });

    @Override
    public void draw(SimpleCanvas canvas) {
        SimpleCanvas simpleCanvas = canvas.clone();
        canvas.forEach(point -> {
            RealVector vector = point.toUniform();
            RealVector value = ROTATE.preMultiply(vector);
            Point2 result = vectorToPoint(value);
            simpleCanvas.setColor(result, canvas.getColor(point));
        });
        canvas.clean();
        canvas.forEach(point -> {
            Color color = simpleCanvas.getColor(point);
            canvas.setColor(point, color);
        });
    }

    private Point2 vectorToPoint(RealVector vector) {
        double[] array = vector.toArray();
        return new Point2(array[0], array[1]);
    }

}
