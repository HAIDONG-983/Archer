package com.ylink.wfms.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ylink.wfms.exception.WfmsException;
import com.ylink.wfms.utils.ConvertUtil;
import com.ylink.wfms.vo.ActionResult;
import com.ylink.wfms.vo.PageInfo;
import com.ylink.wfms.vo.TemplateVo;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ylink.wfms.utils.ConvertUtil.convertToTemplateVo;

/**
 * Created by yukunpeng on 2017/7/13.
 * 流程模板管理
 */
@Controller
public class ProcessTemplateHandler extends BaseHandler{

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("go_processTemplatePage.action")
    public String go_processTemplatePage(){
        return "workflow/pages/process-template";
    }

    /**
     * 查询流程模版列表，activiti区分model与processdef，
     * 此处为简化需求，返回结果为统一的vo.
     * 使用category区分各接入系统
     * @param modelName 流程模型名称
     * @param connectSystem 接入系统id
     * @return
     */
    @RequestMapping("queryTemplate.action")
    @ResponseBody
    public ActionResult queryTemplate(String modelName, String connectSystem, PageInfo pageInfo) {
        List<TemplateVo> templateVoList = wfmsService.queryTemplate(modelName, connectSystem, pageInfo);
        Long count = wfmsService.queryTemplateCount(modelName, connectSystem);
        return new ActionResult<TemplateVo>(templateVoList, "success", count, pageInfo);
    }

    /**
     * 创建流程模板
     * @param processname
     * @param request
     * @param response
     */
    @RequestMapping(value="createModel.action",method= RequestMethod.GET)
    public void createModel(String processname, HttpServletRequest request, HttpServletResponse response) throws Exception{
        // TODO: 2017/7/15  以接入系统ID作为模型的分类信息,从session中获取????
        String modelID = wfmsService.createTemplate(processname, "00001");
        response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelID);
    }

    /**
     * 删除模型
     * @param modelId
     * @return
     */
    @RequestMapping("deleteModel.action")
    @ResponseBody
    public ActionResult deleteModel(String modelId) throws WfmsException {
        wfmsService.deleteModel(modelId);
        return new ActionResult("删除成功","success");
    }

    /**
     * 部署流程
     * @param modelId
     * @return
     */
    @RequestMapping("deployModel.action")
    @ResponseBody
    public ActionResult deployModel(String modelId) throws Exception{
        wfmsService.deployModel(modelId);
        return new ActionResult("部署成功","success");
    }

    /**
     * 编辑流程模型
     * @param modelId
     * @return
     */
    @RequestMapping(value="edidModel.action")
    public String goEdit(String modelId) throws IOException {
        Model model = wfmsService.queryTemplateByModelId(modelId);
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(model.getMetaInfo());
//        jsonNode.
        JSONObject o = (JSONObject) JSON.parse(model.getMetaInfo());
        o.put("upload","edited");
        model.setVersion(model.getVersion()+1);
        model.setMetaInfo(JSON.toJSONString(o));
        wfmsService.saveModel(model);
        return "redirect:/modeler.html?modelId=" + modelId;
        //return "xxx";
    }

    /**
     * 流程图展示
     * @return
     */
    @RequestMapping("showImg.action")
    @ResponseBody
    public ActionResult showImg(String modelId,HttpServletRequest request) throws Exception{
        ServletContext servletContext = request.getSession().getServletContext();
        String contextPath = servletContext.getRealPath("/");
        String workFlowImgPath=contextPath+ File.separator+"workflow"+File.separator+"workflowimages"+File.separator;
        File file=new File(workFlowImgPath+modelId+".png");
        byte[] sourceByte = wfmsService.getSourceModel(modelId);
        FileUtils.writeByteArrayToFile(file, sourceByte);
        return new ActionResult().put("path",file.getName());
    }


    @RequestMapping("uploadBPMN.action")
    public String uploadBPMN(@RequestParam("bpmn") CommonsMultipartFile file) throws Exception{
        wfmsService.uploadBPMN(file);
        return "redirect:go_processTemplatePage.action";
    }

    @RequestMapping("queryUploadVersion.action")
    @ResponseBody
    public ActionResult queryUploadVersion(String modelId) throws IOException {
        String metaInfo = repositoryService.createModelQuery().modelId(modelId).list().get(0).getMetaInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(metaInfo);
        return new ActionResult().put("metaInfo",jsonNode);
    }

    @RequestMapping("export.action")
    public void export( String modelId, HttpServletResponse response){
        String type="bpmn";
        try {
            Model modelData = repositoryService.getModel(modelId);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());

            JsonNode editorNode = new ObjectMapper().readTree(modelEditorSource);
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);

            // 处理异常
            if (bpmnModel.getMainProcess() == null) {
                response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
                response.getOutputStream().println("no main process, can't export for type: " + type);
                response.flushBuffer();
                return;
            }

            String filename = "";
            byte[] exportBytes = null;

            String mainProcessId = bpmnModel.getMainProcess().getId();

            if (type.equals("bpmn")) {
                BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
                exportBytes = xmlConverter.convertToXML(bpmnModel);
                filename = mainProcessId + ".bpmn20.xml";
            } else if (type.equals("json")) {
                exportBytes = modelEditorSource;
                filename = mainProcessId + ".json";
            }
            ByteArrayInputStream in = new ByteArrayInputStream(exportBytes);
            IOUtils.copy(in, response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.flushBuffer();
        } catch (Exception e) {
            logger.error("导出model的xml文件失败:",e);
        }
    }

}
