package com.emard.mybatch.batch;

import com.emard.mybatch.model.SysTrack;
import com.emard.mybatch.modelOracle.OracleSysTrack;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BankTransactionProcessor implements ItemProcessor <OracleSysTrack, SysTrack>{

    @Override
    public SysTrack process(OracleSysTrack item) throws Exception {
        //if(item.getMessage()==null)return null;
        SysTrack sysTrack = new SysTrack();
        sysTrack.setMessage(item.getMessage());
        sysTrack.setProgramme(item.getProgramme());
        sysTrack.setSequence(item.getSequence());
        sysTrack.setSessionId(item.getSessionId());
        return sysTrack;
    }
    
}
