package com.miliyo.danmaku.model.channel;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.miliyo.danmaku.control.dispatcher.IDanMuDispatcher;
import com.miliyo.danmaku.control.speed.SpeedController;
import com.miliyo.danmaku.model.collection.DanMuConsumedPool;
import com.miliyo.danmaku.model.collection.DanMuConsumer;
import com.miliyo.danmaku.model.collection.DanMuProducedPool;
import com.miliyo.danmaku.model.collection.DanMuProducer;
import com.miliyo.danmaku.model.painter.DanMuPainter;
import com.miliyo.danmaku.model.DanMuModel;
import com.miliyo.danmaku.view.IDanMuParent;

import java.util.List;

/**
 * Created by android_ls on 2016/12/7.
 */
public class DanMuPoolManager implements IDanMuPoolManager {

    private DanMuConsumer danMuConsumer;
    private DanMuProducer danMuProducer;

    private DanMuConsumedPool danMuConsumedPool;
    private DanMuProducedPool danMuProducedPool;

    private boolean isStart;

    public DanMuPoolManager(Context context, IDanMuParent danMuParent) {
        danMuConsumedPool = new DanMuConsumedPool(context);
        danMuProducedPool = new DanMuProducedPool(context);
        danMuConsumer = new DanMuConsumer(danMuConsumedPool, danMuParent);
        danMuProducer = new DanMuProducer(danMuProducedPool, danMuConsumedPool);
    }

    public void forceSleep() {
        danMuConsumer.forceSleep();
    }

    public void releaseForce() {
        danMuConsumer.releaseForce();
    }

    @Override
    public void hide(boolean hide) {
        danMuConsumedPool.hide(hide);
    }

    @Override
    public void hideAll(boolean hideAll) {
        danMuConsumedPool.hideAll(hideAll);
    }

    @Override
    public void startEngine() {
        if (!isStart) {
            isStart = true;
            danMuConsumer.start();
            danMuProducer.start();
            Log.i("danmu", "-----startEngine()------");
        }
    }

    @Override
    public void setDispatcher(IDanMuDispatcher iDanMuDispatcher) {
        danMuProducedPool.setDanMuDispatcher(iDanMuDispatcher);
    }

    @Override
    public void setSpeedController(SpeedController speedController) {
        danMuConsumedPool.setSpeedController(speedController);
    }

    @Override
    public void divide(int width, int height) {
        danMuProducedPool.divide(width, height);
        danMuConsumedPool.divide(width,height);
    }

    @Override
    public void addDanMuView(int index, DanMuModel danMuView) {
        danMuProducer.produce(index, danMuView);
    }

    @Override
    public void jumpQueue(List<DanMuModel> danMuViews) {
        danMuProducer.jumpQueue(danMuViews);
    }

    public void release() {
        isStart = false;
        danMuConsumer.release();
        danMuProducer.release();
        danMuConsumedPool = null;
    }

    /**
     * drawing entrance
     *
     * @param canvas
     */
    public void drawDanMus(Canvas canvas) {
        danMuConsumer.consume(canvas);
    }

    public void addPainter(DanMuPainter danMuPainter, int key) {
        danMuConsumedPool.addPainter(danMuPainter, key);
    }

}
