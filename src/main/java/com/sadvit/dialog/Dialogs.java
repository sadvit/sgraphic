package com.sadvit.dialog;

public class Dialogs {

    private static MosaicDialog mosaicDialog;

    private static LineDialog lineDialog;

    private static CircleDialog circleDialog;

    public static void showMosaicDialog() {
        if (mosaicDialog == null)
            mosaicDialog = new MosaicDialog();
        mosaicDialog.showDialog();
    }

    public static void showLineDialog() {
        if (lineDialog == null)
            lineDialog = new LineDialog();
        lineDialog.showDialog();
    }

    public static void showCircleDialog() {
        if (circleDialog == null)
            circleDialog = new CircleDialog();
        circleDialog.showDialog();
    }

    public static void showCurvesDialog() {

    }

}
