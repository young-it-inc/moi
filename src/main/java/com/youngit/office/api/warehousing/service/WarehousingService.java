package com.youngit.office.api.warehousing.service;

import com.youngit.office.api.warehousing.mapper.WarehousingMapper;
import com.youngit.office.api.warehousing.mapstruct.WarehousingMapstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WarehousingService {

    private final WarehousingMapper warehousingMapper;
    private final WarehousingMapstruct warehousingMapstruct;

    public WarehousingService(WarehousingMapper warehousingMapper, WarehousingMapstruct warehousingMapstruct) {
        this.warehousingMapper = warehousingMapper;
        this.warehousingMapstruct = warehousingMapstruct;
    }
}
