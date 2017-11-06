package com.jyw.hefeinews.domain;

import java.util.List;

/**
 * Created by pc on 2017/10/30.
 */

public class NewsCenterPagerBean2 {
    private int retcode;
    private List<DataBean> data;
    private int[] extend;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public int[] getExtend() {
        return extend;
    }

    public void setExtend(int[] extend) {
        this.extend = extend;
    }

    public static class DataBean {
        private int id;
        private int type;
        private String title;
        private String url;
        private String url1;
        private String excurl;
        private String dayurl;
        private String weekurl;
        private List<DataChildren> children;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl1() {
            return url1;
        }

        public void setUrl1(String url1) {
            this.url1 = url1;
        }

        public String getExcurl() {
            return excurl;
        }

        public void setExcurl(String excurl) {
            this.excurl = excurl;
        }

        public String getDayurl() {
            return dayurl;
        }

        public void setDayurl(String dayurl) {
            this.dayurl = dayurl;
        }

        public String getWeekurl() {
            return weekurl;
        }

        public void setWeekurl(String weekurl) {
            this.weekurl = weekurl;
        }

        public List<DataChildren> getChildren() {
            return children;
        }

        public void setChildren(List<DataChildren> children) {
            this.children = children;
        }

        public static  class DataChildren {
            private int id;
            private int type;
            private String title;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
