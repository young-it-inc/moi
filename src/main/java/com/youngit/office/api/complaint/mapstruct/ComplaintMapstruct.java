package com.youngit.office.api.complaint.mapstruct;

import com.youngit.office.api.complaint.dto.ComplaintDto;
import com.youngit.office.api.complaint.model.ComplaintModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComplaintMapstruct {
    ComplaintModel toModel(ComplaintDto complaintDto);
    ComplaintDto toDto(ComplaintModel complaintModel);
}
