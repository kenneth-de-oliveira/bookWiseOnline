package io.github.kenneth.application;

import lombok.Getter;

public class ServiceUnavailableException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
    private final Integer status;

    public ServiceUnavailableException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }

    public ServiceUnavailableException() {
		this.status = null;

    }


}
