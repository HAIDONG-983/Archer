<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@ include file="../common/common.jsp"%>
</head>
<body>
    <div class="row">
        <div class="col-xs-12">
            <button class="btn btn-sm btn-success" onclick='addVariable();'><i class="glyphicon glyphicon-plus"></i>
                新增流程变量
            </button>
            <button class="btn btn-sm btn-success" onclick='startUp();'><i class="glyphicon glyphicon-plus"></i>
                启动流程
            </button>
            <input id="templateid-input" value="" type="hidden"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <table id="grid-table"></table>
        </div>
    </div>


    <script type="text/javascript">
        jQuery(function($) {
            var grid_selector = "#grid-table";
            jQuery(grid_selector).jqGrid({
                height: 350,
                caption:"流程变量列表",
                colNames: ['', '流程变量名称', "流程变量值"],
                colModel: [
                    {name: 'option',width:30,
                        formatter:function (cellvalue, options, rowObject) {
                            var id=options.rowId;
                            $(document).on('click','#btn_'+id,{id:id},function (event) {
                                jQuery("#grid-table").delRowData(event.data.id);
                            });
                            return '<button class="btn btn-sm btn-danger" id="btn_'+id+'">'+
                                    '<i class="glyphicon glyphicon-trash" ></i></button>';
                        }
                    },
                    {name: 'variable_name',editable : true},
                    {name: 'variable_value',editable : true}
                ],

            });
        });
        //-----------------------------------------------------------------
        function addVariable() {
            // 选中行rowid
            //var rowId = $("#grid-table").jqGrid('getGridParam', 'selrow');
            // 选中行实际表示的位置
            //var ind = $("#grid-table").getInd(rowId);
            // 新插入行的位置
           // var newInd = ind + 1;
            $("#grid-table").addRow();
            var ids = jQuery("#grid-table").jqGrid('getDataIDs');
            jQuery("#grid-table").editRow(ids.pop());
            //$grid.jqGrid('editRow', rid, actop);
        }
        function startUp(){
            var o={};
            var ids=$("#grid-table").jqGrid("getDataIDs");
            $.each(ids,function (i,v) {
                $("#grid-table").jqGrid("saveRow",ids[i]);
                var rowdata=$("#grid-table").jqGrid("getRowData",ids[i]);
                o[rowdata.variable_name]=rowdata.variable_value;
            })

            //alert(JSON.stringify(o))
            var data={"templateId":$("#templateid-input").val(),
                     "variables":o
            };

            $.ajax({
                url:"startupProcess.action",
                data:JSON.stringify(data),
                type:"POST",
                contentType:'application/json;charset=utf-8',
                success:function (data) {
                    layer.alert("流程启动成功",function () {
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });

                }
            });
        }
    </script>

</body>
</html>
