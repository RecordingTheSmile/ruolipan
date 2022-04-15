package cc.ruoli.bishe.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler( value = RuntimeException.class)
    public Result RuntimeException(RuntimeException e){
        logger.info("运行时异常！原因是： " );
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result HttpMessageNotReadableException(HttpMessageNotReadableException e){
        logger.info("参数异常！原因是： " );
        e.printStackTrace();
        return Result.fail("缺少请求参数，请反馈！");
    }


    @ExceptionHandler( value = Exception.class)
    public Result GlobalException(Exception e){
        logger.info("未知异常！原因是： " + e);
        return Result.fail(e.getMessage());
    }


}
