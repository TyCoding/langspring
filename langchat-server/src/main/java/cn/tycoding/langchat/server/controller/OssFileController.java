package cn.tycoding.langchat.server.controller;

import cn.tycoding.langchat.core.dto.TextR;
import cn.tycoding.langchat.core.utils.StreamEmitter;
import cn.tycoding.langchat.server.entity.LcOss;
import cn.tycoding.langchat.server.service.ClientFileService;
import cn.tycoding.langchat.server.service.OssService;
import cn.tycoding.langchat.server.utils.R;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@RequestMapping("/langchat/file")
@RestController
@AllArgsConstructor
public class OssFileController {

    private final ClientFileService clientFileService;
    private final OssService ossService;

    @GetMapping("/list")
    public R list() {
        List<LcOss> list = ossService.list(Wrappers.<LcOss>lambdaQuery()
//                .eq(LcOss::getUserId, ClientUtil.getClientId())
//                .eq(LcOss::getChannel, FileEnum.INPUT.getChannel())
                .orderByDesc(LcOss::getCreateTime)
        );
        return R.ok(list);
    }

    @PostMapping("/upload")
    public R upload(MultipartFile file) {
        LcOss oss = clientFileService.upload(file);
        return R.ok(oss);
    }

    @PostMapping("/chat")
    public SseEmitter chat(@RequestBody TextR req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        clientFileService.chat(req);
        return emitter.get();
    }

    @GetMapping("/task")
    public R task() {
//        int count = asyncFuture.getCount(ClientUtil.getClientId());
//        return R.ok(count);
        return R.ok();
    }

    @PutMapping
    public R update(@RequestBody LcOss data) {
        ossService.updateById(data);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        ossService.removeById(id);
        return R.ok();
    }
}
