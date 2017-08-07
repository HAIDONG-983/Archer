//序列化表单插件
(function($){
	$.fn.serializeForm = function(){
		var o ={};
		var array=this.serializeArray();
		$.each(array,function(i,n){//表单中的每一个input，select
			var name = n.name;
			var value = n.value;
			var nameArray=name.split(".");
			checkState(nameArray,o,value);
		});
		//alert(JSON.stringify(o));
		return o;
		function checkState(x,m,value){
			var e=x.shift();
			if(m[e]){
				checkState(x,m[e],value);
			}else{
				if(x.length==0){m[e]=value; return;}
				m[e]={};
				checkState(x,m[e],value);
			}
		};
	}
})(window.jQuery);