/*
 * 首页初始化相关JS
 */
$(function () {
    //异步请求，
    $.get("/wfms/workflow/data/menu.json", function (data, status) {
        //1.动态初始化左侧导航列表
        initSideMenu(data);
        //2.为左侧菜单节点绑定点击事件
        menuNodeClickEvent();
        /**
         * 初始化左侧菜单
         */
        function initSideMenu(data) {
            //var data = JSON.parse(data);
            var li = "<li class=''></li>"
            var a = "<a href='#' class='dropdown-toggle'></a>";
            var i = "<i class=''></i>";
            var span = "<span class='menu-text'></span>";
            var b_down = "<b class='arrow fa fa-angle-down'></b>";
            var b = "<b class='arrow'></b>";
            var ul = "<ul class='submenu childmenu'></ul>"
            for (var k in data) {//一级菜单
                var $span = $(span).html(data[k].name);
                var $i = $(i).attr("class", data[k].icon);
                var $a = $(a).attr("href", data[k].url);
                $a.append($i).append($span);
                var menu = $(li).append($a).append($(b));
                var s0 = initMenu(data[k], menu);
                $("#navmenu").append(s0);
            }
            /**
             * 初始化左侧菜单列表
             */
            function initMenu(ele, menu) {
                if (ele.children.length != 0) {
                    menu.children("a").first().append($(b_down));
                    menu.append($(ul));
                    menu.attr("class", "submenu-li");
                    for (var k in ele.children) {
                        var $i = $(i).attr("class", ele.children[k].icon);
                        var $a = $(a).attr("href", "#");
                        $a.append($i);
                        $a.append(ele.children[k].name);
                        //$a.text(ele.children[k].name);//.append($span).append($(b_down));
                        var $li = $(li).append($a).append($(b));
                        $li.attr("data-url", ele.children[k].url);
                        var s = initMenu(ele.children[k], $li);
                        menu.children("ul").last().append(s);
                    }
                    //console.log(menu.html());
                    return menu;
                } else {
                    return menu;
                }
            }
        }
    });
});

/**
 * 为左侧菜单节点绑定点击事件
 */
function menuNodeClickEvent() {
    $(".childmenu").children("li").not("[class=submenu-li]").click(function () {
        $(".childmenu").children("li").attr("class", "");
        $(this).attr("class", "active");
        var url = $(this).attr("data-url");
        var name = $(this).children("a").text();//.replace(/[ ]/g,"");
        var name_ = name.replace(/[ ]/g, "");
        if ($("#tabbar")[0]) {
            if ($("#tabbar").children("li").children("a[href=#" + name_ + "]").length > 0) {
                $("#tabbar").children("li").children("a[href=#" + name_ + "]").tab('show');
                return;
            }
            inittabli(name, name_, url);
        } else {
            var ul = "<ul id='tabbar' class='nav nav-tabs padding-12 tab-color-blue background-blue' ></ul>"
            $("#tabbable").css("display", "");
            //TODO 默认显示首页，tabbable不显示。当加载Tab页时，去除tabbable dispaly属性，初始化Tab页。 关闭所有
            //Tab时，应显示首页内容。
            $("#tabbable").prepend(ul);
            inittabli(name, name_, url);
        }

    });

}
/**
 * 初始化Tab页
 */
function inittabli(name, name_, url) {
    var li = "<li class='active' style='width: 120px; position: relative;' >" +
        "<a data-toggle='tab' href='#" + name_ + "' >" + name + "</a>" +
        "<span onclick='removeTab(this);'  style='display: block; z-index: 999;  position: absolute; right: 3px ;top: 3px; color: red;cursor:hand;' title='关闭' class='glyphicon glyphicon-remove'></span>" +
        "</li>"
    var tabdiv =
        "<div id='" + name_ + "' class='tab-pane active' style='height: 100%;'>" +
        "<iframe  src='" + url + "'style='width: 99%;height:100%;border: 0px;'  frameborder='no' border='0'></iframe>" +
        "</div>"

    $("#tabbar > li ").attr("class", "");
    $("#tabbar").append(li);
    $("#tab-content > div").attr("class", "tab-pane");
    $("#tab-content").append(tabdiv);
    $("#" + name_).children("iframe").eq(0).height(($(document).height() - $("#navbar").height()) * 0.9);
}
/**
 * 关闭标签页事件方法
 */
function removeTab(that) {
    $(that).parent("li").remove();
    $($(that).prev().attr("href")).remove();
    if ($("#tabbar li").length > 0) {
        $('#tabbar a:first').tab('show');
    } else {
        $("#tabbar").remove();
    }
}


