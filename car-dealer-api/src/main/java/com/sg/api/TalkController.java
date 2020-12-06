package com.sg.api;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.BaseController;
import com.sg.bean.Content;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.bean.vo.TalkVo;
import com.sg.constant.SystemConstant;
import com.sg.exception.BusinessException;
import com.sg.service.ContentService;
import com.sg.service.TalkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论动态表 前端控制器
 * </p>
 *
 * @author sunpeng
 * @since 2020-12-06
 */
@Api(tags = "评论回复功能模块")
@RestController
@RequestMapping("/api/talk")
public class TalkController extends BaseController {
    @Autowired
    TalkService talkService;

    @Autowired
    ContentService contentService;

    @ApiOperation(value = "发表动态")
    @PostMapping("/saveTalk")
    public R saveCarInfo(@ApiParam(value = "动态信息", required = true) @RequestBody TalkVo talkVo) throws BusinessException {
        talkService.saveTalk(talkVo, "");
        return R.ok("");
    }

    @ApiOperation(value = "发布评论")
    @PostMapping("/saveContent")
    public R saveContent(@ApiParam(value = "评论信息", required = true) @RequestBody Content content) throws BusinessException {
        contentService.saveContent(content, "");
        return R.ok("");
    }

    @ApiOperation(value = "分页查询动态评论接口", notes = "分页查询动态评论接口")
    @GetMapping("/queryTalkContentList")
    public R selectPageList(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize) throws BusinessException {
        if (pageNum == null) {
            pageNum = SystemConstant.PAGE_NUM;
        }
        if (pageSize == null) {
            pageSize = SystemConstant.PAGE_SIZE;
        }
        return R.ok(talkService.queryTalkContentList(new Page<>(pageNum, pageSize)));
    }
}
