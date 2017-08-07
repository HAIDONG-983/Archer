package com.ylink.wfms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ylink.wfms.exception.WfmsException;
import com.ylink.wfms.service.WfmsService;
import com.ylink.wfms.utils.ConvertUtil;
import com.ylink.wfms.vo.PageInfo;
import com.ylink.wfms.vo.ProcessInstanceVo;
import com.ylink.wfms.vo.TaskVo;
import com.ylink.wfms.vo.TemplateVo;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.util.io.InputStreamSource;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Map;

import static com.ylink.wfms.utils.ConvertUtil.convertToTemplateVo;

/**
 * Created by yukunpeng on 2017/7/15.
 */
@Component
public class WfmsServiceImpl implements WfmsService {

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    protected ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected HistoryService historyService;

    @Autowired
    protected TaskService taskService;

    public List<TemplateVo> queryTemplate(String modelName, String connectSystem, PageInfo pageInfo) {
        List<Model> models = getModelQuery(modelName, connectSystem).listPage(pageInfo.getStart(), pageInfo.getLimit());
        List<TemplateVo> templateVoList = ConvertUtil.convertToTemplateVo(models);
        return templateVoList;

    }


    public Long queryTemplateCount(String modelName, String connectSystem) {
        return getModelQuery(modelName, connectSystem).count();
    }

    public String createTemplate(String processname, String connectSystem) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode editorNode = objectMapper.createObjectNode();
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        ObjectNode modelObjectNode = objectMapper.createObjectNode();


        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        Model modelData = repositoryService.newModel();

        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processname);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        //modelObjectNode.put(ModelDataJsonConstants.MODEL_ID,modelData.getId());

        modelData.setMetaInfo(modelObjectNode.toString());
        modelData.setName(processname);
        modelData.setCategory(connectSystem);//暂时以分类区分接入系统

        repositoryService.saveModel(modelData);
        repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
        return modelData.getId();
    }

    public void deleteModel(String modelId) {
        repositoryService.deleteModel(modelId);
    }

    public void deployModel(String modelId) throws Exception{
        Model modelData = repositoryService.getModel(modelId);
        ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
        byte[] bpmnBytes = null;

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);
    }

    public byte[] getSourceModel(String modelId) {
        byte[] sourceExtra = repositoryService.getModelEditorSourceExtra(modelId);
        //byte[] sourceExtra =repositoryService.getModelEditorSource(modelId);
        return sourceExtra;
    }

    public Model queryTemplateByModelId(String modelId) {
        return repositoryService.createModelQuery().modelId(modelId).list().get(0);
    }

    public void saveModel(Model model) {
        repositoryService.saveModel(model);
    }

    public void uploadBPMN(CommonsMultipartFile file) throws Exception{
        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(new InputStreamSource(file.getInputStream()), false, false, processEngineConfiguration.getXmlEncoding());
        BpmnJsonConverter conver =new BpmnJsonConverter();
        ObjectNode convertToJson = conver.convertToJson(bpmnModel);
        Model model = repositoryService.newModel();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode modelObjectNode = objectMapper.createObjectNode();

        model.setName(bpmnModel.getMainProcess().getName());

        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, bpmnModel.getMainProcess().getName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelObjectNode.put("upload","1st");
        model.setMetaInfo(modelObjectNode.toString());
        repositoryService.saveModel(model);
        repositoryService.addModelEditorSource(model.getId(), convertToJson.toString().getBytes("utf-8"));

        //repositoryService.addModelEditorSourceExtra(model.getId(), convertToJson.toString().getBytes("utf-8"));

    }

    private ModelQuery getModelQuery(String modelName, String connectSystem){
        ModelQuery modelQuery = repositoryService.createModelQuery();
        if (StringUtils.isNotEmpty(modelName)) modelQuery.modelNameLike("%" + modelName + "%");
        if (StringUtils.isNotEmpty(connectSystem)) modelQuery.modelCategory(connectSystem);
        return modelQuery;
    }

    public void startupProcess(String modelId, Map variables) throws Exception {
        Model model = repositoryService.createModelQuery().modelId(modelId).singleResult();
        if (model.getDeploymentId()==null) throw new WfmsException("此流程模板未部署,无法启动!","0001");
        ProcessDefinition processDefinition = repositoryService .createProcessDefinitionQuery()
                                                                .deploymentId(model.getDeploymentId())
                                                                .singleResult();
        runtimeService.startProcessInstanceById(processDefinition.getId(),variables);
    }

    public List<ProcessInstanceVo> queryInstances(ProcessInstanceVo processInstanceVo) {
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().list();
        return ConvertUtil.convertToInstances(historicProcessInstances);
    }

    public Long queryInstanceCount(ProcessInstanceVo processInstanceVo) {
        return historyService.createHistoricProcessInstanceQuery().count();
    }


    public List<TaskVo> queryTaskInstances(TaskVo taskVo) {
        //taskService.createTaskQuery().
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().list();
        //historyService
        return ConvertUtil.converToTaskInstances(list);
    }
}

