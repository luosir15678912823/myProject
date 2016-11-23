package com.qianfeng.mall.modle;

/**
 * Created by qf on 2016/10/21.
 */
public class Classification {
        private String name;
        private String cid;

    public Classification(String cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    public Classification() {
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }


    @Override
    public String toString() {
        return "Classification{" +
                "name='" + name + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
