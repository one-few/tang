function deleteFans(username){
    $.ajax({
        type:"delete",
        url:"/fans/"+username,
        async:true,
        contentType: "application/json", //必须这样写
        dataType:"json",
        success:function (data) {
            alert(data.data)
            return data.data
        }
    })
}

function insertFans(username){
    $.ajax({
        type:"POST",
        url:"/fans/"+username,
        async:true,
        contentType: "application/json", //必须这样写
        dataType:"json",
        success:function (data) {
            alert(data.data)
            return data.data
        }
    })
}



$(function(){

    //搜索
    $("#searchBtn").click(function () {
        let sv = $("#searchVal").val();
        if (sv!=""){
            window.location.href="/so?s="+sv;
        }

    });



    function f() {
        if ($("#username").val()==undefined)
            return

        $.ajax({
            type:"GET",
            url:"/fans/"+$("#username").val(),
            contentType: "application/json", //必须这样写
            success:function (data) {
                if (data.data){
                    updateToGz();
                } else{
                    $("#follower").removeClass("hidden")
                }
            }
        })
    }

    function updateToGz(){
        $("#follower").removeClass("hidden")
        $("#follower").removeClass("btn-success")
        $("#follower").addClass("btn-default")
        $("#follower").html("已关注")
    }

    function updateToNotGz(){
        $("#follower").removeClass("hidden")
        $("#follower").removeClass("btn-default")
        $("#follower").addClass("btn-success")
        $("#follower").html("关注")
    }

});

function getQueryVariable(variable){
    let query = window.location.search.substring(1);
    let vars = query.split("&");
    for (let i=0;i<vars.length;i++) {
        let pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return null;
}

function UrlUpdateParams(url, name, value) {
    let r = url;
    if (r != null && r != 'undefined' && r != "") {
        value = encodeURIComponent(value);
        let reg = new RegExp("(^|)" + name + "=([^&]*)(|$)");
        let tmp = name + "=" + value;
        if (url.match(reg) != null) {
            r = url.replace(eval(reg), tmp);
        }
        else {
            if (url.match("[\?]")) {
                r = url + "&" + tmp;
            } else {
                r = url + "?" + tmp;
            }
        }
    }
    return r;
}

String.prototype.signMix= function() {
    if(arguments.length === 0) return this;
    let param = arguments[0], str= this;
    if(typeof(param) === 'object') {
        for(let key in param)
            str = str.replace(new RegExp("\\{" + key + "\\}", "g"), param[key]);
        return str;
    } else {
        for(let i = 0; i < arguments.length; i++)
            str = str.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
        return str;
    }
}

function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return;
    }
}

function changeURLArg(url,arg,arg_val){
    let pattern=arg+'=([^&]*)';
    let replaceText=arg+'='+arg_val;
    if(url.match(pattern)){
        let tmp='/('+ arg+'=)([^&]*)/gi';
        tmp=url.replace(eval(tmp),replaceText);
        return tmp;
    }else{
        if(url.match('[\?]')){
            return url+'&'+replaceText;
        }else{
            return url+'?'+replaceText;
        }
    }
}


new Vue({
    el: "#search",
    data: {
        searchValue:""
    },
    methods: {
        searchClick(){
            window.location.href = "/so?s=" + this.searchValue
        }
    }
})



new Vue({
    el: "#home",//绑定元素
    //所有数据都放在数据属性中
    data:{
        pageNum:1,
        show:false,
        blog:{
            list:[],
            isFirstPage:false,
            isLastPage:true,
        }
    }, methods: {
        page(){
            let search = getQueryVariable("s");
            this.show = false
            if (search !== null){
                this.search(search)
            }else{
                this.blogs()
            }
        },
        blogs(){
            let _this = this
            $.ajax({
                type:"GET",
                url:"/blog/blogs",
                contentType: "application/json", //必须这样写
                data:{
                    page:_this.pageNum
                },
                success(result) {
                    _this.blog = result.data
                    _this.show = true
                },error(){
                    alert("加载失败了？")
                }
            })
        },
        search(search){
            let _this = this
            $.ajax({
                type:"GET",
                url:"/blog/so",
                contentType: "application/json", //必须这样写
                data:{
                    page:_this.pageNum,
                    search:search
                },
                success:function (result) {
                    _this.blog = result.data
                    _this.show = true
                },error(){
                    alert("加载失败了？")
                }
            })
        }
    },mounted(){
        let page = getQueryVariable("page");
        if (page != null){
            this.pageNum = page
        }
        this.page()
    }
})