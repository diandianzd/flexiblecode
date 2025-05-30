package com.flexible.enums;

import lombok.Getter;


@Getter
public enum InOutTypeEnum {
    REFUND("refund", "退货"),
    EXPIRED("expired", "过期");


    InOutTypeEnum(String status, String name) {
        this.status = status;
        this.name = name;
    }


    private String status;
    private String name;

    public static InOutTypeEnum getInviterStatusEnum(String status) {
        for (InOutTypeEnum item : values()) {
            if (item.status.equals(status)) {
                return item;
            }
        }
        return null;
    }

}
