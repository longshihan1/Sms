package com.longshihan.sms.bean;

import java.io.Serializable;

/**
 * @author Administrator
 * @time 2016/8/11 14:55
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class PhoneMessage implements Serializable {
    String PhoneNumber;
    String MsgContent;

    public String getMsgTime() {
        return MsgTime;
    }

    public void setMsgTime(String msgTime) {
        MsgTime = msgTime;
    }

    String MsgTime;

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getMsgContent() {
        return MsgContent;
    }

    public void setMsgContent(String msgContent) {
        MsgContent = msgContent;
    }
}
