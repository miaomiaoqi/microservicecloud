package com.miaoqi.springcloudsell.user.vo;

import lombok.Data;

/**
 * http 请求返回的最外层对象
 *
 * @author miaoqi
 * @date 2019-06-07
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码, 正常是 0
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;

}
