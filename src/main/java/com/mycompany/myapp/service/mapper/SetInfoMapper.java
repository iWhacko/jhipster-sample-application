package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.SetInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SetInfo} and its DTO {@link SetInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {MedewerkerMapper.class})
public interface SetInfoMapper extends EntityMapper<SetInfoDTO, SetInfo> {

    @Mapping(source = "medewerker.id", target = "medewerkerId")
    SetInfoDTO toDto(SetInfo setInfo);

    @Mapping(source = "medewerkerId", target = "medewerker")
    SetInfo toEntity(SetInfoDTO setInfoDTO);

    default SetInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        SetInfo setInfo = new SetInfo();
        setInfo.setId(id);
        return setInfo;
    }
}
