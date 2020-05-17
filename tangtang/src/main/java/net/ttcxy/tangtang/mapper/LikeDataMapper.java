package net.ttcxy.tangtang.mapper;

import java.util.List;
import net.ttcxy.tangtang.model.LikeData;
import net.ttcxy.tangtang.model.LikeDataExample;
import org.apache.ibatis.annotations.Param;

public interface LikeDataMapper {
    long countByExample(LikeDataExample example);

    int deleteByExample(LikeDataExample example);

    int insert(LikeData record);

    int insertSelective(LikeData record);

    List<LikeData> selectByExample(LikeDataExample example);

    int updateByExampleSelective(@Param("record") LikeData record, @Param("example") LikeDataExample example);

    int updateByExample(@Param("record") LikeData record, @Param("example") LikeDataExample example);
}