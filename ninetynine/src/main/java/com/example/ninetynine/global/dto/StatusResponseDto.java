package com.example.ninetynine.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class StatusResponseDto<T> {
    private String msg;
    private T data;
    private int statusCode;

    public StatusResponseDto(String msg, int statusCode){
        this.msg = msg;
        this.statusCode = statusCode;
    }

    protected ResponseEntity<T> of(){
        return ResponseEntity.status(this.getStatusCode())
                .body(this.getData());
    }

}
