package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SetInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SetInfo.class);
        SetInfo setInfo1 = new SetInfo();
        setInfo1.setId(1L);
        SetInfo setInfo2 = new SetInfo();
        setInfo2.setId(setInfo1.getId());
        assertThat(setInfo1).isEqualTo(setInfo2);
        setInfo2.setId(2L);
        assertThat(setInfo1).isNotEqualTo(setInfo2);
        setInfo1.setId(null);
        assertThat(setInfo1).isNotEqualTo(setInfo2);
    }
}
