package com.bytedance.blog.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleStatusEnum {
    DELETE(0, "已删除"),
    NOTCHECK(1,"未审核"),
    SUCCESS(2, "审核通过"),
    FAIL(3, "审核失败");

    private int code;
    private String desc;
}
