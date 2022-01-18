package com.icp.worstfilmapi.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntervalAward {

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

}
