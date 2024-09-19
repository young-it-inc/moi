package com.youngit.office.api.schedule.mapstruct;

import com.youngit.office.api.schedule.dto.ScheduleDto;
import com.youngit.office.api.schedule.model.ScheduleModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapstruct {
    ScheduleModel toModel(ScheduleDto scheduleDto);
    ScheduleDto toDto(ScheduleModel scheduleModel);
}
