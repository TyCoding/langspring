package cn.tycoding.langchat.server.mapper;

import cn.tycoding.langchat.server.entity.LcMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Mapper
public interface MessageMapper extends BaseMapper<LcMessage> {

}

