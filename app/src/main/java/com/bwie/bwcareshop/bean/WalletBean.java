package com.bwie.bwcareshop.bean;

import java.util.List;

/**
 * author：张腾
 * date：2019/1/4
 */
public class WalletBean {
    private Result result;

    private String message;

    private String status;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Result {
        private int balance;

        private List<?> detailList;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public List<?> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<?> detailList) {
            this.detailList = detailList;
        }

    }
}
