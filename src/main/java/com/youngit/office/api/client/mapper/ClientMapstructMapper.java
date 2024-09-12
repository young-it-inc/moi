package com.youngit.office.api.client.mapper;

import com.youngit.office.api.client.dto.ClientDto;
import com.youngit.office.api.client.dto.ClientManagerDto;
import com.youngit.office.api.client.model.ClientManagerModel;
import com.youngit.office.api.client.model.ClientModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapstructMapper {

    ClientDto toDto(ClientModel clientModel);
    ClientManagerDto toDto(ClientManagerModel clientManagerModel);
    ClientModel toModel(ClientDto clientDto);
    ClientManagerModel toModel(ClientManagerDto clientManagerDto);
    List<ClientDto> toDtoList(List<ClientModel> clientModelList);
    List<ClientModel> toModelList(List<ClientDto> clientDtoList);

}
