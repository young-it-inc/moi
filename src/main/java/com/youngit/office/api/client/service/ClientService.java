package com.youngit.office.api.client.service;

import com.youngit.office.api.client.dto.ClientDto;
import com.youngit.office.api.client.dto.ClientSearchDto;

import java.io.IOException;
import java.util.List;

public interface ClientService {

    List<ClientDto> getOrSearchListClient(ClientSearchDto clientSearchDto);
    int countGetOrSearchListClient(ClientSearchDto clientSearchDto);
    ClientDto getOneClient(String clientUniqId);
    int registerClient(ClientDto clientDto);
    boolean checkBizNumber(String bizNumber);

    byte[] generateExcelClientList(List<ClientDto> clientDtoList) throws IOException;

    int updateClient(ClientDto clientDto);
    int deleteClient(String clientUniqId);

}
