package dingshuangwu.graduation.graduationo2o.pojo;

import lombok.Data;

@Data
public class RealNameAuthenticationVO {
    private String id;
    private String name;
    private String identityCardFront;
    private String identityCardReverse;
    private String currentPhoto;
    private String applyDate;
    private String flag;
}
