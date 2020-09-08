package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.controller.results.Code;
import com.itheima.controller.results.Result;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.system.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result save(User user) {
        Boolean save = userService.save(user);
        if (!save) {
            throw new BusinessException("保存失败,请检查配置信息", Code.SAVE_ERROR);
        }
        return new Result(Code.SAVE_OK, "保存成功!");
    }

    @DeleteMapping("/{uuid}")
    public Result delete(@PathVariable Integer uuid) {
        Boolean delete = userService.delete(uuid);
        if (!delete) {
            throw new BusinessException("删除失败,原因未知", Code.DELETE_ERROR);
        }
        return new Result(Code.DELETE_OK, "删除成功!");
    }

    @PutMapping
    public Result update(User user) {
        Boolean update = userService.update(user);
        if (!update) {
            throw new BusinessException("更新失败,原因未知", Code.UPDATE_ERROR);
        }
        return new Result(Code.UPDATE_OK, "删除成功!");
    }

    @GetMapping("/{uuid}")
    public User get(@PathVariable Integer uuid) {
        User get = userService.get(uuid);
        if (null == get) {
            throw new BusinessException("查询失败,没有这条数据", Code.GET_ERROR);
        }
        return get;
    }

    @GetMapping("/{Page}/{size}")
    public PageInfo<User> getAll(@PathVariable int Page, @PathVariable int size) {
        PageInfo<User> getAll = userService.getAll(Page, size);
        if (null == getAll) {
            throw new BusinessException("查询失败,数据列表为空", Code.GET_ERROR);
        }
        return getAll;
    }

    @PostMapping("/login")
    public User login(String userName, String password) {
        User login = userService.login(userName, password);
        if (null == login) {
            throw new BusinessException("登录失败，帐号或密码错误", Code.GET_ERROR);
        }
        return login;
    }


    // 以下为另一种写法备份
    // 以下为另一种写法备份
    // 以下为另一种写法备份
    // 以下为另一种写法备份


//    @Autowired
//    private UserService userService;
//
//    @PostMapping
//    public Result save(User user){
//        boolean flag = userService.save(user);
//        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERROR);
//    }
//
//    @PutMapping
//    public Result update(User user){
//        boolean flag = userService.update(user);
//        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERROR);
//    }
//
//    @DeleteMapping("/{uuid}")
//    public Result delete(@PathVariable Integer uuid){
//        boolean flag = userService.delete(uuid);
//        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERROR);
//    }
//
//    @GetMapping("/{uuid}")
//    public Result get(@PathVariable Integer uuid){
//        User user = userService.get(uuid);
//        //模拟出现异常，使用条件控制，便于测试结果
//        if (uuid == 10 ) throw new BusinessException("查询出错啦，请重试！",Code.GET_ERROR);
//        return new Result(null != user ?Code.GET_OK: Code.GET_ERROR,user);
//    }
//
//    @GetMapping("/{page}/{size}")
//    public Result getAll(@PathVariable Integer page, @PathVariable Integer size){
//        PageInfo<User> all = userService.getAll(page, size);
//        return new Result(null != all ?Code.GET_OK: Code.GET_ERROR,all);
//    }
//
//    @PostMapping("/login")
//    public Result login(String userName,String password){
//        User user = userService.login(userName,password);
//        return new Result(null != user ?Code.GET_OK: Code.GET_ERROR,user);
//    }

}
