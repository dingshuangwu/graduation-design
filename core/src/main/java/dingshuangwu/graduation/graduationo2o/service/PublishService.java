package dingshuangwu.graduation.graduationo2o.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dingshuangwu.graduation.graduationo2o.enums.InfoStatusEnum;
import dingshuangwu.graduation.graduationo2o.enums.TableEnum;
import dingshuangwu.graduation.graduationo2o.mapper.PublicMapper;
import dingshuangwu.graduation.graduationo2o.mapper.PublishMapper;
import dingshuangwu.graduation.graduationo2o.pojo.CacheUserInfoVO;
import dingshuangwu.graduation.graduationo2o.pojo.PublishVO;
import dingshuangwu.graduation.graduationo2o.pojo.RequestApplyAndPublishVO;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import dingshuangwu.graduation.graduationo2o.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublishService {
    @Autowired
    private PublishMapper mapper;
    @Autowired
    private PublicMapper publicMapper;
    private String TABLENAME = TableEnum.PUBLISH.getValue();
    final private int PAGESIZE = 10;

    public PageInfo<PublishVO> getAllPublish(RequestApplyAndPublishVO condition) {
        PageInfo<PublishVO> result_data;
        PageHelper.startPage(condition.getCurrentPage(), PAGESIZE);
        List<PublishVO> list = mapper.getAllPublish(condition, TABLENAME);
        result_data = new PageInfo<PublishVO>(list);
        return result_data;
    }

    public PageInfo<PublishVO> getMyPublish(RequestApplyAndPublishVO condition) {
        PageInfo<PublishVO> result_data;
        PageHelper.startPage(condition.getCurrentPage(), PAGESIZE);
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        List<PublishVO> list = mapper.getMyPublish(cacheUserInfo.getId(), TABLENAME);
        result_data = new PageInfo<PublishVO>(list);
        return result_data;
    }

    @Transactional(rollbackFor = Exception.class)
    public int addMyPublish(PublishVO publish) {
        if ("".equals(publish.getCity()) || "".equals(publish.getJob())) {
            return -1;
        }
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        publish.setAuthorId(cacheUserInfo.getId());
        publish.setAuthorName(cacheUserInfo.getName());
        publish.setFlag(InfoStatusEnum.NORMAL.getValue());
        publish.setCreateDate(DateUtils.getFormatDate());
        return mapper.addMyPublish(publish, TABLENAME);
    }

    public int deleteMyPublish(String id) {
        String author_id = publicMapper.getAuthorIdById(id, TABLENAME);
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (author_id != null && !"".equals(author_id) && author_id.equals(cacheUserInfo.getId())) {
            return publicMapper.setFlagById(id, InfoStatusEnum.DELETED.getValue(), TABLENAME);
        } else {
            return -1;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateMyPublish(PublishVO publish) {
        if ("".equals(publish.getCity()) || "".equals(publish.getJob())) {
            return -1;
        }
        if (publicMapper.setFlagById(publish.getId(), InfoStatusEnum.UPDATED.getValue(), TABLENAME) > 0) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            publish.setAuthorId(cacheUserInfo.getId());
            publish.setAuthorName(cacheUserInfo.getName());
            publish.setFlag(InfoStatusEnum.NORMAL.getValue());
            publish.setCreateDate(publicMapper.getCreateDateById(publish.getId(), TABLENAME));
            return mapper.updateMyPublish(publish, TABLENAME);
        } else {
            return -1;
        }
    }
}
