package com.kush.prowler.model.contract.response.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.kush.prowler.model.AppStatusCode;
import com.kush.prowler.model.entity.SystemAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {
    private String message;
    private String code;
    private boolean status;
    private T data;

    private MessageErrorNode errors;

    public ApiResponse(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public String getCode() {
        if(code==null && status){
            code= AppStatusCode.S20000.name();
        }
        return code;
    }
    public void setResponseData(T response) {
        data=response;
        status=true;
        code=AppStatusCode.S20000.name();
    }
    public void setResponseData(T response,AppStatusCode appStatusCode) {
        data=response;
        status=true;
        code=appStatusCode.name();
    }
}