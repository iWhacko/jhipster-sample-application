package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.SetInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SetInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SetInfoRepository extends JpaRepository<SetInfo, Long> {

}
