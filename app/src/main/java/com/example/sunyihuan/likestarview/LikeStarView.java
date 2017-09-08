package com.example.sunyihuan.likestarview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import java.util.Random;

/**
 * Created by Sun on 2017/9/8.
 */

public class LikeStarView extends View {
    public static final String TAG = "LikeStarView";
    private Bitmap mBitmap;
    private Point mPoint, mStartPoint, mEndPoint;

    public LikeStarView(Context context) {
        this(context, null);
    }

    public LikeStarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LikeStarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Resources resources = context.getResources();
        mBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_fav);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(mBitmap, mPoint.x, mPoint.y, new Paint());

        super.onDraw(canvas);
    }


    public void startanim() {
        if (mStartPoint == null) {
            return;
        }

        mEndPoint = new Point(mStartPoint.x, mStartPoint.y - 500);

        Random random = new Random();
        int result = random.nextInt(201) - 100; //随机左右偏移控制点位置
        Point controllPoint = new Point(mStartPoint.x + result, mStartPoint.y - 250);
        LikeStarEvaluator likeStarEvaluator = new LikeStarEvaluator(controllPoint);
        ValueAnimator animator = ValueAnimator.ofObject(likeStarEvaluator, mStartPoint, mEndPoint);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point point = (Point) animation.getAnimatedValue();
                mPoint.x = point.x;
                mPoint.y = point.y;

                //计算透明度
                setAlpha((float) (point.y - mEndPoint.y) / (float) 500);

                invalidate();
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
//              移除图像
                ViewGroup viewGroup = (ViewGroup) getParent();
                viewGroup.removeView(LikeStarView.this);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();

    }


    public void setstartpoint(Point point) {
        this.mStartPoint = point;
        this.mPoint = point;
    }

}
