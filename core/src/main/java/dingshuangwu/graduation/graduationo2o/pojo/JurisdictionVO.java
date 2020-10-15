package dingshuangwu.graduation.graduationo2o.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@Data
public class JurisdictionVO implements Serializable {
    private String id;
    private String name;
    private Boolean management;
    private Boolean publish;
    private Boolean apply;
    private Boolean myPublish;
    private Boolean myApply;
    private Boolean realNameAuthentication;
    private Boolean applyForManagement;
    private Boolean login;
}
