package com.jk.jkproject.ui.entity;

public class UpdateInfo extends BaseInfo {

    /**
     * data : {"update_type":1,"create_time":"2020-07-06 02:03:07","versions":"1.0.0","update_url":"www.baidu.com","content":"ios第一版本"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * update_type : 1
         * create_time : 2020-07-06 02:03:07
         * versions : 1.0.0
         * update_url : www.baidu.com
         * content : ios第一版本
         */

        private int update_type;
        private String create_time;
        private String versions;
        private String update_url;
        private String content;

        public int getUpdate_type() {
            return update_type;
        }

        public void setUpdate_type(int update_type) {
            this.update_type = update_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getVersions() {
            return versions;
        }

        public void setVersions(String versions) {
            this.versions = versions;
        }

        public String getUpdate_url() {
            return update_url;
        }

        public void setUpdate_url(String update_url) {
            this.update_url = update_url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
