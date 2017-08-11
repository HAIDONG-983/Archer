package com.ylink.wfms.utils;

import com.ylink.wfms.constant.WfmsEnum;
import com.ylink.wfms.vo.ProcessInstanceVo;
import com.ylink.wfms.vo.TaskVo;
import com.ylink.wfms.vo.TemplateVo;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by yukunpeng on 2017/7/13.
 */
@Component("ConvertUtil")
public class ConvertUtil implements ApplicationContextAware{

    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    public static  List<TemplateVo> convertToTemplateVo(List<Model> models) {
        List<TemplateVo> templateList = new ArrayList<TemplateVo>();
        for (Model model : models) {
            TemplateVo vo = new TemplateVo();
            vo.setTemplateId(model.getId());
            vo.setTemplateName(model.getName());
            vo.setCreateTime(model.getCreateTime());
            vo.setLastUpdateTime(model.getLastUpdateTime());
            vo.setIsDeploy(WfmsEnum.NOTDEPOLYED);
            vo.setVersion(model.getVersion());
            if (model.getDeploymentId() != null) {
                RepositoryService repositoryService = (RepositoryService) context.getBean("repositoryService");
                Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(model.getDeploymentId()).list().get(0);
                vo.setIsDeploy(WfmsEnum.DEPOLYED);
                vo.setDeployTime(deployment.getDeploymentTime());
            }
            templateList.add(vo);
        }
        return templateList;
    }

    public static List<ProcessInstanceVo> convertToInstances(List<HistoricProcessInstance> processInstances){
        List<ProcessInstanceVo> processInstanceVoList = new ArrayList<ProcessInstanceVo>(10);
        for (HistoricProcessInstance historicProcessInstance: processInstances) {
            ProcessInstanceVo processInstanceVo =new ProcessInstanceVo();
            processInstanceVo.setProcessInstanceId(historicProcessInstance.getId());
            processInstanceVo.setProcessDefinitionName(historicProcessInstance.getProcessDefinitionName());
            processInstanceVo.setProcessDefinitionVersion(historicProcessInstance.getProcessDefinitionVersion());
            processInstanceVo.setStartTime(historicProcessInstance.getStartTime());
            processInstanceVo.setEndTime(historicProcessInstance.getEndTime());
            processInstanceVo.setDescription(historicProcessInstance.getDescription());
            processInstanceVo.setProcessDefinitionId(historicProcessInstance.getProcessDefinitionId());
            processInstanceVoList.add(processInstanceVo);
        }
        return processInstanceVoList;
    }


    public static List<TaskVo> converToTaskInstances(List<HistoricTaskInstance> taskInstances){
        List<TaskVo> taskVos =new ArrayList<TaskVo>(10);
        HistoryService historyService = context.getBean(HistoryService.class);
        for (HistoricTaskInstance taskInstance:taskInstances) {
            TaskVo taskVo =new TaskVo();
            taskVo.setId(taskInstance.getId());
            taskVo.setName(taskInstance.getName());
            taskVo.setDescription(taskInstance.getDescription());
            taskVo.setCreateTime(taskInstance.getCreateTime());
            taskVo.setEndTime(taskInstance.getEndTime());
            taskVo.setPriority(taskInstance.getPriority());

            HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(taskInstance.getProcessInstanceId())
                    .singleResult();

            taskVo.setProcessName(processInstance.getProcessDefinitionName());
            taskVo.setProcessInstanceId(taskInstance.getProcessInstanceId());
            taskVos.add(taskVo);


        }
        return taskVos;

    }
}
