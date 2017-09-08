package com.example.sunyihuan.likestarview;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class LikeStarActivity extends AppCompatActivity implements View.OnTouchListener{

    private LinearLayout mTouchView;
    public static final String TAG = "LikeStarActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_star);
        mTouchView = (LinearLayout) findViewById(R.id.touchview);
        mTouchView.setOnTouchListener(this);
    }





    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LikeStarView likeStarView = new LikeStarView(LikeStarActivity.this);
                int pointX = (int) event.getRawX();
                int pointY = (int) event.getRawY();
                likeStarView.setstartpoint(new Point(pointX,pointY));
                //添加自定义view
                ViewGroup rootView = (ViewGroup) LikeStarActivity.this.getWindow().getDecorView();
                rootView.addView(likeStarView);
                likeStarView.startanim();
                break;
        }
        return super.onTouchEvent(event);
    }
}
