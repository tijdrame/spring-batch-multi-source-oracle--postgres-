package com.emard.mybatch.batch;

import java.util.Iterator;

import com.emard.mybatch.modelOracle.OracleSysTrack;
import com.emard.mybatch.modelOracle.OracleSysTrackRepo;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@AllArgsConstructor
@Component
public class OracleSysTrackItemReader implements ItemReader<OracleSysTrack> {

    //private DataSource dataSource;
    
    @Autowired
    private OracleSysTrackRepo respository;

    private Iterator<OracleSysTrack> sysTrackIterator;

    @BeforeStep
    public void before(StepExecution stepExecution) {
        sysTrackIterator = respository.findAll().iterator();
    }

    @Override
    public OracleSysTrack read()
            throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (sysTrackIterator != null && sysTrackIterator.hasNext()) {
            return sysTrackIterator.next();
        } else {
            return null;
        }
    }


}
