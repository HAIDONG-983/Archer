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
                        <h4 class="widget-title lighter smaller">任务信息查询</h4>
                    </div>
                    <div class="widget-body" >
                        <div class="widget-main ">
                            <form id="frm" class="form-group">
                                <div class ="row">
                                    <div class="col-xs-3">
                                        <label>任务实例ID</label>
                                        <input type="text" name="ProcessInstanceVo.processInstanceId"/>
                                    </div>
                                    <div class="col-xs-3">
                                        <label>任务名称</label>
                                        <input type="text" name="ProcessInstanceVo.processDefinitionName"/>
                                    </div>
                                    <div class="col-xs-3">
                                        <label>接入系统名称</label>
                                        <select name="category" data-url="sysImgRights_Category.action"></select>
                                    </div>
                                    <%--TO-DO 日期控件未解决--%>
                                    <%--<div class="col-xs-4"> --%>
                                        <%--&lt;%&ndash;<div class="input-group ">&ndash;%&gt;--%>
                                        <%--<label>创建时间</label>--%>
                                        <%--<input class="form-control date-picker" id="dateTimeRange" value="" type="text">--%>
                                            <%--<span class="input-group-addon">--%>
                                                <%--<i class="fa fa-calendar bigger-110"></i>--%>
                                            <%--</span>--%>
                                        <%--<input name="beginTime" id="beginTime" value="" type="hidden">--%>
                                        <%--<input name="endTime" id="endTime" value="" type="hidden">--%>
                                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                    <%--</div>--%>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>

        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <button class="btn btn-sm btn-success" onclick='queryModel();'><i class="glyphicon glyphicon-search"></i>
                查询
            </button>
            <button class="btn btn-sm btn-success" onclick='traceProcess();'><i class="glyphicon  glyphicon-time"></i>
                流程跟踪
            </button>
        </div>
    </div>            </button>

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
            url:"queryTasks.action",
            height: 350,
            pager : pager_selector,
            colNames:['任务实例id','任务实例名称',"流程实例Id","任务开始时间","任务结束时间",'操作'],
            colModel:[
                {name:'id'},
                {name:'name'},
                {name:'processInstanceId'},
                {name:'createTime'},
                {name:'endTime'},
                {name:'options',
                    width:200,
                    formatter:"button",
                    formatoptions:{ buttonOptions:[
                        {   text:"查看流程变量",
                            class:"btn btn-sm btn-primary",
                            icon:"glyphicon glyphicon-play-circle ",
                            callback:function (rowObject) {
                                alert(JSON.stringify(rowObject));
                            }
                        },
                        {   text:"签收",
                            class:"btn btn-sm btn-success",
                            icon:"glyphicon glyphicon-play-circle ",
                            callback:function (rowObject) {
                                alert(JSON.stringify(rowObject));
                            }
                        }
                    ]}

//                    formatter:function (cellvalue, options, rowObject) {
//                        return "<button class='btn btn-sm btn-primary' onclick='traceProcess("+rowObject.processInstanceId+");'><i class='glyphicon glyphicon-play-circle '></i>查看流程变量</button>";
//                    }
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



    //跟踪流程
    function traceProcess(){
        var rowId=$("#grid-table").jqGrid("getGridParam",'selrow');
        var rowData = $("#grid-table").jqGrid('getRowData',rowId);
        if (Object.getOwnPropertyNames(rowData).length ==0){layer.alert("请选择记录!"); return;}
        var processDefinitionId = rowData.processDefinitionId;
        var processInstanceID =  rowData.processInstanceId;
        alert(processDefinitionId+" "+ processInstanceID);
        layer.open({
            type: 2,
            title: '流程跟踪',
            shadeClose: true,
            shade: 0.8,
            skin: 'layui-layer-rim', //加上边框
            area: ['90%', '90%'],
            //content: "traceProcess?processInstanceID="+window.processInstanceID //iframe的url
            content:"diagram-viewer/index.html?processDefinitionId="+processDefinitionId+"&processInstanceId="+processInstanceID
        });
    }

</script>

</body>

</html>
