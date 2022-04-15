package com.aaop.everykid.dto;

import com.aaop.everykid.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardDto {

    private int B_ID;
    private String K_ID;
    private String T_ID;
    private String P_ID;
    private Date WRITE_DATE;
    private String WRITE_SUBJECT;
    private String TEXT;
    private int HITS;

    public BoardDto(int b_ID, String k_ID, String t_ID, String p_ID, Date WRITE_DATE, String WRITE_SUBJECT, String TEXT, int HITS) {
        B_ID = b_ID;
        K_ID = k_ID;
        T_ID = t_ID;
        P_ID = p_ID;
        this.WRITE_DATE = WRITE_DATE;
        this.WRITE_SUBJECT = WRITE_SUBJECT;
        this.TEXT = TEXT;
        this.HITS = HITS;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "B_ID=" + B_ID +
                ", K_ID='" + K_ID + '\'' +
                ", T_ID='" + T_ID + '\'' +
                ", P_ID='" + P_ID + '\'' +
                ", WRITE_DATE=" + WRITE_DATE +
                ", WRITE_SUBJECT='" + WRITE_SUBJECT + '\'' +
                ", TEXT='" + TEXT + '\'' +
                ", HITS=" + HITS +
                '}';
    }

    public Board toEntity() {
        return new Board(B_ID, K_ID, T_ID, P_ID, WRITE_DATE, WRITE_SUBJECT, TEXT, HITS);
    }
}

