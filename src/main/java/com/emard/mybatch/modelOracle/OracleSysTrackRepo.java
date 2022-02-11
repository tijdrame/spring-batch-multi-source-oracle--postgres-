package com.emard.mybatch.modelOracle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OracleSysTrackRepo extends JpaRepository<OracleSysTrack, Long> {
    
}
