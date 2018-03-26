package com.larryzhang.fonp.bean;

/**
 * 翻译结果bean
 *
 * @author zhangqiang
 * @date 2018/3/26
 */

public class TranslateBean {

    /**
     * from : en
     * to : zh
     * trans_result : {"src":"apple","dst":"苹果"}
     */

    private String from;
    private String to;
    private TransResultBean trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public TransResultBean getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(TransResultBean trans_result) {
        this.trans_result = trans_result;
    }

    public static class TransResultBean {
        /**
         * src : apple
         * dst : 苹果
         */

        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }
}
