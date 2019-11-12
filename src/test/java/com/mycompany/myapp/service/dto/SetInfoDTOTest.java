package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SetInfoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SetInfoDTO.class);
        SetInfoDTO setInfoDTO1 = new SetInfoDTO();
        setInfoDTO1.setId(1L);
        SetInfoDTO setInfoDTO2 = new SetInfoDTO();
        assertThat(setInfoDTO1).isNotEqualTo(setInfoDTO2);
        setInfoDTO2.setId(setInfoDTO1.getId());
        assertThat(setInfoDTO1).isEqualTo(setInfoDTO2);
        setInfoDTO2.setId(2L);
        assertThat(setInfoDTO1).isNotEqualTo(setInfoDTO2);
        setInfoDTO1.setId(null);
        assertThat(setInfoDTO1).isNotEqualTo(setInfoDTO2);
    }
}
