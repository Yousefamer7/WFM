package com.example.wfm.WFM.Dtoes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

    private String MESSAGE;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object body;

        public ResponseMessage(String msg){
            this.MESSAGE = msg;
        }
        public ResponseMessage(Object body){
            this.body =body;
        }
}

