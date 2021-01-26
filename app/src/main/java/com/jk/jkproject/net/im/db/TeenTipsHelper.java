package com.jk.jkproject.net.im.db;

import android.content.ContentValues;
import android.content.Context;

import org.litepal.LitePal;

import java.util.List;

public class TeenTipsHelper {

    private static TeenTipsHelper inst;

    private static Context context;

    public static synchronized TeenTipsHelper instance(Context ctx) {
        if (inst == null) {
            inst = new TeenTipsHelper();
            context = ctx;
        }
        return inst;
    }

    public void save(String u_id, String time) {
        //创建要保存的对象，调用save方法直接保存  因为继承了LitePalSupport 里面提供了许多保存的APi ,可以根据需求选择合适的方法
        TeenTipsDB sqliteDemo = new TeenTipsDB();
        sqliteDemo.setU_id(u_id);
        sqliteDemo.setTime(time);
        sqliteDemo.save();
//例：异步保存：开个线程进行保存，处理完监听回调是否保存成功
        sqliteDemo.saveAsync().listen(success -> {
            List<TeenTipsDB> all = LitePal.findAll(TeenTipsDB.class);
//            for (TeenTipsDB teenTipsDB : all) {
//                LogUtils.e("=====save", teenTipsDB.toString());
//            }
//            mAdapter.setNewData(all);
        });
    }


    public void delete(String u_id) {
        //LitePal.delete();
        //LitePal.deleteAll();
        //LitePal.deleteAsync()
//        LitePal.deleteAllAsync(TeenTipsDB.class, "u_id = ? or address = ?", Name, Address).listen(new UpdateOrDeleteCallback() {
//            @Override
//            public void onFinish(int rowsAffected) {
//                List<TeenTipsDB> all = LitePal.findAll(TeenTipsDB.class);
////                mAdapter.setNewData(all);
//            }
//        });
        LitePal.deleteAllAsync(TeenTipsDB.class, "u_id = ?", u_id).listen(rowsAffected -> {
            List<TeenTipsDB> all = LitePal.findAll(TeenTipsDB.class);
//                mAdapter.setNewData(all);
        });
    }

    public void update(long id, String u_id, String time) {
        //LitePal.update();
        //LitePal.updateAll();
        //LitePal.updateAsync();
        //LitePal.updateAllAsync();
        ContentValues contentValues = new ContentValues();
        contentValues.put("u_id", u_id);
        contentValues.put("time", time);
        LitePal.updateAsync(TeenTipsDB.class, contentValues, id).listen(rowsAffected -> {
            List<TeenTipsDB> all = LitePal.findAll(TeenTipsDB.class);
//            for (TeenTipsDB teenTipsDB : all) {
//                LogUtils.e("=====update", teenTipsDB.toString());
//            }
//            mAdapter.setNewData(all);
        });
    }

    public List<TeenTipsDB> findAll() {
        return LitePal.findAll(TeenTipsDB.class);
    }


    public List<TeenTipsDB> findU_id(String u_id) {
        //查询全部
        //List<TeenTipsDB> all = LitePal.findAll(TeenTipsDB.class);
        //获取名字和地址对应的数据
//        String selection = "name = ? and address = ?";
//        List<TeenTipsDB> all = LitePal.where(selection, Name, Address).find(SqliteDemo.class);

        //获取名字和地址对应的数据
        String selection = "u_id = ?";
        return LitePal.where(selection, u_id).find(TeenTipsDB.class);
    }
}
