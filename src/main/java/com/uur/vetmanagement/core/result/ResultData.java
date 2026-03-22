package com.uur.vetmanagement.core.result;

import com.uur.vetmanagement.core.config.exception.Msg;
import lombok.Getter;

@Getter
public class ResultData <T> extends Result{

    private T data;


    public ResultData(boolean status, String message, String httpCode,T data) {
        super(status, message, httpCode);
        this.data=data;
    }

    public static <T>ResultData<T> created(T data){
        return new ResultData<>(true, Msg.CREATED,"201",data);
    }

    public static <T>ResultData<T> validationError(T data){
        return new ResultData<>(false, Msg.INVALID,"400",data);
    }


    public static <T>ResultData<T> success(T data){
        return new ResultData<>(true, Msg.OK,"200",data);
    }

    public static Result notFoundException(String msg){
        return new Result(false,msg,"404");
    }

    




}
