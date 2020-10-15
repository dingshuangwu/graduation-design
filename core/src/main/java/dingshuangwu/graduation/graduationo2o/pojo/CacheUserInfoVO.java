package dingshuangwu.graduation.graduationo2o.pojo;

import lombok.Data;

@Data
public class CacheUserInfoVO {
    private String token;
    private String name;
    private String id;
    private JurisdictionVO jurisdiction;
    private Boolean isRoot;
}
