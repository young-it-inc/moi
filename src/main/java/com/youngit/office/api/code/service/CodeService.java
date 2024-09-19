package com.youngit.office.api.code.service;

import com.youngit.office.api.code.mapper.CodeMapper;
import com.youngit.office.api.code.mapstruct.CodeMapstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CodeService {

    private final CodeMapper codeMapper;

    private final CodeMapstruct codeMapstruct;

    @Autowired
    CodeService(CodeMapper codeMapper, CodeMapstruct codeMapstruct) {
        this.codeMapper = codeMapper;
        this.codeMapstruct = codeMapstruct;
    }
}
