package com.youngit.office.api.client.dto;

import lombok.Data;

@Data
public class ClientDto {
    String searchOption;
    String searchData;
    String sqlQuery;

    public ClientDto() {
        this.searchOption = "";
        this.searchData = "";
        this.sqlQuery = "";
    }



}
