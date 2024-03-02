package cn.tycoding.langchat.biz.controller;

import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.biz.dto.LcUserInfo;
import cn.tycoding.langchat.biz.entity.LcUser;
import cn.tycoding.langchat.biz.service.UserService;
import cn.tycoding.langchat.biz.utils.AuthUtil;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/langchat/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public R<LcUserInfo> info() {
        LcUserInfo userInfo = userService.info(AuthUtil.getUsername());
        userInfo.setPassword(null);
        return R.ok(userInfo);
    }

    @GetMapping("/checkName")
    public R<Boolean> checkName(LcUser user) {
        return R.ok(userService.checkName(user));
    }

    @GetMapping("/list")
    public R<List<LcUser>> list(LcUser user) {
        return R.ok(userService.list(user));
    }

    @GetMapping("/page")
    public R<Dict> page(LcUser user, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(userService.page(user, queryPage)));
    }

    @GetMapping("/{id}")
    public R<LcUser> findById(@PathVariable String id) {
        return R.ok(userService.findById(id));
    }

    @PostMapping
//    @PreAuthorize("@auth.hasAuth('upms:user:add')")
    public R<LcUser> add(@RequestBody LcUser user) {
        userService.add(user);
        return R.ok();
    }

    @PutMapping
//    @PreAuthorize("@auth.hasAuth('upms:user:update')")
    public R update(@RequestBody LcUser user) {
        userService.update(user);
        return R.ok();
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("@auth.hasAuth('upms:user:delete')")
    public R delete(@PathVariable String id) {
        LcUser user = userService.getById(id);
        if (user != null) {
            userService.delete(id, user.getUsername());
        }
        return R.ok();
    }

    @GetMapping("/reset")
//    @PreAuthorize("@auth.hasAuth('upms:user:reset')")
    public R reset(@RequestParam String id, String password) {
        LcUser user = userService.getById(id);
        if (user != null) {
            userService.reset(id, password, user.getUsername());
        }
        return R.ok();
    }
}
