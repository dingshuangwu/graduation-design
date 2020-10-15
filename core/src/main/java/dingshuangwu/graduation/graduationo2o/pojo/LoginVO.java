package dingshuangwu.graduation.graduationo2o.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@Data
public class LoginVO implements Serializable {
    /**
     * token串
     */
    private String token;
    private String name;
    private String imageUrl;
    private JurisdictionVO jurisdiction;
}
