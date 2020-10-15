package dingshuangwu.graduation.graduationo2o.mapper;

import dingshuangwu.graduation.graduationo2o.pojo.PublishVO;
import dingshuangwu.graduation.graduationo2o.pojo.RequestApplyAndPublishVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishMapper {
    List<PublishVO> getAllPublish(@Param("map") RequestApplyAndPublishVO condition, @Param("tableName") String tableName);

    List<PublishVO> getMyPublish(@Param("id") String id, @Param("tableName") String tableName);

    int addMyPublish(@Param("map") PublishVO apply, @Param("tableName") String tableName);

    int updateMyPublish(@Param("map") PublishVO apply, @Param("tableName") String tableName);
}
