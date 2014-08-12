/*!
 * myPagination Jquery Pagination Plug-in Library v5.0
 * 
 * http://linapex.blog.163.com/blog/#m=0&t=1&c=fks_084071082080087067080095087095085094088065087081083066082
 *
 * Date: 2012/9/24 21:20
 */
(function($) {
		
        $.fn.myPagination = function(param) {
          return init(param,$(this));
        };
		
		// opts 代表所有参数
		// obj 代表当前对象
		// init 方法 采用闭包概念
		function init(param,obj){
			
			//默认配置
			var defaults = {
              currPage: 1,
              pageCount: 10,
              pageNumber: 5,
              cssStyle: 'badoo',
              ajax: {
                on: false,
				type : "POST",
                pageCountId: 'pageCount',
				url: "jsonTest.php",
                dataType: 'json',
                param: false,
				onClick:function(){
					return false;
				},
                ajaxStart: function() {
                  return false;
                },
				callback: function (data){
					return false;
				}
              },
              panel: {
                first: '首页',
                last: '尾页',
                next: '下一页',
                prev: '上一页',
                first_on: true,
                last_on: true,
                next_on: true,
                prev_on: true,
                links: '#',
                tipInfo_on: false,
                tipInfo: '<span>&nbsp;&nbsp;跳{currText}/{sumPage}页</span>',
                tipInfo_css: {
                  width: '22px'
                }
              }
            };
						
			//得到参数
			function getParam() {
				var param = "page="+opts.currPage;	//默认发送
				if (opts.ajax.param) {
					param += "&" + opts.ajax.param;
				}
				return param;
            }
			
			//检查输入页数
			function checkInputPage(val) {
			  var msg ;
              if (val < 1) {
                msg = "输入值不能小于1";
              }
              var patrn = /^[0-9]{1,8}$/;
              if (!patrn.exec(val)) {
                msg = "请输入正确的数字";
              }
              if (val > opts.pageCount) {
                msg = "输入值不能大于总页数";
              }
			  if(msg){
				  alert(msg);
				  return false;
			  }
              return true;
            }
			
			//得到面板提示信息
			function getPanelTipInfo() {
				var str = "";
				if(opts.panel.tipInfo_on){
					var input = "<input type='text' value='" + opts.currPage + "' >";
					//info内容 <span>&nbsp;&nbsp;跳{currText}/{sumPage}页</span>
					var info = opts.panel.tipInfo;
					info = info.replace("{currText}", input);
                	info = info.replace("{sumPage}", opts.pageCount);
					
					info = $(info);
					input = info.children(":text:first");
					var css = opts.panel.tipInfo_css;
					for (var temp in css) {
					  var val = eval("css." + temp);
					  input.css(temp, val);
					}
					str = info.html();
				}
				return str;
			}
			
			//开始请求
			function onRequest(){
				debug(opts.id);
				debug("ajax请求参数列表:");
				debug(getParam());
				if(opts.ajax.on){
					//开启了ajax请求有效
					opts.ajax.ajaxStart();
					$.ajax({
					  url: opts.ajax.url,
					  // 请求Url地址
					  type: opts.ajax.type,
					  // 提交数据方式
					  data: getParam(),
					  // 参数
					  contentType: "application/x-www-form-urlencoded;utf-8",
					  // 发送信息给服务器编码类型
					  async: true,
					  // 异步
					  cache: false,
					  // 不缓存
					  timeout: 60000,
					  // 超时时间
					  error: function() {
						alert("访问服务器超时，请重试，谢谢！");
					  },
					  success: function(data) {
						responseHandle(data);
						createPageBar();
					  }
					});
				}else{
					//var data = {t:1,q:2,pageCount:20};
					//opts.dataType = "json";
					//responseHandle(data);
					//var data = "<div id='d1'><span id='s1'>s</span><input type='hidden' id='pageCount' name='aas' value='20' /></div>";
					//opts.ajax.dataType = "html";
					
					//var data = '<?xml version="1.0" encoding="utf-8" ?> <books> <book title="藏地密码" imageurl="images/Tibet_Code.jpg"> <description> 这里是概况(www.jb51.net) </description> </book> <book title="剑桥雅思6"                          imageurl="images/ielts.jpg"> <description>                              这里是概况(www.jb51.net) </description> </book> <book title="Professional ASP.NET"                              imageurl="images/asp.jpg"> <description>                                这里是概况(www.jb51.net) </description> </book>                                 <pageCount>20</pageCount></books>';
					//opts.ajax.dataType = "xml";
					createPageBar();
				}
			}
			
			//返回处理
			function responseHandle(data){
				var pageCountId = opts.ajax.pageCountId;
				var resultPageCount = 1;	//设置为1
				switch (opts.ajax.dataType) {
                case "json":
					try{
						data = eval("(" + data + ")"); // 转换为对象
					}catch(err){
					}finally{
						resultPageCount = eval("data." + pageCountId);
					}
                  break;
                case "xml":
                  resultPageCount = $(data).find(pageCountId).text();
                  break;
                default:
				  resultPageCount = $(data).find(":hidden[id='"+pageCountId+"']").val();
                  break;
                }
				debug(opts.id);
				debug("返回总页数:"+resultPageCount);
				opts.pageCount = resultPageCount;
				opts.ajax.callback(data);
			}
			
			
			
			 // 得到页码操作

            function createPageBar() {
			  var links = opts.panel.links;
			  opts.currPage = opts.currPage > opts.pageCount ? opts.pageCount : opts.currPage;
			  var currPage = opts.currPage;
			  var pageCount = parseInt(opts.pageCount);
			  var tempPage = parseInt (opts.pageNumber / 2);
			  var pageNumber = opts.pageNumber;
			  
			  var str = "";
			  
			  //首页
              if (opts.panel.first_on) {
				str = "<a href='" + links + "' title='1'>" + opts.panel.first + "</a>";
              }
			  
			  // 上一页
			  if (opts.panel.prev_on) {
				  if (currPage == 1) {
					str += "<span class=\"disabled\" title=\"" + opts.panel.prev + "\">" + opts.panel.prev + " </span>";
				  }
			else {
					str += "<a href='" + links + "' title='" + (currPage - 1) + "'>" + opts.panel.prev + " </a>";
				  }
			  }
			  
			  // 开始页和最后页			  
              var firstPage = lastPage = 1;
              // 当前页 - 中间数 > 0，则开始页 = 当前页 - 中间数，否则 开始页 = 1
			  firstPage = currPage - tempPage > 0 ? firstPage = currPage - tempPage : 1;
			  
              // 开始页 + 显示页数 > 总页数，则 最后页 = 最后页 +1，开始页 = 最后页 - 显示页，否则 最后页 = 开始页 +
              // 显示页
              if (firstPage + pageNumber > pageCount) {
                lastPage = pageCount + 1;
                firstPage = lastPage - pageNumber;
              }
              else {
                lastPage = firstPage + pageNumber;
              }
			  
			  //防止开始页数低于1
              if (firstPage <= 0) {
                firstPage = 1;
              }
			  
              // 生成链接
			  // 这里的 firstPage 需要看成是 currPage ，每一页都是当前页，生成链接。
              for (firstPage; firstPage < lastPage; firstPage++) {
                if (firstPage == currPage) {
                  str += "<span class=\"current\" title=\"" + firstPage + "\">" + firstPage + "</span>";;
                }
                else {
                  str += "<a href='" + links + "' title='" + firstPage + "'>" + firstPage + "</a>";
                }
              }
			  
			  //下一页
			  if(opts.panel.next_on){
				  if (currPage == pageCount) {
					str += "<span class=\"disabled\" title=\"" + opts.panel.next + "\">" + opts.panel.next + " </span>";
				  }
				  else {
					str += "<a href='" + links + "' title='" + (currPage + 1) + "'>" + opts.panel.next + " </a>";
				  }
			  }
			  
			  //尾页
			  if(opts.panel.last_on){
				    str += "<a href='" + links + "' title='" + pageCount + "'>" + opts.panel.last + "</a>";
			  }
			  
			  str += getPanelTipInfo(); 
			  debug(opts.id);
			  debug("最终生成菜单：");
			  debug(str);
			  
			  obj.html(str);
			  
			  //文本框增加事件
              obj.children(":text").keypress(function(event) {
                var keycode = event.which;
                if (keycode == 13) {
                  var page = $(this).val();
                  //验证输入值
                  if (checkInputPage(page)) {
                    //回车事件时，去除其他 a 标签的 click 事件
                    obj.children("a").unbind("click"); // 去除 所有 a 标签 click
                    //再次绑定click 事件
                    obj.children("a").each(function() {
                      $(this).click(function() {
                        return false;
                      });
                    });
                    opts.currPage = page;
                    onRequest();	//发送请求
                  }
                }
              });
			  
			  // 增加事件
              obj.children("a").each(function(index,element) {
                //给与每个a 标签 增加 单击事件
                $(this).click(function() {
				  //得到当前页数，以 title 属性为容器存放
                  var page = parseInt(this.title);
				  page = page > 0 ? page : 1;
                  //单击某一个 a  标签时，去除其他 a 标签的 click 事件
                  obj.children("a").unbind("click"); // 去除 所有 a 标签 click
                  //再次绑定click 点击无效事件
                  obj.children("a").each(function() {
                    $(this).click(function() {
                      return false;
                    });
                  });
				  opts.currPage = page;
				  opts.ajax.onClick(page);
                  onRequest();	//发送请求
                  $(this).focus();
                  return false;
                }); // click
				
              });
            }
			
			//debug 调试
			function debug(str){
				$.fn.debug(str);
			}
			
		 	//目标对象拥有了所有源对象所拥有的特性，可理解为继承
		  	// true 为深度拷贝，将子对象进行合并
			var opts = $.extend(true,defaults, param);

			opts.id = obj.attr("id");	//得到id
			
			 // 添加样式
            obj.addClass(opts.cssStyle);
			//开始请求
			onRequest();
			
			
			var method = {};	//方法
			
			method.getPage = function(){
				  return this.currPage;
			  }
			  
			  method.onReload = function(){
				  debug("reload()");
				  onRequest();
			  }
			  
			  //指定加载数据
			  method.onLoad = function(param){
				  if(param && param instanceof Object ){
					  debug(param);
					  opts.currPage = 1;
					  opts.ajax.param = param.param;
					  onRequest();
				  }
			  }
			  
			  //跳转指定页面
			  method.jumpPage = function(page){
				  debug("jumpPage()");
				  page = page < 1 ? 1 : page;	//如果page小于1，就为1
				  page = page > opts.pageCount ? opts.pageCount : page;	//如果page大于pageCount则为pageCount
				  opts.currPage = page;
				  onRequest();
			  }
			
			return method;
		}
		
		$.fn.debug = function(str){
			if(window.console && window.console.log){
				console.log(str);
			}
		}
			
      })(jQuery);