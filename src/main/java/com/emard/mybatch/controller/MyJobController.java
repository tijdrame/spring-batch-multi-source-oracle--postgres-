package com.emard.mybatch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emard.mybatch.model.SysTrack;
import com.emard.mybatch.model.SysTrackRepo;
import com.emard.mybatch.modelOracle.OracleSysTrack;
import com.emard.mybatch.modelOracle.OracleSysTrackRepo;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Log4j2
public class MyJobController {
    
    private final  OracleSysTrackRepo repo;
    private final  SysTrackRepo sysTrackRepo;
    private final JobLauncher jobLauncher;
    private final Job job;
    

    @GetMapping("/test")
    public ResponseEntity<String> testBi() {
        List<OracleSysTrack> list = repo.findAll();
        for (OracleSysTrack it : list) {
            log.info("message= [{}]", it);
        }
        log.info("taile = [{}]", list.isEmpty()?"":list.size());
        SysTrack sysTrack = new SysTrack();
        sysTrack.setMessage(list.get(0).getMessage());
        sysTrack.setProgramme(list.get(0).getProgramme());
        sysTrack.setSequence(list.get(0).getSequence());
        sysTrack.setSessionId(list.get(0).getSessionId());
        sysTrackRepo.save(sysTrack);
        return ResponseEntity.ok(list.toString());
    }

    @GetMapping("/chargerMsg")
    public BatchStatus charger() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        Map<String, JobParameter> params = new HashMap<>();
        params.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(params);
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        while(jobExecution.isRunning()){
            log.info("Running ..........");
        }
        return jobExecution.getStatus();
    }
}
