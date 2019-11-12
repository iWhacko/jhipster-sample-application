package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SetInfoMapperTest {

    private SetInfoMapper setInfoMapper;

    @BeforeEach
    public void setUp() {
        setInfoMapper = new SetInfoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(setInfoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(setInfoMapper.fromId(null)).isNull();
    }
}
