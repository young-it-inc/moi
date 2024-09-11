package com.youngit.office.api.warehousing.service;

import com.youngit.office.api.warehousing.mapper.WarehousingMapper;
import org.springframework.stereotype.Service;

@Service
public class WarehousingService {

    private final WarehousingMapper warehousingMapper;

    public WarehousingService(WarehousingMapper warehousingMapper) {
        this.warehousingMapper = warehousingMapper;
    }
}
