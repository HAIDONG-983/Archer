package com.ylink.wfms.web;

import com.ylink.wfms.vo.ActionResult;
import com.ylink.wfms.vo.ProcessInstanceVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static oracle.net.aso.C11.w;

/**
 * Created by yukunpeng on 2017/7/31.
 */
@Controller
public class ProcessInstanceHandler extends BaseHandler {

    @RequestMapping("go_processInstancePage.action")
    public String go_processInstancePage(){
        return "workflow/pages/process-instance";
    }


    @RequestMapping(value="startupProcess.action", method= RequestMethod.POST)
    @ResponseBody
    public ActionResult startupProcess(@RequestBody Map<String,Object> data) throws Exception {
        wfmsService.startupProcess((String) data.get("templateId"),(Map)data.get("variables"));
        return new ActionResult("流程启动成功","200");
    }

    @RequestMapping("queryInstances.action")
    @ResponseBody
    public ActionResult queryInstances(ProcessInstanceVo processInstanceVo){
        List<ProcessInstanceVo> instances = wfmsService.queryInstances(processInstanceVo);
        return new ActionResult(instances,"200",wfmsService.queryInstanceCount(processInstanceVo),processInstanceVo);
    }


    @RequestMapping("queryInstance.action")
    @ResponseBody
    public ActionResult<ProcessInstanceVo> queryInstance(ProcessInstanceVo processInstanceVo){
        ProcessInstanceVo processInstanceVo1 = wfmsService.queryInstance(processInstanceVo);
        return new ActionResult<ProcessInstanceVo>(processInstanceVo1);
    }

}
