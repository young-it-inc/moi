package com.youngit.office.api.complaint.service;

import com.youngit.office.api.complaint.mapper.ComplaintMapper;
import com.youngit.office.api.complaint.mapstruct.ComplaintMapstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintMapper complaintMapper;

    private final ComplaintMapstruct complaintMapstruct;

    @Autowired
    public ComplaintServiceImpl (ComplaintMapper complaintMapper, ComplaintMapstruct complaintMapstruct) {
        this.complaintMapper = complaintMapper;
        this.complaintMapstruct = complaintMapstruct;
    }
}
