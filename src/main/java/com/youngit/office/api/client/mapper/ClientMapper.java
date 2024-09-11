package com.youngit.office.api.client.mapper;

import com.youngit.office.api.client.dto.ClientDto;
import com.youngit.office.api.client.dto.ClientManagerDto;
import com.youngit.office.api.client.model.ClientManagerModel;
import com.youngit.office.api.client.model.ClientModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {

    ClientDto toDto(ClientModel clientModel);
    ClientManagerDto toDto(ClientManagerModel clientManagerModel);
    ClientModel toModel(ClientDto clientDto);
    ClientManagerModel toModel(ClientManagerDto clientManagerDto);
    List<ClientDto> toDtoList(List<ClientModel> clientModelList);
    List<ClientModel> toModelList(List<ClientDto> clientDtoList);


    List<ClientModel> getListClient();
    ClientModel getOneClient(String clientUniqId);

    List<ClientManagerModel> getListClientManager(String clientUniqId);


    int registerClient(ClientModel clientModel);
    int registerClientManager(ClientManagerModel clientManagerModel);
    boolean checkBizNumber(String bizNumber);
    String getLastClientUniqId();




    int updateClient(ClientModel clientModel);
    int updateClientManager(ClientManagerModel clientManagerModel);




    int deleteClient(String clientUniqId);

}
