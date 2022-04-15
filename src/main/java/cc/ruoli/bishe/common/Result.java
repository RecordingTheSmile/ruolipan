package cc.ruoli.bishe.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result {
    private Integer status;
    private String message;
    private Object data;


    public Result(Status s) {
        this.setStatus(s.getStatus());
        this.setMessage(s.getMessage());
    }

    /**
     * 自定义异常返回
     */
    public static Result defineError(Integer status,String message){
        Result result = new Result();
        result.setStatus(status);
        result.setMessage(message);
        return  result;
    }
    /**
     * 操作成功
     */
    public static Result success(Object obj){
        Result result = new Result(Status.SUCCESS);
        result.setData(obj);
        return  result;
    }

    /**
     * 操作失败
     * */
    public static Result fail(String obj){
        Result result = new Result(Status.OPR_ERROR);
        result.setMessage(obj);
        return  result;
    }
    public static Result fail(){
        return fail(null);
    }
}
