package com.youngit.office.api.client.mapstruct;

import com.youngit.office.api.client.dto.ClientDto;
import com.youngit.office.api.client.dto.ClientManagerDto;
import com.youngit.office.api.client.model.ClientManagerModel;
import com.youngit.office.api.client.model.ClientModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapstruct {

    ClientDto toDto(ClientModel clientModel);
    ClientManagerDto toDto(ClientManagerModel clientManagerModel);
    ClientModel toModel(ClientDto clientDto);
    ClientManagerModel toModel(ClientManagerDto clientManagerDto);


}