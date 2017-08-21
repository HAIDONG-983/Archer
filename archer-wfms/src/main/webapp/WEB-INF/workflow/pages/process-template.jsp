<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>影像权限管理</title>
    <%@ include file="../common/common.jsp"%>
</head>

<body class="no-skin" style="background-color:white;">
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
                <div class="widget-box widget-color-green2">
                    <div class="widget-header">
                        <h4 class="widget-title lighter smaller">流程模板信息查询</h4>
                    </div>
                    <div class="widget-body" >
                        <div class="widget-main ">
                            <form id="frm" class="form-group">
                                <div class ="row">
                                    <div class="col-xs-3">
                                        <label>流程模板名称</label>
                                        <input type="text" name="modelName"/>
                                    </div>
                                    <div class="col-xs-3">
                                        <label>接入系统名称</label>
                                        <select name="category" data-url="CfgSysRegistry/getCfgSysRegistryEnum.action"></select>
                                    </div>
                                    <%--TO-DO 日期控件未解决--%>
                                    <div class="col-xs-3">
                                        <%--<div class="input-group ">--%>
                                        <label>创建时间</label><span><i class="fa fa-calendar bigger-110"></i></span>
                                        <input class="date-picker" value="" type="text">
                                        <input name="beginTime" id="beginTime" value="" type="hidden">
                                        <input name="endTime" id="endTime" value="" type="hidden">
                                        <%--</div>--%>
                                    </div>
                                </div>
                            </form>
                            <div class="row">
                                <button class="btn btn-sm btn-success" onclick='queryModel();' style="position: relative;right: -1030px;"><i class="glyphicon glyphicon-search"></i>
                                    查询
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <button class="btn btn-sm btn-success" onclick='createModel();'><i class="glyphicon glyphicon-plus"></i>
                新增
            </button>

            <button class="btn btn-sm btn-success" onclick='deployModel();'><i class="glyphicon  glyphicon-time"></i>
                部署
            </button>
            <button class="btn btn-sm btn-success" onclick='editModel();'><i class="glyphicon  glyphicon-pencil"></i>
                编辑模板
            </button>
            <button class="btn btn-sm btn-success" onclick="uploadBPM();"><i class="glyphicon  glyphicon-upload"></i>
                上传流程模板
            </button>
            <button class="btn btn-sm btn-success" onclick="exportBPMN();"><i class="glyphicon  glyphicon-download"></i>
                下载流程模板
            </button>
            <button class="btn btn-sm btn-success" onclick="showImg();"><i class="glyphicon  glyphicon-picture"></i>
                查看流程图
            </button>
            <button class="btn btn-sm btn-success" onclick="showXML();"><i class="glyphicon  glyphicon-picture"></i>
                XML查看
            </button>
            <button class="btn btn-sm btn-danger" onclick='deleteModel();'><i class="glyphicon  glyphicon-trash"></i>
                删除
            </button>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <table id="grid-table"></table>
            <div id="grid-pager"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
    //初始化表格组件
    jQuery(function($) {
        var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";
        jQuery(grid_selector).jqGrid({
            url:"queryTemplate.action",
            height: 350,
            pager : pager_selector,
            colNames:['流程模板id','流程模板名称',"版本","是否部署","部署时间",'创建时间','最后修改时间 ','操作'],
            colModel:[
                {name:'templateId'},
                {name:'templateName',editable: true,addable: true,editrules:{required:true}},
                {name:'version',editable: true,addable: true,editrules:{required:true}},
                {name:'isDeploy.name'},
                {name:'deployTime',defultValue:'-'},
                {name:'createTime'},
                {name:'lastUpdateTime'},
                {name:'options',
                    formatter:function (cellvalue, options, rowObject) {
                        return "<button class='btn btn-sm btn-primary' onclick='startUp("+rowObject.templateId+");'><i class='glyphicon glyphicon-play-circle '></i>启动流程</button>";
                    }
//                    formatter:'actions',
//                    formatoptions:{ custome:true,
//                                    customeOptions:[
//                                        {   icon:"glyphicon glyphicon-play-circle green",
//                                            title:"启动流程",
//                                            callback:function (rowid) {
//                                                alert(JSON.stringify($(grid_selector).jqGrid("getRowData",rowid)));
//                                            }
//                                        }]
//                    }
                }
            ],
            loadComplete : function() {
                var table = this;
                setTimeout(function(){
                    updatePagerIcons(table);
                    enableTooltips(table);
                }, 0);
            }
        });
        //navGrid 初始化
        jQuery(grid_selector).jqGrid('navGrid',pager_selector,
                {
                    edit: true,
                    add: false,
                    del: true,
                    search: false,//搜索功能暂时不启用
                    refresh: true,
                    view: true,
                },
                {url:"sysImgRights-update.do"},
                {url:"sysImgRights!add.do"},
                {url:"sysImgRights!delete.do"},
                null,
                null
        );
    });
    
    
    
    //新建流程模板
    function createModel() {
        bootbox.prompt("请输入流程模板名称", function(result) {
            if (result === null) {

            } else {
                window.top.location="createModel.action?processname="+result;
            }
        });
    }
    //删除流程模板
    function deleteModel(){
        var templateId=$("#grid-table").jqGrid('getRowObject','templateId');
        if (!templateId)  {layer.alert("请选择流程模板记录!");return;}
        layer.confirm('确认删除流程模板？',
                {btn: ['确认','取消']},
                function(){
                    $.ajax("deleteModel.action",{
                        data:{"modelId":templateId},
                        success:function (data) {
                            layer.alert(data.message);
                            $("#grid-table").trigger("reloadGrid");
                        }
                    });
                },
                function(index){layer.close(index);});
    }
    
    //查询流程模板
    function queryModel() {
        $("#grid-table").jqGrid('setGridParam',{  // 重新加载数据
            url:'queryTemplate.action',
            postData:$("#frm").serializeForm()
        }).trigger("reloadGrid");
        return false;
    }
    //部署流程
    function deployModel() {
        var templateId = $("#grid-table").jqGrid('getRowObject', 'templateId');
        if (!templateId) {
            layer.alert("请选择流程模板记录!");
            return;
        }
        $.ajax("deployModel.action", {
            data: {"modelId": templateId},
            success: function (data) {
                if (data.status == "ERROR") {
                    layer.alert("部署失败:" + data.MESSAGE);
                    return;
                }
                layer.alert(data.message);
                $("#grid-table").trigger("reloadGrid");
            }
        });
    }
    //修改模型
    function editModel() {
        var templateId=$("#grid-table").jqGrid('getRowObject','templateId');
        if (!templateId)  {layer.alert("请选择流程模板记录!");return;}
        window.parent.location.href="edidModel.action?modelId="+templateId;
    }
    //展示流程图
    function showImg(){
        var templateId=$("#grid-table").jqGrid('getRowObject','templateId');
        if (!templateId)  {layer.alert('提示', {skin: 'layui-layer-lan', content:"请选择流程模板记录!"});return;}
        $.ajax("queryUploadVersion.action",{
            data:{"modelId":templateId},
            success:function (data) {
                //alert(JSON.stringify(data));
                if(data.model.metaInfo.upload=="1st") {layer.alert("此流程模板首次上传,请先使用流程编辑器打开查看");return;}
                $.ajax("showImg.action",{
                    data:{"modelId":templateId},
                    success:function (data) {
                        layer.alert('流程图', {
                            skin: 'layui-layer-lan',
                            area: ["1000px",'500px'],
                            content:'<img src="/wfms/workflow/workflowimages/'+data.model.path+'"/>',
                            closeBtn: 0
                        });
                    }
                });

            }
        });


    }
    //上传流程模型
    function uploadBPM(){
        var form =  "<form id='uploadFrom' role='form' action='uploadBPMN.action'  method='post' enctype='multipart/form-data'>"+
                        "<div class='form-group'>"+
                            "<label for='name'>请选择文件</label>"+
                            "<input type='file' class='form-control' id='bpmn'  name='bpmn' placeholder='请上传文件'>"+
                        "</div>"+
                    "</form>";
        layer.open({
            title:"上传流程图",
            skin: 'layui-layer-lan',
            content:form,
            yes: function(index, layero){
                $('#uploadFrom').submit();
                layer.close(index); //如果设定了yes回调，需进行手工关闭
            }
        });
    }

    //导出流程图
    function exportBPMN(){
        var templateId=$("#grid-table").jqGrid('getRowObject','templateId');
        if (!templateId)  {layer.alert("请选择流程模板记录!");return;}
        window.location.href="export.action?modelId="+templateId;
    }
    //启动流程
    function startUp(templateId) {

        $.ajax({
            url:"isDeploy.action",
            data:{modelId:templateId},
            success:function (data) {
                if (data.message==500){
                    layer.alert("此流程未部署,无法启动.");
                    return;
                }
                layer.open({
                    type: 2,
                    area: ['60%', '60%'],
                    content: 'go_setUpVariable.action',
                    success:function (layero, index) {
                        var body = layer.getChildFrame('body',index);
                        body.find("#templateid-input").val(templateId);
                    }
                });
            }

        });





//        $.ajax("startupProcess.action",
//                {data: {"templateId":templateId},
//                    success:function (data) {
//                        layer.alert("流程启动成功");
//                    }
//                }
//        )
    }

    function showXML(){
        var templateId=$("#grid-table").jqGrid('getRowObject','templateId');
        if (!templateId)  {layer.alert("请选择流程模板记录!");return;}
        layer.open({
            type: 2,
            title: '流程XML资源展示',
            shadeClose: true,
            shade: 0.8,
            skin: 'layui-layer-rim', //加上边框
            area: ['90%', '90%'],
            content: "showXML.action?templateId="+templateId //iframe的url
        });
    }




</script>

</body>

</html>
