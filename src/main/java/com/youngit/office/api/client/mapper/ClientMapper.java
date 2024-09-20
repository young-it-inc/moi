package com.youngit.office.api.client.mapper;

import com.youngit.office.api.client.dto.ClientSearchDto;
import com.youngit.office.api.client.model.ClientManagerModel;
import com.youngit.office.api.client.model.ClientModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {
    List<ClientModel> getOrSearchListClient(ClientSearchDto clientSearchDto);
    int countGetOrSearchListClient(ClientSearchDto clientSearchDto);
    ClientModel getOneClient(String clientUniqId);
    List<ClientManagerModel> getListClientManager(String clientUniqId);

    List<ClientModel> searchListClient(ClientSearchDto clientSearchDto);

    int registerClient(ClientModel clientModel);
    int registerClientManager(ClientManagerModel clientManagerModel);
    boolean checkBizNumber(String bizNumber);
    String getLastClientUniqId();



    int updateClient(ClientModel clientModel);
    int updateClientManager(ClientManagerModel clientManagerModel);


    int deleteClient(String clientUniqId);

}