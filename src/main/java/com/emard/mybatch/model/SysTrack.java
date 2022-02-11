package com.emard.mybatch.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class SysTrack {
    
    private Long sessionId;
    @Id
    private Long sequence;
    private String programme;
    private String message;
}
