package com.sadvit.dialog;

public class Dialogs {

    private static MosaicDialog mosaicDialog;

    private static LineDialog lineDialog;

    private static CircleDialog circleDialog;

    private static CurveDialog curveDialog;

    private static FillDialog fillDialog;

    public static void showMosaicDialog() {
        if (mosaicDialog == null)
            mosaicDialog = new MosaicDialog();
        if (!mosaicDialog.isShowing())
            mosaicDialog.showDialog();
    }

    public static void showLineDialog() {
        if (lineDialog == null)
            lineDialog = new LineDialog();
        if (!lineDialog.isShowing())
            lineDialog.showDialog();
    }

    public static LineDialog getLineDialog() {
        if (lineDialog != null && lineDialog.isShowing())
            return lineDialog;
        return null;
    }

    public static void showCircleDialog() {
        if (circleDialog == null)
            circleDialog = new CircleDialog();
        if (!circleDialog.isShowing())
            circleDialog.showDialog();
    }

    public static void showCurveDialog() {
        if (curveDialog == null)
            curveDialog = new CurveDialog();
        if (!curveDialog.isShowing())
            curveDialog.showDialog();
    }

    public static CurveDialog getCurveDialog() {
        if (curveDialog != null && curveDialog.isShowing())
            return curveDialog;
        return null;
    }

    public static void showFillDialog() {
        if (fillDialog == null)
            fillDialog = new FillDialog();
        if (!fillDialog.isShowing())
            fillDialog.showDialog();
    }

    public static FillDialog getFillDialog() {
        if (fillDialog != null && fillDialog.isShowing())
            return fillDialog;
        return null;
    }
}