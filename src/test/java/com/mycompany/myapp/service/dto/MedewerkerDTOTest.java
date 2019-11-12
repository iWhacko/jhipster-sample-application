package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class MedewerkerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedewerkerDTO.class);
        MedewerkerDTO medewerkerDTO1 = new MedewerkerDTO();
        medewerkerDTO1.setId(1L);
        MedewerkerDTO medewerkerDTO2 = new MedewerkerDTO();
        assertThat(medewerkerDTO1).isNotEqualTo(medewerkerDTO2);
        medewerkerDTO2.setId(medewerkerDTO1.getId());
        assertThat(medewerkerDTO1).isEqualTo(medewerkerDTO2);
        medewerkerDTO2.setId(2L);
        assertThat(medewerkerDTO1).isNotEqualTo(medewerkerDTO2);
        medewerkerDTO1.setId(null);
        assertThat(medewerkerDTO1).isNotEqualTo(medewerkerDTO2);
    }
}
