package com.wellyprtm.contact.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.message.Message;

import java.util.ArrayList;

@JsonInclude(Include.NON_EMPTY)
@Setter
@Getter
public class ApiResponse {

    private Object data;
    private String error;
    private int totalRecords;

    public ApiResponse() {
        data = null;
        error = null;
        totalRecords = 0;
    }

    public static ApiResponse data(final Object data, final int totalRecords) {
        final ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(data);
        apiResponse.setTotalRecords(totalRecords);

        return apiResponse;
    }

    public static ApiResponse data(final Object data) {
        final ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(data);

        return apiResponse;
    }

    public static ApiResponse error(final String error) {
        final ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(error);

        return apiResponse;
    }
}