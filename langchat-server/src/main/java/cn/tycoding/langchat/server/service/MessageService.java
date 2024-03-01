package cn.tycoding.langchat.server.service;

import cn.tycoding.langchat.server.entity.LcConversation;
import cn.tycoding.langchat.server.entity.LcMessage;
import cn.tycoding.langchat.server.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/4
 */
public interface MessageService extends IService<LcMessage> {

    /**
     * 获取会话列表
     */
    List<LcConversation> conversations();

    /**
     * 获取会话分页列表
     */
    IPage<LcConversation> conversationPages(LcConversation data, QueryPage queryPage);

    /**
     * 新增会话
     */
    LcConversation addConversation(LcConversation conversation);

    /**
     * 修改会话
     */
    void updateConversation(LcConversation conversation);

    /**
     * 删除会话
     */
    void delConversation(String conversationId);

    LcMessage addMessage(LcMessage message);

    void clearMessage(String conversationId);
}

