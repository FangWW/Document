/**
 * Stip JavaScript v3.0
 * http://www.cnblogs.com/idche/
 * download by http://www.codefans.net
 * Copyright 2010, lujun
 * Date: 2011/01/21 14:39
 */
;;(function(win, namespace, undef){
	var D = {
		$:function(id){return document.getElementById(id);},
		gt:function(parent, nodeName){return  parent.getElementsByTagName(nodeName);},
		db:document.body,
		dd:document.documentElement,
		i:0, // 最外层DOM元素开始数
		mix:function(r, s, a){
			for(var i in s){
				r[i] = s[i];
			}
			return r;
		},
		html:"<div class=\"lj-tipsWrap lj-<%=kind%>\" id=\"tipsWrap-<%=r%>\">\
						<div class=\"lj-content\"></div>\
						<span class=\"lj-in lj-<%=p%>\"><span class=\"lj-in lj-span\"></span></span>\
						<a href=\"javascript:void(0)\" id=\"ljClose<%=r%>\" class=\"lj-close\">x</a>\
					</div>"
	}
	
	/* 可配置参数 */
	var defaultConfig = {
		prefix: 'JunLu', // 最外层DOM元素ID前缀
		p: 'right', // 默认方向
		kind: 'correct', // 类型 correct or error
		closeP: 'ljClose', // 关闭按钮前缀
		wrapP: 'tipsWrap-', //
		closeBtn: false, // 默认是否有关闭按钮
		time:null, // 默认显示时间 一直显示
		offset: null,
		content:function(){ return "Hello World";},//默认内容
		of: 15,
		rand: 0
	}
	/* 可配置参数 end */
	
	var TIP = function(id){
		if( !(this instanceof TIP)){
			return new TIP(id);
		}		
		this.elem = id ? typeof id == "string" ? D.$(id) : id : this;
		this.defaultConfig = D.mix({}, defaultConfig);
		this._config = {};
		this.clearTime = null;
		this.func = null;
		(D.db !== document.body) && this._init(); // 防止 D.db 对象加载失败
	}
	
	TIP.prototype = {
		// 显示
		show:function(json){
			
			var self = this, config = self._config,
				wrap, p, c, sp, type = Object.prototype.toString.call(json),
				content = self.defaultConfig.content;
			
			// 不传递参数 和不传递 .content 参数
			if( !json || (json && !json.content) ){
				json = D.mix(json || {}, {content: typeof content == "function" ? content.call(self.elem, self.elem) : content})
			}
			
			// 参数为 String 或者 Number
		    if(/String|Number/.test(type)){
				json = {content:json};
			}
			
			//参数为一个function
			if("[object Function]" == type){
				json = {content:json.call(self.elem, self.elem)};
			}
			
			D.mix(config, self.defaultConfig); // 调用默认参数配置
			D.mix(config, json); // 覆盖默认参数配置
	
			self._updateInfo();
			self.id && self.hide()
			
			wrap = self._append();
			
			D.gt(wrap, "DIV")[0].innerHTML = config.content ;
			p 	= self._pos.call(self, config.p, wrap.offsetWidth, wrap.offsetHeight);
			sp	= self._getScroll();


			wrap.style.top = p.top + sp.top + "px";
			wrap.style.left = p.left + sp.left + "px";
			
			self._winSizeCheck(wrap);
			if(config.time){
				self.clearTime = setTimeout(function(){self.hide(c)}, config.time);
			}
			return false;
		},
		// 隐藏Stip
		hide:function(){
			var self = this
			self.clearTime && clearTimeout(self.clearTime);
			self._clear(D.$(self.id));
		},
		
		_init:function(){
			D.mix(D,{dd:document.body, db:document.documentElement});
		},
		_clear:function(a){
			var config = this._config;
			a && a.parentNode && a.parentNode.removeChild(a);
			win.detachEvent ? win.detachEvent('onresize', this.func) : win.removeEventListener('resize', this.func, false);
		},
		
		// 更新位置大小信息
		_updateInfo:function(){
			var self = this, elem = self.elem, config = self._config;
			config.width	= elem.offsetWidth;
			config.height	= elem.offsetHeight;
			config.offset	= elem.getBoundingClientRect();
		},
		
		// 内部方法
		_append:function(){
			var self = this , config = self._config,
				r, x;
				
			r = config.rand = ++D.i
			x = document.createElement("DIV");
			x.id = config.prefix + r;
			self.id = x.id;
			
			x.innerHTML = D.html.replace("<%=p%>",config.p).replace(/<%=r%>/g, r).replace("<%=kind%>", config.kind);
			document.body.appendChild(x);
			
			if(config.closeBtn){ // 有关闭按钮
				var hide = function(){self.hide();}
				D.$(config.closeP + r).onclick = hide;
			}else{
				D.$(config.closeP + r).style.display = "none";
			}
			
			return D.$(config.wrapP + r);
		},
		// 内部方法
		_pos:function(p,w,h){
				var self = this, C = self._config;
				var a = {
					left	: function(w, h){return {"top":C.offset.top , "left":C.offset.left - w - C.of}},
					top		: function(w, h){return {"top":C.offset.top - h - C.of, "left":C.offset.left}},
					right	: function(w, h){return {"top":C.offset.top , "left":C.offset.left + C.width + C.of}},
					bottom	: function(w, h){return {"top":C.offset.top + C.height + C.of , "left":C.offset.left}}
				}
				
				return a[p](w,h);
		},
		// 内部方法
		_getScroll:function(){
			return {
				top: D.db.scrollTop + D.dd.scrollTop,
				left: D.db.scrollLeft + D.dd.scrollLeft
			}
		},
		// 内部方法
		_winSizeCheck:function(wrap){
			var self = this, config = self._config;
			self.func = function(){
				self._updateInfo();
				var p 	= self._pos.call(self, config.p, wrap.offsetWidth, wrap.offsetHeight);
				var sp	= self._getScroll();
				
				wrap.style.top = p.top + sp.top + "px";
				wrap.style.left = p.left + sp.left + "px";
			};
			win.attachEvent ? win.attachEvent('onresize', self.func) : win.addEventListener('resize', self.func, false);
		}
	}
	
	win[namespace] = TIP; // 声明命名空间
	win[namespace]['config'] = defaultConfig; // 静态默认配置
	
})(window, 'Stip');