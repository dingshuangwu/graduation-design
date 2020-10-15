package dingshuangwu.graduation.graduationo2o.service;

import dingshuangwu.graduation.graduationo2o.enums.TableEnum;
import dingshuangwu.graduation.graduationo2o.mapper.MyResumeMapper;
import dingshuangwu.graduation.graduationo2o.mapper.PublicMapper;
import dingshuangwu.graduation.graduationo2o.pojo.MyResume.*;
import dingshuangwu.graduation.graduationo2o.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyResumeService {

    @Autowired
    MyResumeMapper mapper;
    @Autowired
    PublicMapper publicMapper;
    final private String TABLENAME = TableEnum.MYRESUME.getValue();

    public MyResumeAllVO getMyResumeAll(String id) {
        MyResumeAllVO result = mapper.getMyResumeAll(id, TABLENAME);
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        result.setBirthday(DateUtils.tansformDate(result.getBirthday()));
        return result;
    }

    public MyResumeInfoVO getMyResumeInfo(String id) {
        MyResumeInfoVO result = mapper.getMyResumeInfo(id, TABLENAME);
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        result.setBirthday(DateUtils.tansformDate(result.getBirthday()));
        return result;
    }

    public int setMyResumeInfo(String id, MyResumeInfoVO myResumeInfo) {
        myResumeInfo.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setMyResumeInfo(id, myResumeInfo, TABLENAME);
    }

    public MyResumeExpertiseVO getMyResumeExpertise(String id) {
        MyResumeExpertiseVO result = mapper.getMyResumeExpertise(id, TABLENAME);
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public int setMyResumeExpertise(String id, MyResumeExpertiseVO myResumeExpertise) {
        myResumeExpertise.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setMyResumeExpertise(id, myResumeExpertise, TABLENAME);
    }

    public MyResumeExpectedWorkVO getMyResumeExpectedWork(String id) {
        MyResumeExpectedWorkVO result = mapper.getMyResumeExpectedWork(id, TABLENAME);
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public int setMyResumeExpectedWork(String id, MyResumeExpectedWorkVO myResumeExpectedWork) {
        myResumeExpectedWork.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setMyResumeExpectedWork(id, myResumeExpectedWork, TABLENAME);
    }

    public MyResumeWorkExperienceVO getMyResumeWorkExperience(String id) {
        MyResumeWorkExperienceVO result = mapper.getMyResumeWorkExperience(id, TABLENAME);
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        return result;
    }

    public int setMyResumeWorkExperience(String id, MyResumeWorkExperienceVO myResumeWorkExperience) {
        myResumeWorkExperience.setLastModifyDate(DateUtils.getFormatDate());
        return mapper.setMyResumeWorkExperience(id, myResumeWorkExperience, TABLENAME);
    }

    public SomeBodyResumeVO getSomeBodyResumeByUserName(String name) {
        String id = publicMapper.getIdByName(name, TableEnum.USER.getValue());
        SomeBodyResumeVO result = mapper.getSomeBodyResumeById(id, TABLENAME);
        result.setLastModifyDate(DateUtils.tansformDate(result.getLastModifyDate()));
        result.setBirthday(DateUtils.tansformDate(result.getBirthday()));
        result.setImageUrl(publicMapper.getImageUrlById(id, TableEnum.USERINFOMATION.getValue()));
        return result;
    }
}
