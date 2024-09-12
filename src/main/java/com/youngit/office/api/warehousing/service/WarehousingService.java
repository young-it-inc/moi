package com.youngit.office.api.warehousing.service;

import com.youngit.office.api.warehousing.mapper.WarehousingMapper;
import com.youngit.office.api.warehousing.mapper.WarehousingMapstructMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WarehousingService {

    private final WarehousingMapper warehousingMapper;
    private final WarehousingMapstructMapper warehousingMapstructMapper;

    public WarehousingService(WarehousingMapper warehousingMapper, WarehousingMapstructMapper warehousingMapstructMapper) {
        this.warehousingMapper = warehousingMapper;
        this.warehousingMapstructMapper = warehousingMapstructMapper;
    }
}
