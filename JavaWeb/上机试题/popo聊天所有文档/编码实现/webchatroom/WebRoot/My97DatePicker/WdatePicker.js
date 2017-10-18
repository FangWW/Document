/*****************************************************
 * My97 DatePicker Ver 3.0.1
 * BLOG: http://blog.csdn.net/my97/
 * MAIL: smallcarrot@163.com
 ****************************************************/
$position=0;
$dpPath='';
var $dp=null;if(!window.$){$ = function(){var elements = new Array();for (var i=0;i<arguments.length;i++){var element=arguments[i];
if (typeof element=='string'){element=document.getElementById(element);}if (arguments.length==1){return element;}elements.push(element);}return elements;}}
function $getClientWidthHeight(w){var win=w||window;var d = win.document;if(d.documentElement&& !(d.documentElement.scrollTop==undefined||d.documentElement.scrollTop==0)){return{'width':win.document.documentElement.clientWidth,'height':win.document.documentElement.clientHeight};}
else if(win.document.body){return{'width':win.document.body.clientWidth,'height':win.document.body.clientHeight};}}
function $getScroll(w){var win=w||window;var d = win.document;if(d.documentElement&& !(d.documentElement.scrollTop==undefined||d.documentElement.scrollTop==0)){return{'top':win.document.documentElement.scrollTop,'left':win.document.documentElement.scrollLeft};}
else if(win.document.body){return{'top':win.document.body.scrollTop,'left':win.document.body.scrollLeft};}}
function $getAbsM(topWin)
{if(topWin==null){topWin=top;}
var leftM=0;var topM=0;
var tempWin=window;
while(tempWin!=topWin){var ifs=tempWin.parent.document.getElementsByTagName('iframe');
for(var i=0;i<ifs.length;i++){try{if(ifs[i].contentWindow==tempWin.window){var rc=$getBoundingClientRect(ifs[i]);
leftM+=rc.left;
topM+=rc.top;
break;}}
catch(e){continue;}}
tempWin=tempWin.parent;}
return{'leftM':leftM,'topM':topM};}
function $getBoundingClientRect(obj){if(navigator.product=='Gecko'){var objWin=null;
var top=obj.offsetTop;
var left=obj.offsetLeft;
var right=obj.offsetWidth;
var bottom=obj.offsetHeight;
while(obj=obj.offsetParent){
top+=obj.offsetTop;left+=obj.offsetLeft;
if(obj.tagName.toLowerCase()=="body"){objWin=obj.ownerDocument.defaultView;}}
var theScroll=$getScroll(objWin);
left-=theScroll.left;
top-=theScroll.top;
right+=left;
bottom+=top;
return{'left':left,'top':top,'right':right,'bottom':bottom};}
else{return obj.getBoundingClientRect();}}
if(navigator.product=='Gecko')
{Window.prototype.attachEvent = function(sType,fHandler){var shortTypeName=sType.replace(/on/,"");
fHandler._ieEmuEventHandler=function(e){window.event=e;	return fHandler();	};
this.addEventListener(shortTypeName,fHandler._ieEmuEventHandler,false);};Document.prototype.attachEvent=function(sType,fHandler){var shortTypeName=sType.replace(/on/,"");fHandler._ieEmuEventHandler=function(e){window.event=e;return fHandler();};
this.addEventListener(shortTypeName,fHandler._ieEmuEventHandler,false);};
Document.prototype.createStyleSheet=function(cssPath){var head=document.getElementsByTagName('HEAD').item(0);
var style=document.createElement('link');
style.href=cssPath;
style.rel='stylesheet';
style.type='text/css';
head.appendChild(style);};
HTMLElement.prototype.insertAdjacentElement=function(where,parsedNode){switch(where){case"beforeBegin":
this.parentNode.insertBefore(parsedNode,this);
break;
case"afterBegin":
this.insertBefore(parsedNode,this.firstChild);
break;
case"beforeEnd":
this.appendChild(parsedNode);
break;
case"afterEnd":
if(this.nextSibling){this.parentNode.insertBefore(parsedNode,this.nextSibling);}
else{this.parentNode.appendChild(parsedNode);}
break;}};
HTMLIFrameElement.prototype.__defineGetter__("Document",function(){return this.contentDocument});}
function WdatePicker(el,dateFmt,showTime,skin,onPicked){this.win=window;
this.top=window;
while(this.top.parent.document!=this.top.document&&this.top.parent.document.getElementsByTagName("frameset").length==0){this.top=this.top.parent;}
if(navigator.product=='Gecko'){if(!this.top.HTMLElement.prototype.insertAdjacentElement){this.top.HTMLElement.prototype.insertAdjacentElement=HTMLElement.prototype.insertAdjacentElement;}
if(!this.top.Document.prototype.attachEvent){this.top.Document.prototype.attachEvent=Document.prototype.attachEvent;}if(!this.top.Window.prototype.attachEvent){this.top.Window.prototype.attachEvent=Window.prototype.attachEvent;}}
this.eCont=(typeof el=='string')?document.getElementById(el):el;
this.dateFmt=dateFmt;
this.showTime=showTime;
this.skin=skin;
if(this.top.document.dateDiv==null){if($dpPath==''){$dpPath=this._createDpPath();}
this.dd=this.top.document.createElement("DIV");
this.dd.style.cssText='position:absolute;z-index:19700;';
this.dd.obj=this;
this.dd.className="WdateDiv";
this.dd.innerHTML='<iframe src="'+$dpPath+'My97DatePicker.htm#97" frameborder="0" border="0" width="1px" height="1px" scrolling="no" ></iframe>';
this.top.document.body.insertAdjacentElement('afterBegin',this.dd);
this.top.document.dateDiv=this.dd;}
else{var td=this.top.document.dateDiv;
td.obj.win=this.win;
td.obj.eCont=this.eCont;
td.obj.dateFmt=this.dateFmt;
td.obj.showTime=this.showTime;
td.obj.skin=this.skin;
var dwin=td.childNodes[0].contentWindow;
var d=dwin.$d;
this.dd=this.top.document.dateDiv;
d.obj.init();
d.yDiv.style.display=d.mDiv.style.display='none';
d.navLeftImg.src='skin/'+d.obj.skin+"/navLeft.gif";
d.leftImg.src='skin/'+d.obj.skin+"/left.gif";
d.rightImg.src='skin/'+d.obj.skin+"/right.gif";
d.navRightImg.src='skin/'+d.obj.skin+"/navRight.gif";
d.mInput.setAttribute('realValue',d.obj.month);
d.mInput.value=dwin.dpcfg.aMonStr[d.obj.month-1];
d.yInput.value=d.obj.year;
if(this.showTime){d.tDiv.style.display='block';
d.hhInput.value=d.obj.hour;
d.mmInput.value=d.obj.minute;
d.ssInput.value=d.obj.sec;}
else{d.tDiv.style.display='none';}
this.dd.style.display='block';
d.obj._setOkInput();
d.obj._setShowAndHide();
d.obj.redraw();}
$dp=this.top.$dp=this.top.document.dateDiv;
var objxy=$getBoundingClientRect(this.eCont);
var mm=$getAbsM(this.top);
var currWinSize=$getClientWidthHeight(this.top);
var theScroll=$getScroll(this.top);
var ddTop=mm.topM+objxy.bottom;
var ddLeft=mm.leftM+objxy.left;
var ddHeight=Math.max(parseInt(this.dd.offsetHeight),(this.dateFmt&&this.dateFmt.indexOf('%D')>0)?180:30);
var ddWidth=Math.max(parseInt(this.dd.offsetWidth),180);
if(($position==2)||(($position==0)&&((ddTop+ddHeight<currWinSize.height)||(ddTop-ddHeight<ddHeight*0.8))))
{this.dd.style.top=(theScroll.top+ddTop+1)+'px';}
else{this.dd.style.top=(theScroll.top+ddTop-ddHeight-this.eCont.offsetHeight-3)+'px';}
this.dd.style.left=-1+theScroll.left+Math.min(ddLeft,currWinSize.width-ddWidth-5)+'px';
if(!this.win.document.hasDispose){this.win.document.attachEvent('onmousedown',disposeDatePicker);
this.win.document.hasDispose=true;}
if(!this.top.document.hasDispose){this.top.document.attachEvent('onmousedown',disposeDatePicker);
this.top.document.hasDispose=true;}
if(!this.win.document.hasUnload){this.win.attachEvent('onbeforeunload',function(){$dp.style.display='none';});
this.win.document.hasUnload=true;}}
WdatePicker.prototype._createDpPath=function(){var path='';
var i;
path=$dpjspath
if(path.substring(0,1)!="/"&&path.indexOf('://')==-1){var a=this.top.location.href;
var b=location.href;
var al='',bl='',bls='';
var j,s='';
for(i=0;i<Math.max(a.length,b.length);i++){if(a.charAt(i).toLowerCase()!=b.charAt(i).toLowerCase()){j=i;
while(a.charAt(j)!='/'){if(j==0){break;}j-=1;}
al=a.substring(j+1,a.length);
al=al.substring(0,al.lastIndexOf('/'));
bl=b.substring(j+1,b.length);
bl=bl.substring(0,bl.lastIndexOf('/'));
break;}}
if(al!=''){for(i=0;i<al.split('/').length;i++){s+="../";}}
if(bl!=''){s+=bl+'/';}
path=s+path;}
return path;};
function disposeDatePicker(){var src=null;
if(window.event){src=event.srcElement||event.target;}
if($dp!=undefined&&$dp.obj!=undefined&&$dp.style.display!='none'&&src!=$dp.obj.eCont)
{var d=$dp.childNodes[0].contentWindow.$d;
if(d.obj.eCont.value==''||d.obj._judgeCorrectDateTime(d.obj.eCont.value)){d.obj._markValue(true);
if(d.obj.eCont.value!=''){d.obj._initDate(d.obj.eCont.value,d.obj.dateFmt);
d.obj._setRealValue();}
else{d.obj.eCont.setAttribute("REALVALUE","");}}
else{d.obj._markValue(false);}
$dp.style.display='none';}}
var $dpjspath;
var scripts=document.getElementsByTagName("script");
for(i=0;i<scripts.length;i++){if(scripts[i].src.substring(scripts[i].src.length-14).toLowerCase()=='wdatepicker.js'){$dpjspath=scripts[i].src.substring(0,scripts[i].src.length-14);
break;}}
document.createStyleSheet($dpjspath+'skin/WdatePicker.css');
