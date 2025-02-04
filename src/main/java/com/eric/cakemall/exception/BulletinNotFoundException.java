package com.eric.cakemall.exception;

public class BulletinNotFoundException extends RuntimeException {

    public BulletinNotFoundException(String message) {
        super(message);
    }

    public BulletinNotFoundException(Integer bulletinNo) {
        super("找不到公告，公告編號：" + bulletinNo);
    }
}
