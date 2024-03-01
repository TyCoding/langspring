package cn.tycoding.langchat.server.service;

import cn.tycoding.langchat.core.dto.TextR;
import cn.tycoding.langchat.server.entity.LcOss;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tycoding
 * @since 2024/1/4
 */
public interface ClientFileService {

    /**
     * 流式响应
     */
    void chat(TextR req);

    /**
     * 上传文件
     */
    LcOss upload(MultipartFile file);
}
