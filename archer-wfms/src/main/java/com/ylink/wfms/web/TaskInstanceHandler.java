package com.ylink.wfms.web;

import com.ylink.wfms.vo.ActionResult;
import com.ylink.wfms.vo.TaskVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by yukunpeng on 2017/8/7.
 */
@Controller
public class TaskInstanceHandler extends BaseHandler{

    @RequestMapping("goTaskInstancePage.action")
    public String goTaskInstancePage(){
        return "workflow/pages/task-instance";
    }


    @RequestMapping("queryTasks.action")
    @ResponseBody
    public ActionResult queryTasks(TaskVo taskVo){
        List list = wfmsService.queryTaskInstances(taskVo);
        return new ActionResult(list,"200",null,null);
    }

    @RequestMapping("completeTask.action")
    @ResponseBody
    public ActionResult completeTask(@RequestBody TaskVo taskVo){
        wfmsService.completeTask(taskVo);
        return new ActionResult("完成任务成功!","200");
    }


}
