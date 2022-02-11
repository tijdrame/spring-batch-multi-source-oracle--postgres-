package com.emard.mybatch.model;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

// @Transactional (propagation=Propagation.REQUIRED,readOnly = false)//(transactionManager="sysTrackTransactionManager", readOnly = true, propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
@Repository
public interface SysTrackRepo extends JpaRepository<SysTrack, Long> {
    
}
