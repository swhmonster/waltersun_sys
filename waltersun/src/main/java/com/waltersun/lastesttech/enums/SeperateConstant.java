package com.waltersun.lastesttech.enums;

/**
 * SeperateConstant
 *
 * @author sunwh33441
 * @date 2021-07-16
 */
public enum SeperateConstant {
    /**
     * 分号
     */
    SEPERATE_FH(";"),
    /**
     * 冒号
     */
    SEPERATE_MH(":"),
    /**
     * 英文点
     */
    SEPERATE_DOC("."),
    /**
     * 中文逗号
     */
    SEPERATE_ZWDH("，"),
    /**
     * 英文逗号
     */
    SEPERATE_YWDH(","),
    /**
     * 中文冒号
     */
    SEPERATE_ZWMH("："),
    /**
     * 英文冒号
     */
    SEPERATE_YWMH(":"),
    /**
     * 横杠
     */
    SEPERATE_HG("-"),
    /**
     * 单个空格
     */
    SEPERATE_KG(" "),
    /**
     * 下划线
     */
    SEPERATE_XHX("_"),

    /**
     * &
     */
    SEPERATE_YU("&");
    private final String seperate;

    /**
     * 构造
     *
     * @param seperate
     */
    SeperateConstant(String seperate) {
        this.seperate = seperate;
    }

    /**
     * 获取分割符号
     *
     * @return string
     */
    public String getSeperate() {
        return this.seperate;
    }

}
