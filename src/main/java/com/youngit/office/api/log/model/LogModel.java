package com.youngit.office.api.log.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "로그 정보")
public class LogModel {

    String logIdx; //serial idx
    String logType; //loginSuccess, data
    String method; //GET, POST, PUT, DELETE
    String url; // /login ...
    String clientId; //id
    String clientIp; //
    String info;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Seoul")
    Timestamp created_at;

    public LogModel(String logType, String method, String url, String clientId, String clientIp, String info)
    {
        this.logType = logType;
        this.method = method;
        this.url = url;
        this.clientId = clientId;
        this.clientIp = clientIp;
        this.info = info;
    }
}
