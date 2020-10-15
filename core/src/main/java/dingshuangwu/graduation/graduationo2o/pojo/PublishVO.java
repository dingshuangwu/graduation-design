package dingshuangwu.graduation.graduationo2o.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dingshuangwu
 * @date 2019-12-24
 */
@Data
public class PublishVO implements Serializable {
    private String authorId;
    private String authorName;
    private String id;
    private String provice;
    private String proviceName;
    private String city;
    private String cityName;
    private String jobType;
    private String jobTypeName;
    private String job;
    private String jobName;
    private String salary;
    private String contactWay;
    private String createDate;
    private String flag;
}
