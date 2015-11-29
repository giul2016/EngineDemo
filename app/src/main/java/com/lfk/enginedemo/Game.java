package com.lfk.enginedemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.lfk.justweengine.Anim.CircleMoveAnimation;
import com.lfk.justweengine.Anim.ThrobAnimation;
import com.lfk.justweengine.Engine.Engine;
import com.lfk.justweengine.Engine.GameTextPrinter;
import com.lfk.justweengine.Engine.GameTexture;
import com.lfk.justweengine.Engine.GameTimer;
import com.lfk.justweengine.Sprite.BaseSprite;

//import android.util.Log;

public class Game extends Engine {
    GameTextPrinter printer;
    Paint paint;
    Canvas canvas;
    GameTimer timer;
    //    GameTexture texture;
    BaseSprite sprite;

    public Game() {
        super(false);
//        Log.d("game", " constructor");
        paint = new Paint();
        canvas = null;
//        texture = null;
        printer = new GameTextPrinter();
        printer.setTextColor(Color.WHITE);
        printer.setTextSize(24);
        printer.setLineSpaceing(28);
        timer = new GameTimer();
        setTouchNum(5);
    }

    @Override
    public void init() {
//        Log.d("game", "init");
        super.setScreenOrientation(ScreenMode.PORTRAIT);
    }

    @Override
    public void load() {
        GameTexture texture = new GameTexture(this);
        texture.loadFromAsset("pic/zombie_walk.png");
        sprite = new BaseSprite(this, 96, 96, 8);
        sprite.setTexture(texture);
        sprite.setPosition(100, 100);
        sprite.addAnimation(new ThrobAnimation(0.2f, 0.8f, 0.001f));

        sprite.addAnimation(new CircleMoveAnimation(
                300, 200, 200, 0, 0.05f));
    }


    @Override
    public void draw() {
//        Log.d("game", " draw");

        paint.setColor(Color.WHITE);
        canvas = super.getCanvas();

        printer.setCanvas(canvas);
        printer.drawText("First engine demo", 10, 20);


        sprite.draw();

        if (super.getTouchPoints() > 0) {
            printer.drawText("Touch inputs: " + super.getTouchPoints());
            for (int i = 0; i < super.getTouchPoints(); i++) {
                printer.drawText(i + " :" + super.getTouchPoint(i).toString());
                Point p = super.getTouchPoint(i);
//                Log.d("point :", "x:" + p.x + " y:" + p.y);
                if (p.x != 0 && p.y != 0) {
                    canvas.drawCircle(p.x, p.y, 50, paint);
                }
            }
        }

        if (timer.stopWatch(500)) {
            super.drawText("**TIMER**", super.getCanvas().getWidth() / 2, 20);
        }
    }

    @Override
    public void update() {
//        Log.d("engine", " update" + sprite.getAlpha());
        if (timer.stopWatch(20)) {

//            if (sprite.getScale().x <= 0.5f) {
//                sprite.addfixedAnimation("alpha", new ThrobAnimation(0.5f, 3.0f, 0.01f));
//            }
//        }
            if (sprite.getAlpha() == 0) {
                sprite.setAlpha(255);
            }
            sprite.animation();
            //manually update rotation
            float r = sprite.getRotation();
            sprite.setRotation(r + 0.01f);
            //manually reset scaling
            if (sprite.getScale().x <= 0.2f)
                sprite.addAnimation(new ThrobAnimation(0.2f, 0.8f, 0.001f));
//            sprite.fixedAnimation("alpha");
        }
    }
}
