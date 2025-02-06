package com.eric.cakemall.dto;

public class CakeIllustrateDTO {

    private Integer cakeIllustrateId;
    private String cakeImgUrl;

    public CakeIllustrateDTO() {}

    public CakeIllustrateDTO(Integer cakeIllustrateId, String cakeImgUrl) {
        this.cakeIllustrateId = cakeIllustrateId;
        this.cakeImgUrl = cakeImgUrl;
    }

    public Integer getCakeIllustrateId() {
        return cakeIllustrateId;
    }

    public void setCakeIllustrateId(Integer cakeIllustrateId) {
        this.cakeIllustrateId = cakeIllustrateId;
    }

    public String getCakeImgUrl() {
        return cakeImgUrl;
    }

    public void setCakeImgUrl(String cakeImgUrl) {
        this.cakeImgUrl = cakeImgUrl;
    }
}
