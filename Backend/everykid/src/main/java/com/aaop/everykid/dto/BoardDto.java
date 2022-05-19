package com.aaop.everykid.dto;

import com.aaop.everykid.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardDto {

    private Long B_KID;
    private Long K_KID;
    private String T_ID;
    private String P_ID;
    private Date WRITE_DATE;
    private String WRITE_SUBJECT;
    private String CONTENTS;
    private int HITS;

    public BoardDto(Long b_KID, Long k_KID, String t_ID, String p_ID, Date WRITE_DATE, String WRITE_SUBJECT, String CONTENTS, int HITS) {
        B_KID = b_KID;
        K_KID = k_KID;
        T_ID = t_ID;
        P_ID = p_ID;
        this.WRITE_DATE = WRITE_DATE;
        this.WRITE_SUBJECT = WRITE_SUBJECT;
        this.CONTENTS = CONTENTS;
        this.HITS = HITS;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "B_KID=" + B_KID +
                ", K_KID='" + K_KID + '\'' +
                ", T_ID='" + T_ID + '\'' +
                ", P_ID='" + P_ID + '\'' +
                ", WRITE_DATE=" + WRITE_DATE +
                ", WRITE_SUBJECT='" + WRITE_SUBJECT + '\'' +
                ", CONTENTS='" + CONTENTS + '\'' +
                ", HITS=" + HITS +
                '}';
    }

    public Board toEntity() {
        return new Board(B_KID, K_KID, T_ID, P_ID, WRITE_DATE, WRITE_SUBJECT, CONTENTS, HITS);
    }
}

