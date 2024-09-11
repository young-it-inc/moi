package com.youngit.office.api.client.service;

import com.youngit.office.api.client.dto.ClientDto;
import com.youngit.office.api.client.dto.ClientManagerDto;
import com.youngit.office.api.client.mapper.ClientMapper;
import com.youngit.office.api.client.model.ClientManagerModel;
import com.youngit.office.api.client.model.ClientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }
    public ClientDto convertToDto(ClientModel clientModel) {
        return clientMapper.toDto(clientModel);
    }
    public ClientModel converToModel(ClientDto clientDto) {
        return clientMapper.toModel(clientDto);
    }
    public List<ClientDto> convertToDtoList(List<ClientModel> clientModelList) {
        return clientMapper.toDtoList(clientModelList);
    }
    public List<ClientModel> convertToModelList(List<ClientDto> clientDtoList) {
        return clientMapper.toModelList(clientDtoList);
    }



    public List<ClientDto> getListClient() {
        List<ClientModel> resultModel = clientMapper.getListClient();
        List<ClientDto> resultDto = convertToDtoList(resultModel);
        return resultDto;
    }

    public ClientDto getOneClient(String clientUniqId) {
        ClientModel clientModel = clientMapper.getOneClient(clientUniqId);
        clientModel.setClientManagerModelList(clientMapper.getListClientManager(clientUniqId));
        ClientDto resultDto = convertToDto(clientModel);
        return resultDto;

    }
    public int registerClient(ClientDto clientDto) {

        //clientUniqId: 고유번호(BCNC_15자리) 등록하기 위해 가장 최근의 고유번호를 가져옴
        String lastClientUniqId = clientMapper.getLastClientUniqId();
        String newClientUniqId = generateNewClientUniqId(lastClientUniqId);
        clientDto.setClientUniqId(newClientUniqId);
        ClientModel clientModel = converToModel(clientDto);

        int result = 0;
        result = clientMapper.registerClient(clientModel);

        //담당자 추가됐을 시 담당자도 등록
        if(clientModel.getClientManagerModelList() != null)
        {
            for(ClientManagerModel clientManagerModel : clientModel.getClientManagerModelList())
            {
                clientManagerModel.setClientUniqId(newClientUniqId);
                result += clientMapper.registerClientManager(clientManagerModel);
            }
        }
        //clientTypeCode:
        return result;
    }

    private String generateNewClientUniqId(String lastClientUniqId) {
        if(lastClientUniqId == null)
            return "BCNC_000000000000001";
        String prefix = "BCNC_";
        String numberPart = lastClientUniqId.substring(prefix.length());
        int newNumber = Integer.parseInt(numberPart) + 1;
        String newNubmerStr = String.format("%015d", newNumber); //0으로 패딩하여 15자리로 맞춤
        return prefix + newNubmerStr;
    }


    public boolean checkBizNumber(String bizNumber) {

        return clientMapper.checkBizNumber(bizNumber);
    }

    public int updateClient(ClientDto clientDto) {

        ClientModel clientModel = converToModel(clientDto);
        int result = clientMapper.updateClient(clientModel);
        if(clientDto.getClientManagerDtoList() != null)
        {
            for(ClientManagerDto clientManagerDto : clientDto.getClientManagerDtoList())
            {
                if(clientManagerDto.getClientUniqId() == null)
                {
                    clientManagerDto.setClientUniqId(clientDto.getClientUniqId());
                }
                ClientManagerModel clientManagerModel = clientMapper.toModel(clientManagerDto);
                result += clientMapper.updateClientManager(clientManagerModel);
            }
        }
        return result;
    }

    public int deleteClient(String clientUniqId) {

        return clientMapper.deleteClient(clientUniqId);
    }
}
