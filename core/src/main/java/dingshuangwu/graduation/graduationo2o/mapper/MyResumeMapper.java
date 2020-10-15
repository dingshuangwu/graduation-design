package dingshuangwu.graduation.graduationo2o.mapper;

import dingshuangwu.graduation.graduationo2o.pojo.MyResume.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MyResumeMapper {
    MyResumeAllVO getMyResumeAll(@Param("id") String id, @Param("tableName") String tableName);

    MyResumeInfoVO getMyResumeInfo(@Param("id") String id, @Param("tableName") String tableName);

    int setMyResumeInfo(@Param("id") String id, @Param("map") MyResumeInfoVO myResumeInfo, @Param("tableName") String tableName);

    MyResumeExpertiseVO getMyResumeExpertise(@Param("id") String id, @Param("tableName") String tableName);

    int setMyResumeExpertise(@Param("id") String id, @Param("map") MyResumeExpertiseVO myResumeExpertise, @Param("tableName") String tableName);

    MyResumeExpectedWorkVO getMyResumeExpectedWork(@Param("id") String id, @Param("tableName") String tableName);

    int setMyResumeExpectedWork(@Param("id") String id, @Param("map") MyResumeExpectedWorkVO myResumeExpectedWork, @Param("tableName") String tableName);

    MyResumeWorkExperienceVO getMyResumeWorkExperience(@Param("id") String id, @Param("tableName") String tableName);

    int setMyResumeWorkExperience(@Param("id") String id, @Param("map") MyResumeWorkExperienceVO myResumeWorkExperience, @Param("tableName") String tableName);

    SomeBodyResumeVO getSomeBodyResumeById(@Param("id") String id, @Param("tableName") String tableName);
}
