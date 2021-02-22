package org.sacc.smis.exception;

import lombok.Getter;
import org.sacc.smis.enums.Business;

/**
 * Created by 林夕
 * Date 2021/1/21 20:58
 */
@Getter
public class BusinessException extends RuntimeException {
    private final Business business;

    public BusinessException(Business business) {
        super(business.getMessage());
        this.business = business;
    }

    public BusinessException(Business business, Throwable throwable) {
        super(business.getMessage(), throwable);
        this.business = business;
    }
}
