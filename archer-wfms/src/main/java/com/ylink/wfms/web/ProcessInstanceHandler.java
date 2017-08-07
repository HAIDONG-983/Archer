package com.ylink.wfms.web;

import com.ylink.wfms.vo.ActionResult;
import com.ylink.wfms.vo.ProcessInstanceVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by yukunpeng on 2017/7/31.
 */
@Controller
public class ProcessInstanceHandler extends BaseHandler {

    @RequestMapping("go_processInstancePage.action")
    public String go_processInstancePage(){
        return "workflow/pages/process-instance";
    }


    @RequestMapping("startupProcess.action")
    @ResponseBody
    public ActionResult startupProcess(@RequestParam("templateId") String modelId) throws Exception {
        wfmsService.startupProcess(modelId,null);
        return new ActionResult("流程启动成功","200");
    }

    @RequestMapping("queryInstances.action")
    @ResponseBody
    public ActionResult queryInstances(ProcessInstanceVo processInstanceVo){
        List<ProcessInstanceVo> instances = wfmsService.queryInstances(processInstanceVo);
        return new ActionResult(instances,"200",wfmsService.queryInstanceCount(processInstanceVo),processInstanceVo);
    }



}
