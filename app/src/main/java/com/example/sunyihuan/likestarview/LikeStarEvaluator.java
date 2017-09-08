package com.example.sunyihuan.likestarview;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Created by Sun on 2017/8/31.
 */

class LikeStarEvaluator implements TypeEvaluator<Point> {


    private Point controllPoint;

    public LikeStarEvaluator(Point controllPoint) {
        this.controllPoint = controllPoint;
    }

    @Override
    public Point evaluate(float t, Point startValue, Point endValue) {
        int x = (int) ((1 - t) * (1 - t) * startValue.x + 2 * t * (1 - t) * controllPoint.x + t * t * endValue.x);
        int y = (int) ((1 - t) * (1 - t) * startValue.y + 2 * t * (1 - t) * controllPoint.y + t * t * endValue.y);
        return new Point(x, y);//返回获取path上的坐标点
    }
}
