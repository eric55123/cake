package model;

import jakarta.persistence.*;

@Entity
@Table(name = "cake_illustrate")
public class CakeIllustrate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cake_illustrate")
    private  Integer cakeIllustrate;

    @Column(name = "cake_img_url")
    private String cakeImgUrl;

    public Integer getCakeIllustrate() {
        return cakeIllustrate;
    }

    public void setCakeIllustrate(Integer cakeIllustrate) {
        this.cakeIllustrate = cakeIllustrate;
    }

    public String getCakeImgUrl() {
        return cakeImgUrl;
    }

    public void setCakeImgUrl(String cakeImgUrl) {
        this.cakeImgUrl = cakeImgUrl;
    }
}