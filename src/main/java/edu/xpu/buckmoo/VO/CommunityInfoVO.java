package edu.xpu.buckmoo.VO;

import lombok.Data;

@Data
public class CommunityInfoVO {
    private Integer id;

    private String name;

    private String school;

    private String icon;

    private String des;

    private String openid;

    private Integer member;

    private String memberStr;

    private Integer status;

    private String statusStr;
}
