package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MedewerkerMapperTest {

    private MedewerkerMapper medewerkerMapper;

    @BeforeEach
    public void setUp() {
        medewerkerMapper = new MedewerkerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(medewerkerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medewerkerMapper.fromId(null)).isNull();
    }
}
