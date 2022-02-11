package com.emard.mybatch.modelOracle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SYS_TRACK_MESSAGES")
@Data
@NoArgsConstructor
public class OracleSysTrack {
    //@Id
    @Column(name = "SESSION_ID")
    private Long sessionId;
    @Id
    private Long sequence;
    private String programme;
    private String message;
}
