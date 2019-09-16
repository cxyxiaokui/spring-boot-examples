//alert(window.location.href.split('#')[0]);/***用于获得当前连接url用**/
/***用户点击分享到微信圈后加载接口接口*******/
$.post("/weixin/initWXJSSDKConfigInfo",{"shareUrl":window.location.href.split('#')[0]},function(data,status){
    data=eval("("+data+")");
    wx.config({
        debug: false,
        appId: data.appid,
        timestamp:data.timestamp,
        nonceStr:data.nonceStr,
        signature:data.signature,
        jsApiList: [
            'checkJsApi',
            'onMenuShareTimeline',
            'onMenuShareAppMessage',
            'onMenuShareQQ',
            'onMenuShareWeibo',
            'onMenuShareQZone',
            'hideOptionMenu',
        ]
    });
    var shareTitle = $("#wx_share_span").data("shareTitle");
    if(!shareTitle){
    	shareTitle = $("title").html();
    }
    var shareImg = $("#wx_share_span").data("shareImg");
    if(!shareImg){
    	//shareImg = common.bp()+'/m_images/shareImg.jpg';
    }
    var shareLink = $("#wx_share_span").data("shareLink");
    if(!shareLink){
    	shareLink = window.location.href.split('#')[0];
    }
    var shareDesc = $("#wx_share_span").data("shareDesc");
    if(!shareDesc){
    	shareDesc = $("meta[name=description]").attr("content");
    }
    wx.ready(function(){
       // alert("准备分享");
        wx.onMenuShareTimeline({
            title : shareTitle, // 分享标题
            link : shareLink, // 分享链接
            imgUrl : shareImg, // 分享图标
            success : function() {
                // 用户确认分享后执行的回调函数
                //alert("分享成功");
            },
            cancel : function() {
                // 用户取消分享后执行的回调函数
                //alert("分享取消");
            }
        });
        //wx.hideOptionMenu();/***隐藏分享菜单****/
        wx.onMenuShareAppMessage({
        	title: shareTitle, // 分享标题
        	desc: shareDesc, // 分享描述
        	link: shareLink, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        	imgUrl: shareImg, // 分享图标
        	success: function () {
        	// 用户确认分享后执行的回调函数
        	},
        	cancel: function () {
        	// 用户取消分享后执行的回调函数
        	}
    	});
        wx.onMenuShareQQ({
        	title: shareTitle, // 分享标题
        	desc: shareDesc, // 分享描述
        	link: shareLink, // 分享链接
        	imgUrl: shareImg, // 分享图标
        	success: function () {
        	// 用户确认分享后执行的回调函数
        	},
        	cancel: function () {
        	// 用户取消分享后执行的回调函数
        	}
    	});
        wx.onMenuShareWeibo({
        	title: shareTitle, // 分享标题
        	desc: shareDesc, // 分享描述
        	link: shareLink, // 分享链接
        	imgUrl: shareImg, // 分享图标
        	success: function () {
        	// 用户确认分享后执行的回调函数
        	},
        	cancel: function () {
        	// 用户取消分享后执行的回调函数
        	}
    	});
        wx.onMenuShareQZone({
        	title: shareTitle, // 分享标题
        	desc: shareDesc, // 分享描述
        	link: shareLink, // 分享链接
        	imgUrl: shareImg, // 分享图标
        	success: function () {
        	// 用户确认分享后执行的回调函数
        	},
        	cancel: function () {
        	// 用户取消分享后执行的回调函数
        	}
    	});
    });
});
	
