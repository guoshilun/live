package com.jk.jkproject.ui.entity;

import java.util.List;

public class MixerOutputList {

    private List<MixerOutputListBean> mixer_output_list;

    public List<MixerOutputListBean> getMixer_output_list() {
        return mixer_output_list;
    }

    public void setMixer_output_list(List<MixerOutputListBean> mixer_output_list) {
        this.mixer_output_list = mixer_output_list;
    }

    public static class MixerOutputListBean {
        /**
         * stream_id : streamid-736
         * rtmp_url : rtmp://rtmp.wsdemo.zego.im/miniapp/zegotest-2250166144-streamid-736
         * flv_url : http://hdl-wsdemo.zego.im/miniapp/zegotest-2250166144-streamid-736.flv
         * hls_url : http://hls.wsdemo.zego.im/miniapp/zegotest-2250166144-streamid-736/playlist.m3u8
         */

        private String stream_id;
        private String rtmp_url;
        private String flv_url;
        private String hls_url;

        public String getStream_id() {
            return stream_id;
        }

        public void setStream_id(String stream_id) {
            this.stream_id = stream_id;
        }

        public String getRtmp_url() {
            return rtmp_url;
        }

        public void setRtmp_url(String rtmp_url) {
            this.rtmp_url = rtmp_url;
        }

        public String getFlv_url() {
            return flv_url;
        }

        public void setFlv_url(String flv_url) {
            this.flv_url = flv_url;
        }

        public String getHls_url() {
            return hls_url;
        }

        public void setHls_url(String hls_url) {
            this.hls_url = hls_url;
        }
    }
}
