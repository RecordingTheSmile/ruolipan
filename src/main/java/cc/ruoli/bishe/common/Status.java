package cc.ruoli.bishe.common;

import lombok.Getter;

@Getter
public enum Status {
    SUCCESS(200, "操作成功"),
    NOT_FOUND(404,"未知路径，文件不存在"),
    SERVER_ERROR(500,"服务器已经跑路了，请联系客服"),
    NOT_AUTH(403,"权限不足，请检查您的权限"),
    OPR_ERROR(201,"操作失败");
    /**
     * 错误状态码
     * */
    private Integer status;
    private String message;


    Status(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
