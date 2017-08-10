<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />

<meta name="description" content="Dynamic tables and grids using jqGrid plugin" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${ctx }/css/bootstrap.css" />
<link rel="stylesheet" href="${ctx }/css/font-awesome.css" />

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${ctx }/css/jquery-ui.css" />
<link rel="stylesheet" href="${ctx }/css/datepicker.css" />
<link rel="stylesheet" href="${ctx }/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx }/css/chosen.css" />
<link rel="stylesheet" href="${ctx }/css/datepicker.css" />
<link rel="stylesheet" href="${ctx }/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="${ctx }/css/daterangepicker.css" />
<link rel="stylesheet" href="${ctx }/css/bootstrap-datetimepicker.css" />
<!-- text fonts -->
<link rel="stylesheet" href="${ctx }/css/ace-fonts.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${ctx }/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
	<link rel="stylesheet" href="${ctx }/css/ace-part2.css" class="ace-main-stylesheet" />
<![endif]-->

<!--[if lte IE 9]>
  <link rel="stylesheet" href="${ctx }/css/ace-ie.css" />
<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="${ctx }/js/ace-extra.js"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
<script src="${ctx }/js/html5shiv.js"></script>
<script src="${ctx }/js/respond.js"></script>
<![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='${ctx }/js/jquery.js'>"+"<"+"/script>");
</script>
<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 	window.jQuery || document.write("<script src='${ctx }/js/jquery1x.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script src="${ctx }/js/bootstrap.js"></script>
<script src="${ctx }/js/jquery-ui.js"></script>
<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='${ctx }/js/jquery.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
	<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx }/js/jquery1x.js'>"+"<"+"/script>");
	</script>
<![endif]-->
<script src="${ctx }/js/bootstrap.js"></script>

<!-- page specific plugin scripts -->
<script src="${ctx }/js/bootbox.js"></script>
<script src="${ctx }/js/layer/layer.js"></script>

<script src="${ctx }/js/date-time-CN/common.js"></script>
<script src="${ctx }/js/date-time-CN/bootstrap-datepicker.js"></script>
<script src="${ctx }/js/date-time-CN/bootstrap-timepicker.js"></script>
<script src="${ctx }/js/date-time-CN/moment.js"></script>
<script src="${ctx }/js/date-time-CN/daterangepicker.zh-CN.js"></script>
<script src="${ctx }/js/date-time-CN/daterangepicker.js"></script>
<script src="${ctx }/js/date-time-CN/bootstrap-datetimepicker.js"></script>

<script src="${ctx }/js/jqGrid/i18n/grid.locale-cn.js"></script>
<script src="${ctx }/js/jqGrid/jquery.jqGrid.src.js"></script>
<script src="${ctx }/js/EnumConstants.js"></script>
<script src="${ctx }/js/main.js"></script>
<script type="text/javascript">
//resize to fit page size
/* $(window).on('resize.jqGrid', function () {
	$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
}) */
/**
 * 更改表格组件图标
 */
function updatePagerIcons(table) {
	var replacement = 
	{
		'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
		'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
		'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
		'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
}

function enableTooltips(table) {
	$('.navtable .ui-pg-button').tooltip({container:'body'});
	$(table).find('.ui-pg-div').tooltip({container:'body'});
}


$.extend($.jgrid,{
	style_edit_form:function(form){
		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
		buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
		buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')
		
		buttons = form.next().find('.navButton a');
		buttons.find('.ui-icon').hide();
		buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
		buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');
	}
});




</script>
<script type="text/javascript">
	//初始化表单中的select
	$(function(){
		$("select").each(function(){
			//selected='selected'
			var opt="<option  value=''>==请选择==</option>";
			$(this).append(opt);
			var that=$(this);
			if($(this).attr("data-url")){
				$.ajax($(this).attr("data-url"),{
					success:function (data) {
						for (var ele in data){
							var option=$(opt).attr("value",ele).html(data[ele]);
							that.append(option);
						}

					}
				});
			} else {

			}
			
//			var array=EnumConstants[$(this).attr("data-enum")];
//			if(array){
//				for(var i=0;i<array.length;i++){
//					var option=$(opt).attr("value",array[i][0]).html(array[i][0]+"-"+array[i][1]);
//					$(this).append(option);
//				}
//			}
		});



		$('#dateTimeRange').daterangepicker({
					applyClass : 'btn-sm btn-success',
					cancelClass : 'btn-sm btn-default',
					locale: {
						applyLabel: '确认',
						cancelLabel: '取消',
						fromLabel : '起始时间',
						toLabel : '结束时间',
						customRangeLabel : '自定义',
						firstDay : 1
					},
					ranges : {
						//'最近1小时': [moment().subtract('hours',1), moment()],
						'今日': [moment().startOf('day'), moment()],
						'昨日': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],
						'最近7日': [moment().subtract('days', 6), moment()],
						'最近30日': [moment().subtract('days', 29), moment()],
						'本月': [moment().startOf("month"),moment().endOf("month")],
						'上个月': [moment().subtract(1,"month").startOf("month"),moment().subtract(1,"month").endOf("month")]
					},
					opens : 'right',	// 日期选择框的弹出位置
					separator : ' 至 ',
					showWeekNumbers : true,		// 是否显示第几周


					timePicker: true,
					timePickerIncrement : 10,	// 时间的增量，单位为分钟
					timePicker12Hour : false,	// 是否使用12小时制来显示时间


					//maxDate : moment(),			// 最大时间
					format: 'YYYY-MM-DD hh:mm:ss'

				}, function(start, end, label) { // 格式化日期显示框
					$('#beginTime').val(start.format('YYYY-MM-DD hh:mm:ss'));
					$('#endTime').val(end.format('YYYY-MM-DD hh:mm:ss'));
				}).next().on('click', function(){
					$(this).prev().focus();
		});


	});
	//resize to fit page size
	/* $(window).on('resize.jqGrid', function () {
		$(".ui-jqgrid ui-widget .ui-widget-content .ui-corner-all").jqGrid('setGridWidth', $(".page-content").width() );
    }); */
	//trigger window resize to make the grid get the correct size

</script>



















