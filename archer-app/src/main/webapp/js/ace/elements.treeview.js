/**
 <b>Treeview</b>. A wrapper for FuelUX treeview element.
 It's just a wrapper so you still need to include FuelUX treeview script first.
*/
(function($ , undefined) {

	$.fn.aceTree = $.fn.ace_tree = function(TreeOptions) {
		//树形结构数据源
		var remoteDataSource =function (options,callback){
			var $data = null
			if(!("text" in options) && !("type" in options)){
				$.ajax({
					type:"get",
					url:TreeOptions.url,
					cache:false,
					datatype:"json",
					success:function(data){
						//alert(JSON.stringify(data))
						callback({ data: data.pojoMap });
					},
					error:function(data){
						console.log(data);
					}
			});
				return;
			}
			else if("type" in options && options.type == "folder") {
				if("additionalParameters" in options && "children" in options.additionalParameters)
					$data = options.additionalParameters.children ;
				else $data = {}//no data
			}
			
			if($data != null)//this setTimeout is only for mimicking some random delay
				setTimeout(function(){callback({ data: $data });} , parseInt(Math.random() * 500) + 200);
			}
		
		var $options = {
			dataSource: remoteDataSource,
			'open-icon' : ace.vars['icon'] + 'fa fa-folder-open',
			'close-icon' : ace.vars['icon'] + 'fa fa-folder',
			'selectable' : true,
			'selected-icon' : ace.vars['icon'] + 'fa fa-check',
			'unselected-icon' : ace.vars['icon'] + 'fa fa-times',
			'loadingHTML': 'Loading...'
		}
	
		$options = $.extend({}, $options, TreeOptions)
		this.each(function() {
			var $this = $(this);
			$this.addClass('tree').attr('role', 'tree');
			$this.html(
			'<li class="tree-branch hide" data-template="treebranch" role="treeitem" aria-expanded="false">\
				<div class="tree-branch-header">\
					<span class="tree-branch-name">\
						<i class="icon-folder '+$options['close-icon']+'"></i>\
						<span class="tree-label"></span>\
					</span>\
				</div>\
				<ul class="tree-branch-children" role="group"></ul>\
				<div class="tree-loader" role="alert">'+$options['loadingHTML']+'</div>\
			</div>\
			<li class="tree-item hide" data-template="treeitem" role="treeitem">\
				<span class="tree-item-name">\
				  '+($options['unselected-icon'] == null ? '' : '<i class="icon-item '+$options['unselected-icon']+'"></i>')+'\
				  <span class="tree-label"></span>\
				</span>\
			</li>');
			
			$this.addClass($options['selectable'] == true ? 'tree-selectable' : 'tree-unselectable');
			
			$this.tree($options);
		});

		return this;
	}

})(window.jQuery);
