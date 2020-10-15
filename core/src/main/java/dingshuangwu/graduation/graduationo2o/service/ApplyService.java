package dingshuangwu.graduation.graduationo2o.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dingshuangwu.graduation.graduationo2o.enums.InfoStatusEnum;
import dingshuangwu.graduation.graduationo2o.enums.TableEnum;
import dingshuangwu.graduation.graduationo2o.mapper.ApplyMapper;
import dingshuangwu.graduation.graduationo2o.mapper.PublicMapper;
import dingshuangwu.graduation.graduationo2o.pojo.ApplyVO;
import dingshuangwu.graduation.graduationo2o.pojo.CacheUserInfoVO;
import dingshuangwu.graduation.graduationo2o.pojo.RequestApplyAndPublishVO;
import dingshuangwu.graduation.graduationo2o.utils.DataUtils;
import dingshuangwu.graduation.graduationo2o.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplyService {
    @Autowired
    private ApplyMapper mapper;
    @Autowired
    private PublicMapper publicMapper;
    private String TABLENAME = TableEnum.APPLY.getValue();
    final private int PAGESIZE = 10;

    public PageInfo<ApplyVO> getAllApply(RequestApplyAndPublishVO condition) {
        PageInfo<ApplyVO> result_data;
        PageHelper.startPage(condition.getCurrentPage(), PAGESIZE);
        List<ApplyVO> list = mapper.getAllApply(condition, TABLENAME);
        result_data = new PageInfo<ApplyVO>(list);
        return result_data;
    }

    public PageInfo<ApplyVO> getMyApply(RequestApplyAndPublishVO condition) {
        PageInfo<ApplyVO> result_data;
        PageHelper.startPage(condition.getCurrentPage(), PAGESIZE);
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        List<ApplyVO> list = mapper.getMyApply(cacheUserInfo.getId(), TABLENAME);
        result_data = new PageInfo<ApplyVO>(list);
        return result_data;
    }

    @Transactional(rollbackFor = Exception.class)
    public int addMyApply(ApplyVO apply) {
        if ("".equals(apply.getCity()) || "".equals(apply.getJob())) {
            return -1;
        }
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        apply.setAuthorId(cacheUserInfo.getId());
        apply.setAuthorName(cacheUserInfo.getName());
        apply.setFlag(InfoStatusEnum.NORMAL.getValue());
        apply.setCreateDate(DateUtils.getFormatDate());
        return mapper.addMyApply(apply, TABLENAME);
    }

    public int deleteMyApply(String id) {
        String author_id = publicMapper.getAuthorIdById(id, TABLENAME);
        CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
        if (author_id != null && !"".equals(author_id) && author_id.equals(cacheUserInfo.getId())) {
            return publicMapper.setFlagById(id, InfoStatusEnum.DELETED.getValue(), TABLENAME);
        } else {
            return -1;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateMyApply(ApplyVO apply) {
        if ("".equals(apply.getCity()) || "".equals(apply.getJob())) {
            return -1;
        }
        if (publicMapper.setFlagById(apply.getId(), InfoStatusEnum.UPDATED.getValue(), TABLENAME) > 0) {
            CacheUserInfoVO cacheUserInfo = DataUtils.getUserCacheUserInfo();
            apply.setAuthorId(cacheUserInfo.getId());
            apply.setAuthorName(cacheUserInfo.getName());
            apply.setFlag(InfoStatusEnum.NORMAL.getValue());
            apply.setCreateDate(publicMapper.getCreateDateById(apply.getId(), TABLENAME));
            return mapper.updateMyApply(apply, TABLENAME);
        } else {
            return -1;
        }
    }
}