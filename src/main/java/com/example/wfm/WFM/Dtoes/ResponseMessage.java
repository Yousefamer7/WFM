package com.example.wfm.WFM.Dtoes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int statusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object body;

        public ResponseMessage(int statusCode, String message){
            this.statusCode = statusCode;
            this.message = message;
        }
        public ResponseMessage(int statusCode, Object body){
            this.statusCode = statusCode;
            this.body =body;
        }
}

