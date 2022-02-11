package com.emard.mybatch.batch;

import java.util.List;

import com.emard.mybatch.model.SysTrack;
import com.emard.mybatch.model.SysTrackRepo;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//@AllArgsConstructor
@Component
//@Slf4j
public class OracleSysTrackItemWriter implements ItemWriter<SysTrack> {

    @Autowired
    private SysTrackRepo repo;


    @Override
    //@Transactional (transactionManager="sysTrackTransactionManager", readOnly = true, propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    public void write(List<? extends SysTrack> items) throws Exception {
        repo.saveAll(items);
    }
    
}
