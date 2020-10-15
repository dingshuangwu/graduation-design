package dingshuangwu.graduation.graduationo2o.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@Data
public class UserVO implements Serializable {
    private String id;
    private String name;
    private String password;
    private String realNameAuthentication;
    private String createDate;
    private String flag;
}
