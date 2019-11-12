package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.MedewerkerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Medewerker} and its DTO {@link MedewerkerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedewerkerMapper extends EntityMapper<MedewerkerDTO, Medewerker> {



    default Medewerker fromId(Long id) {
        if (id == null) {
            return null;
        }
        Medewerker medewerker = new Medewerker();
        medewerker.setId(id);
        return medewerker;
    }
}
