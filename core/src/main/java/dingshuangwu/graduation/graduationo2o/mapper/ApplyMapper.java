package dingshuangwu.graduation.graduationo2o.mapper;

import dingshuangwu.graduation.graduationo2o.pojo.ApplyVO;
import dingshuangwu.graduation.graduationo2o.pojo.RequestApplyAndPublishVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyMapper {
    List<ApplyVO> getAllApply(@Param("map") RequestApplyAndPublishVO condition, @Param("tableName") String tableName);

    List<ApplyVO> getMyApply(@Param("id") String id, @Param("tableName") String tableName);

    int addMyApply(@Param("map") ApplyVO apply, @Param("tableName") String tableName);

    int updateMyApply(@Param("map") ApplyVO apply, @Param("tableName") String tableName);
}
