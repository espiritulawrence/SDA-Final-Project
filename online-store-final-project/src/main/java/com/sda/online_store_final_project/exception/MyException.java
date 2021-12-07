package com.sda.online_store_final_project.exception;


import com.sda.online_store_final_project.enums.ResultEnum;

public class MyException extends RuntimeException{

    private Integer code;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
