package io.github.kenneth.application;

import lombok.Getter;

public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;
	
	@Getter
    private Integer status;

    public BusinessException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }

    public BusinessException() {

    }


}
