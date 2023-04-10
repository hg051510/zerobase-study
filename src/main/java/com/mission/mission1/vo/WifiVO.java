package com.mission.mission1.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class WifiVO {
    private String x_swifi_mgr_no;
    private String x_swifi_wrdofc;
    private String x_swifi_main_nm;
    private String x_swifi_adres1;
    private String x_swifi_adres2;
    private String x_swifi_instl_floor;
    private String x_swifi_instl_ty;
    private String x_swifi_instl_mby;
    private String x_swifi_svc_se;
    private String x_swifi_cmcwr;
    private int x_swifi_cnstc_year;
    private String x_swifi_inout_door;
    private String x_swifi_remars3;
    private float lat;
    private float lnt;
    private String work_dttm;
}
