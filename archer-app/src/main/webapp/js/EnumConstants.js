var EnumConstants = (function() {
	return{
		bizFileInfo_ConType : [['1','影像'],['2','视频'],['3','音频'],['0','其他']],
		bizFileInfo_FrbkMark : [['0','反面'],['1','正面']],
		bizFileInfo_ArchiveMark : [['0','未归档'],['1','已归档'],['2','归档失败']],
		bizFileVersion_ReqChannel : [['0','多媒体控件'],['1','客户端API'],['2','URL链接']],
		bizFileVersion_CurVersionMark : [['0','否'],['1','是']],
		cfgAccounts_State : [['1','启用'],['0','停用']],
		cfgAccounts_DeleteMark : [['0','已删除'],['1','正常']],
		cfgCategory_DeleteMark : [['0','已删除'],['1','正常']],
		cfgCenters_Type : [['01','总行中心'],['02','区域中心']],
		cfgCenters_MonitorMark : [['0','否'],['1','是']],
		cfgCenters_State : [['1','启用'],['0','停用']],
		cfgMetadataModel_State : [['1','启用'],['0','停用']],
		cfgMetaDetail_DataType : [['01','字符型'],['02','整型'],['03','浮点型'],['04','日期型']],
		cfgMetaDetail_State : [['0','新增未提交'],['1','新增已提交'],['2','修改未提交'],['3','修改已提交']],
		cfgOcrArea_CharaType : [['0','汉字'],['1','字母'],['2','数字'],['3','二维码'],['4','条形码'],['5','磁码'],['6','混合字符']],
		cfgOcrArea_PrepMethod : [['0','灰度'],['1','锐化']],
		cfgOcrArea_AreaType : [['0','凭证类型'],['1','数据属性']],
		cfgOcrTemplate_State : [['1','启用'],['0','停用']],
		cfgOcrTemplate_DeleteMark : [['0','已删除'],['1','正常']],
		cfgServers_ServerState : [['0','已停止'],['1','运行中']],
		cfgServers_State : [['1','启用'],['0','停用']],
		cfgSysRegistry_JoinMethod : [['0','总行接入'],['1','区域中心接入']],
		cfgSysRegistry_CateFlag : [['0','不启用'],['1','启用']],
		cfgSysRegistry_State : [['1','启用'],['0','停用']],
		cfgSysRegistry_DeleteMark : [['0','已删除'],['1','正常']],
		sysDataDict_State : [['1','启用'],['0','停用']],
		sysDataDict_DeleteMark : [['0','已删除'],['1','正常']],
		sysDictType_State : [['1','启用'],['0','停用']],
		sysDictType_DeleteMark : [['0','已删除'],['1','正常']],
		sysImgRights_Category : [['0','影像采集'],['1','影像展示'],['2','影像处理']],
		sysImgRights_State : [['1','启用'],['0','停用']],
		sysImgRights_DeleteMark : [['0','已删除'],['1','正常']],
		sysOrg_State : [['1','启用'],['0','停用']],
		sysOrg_DeleteMark : [['0','已删除'],['1','正常']],
		sysResource_State : [['1','启用'],['0','停用']],
		sysResource_DeleteMark : [['0','已删除'],['1','正常']],
		sysRole_State : [['1','启用'],['0','停用']],
		sysRole_DeleteMark : [['0','已删除'],['1','正常']],
		sysUser_State : [['1','启用'],['0','停用']],
		sysUser_DeleteMark : [['0','已删除'],['1','正常']],
		 transferEnumToOptions : function(enumObj, addNullOption, defaultValue) {
			var options = new Array;
			var seq = 0;
			if(addNullOption) {
				options[0] = {};
				options[0].showText = "";
				options[0].value = "";
				seq = 1;
			}
			if (enumObj && enumObj.length) {
				for ( var i = 0; i < enumObj.length; i++) {
					options[i + seq] = {};
					options[i + seq].showText = enumObj[i][0] + "-" + enumObj[i][1];
					options[i + seq].value = enumObj[i][0];
					if (defaultValue && defaultValue == options[i + seq].value) {
						options[i + seq].checked = 'true';
					}
				}
			}
			return options;
		},
		transferEnumToJson : function(enumObj) {
			var options = new Array;
			if (enumObj && enumObj.length) {
				for ( var i = 0; i < enumObj.length; i++) {
					options[i] = {};
					options[i].showText = enumObj[i][0] + "-" + enumObj[i][1];
					options[i].value = enumObj[i][0];
				}
			}
			return options;
		}

	};
})();