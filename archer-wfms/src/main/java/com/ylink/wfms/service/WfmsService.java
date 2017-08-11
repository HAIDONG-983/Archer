package com.ylink.wfms.service;

import com.ylink.wfms.vo.PageInfo;
import com.ylink.wfms.vo.ProcessInstanceVo;
import com.ylink.wfms.vo.TaskVo;
import com.ylink.wfms.vo.TemplateVo;
import org.activiti.engine.repository.Model;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by yukunpeng on 2017/7/15.
 */
public interface WfmsService<T> {
    /**
     * 查询流程模板(模糊查询)
     *
     * @return
     */
     List<TemplateVo> queryTemplate(String modelName, String connectSystem, PageInfo pageInfo);

    /**
     * 根据流程模板id查询模型
     * @param modelId
     * @return
     */
    Model queryTemplateByModelId(String modelId);

    /**
     * 保存模型
     * @param model
     */
    void saveModel(Model model);

    /**
     * 查询满足查询条件的模板总记录数
     * @param modelName
     * @param connectSystem
     * @return
     */
    Long queryTemplateCount(String modelName, String connectSystem);

    /**
     * 新建流程模板
     * @param modelName
     * @param connectSystem
     * @return
     */
    String createTemplate(String modelName, String connectSystem) throws Exception;

    /**
     * 删除流程模板
     * @param modelId
     */
    void deleteModel(String modelId);

    /**
     * 部署流程模板
     * @param modelId
     */
    void deployModel(String modelId) throws Exception;

    /**
     * 获取流程图原始数据
     * @param modelId
     * @return
     */
    byte[] getSourceModel(String modelId);

    /**
     * 上传流程模型
     * @param file
     */
    void uploadBPMN(CommonsMultipartFile file) throws Exception;

    /**
     * 启动流程
     * @param modelId
     * @param variables
     * @throws Exception
     */
    void startupProcess(String modelId, Map variables) throws Exception;

    /**
     * 查询流程实例
     * @param processInstanceVo
     * @return
     */
    List<ProcessInstanceVo> queryInstances(ProcessInstanceVo processInstanceVo);

    /**
     * 查询流程实例数量
     * @param processInstanceVo
     * @return
     */
    Long queryInstanceCount(ProcessInstanceVo processInstanceVo);



    List<TaskVo> queryTaskInstances(TaskVo taskVo);

    void completeTask(TaskVo taskVo);


}
