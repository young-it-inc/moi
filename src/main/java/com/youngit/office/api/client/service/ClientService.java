package com.youngit.office.api.client.service;

import com.youngit.office.api.client.mapper.ClientMapper;
import com.youngit.office.api.client.model.ClientManagerModel;
import com.youngit.office.api.client.model.ClientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientMapper clientMapper;

    public List<ClientModel> getListClient() {
        //사용여부 N은 출력x
        return clientMapper.getListClient();
    }

    public ClientModel getOneClient(String clientUniqId) {
        ClientModel clientModel = clientMapper.getOneClient(clientUniqId);
        System.out.println("clientModel : " + clientModel);
        System.out.println("clientModel.getClientManagerModelList() : " + clientModel.getClientManagerModelList());
        clientModel.setClientManagerModelList(clientMapper.getListClientManager(clientUniqId));
        return clientMapper.getOneClient(clientUniqId);

    }
    public int registerClient(ClientModel clientModel) {

        //clientUniqId: 고유번호(BCNC_15자리) 등록하기 위해 가장 최근의 고유번호를 가져옴
        String lastClientUniqId = clientMapper.getLastClientUniqId();
        String newClientUniqId = generateNewClientUniqId(lastClientUniqId);
        clientModel.setClientUniqId(newClientUniqId);

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

    public int updateClient(ClientModel clientModel) {

        int result = clientMapper.updateClient(clientModel);
        if(clientModel.getClientManagerModelList() != null)
        {
            for(ClientManagerModel clientManagerModel : clientModel.getClientManagerModelList())
            {
                if(clientManagerModel.getClientUniqId() == null)
                {
                    clientManagerModel.setClientUniqId(clientModel.getClientUniqId());
                }
                result += clientMapper.updateClientManager(clientManagerModel);
            }
        }
        return result;
    }

    public int deleteClient(String clientUniqId) {

        return clientMapper.deleteClient(clientUniqId);
    }
}
