package com.jk.jkproject;

import com.jk.jkproject.ui.entity.BaseInfo;

import java.util.List;

/**
 * @author fansan
 * @version 12/3/20
 */

public class UserGameListBean extends BaseInfo {

    public List<DataBean> data;

    public static class DataBean{

        /**
         * "video_duration": "5",
         *                 "introduce": "你们都在想我的！你说你的生活方式的不同阶段都有自己的想法去去感受一下",
         *                 "sex": 1,
         *                 "count": 34,
         *                 "order_price": 2000,
         *                 "video": "http://qn.zbjlife.cn/a498a6c384eb661fab1698053e403e94.mp3",
         *                 "units": "小时",
         *                 "dan_name": "新锐",
         *                 "userId": "1020820",
         *                 "picture": "http://qn.zbjlife.cn/f9c17a5e83d4dcb28ec298af3cd1b5bb.png",
         *                 "play_id": 1,
         *                 "name": "cf",
         *                 "nickname": "Kae的直播"
         */

        public String video_duration;
        public String introduce;
        public String userId;
        public int sex;
        public int count;
        public int order_price;
        public String video;
        public String units;
        public String dan_name;
        public String picture;
        public int play_id;
        public String name;
        public String nickname;
        public String img;
        public int events_one_buy;
    }
}
