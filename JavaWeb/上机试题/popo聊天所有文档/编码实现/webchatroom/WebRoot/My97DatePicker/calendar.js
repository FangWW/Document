/*****************************************************
 * My97 DatePicker Ver 3.0.1
 * BLOG: http://blog.csdn.net/my97/
 * MAIL: smallcarrot@163.com
 ****************************************************/
if(navigator.product=='Gecko')
{Document.prototype.attachEvent=function(sType,fHandler){var shortTypeName=sType.replace(/on/,"");
fHandler._ieEmuEventHandler=function(e){window.event=e;return fHandler();};
this.addEventListener(shortTypeName,fHandler._ieEmuEventHandler,false);};
Event.prototype.__defineSetter__("returnValue",function(value){if(!value){this.preventDefault();}return value;});
Event.prototype.__defineGetter__("srcElement",function(){var node=this.target;
while(node.nodeType!=1){node=node.parentNode;}
return node;});
HTMLElement.prototype.attachEvent=function(sType,fHandler)
{var shortTypeName=sType.replace(/on/,"");
fHandler._ieEmuEventHandler=function(e){window.event=e;return fHandler();};
this.addEventListener(shortTypeName,fHandler._ieEmuEventHandler,false);};
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
break;}};}
$BindAsEventListener=function(sender,listener){var __method=sender;
return function(event){return __method.call(listener,event||window.event);}}
function __sb()
{this.s=new Array();
this.i=0;
this.a=function(t){this.s[this.i++]=t;};
this.j=function(){return this.s.join('');};}
if(!window.$){$ = function(){var elements = new Array();for (var i=0;i<arguments.length;i++){var element=arguments[i];
if (typeof element=='string'){element=document.getElementById(element);}if (arguments.length==1){return element;}elements.push(element);}return elements;}}
var $d=null;
function My97DatePicker(){if(parent==window)return;
if(navigator.product!='Gecko'){
if(location.href.substring(location.href.length-3).toLowerCase()=='#97'){location.replace(location.href.substring(0,location.href.length-3));
return;}
else{location.replace(location.href+"#97");}
history.go(-1);}
this.init();
this.dd=document.createElement("DIV");
this.dd.style.cssText='position:absolute;z-index:197;display:block;';
this.dd.obj=this;
this.dd.className="WdateDiv";
this.dd.innerHTML=this._createPickerHTML();
var tmpImgs=this.dd.getElementsByTagName('img');
this.dd.navLeftImg=tmpImgs[0];
this.dd.leftImg=tmpImgs[1];
this.dd.rightImg=tmpImgs[2];
this.dd.navRightImg=tmpImgs[3];
this.dd.navLeftImg.onclick=function(){$d.yInput.value=parseInt($d.yInput.value)-1;
$d.obj.redraw();}
this.dd.leftImg.onclick=function(){if(parseInt($d.mInput.getAttribute("realValue"))>1){$d.mInput.setAttribute("realValue",parseInt($d.mInput.getAttribute("realValue"))-1);}
else{$d.mInput.setAttribute("realValue",12);
$d.yInput.value=parseInt($d.yInput.value)-1;}
$d.mInput.value=dpcfg.aMonStr[parseInt($d.mInput.getAttribute("realValue"))-1];
$d.obj.redraw();}
this.dd.rightImg.onclick=function(){if(parseInt($d.mInput.getAttribute("realValue"))<12){$d.mInput.setAttribute("realValue",parseInt($d.mInput.getAttribute("realValue"))+1);}
else{$d.mInput.setAttribute("realValue",1);
$d.yInput.value=parseInt($d.yInput.value)+1;}
$d.mInput.value=dpcfg.aMonStr[parseInt($d.mInput.getAttribute("realValue"))-1];
$d.obj.redraw();}
this.dd.navRightImg.onclick=function(){$d.yInput.value=parseInt($d.yInput.value)+1;
$d.obj.redraw();}
var tmpInputs=this.dd.getElementsByTagName('input');
this.dd.mInput=tmpInputs[0];
this.dd.yInput=tmpInputs[1];
this.dd.mInput.setAttribute('realValue',this.month);
this.dd.mInput.value=dpcfg.aMonStr[this.month-1];
this.dd.yInput.value=this.year;
var tmpDivs=this.dd.getElementsByTagName('div');
this.dd.titleDiv=tmpDivs[0];
this.dd.mDiv=tmpDivs[3];
this.dd.yDiv=tmpDivs[5];
this.dd.dDiv=tmpDivs[7];
this.dd.tDiv=tmpDivs[8].firstChild;
this.dd.bDiv=tmpDivs[10];
this.dd.dDiv.innerHTML=this._createDateTable();
this._inputKeydown=function(){var evt=event;
var k=(evt.which==undefined)?evt.keyCode:evt.which;
if(!((k>=48&&k<=57)||(k>=96&&k<=105)||k==8||k==46||k==37||k==39||k==9)){evt.returnValue=false;}};
this.dd.mInput.attachEvent('onkeydown',function(){var evt=event;
var k=(evt.which==undefined)?evt.keyCode:evt.which;
if(k==9){$d.mDiv.style.display='none';}
if(event.srcElement.value.length-document.selection.createRange().text.length==2){event.returnValue=false;};});
this.dd.yInput.attachEvent('onkeydown',function(){var evt=event;
var k=(evt.which==undefined)?evt.keyCode:evt.which;
if(k==9){$d.yDiv.style.display='none';}});
this.dd.mInput.attachEvent('onkeydown',this._inputKeydown);
this.dd.yInput.attachEvent('onkeydown',this._inputKeydown);
this.dd.yInput.onblur=function(){var v=parseInt(this.value);
if(v<$d.obj.minDate.year){this.value=$d.obj.minDate.year;}
else if(v>$d.obj.maxDate.year){this.value=$d.obj.maxDate.year;}
if(v!=$d.obj.year){$d.obj.redraw();}
this.className='yminput';};
this.dd.mInput.onblur=function(){if(parseInt(this.value)>12){this.value='12';}
else if(v<1){this.value='1';}
var tmpYM=$d.obj.year*100+parseInt(this.value)*1;
if(tmpYM<($d.obj.minDate.year*100+$d.obj.minDate.month*1)){this.value=$d.obj.minDate.month;}
else if(tmpYM>($d.obj.maxDate.year*100+$d.obj.maxDate.month*1)){this.value=$d.obj.maxDate.month;}
var v=parseInt(this.value);
this.setAttribute('realValue',v);
this.value=dpcfg.aMonStr[v-1]
if(v!=$d.obj.month){$d.obj.redraw();}
this.className='yminput';};
this.dd.mInput.onfocus=function(){this.className='yminputfocus';
this.value=this.getAttribute("realValue");
this.select();
if($d.obj.hasDate){$d.obj._fillmonth();
$d.mDiv.style.display='block';}};
this.dd.yInput.onfocus=function(){this.className='yminputfocus';
this.select();
if($d.obj.hasDate){$d.obj._fillyear();
$d.yDiv.style.display='block';}};
this.dd.hhInput=tmpInputs[2];
this.dd.hmSplitInput=tmpInputs[3];
this.dd.mmInput=tmpInputs[4];
this.dd.msSplitInput=tmpInputs[5];
this.dd.ssInput=tmpInputs[6];
this.dd.clearInput=tmpInputs[7];
this.dd.todayInput=tmpInputs[8];
this.dd.okInput=tmpInputs[9];
this.dd.hhInput.onfocus=this.dd.mmInput.onfocus=this.dd.ssInput.onfocus=function(){this.select();$d.obj.currFocus=this;};
this.dd.hhInput.onblur=function(){if(parseInt(this.value)>23){this.value='23';}
else if(parseInt(this.value)<0){this.value='0';}};
this.dd.mmInput.onblur=this.dd.ssInput.onblur=function(){if(parseInt(this.value)>59){this.value='59';}
else if(parseInt(this.value)<0){this.value='0';}};
this.dd.hmSplitInput.attachEvent('onfocus',function(){$d.mmInput.focus();});
this.dd.msSplitInput.attachEvent('onfocus',function(){$d.ssInput.focus();});
this.dd.ssInput.attachEvent('onkeydown',function(){var evt=event;
var k=(evt.which==undefined)?evt.keyCode:evt.which;
if(k==9){$d.downButton.focus();}});
this.dd.hhInput.attachEvent('onkeydown',this._inputKeydown);
this.dd.mmInput.attachEvent('onkeydown',this._inputKeydown);
this.dd.ssInput.attachEvent('onkeydown',this._inputKeydown);
var tmpBtn=this.dd.getElementsByTagName('button');
this.dd.upButton=tmpBtn[0];
this.dd.downButton=tmpBtn[1];
this.dd.upButton.onclick=function(){if($d.obj.currFocus==undefined){$d.obj.currFocus=$d.mmInput;}
if(($d.obj.currFocus==$d.hhInput&&parseInt($d.obj.currFocus.value)<23)||($d.obj.currFocus!=$d.hhInput&&parseInt($d.obj.currFocus.value)<59)){$d.obj.currFocus.value=parseInt($d.obj.currFocus.value)+1;}
else{$d.obj.currFocus.value='0';}
$d.obj.currFocus.focus();};
this.dd.downButton.onclick=function(){if($d.obj.currFocus==undefined){$d.obj.currFocus=$d.mmInput;}
if(parseInt($d.obj.currFocus.value)>0){$d.obj.currFocus.value=parseInt($d.obj.currFocus.value)-1;}
else{if($d.obj.currFocus==$d.hhInput){$d.obj.currFocus.value='23';}
else{$d.obj.currFocus.value='59';}}
$d.obj.currFocus.focus();};
$d=this.dd;
document.body.insertAdjacentElement('beforeEnd',this.dd);
this._setOkInput();
this._setShowAndHide();}
My97DatePicker.prototype.init=function(){this._todayDate=new Date();
this.t_year=this._todayDate.getFullYear();
this.t_month=this._todayDate.getMonth()+1;
this.t_date=this._todayDate.getDate();
this.t_hour=this._todayDate.getHours();
this.t_minute=this._todayDate.getMinutes();
this.t_sec=this._todayDate.getSeconds();
var tmpdd=parent.$dp;
this.eCont=tmpdd.obj.eCont;
this.dateFmt=tmpdd.obj.dateFmt||dpcfg.dateFmt;
this.showTime=(tmpdd.obj.showTime==true)?tmpdd.obj.showTime:dpcfg.showTime;
this.skin=tmpdd.obj.skin||dpcfg.skin;
this.onPicked=new parent.$dp.obj.win.Function(this.eCont.getAttribute("ONPICKED"));
this.onPicked=$BindAsEventListener(this.onPicked,this.eCont);
this.hasYear=(this.dateFmt.indexOf('%Y')==-1)?false:true;
this.hasMonth=(this.dateFmt.indexOf('%M')==-1)?false:true;
this.hasDate=(this.dateFmt.indexOf('%D')==-1)?false:true;
this.hasHour=(this.dateFmt.indexOf('%h')==-1)?false:true;
this.hasMin=(this.dateFmt.indexOf('%m')==-1)?false:true;
this.hasSec=(this.dateFmt.indexOf('%s')==-1)?false:true;
this.minDate=this._doCustomDate(this.eCont.getAttribute("MINDATE"),dpcfg.realValueShortFmt,"min");
this.maxDate=this._doCustomDate(this.eCont.getAttribute("MAXDATE"),dpcfg.realValueShortFmt,"max");
if((this.minDate.year*10000+this.minDate.month*100+this.minDate.date*1)>(this.maxDate.year*10000+this.maxDate.month*100+this.maxDate.date*1)){alert('\u6700\u5C0F\u65E5\u671F\u4E0D\u80FD\u5927\u4E8E\u6700\u5927\u65E5\u671F(MinDate Cannot be bigger than MaxDate)!')
parent.$dp.obj.eCont.onfocus=function(){};
parent.$dp.obj.eCont.onclick=function(){alert('\u6700\u5C0F\u65E5\u671F\u4E0D\u80FD\u5927\u4E8E\u6700\u5927\u65E5\u671F(MinDate Cannot be bigger than MaxDate)!')};}
this._setActiveCSS();
this._initDate(this.eCont.value,this.dateFmt);
if(this.eCont.value!=''&&this.eCont.getAttribute("REALVALUE")==null&&this._judgeCorrectDateTime(this.eCont.value)){this._setRealValue();}
this.s_year=this.year;
this.s_month=this.month;
this.s_date=this.date;
this.eCont.getValue=function(){if(this.value==''){return'';}
else{return this.getAttribute("REALVALUE");}};};
My97DatePicker.prototype._setShowAndHide=function(){if(!(this.hasYear||this.hasMonth)){this.dd.titleDiv.style.display='none';}
else{this.dd.titleDiv.style.display='';}
if(!this.hasYear){this.dd.yInput.style.display=this.dd.navLeftImg.style.display=this.dd.navRightImg.style.display='none';}
else{this.dd.yInput.style.display=this.dd.navLeftImg.style.display=this.dd.navRightImg.style.display='';}
if(!this.hasMonth){this.dd.mInput.style.display=this.dd.leftImg.style.display=this.dd.rightImg.style.display='none';}
else{this.dd.mInput.style.display=this.dd.leftImg.style.display=this.dd.rightImg.style.display='';}
if(!this.hasDate){this.dd.dDiv.style.display='none';}
else{this.dd.dDiv.style.display='';}
if(!this.hasHour){this.dd.hhInput.disabled='disabled';}
else{this.dd.hhInput.disabled='';}
if(!this.hasMin){this.dd.mmInput.disabled='disabled';}
else{this.dd.mmInput.disabled='';}
if(!this.hasSec){this.dd.ssInput.disabled='disabled';}
else{this.dd.ssInput.disabled='';}}
My97DatePicker.prototype._setActiveCSS=function(){var i,a,main;
for(i=0;(a=document.getElementsByTagName("link")[i]);i++){if(a.getAttribute("rel").indexOf("style")!=-1&&a.getAttribute("title")){a.disabled=true;
if(a.getAttribute("title")==this.skin)a.disabled=false;}}};
My97DatePicker.prototype.redraw=function(){this.year=this.dd.yInput.value;
this.month=this.dd.mInput.getAttribute("realValue");
this.dd.dDiv.innerHTML=this._createDateTable();
autoSizeIframe();};
My97DatePicker.prototype._initDate=function(str,fmt)
{this.year=this.month=this.date=this.hour=this.minute=this.sec=-1;
var v=str.split(/\W+/);
var f=fmt.match(/%./g);
for(var i=0;i<f.length;i++){if(v[i]){if(f[i].toLowerCase()=='%y'){this.year=parseInt(v[i],10);
if(isNaN(this.year)){this.year=this.t_year;}}
else if(f[i]=='%M'){this.month=parseInt(v[i],10);
if(isNaN(this.month)){this.month=this.t_month;}}
else if(f[i].toLowerCase()=='%d'){this.date=parseInt(v[i],10);
if(isNaN(this.date)){this.date=this.t_date;}}
else if(f[i].toLowerCase()=='%h'){this.hour=parseInt(v[i],10);
if(isNaN(this.hour)){this.hour=this.t_hour;}}
else if(f[i]=='%m'){this.minute=parseInt(v[i],10);
if(isNaN(this.minute)){this.minute=this.t_minute;}}
else if(f[i].toLowerCase()=='%s'){this.sec=parseInt(v[i],10);
if(isNaN(this.sec)){this.sec=this.t_sec;}}}}
if(!this._isDate(this.year+'-'+this.month+'-'+this.date)){this.year=this.t_year;this.month=this.t_month;this.date=this.t_date;}
if((this.hour<0)||(this.hour>23)){this.hour=this.t_hour;}
if((this.minute<0)||(this.minute>59)){this.minute=this.t_minute;}
if((this.sec<0)||(this.sec>59)){this.sec=this.t_sec;}};
My97DatePicker.prototype._isDate=function(sDate){return sDate.match(/^((\d{2}(([02468][048])|([13579][26]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\s(((0?[0-9])|([1-2][0-3]))\:([0-5]?[0-9])((\s)|(\:([0-5]?[0-9])))))?$/);};
My97DatePicker.prototype._createPickerHTML=function()
{var s=new __sb();
s.a("<div id=dpTitle>");
s.a("<div style='float:left;margin:2px;width:45px'><img style='cursor:pointer;' src='skin/"+this.skin+"/navLeft.gif' width=14 height=16 /><img style='cursor:pointer;' src='skin/"+this.skin+"/left.gif' width=14 height=16 /></div>");
s.a("<div style='float:left;margin:2px;'><div style='margin-left:0px;width:62px' class='ymsel'></div><input class='yminput' style='margin-top:1px;width:30px;' maxlength=3></div>");
s.a("<div style='float:left;margin:2px'><div style='margin-left:0px' class='ymsel'></div><input class='yminput' style='margin-top:-1px;width:40px;' maxlength=4></div>");
s.a("<div style='float:right;margin:2px'><img style='cursor:pointer;' src='skin/"+this.skin+"/right.gif' width=14 height=16 /><img style='cursor:pointer;' src='skin/"+this.skin+"/navRight.gif' width=14 height=16 /></div></div>");
s.a("<div></div>");
s.a("<div>");
s.a(this._createTimeTable());
s.a("</div>");
return s.j();};
My97DatePicker.prototype._createDateTable=function(){var tempYear,tempMonth;
if((this.year*100+this.month*1)<(this.minDate.year*100+this.minDate.month*1)){this.dd.yInput.value=tempYear=this.minDate.year;
tempMonth=this.minDate.month;
this.dd.mInput.value=dpcfg.aMonStr[tempMonth-1];
this.dd.mInput.setAttribute('realValue',tempMonth);}
else if((this.year*100+this.month*1)>(this.maxDate.year*100+this.maxDate.month*1)){this.dd.yInput.value=tempYear=this.maxDate.year;
tempMonth=this.maxDate.month;
this.dd.mInput.value=dpcfg.aMonStr[tempMonth-1];
this.dd.mInput.setAttribute('realValue',tempMonth);}
else{tempYear=this.year;
tempMonth=this.month;}
var firstDay,firstDate,lastDay,lastDate;
var s=new __sb();
var i,j,k;
firstDay=new Date(tempYear,tempMonth-1,1).getDay();
firstDate=1-firstDay;
lastDay=new Date(tempYear,tempMonth,0).getDay();
lastDate=new Date(tempYear,tempMonth,0).getDate();
s.a("<table id=dpDayTable width=100% border=0 cellspacing=0 cellpadding=0>");
s.a("<tr id=dpWeekTitle align=center>");
var ss=new Array();
for(i=0;i<7;i++){s.a("<td>"+dpcfg.aWeekStr[i]+"</td>");}
var classStr='';
var chassOnStr='';
var isThisMonth=((tempYear==this.t_year)&&(tempMonth==this.t_month));
var isSelMonth=((tempYear==this.s_year)&&(tempMonth==this.s_month));
var needCompareMinDate=((tempYear*100+tempMonth*1)==(this.minDate.year*100+this.minDate.month));
var needCompareMaxDate=((tempYear*100+tempMonth*1)==(this.maxDate.year*100+this.maxDate.month));
var needCompareBoth=needCompareMinDate&&needCompareMaxDate;
var isValidDate=!needCompareMinDate&&!needCompareMaxDate;
for(i=1,j=firstDate;i<7;i++){s.a("<tr>");
for(k=0;k<7;k++){if(j>=1&&j<=lastDate){if(isSelMonth&&(j==this.s_date)){classStr='Wselday';}
else if(isThisMonth&&(j==this.t_date)){classStr='Wtoday';}
else{classStr=((dpcfg.highLineWeekDay&&(k==0||k==6))?'Wwday':'Wday');}
classOnStr=((dpcfg.highLineWeekDay&&(k==0||k==6))?'WwdayOn':'WdayOn');
s.a("<td align=center ");
if(isValidDate||(!needCompareBoth&&((needCompareMinDate&&j>=this.minDate.date)||(needCompareMaxDate&&j<=this.maxDate.date)))||(needCompareBoth&&(j>=this.minDate.date)&&(j<=this.maxDate.date))){s.a("onclick=\"$d.obj.pickDate(null,null,"+j+");\" ");
s.a("onmouseover=\"this.className='"+classOnStr+"'\" ");
s.a("onmouseout=\"this.className='"+classStr+"'\" ");}
else{classStr='WinvalidDay';}
s.a("class='"+classStr+"'");
s.a("><span>"+j+"</span>");}
else{s.a("<td><span></span>");}
j++;
s.a("</td>");}
s.a("</tr>");}
s.a("</table>");
return s.j();};
My97DatePicker.prototype._createTimeTable=function(){var s=new __sb();
s.a("<div id=dpTime style='"+((this.showTime)?'':'display:none;')+"float:left;margin-top:3px'><table cellspacing=0 cellpadding=0 border=0><tr><td rowspan=2><span id=dpTimeStr>"+dpcfg.timeStr+"</span>");
s.a(" <input class=tB maxlength=2 value="+this.hour+"><input value=':' class=tm readonly>");
s.a("<input class=tE maxlength=2 value="+this.minute+"><input value=':' class=tm readonly>");
s.a("<input class=tE maxlength=2 value="+this.sec+"></td><td>");
s.a("<button id=dpTimeUp></button></td></tr><tr><td><button id=dpTimeDown></button></td></tr></table></div>");
s.a("<div id=dpButton style='float:right;margin-top:3px;text-align:right;'>");
s.a("<input id=dpClearInput type=button value='"+dpcfg.clearStr+"'> <input id=dpTodayInput type=button value='"+dpcfg.todayStr+"'> <input id=dpOkInput type=button value='"+dpcfg.okStr+"'>");
s.a("</div>");
return s.j();};
My97DatePicker.prototype._fillmonth=function()
{var s=new __sb();
s.a("<table cellspacing=0 cellpadding=2 border=0>");
var i,n=0,v=parseInt(this.dd.mInput.getAttribute("realValue"));
var aMonStrT=new Array(12);
var aMonStrV=new Array(12);
for(i=0;i<11;i++){if(i+1==v){n=1;}
aMonStrT[i]=dpcfg.aMonStr[n+i];
aMonStrV[i]=n+i+1;}
this.year=parseInt(this.dd.yInput.value);
var needCompareMinMonth=this.year==this.minDate.year;
var needCompareMaxMonth=this.year==this.maxDate.year;
var needCompareBoth=needCompareMinMonth&&needCompareMaxMonth;
var validMonth=(this.year>this.minDate.year&&this.year<this.maxDate.year);
var isValidMonth;
for(i=0;i<6;i++){s.a("<tr><td ");
isValidMonth=(validMonth)||(!needCompareBoth&&((needCompareMinMonth&&aMonStrV[i]>=this.minDate.month)||(needCompareMaxMonth&&aMonStrV[i]<=this.maxDate.month)))||(needCompareBoth&&(aMonStrV[i]>=this.minDate.month&&aMonStrV[i]<=this.maxDate.month));
s.a((isValidMonth)?"class='Wym' onmouseover=\"this.className='WdayOn'\" onmouseout=\"this.className='Wym'\" onmousedown=\"$d.mInput.value="+aMonStrV[i]+";$d.mDiv.style.display='none';$d.mInput.blur();\"":"class='Winvalidym'");
s.a(">"+aMonStrT[i]+"</td>");
if(i==5){break;}
s.a("<td ");
isValidMonth=(validMonth)||(!needCompareBoth&&((needCompareMinMonth&&aMonStrV[i+6]>=this.minDate.month)||(needCompareMaxMonth&&aMonStrV[i+6]<=this.maxDate.month)))||(needCompareBoth&&(aMonStrV[i+6]>=this.minDate.month&&aMonStrV[i+6]<=this.maxDate.month));
s.a((isValidMonth)?"class='Wym' onmouseover=\"this.className='WymOn'\" onmouseout=\"this.className='Wym'\" onmousedown=\"$d.mInput.value="+aMonStrV[i+6]+";$d.mDiv.style.display='none';$d.mInput.blur();\"":"class='Winvalidym'");
s.a(">"+aMonStrT[i+6]+"</td></tr>");}
s.a("<td align=center onmouseover=\"this.className='WymOn'\" onmouseout=\"this.className='Wym'\" onmousedown=\"$d.mDiv.style.display='none';\">¡Á</td></tr>");
s.a("</table>");
this.dd.mDiv.innerHTML=s.j();};
My97DatePicker.prototype._fillyear=function(minV,maxV)
{if(minV==null||maxV==null){var v=parseInt(this.dd.yInput.value);
minV=v-5;maxV=v+4;}
var i;
var a=new Array(maxV-minV);
for(i=minV;i<=maxV;i++){a[i-minV]=i;}
var n=(a.length/2);
var s=new __sb();
var isValidYear;
s.a("<table cellspacing=0 cellpadding=2 border=0>");
for(i=0;i<n;i++){isValidYear=(a[i]>=this.minDate.year&&a[i]<=this.maxDate.year);
s.a("<tr><td ");
s.a((isValidYear)?"class='Wym' onmouseover=\"this.className='WymOn'\" onmouseout=\"this.className='Wym'\" onmousedown=\"$d.yInput.value='"+a[i]+"';$d.yDiv.style.display='none';$d.yInput.blur();\"":"class='Winvalidym'");
s.a(">"+a[i]+"</td><td ");
isValidYear=(a[i+n]>=this.minDate.year&&a[i+n]<=this.maxDate.year);
s.a((isValidYear)?"class='Wym' onmouseover=\"this.className='WymOn'\" onmouseout=\"this.className='Wym'\" onmousedown=\"$d.yInput.value='"+a[i+n]+"';$d.yDiv.style.display='none';$d.yInput.blur();\"":"class='Winvalidym'");
s.a(">"+a[i+n]+"</td></tr>");}
s.a("</table>");
s.a("<table cellspacing=0 cellpadding=3 border=0><tr><td ");
s.a((this.minDate.year<minV)?"class='Wym' onmouseover=\"this.className='WymOn'\" onmouseout=\"this.className='Wym'\" onmousedown='$d.obj._fillyear("+(minV-10)+","+(maxV-10)+")'":"class='Winvalidym'");
s.a(">\u2190</td><td class='Wym' onmouseover=\"this.className='WymOn'\" onmouseout=\"this.className='Wym'\" onmousedown=\"$d.yDiv.style.display='none';$d.yInput.blur();\">¡Á</td><td ");
s.a((this.maxDate.year>maxV)?"class='Wym' onmouseover=\"this.className='WymOn'\" onmouseout=\"this.className='Wym'\" onmousedown='$d.obj._fillyear("+(minV+10)+","+(maxV+10)+")'":"class='Winvalidym'");
s.a(">\u2192</td></tr></table>");
this.dd.yDiv.innerHTML=s.j();};
My97DatePicker.prototype._setOkInput=function(){this.dd.clearInput.onclick=function(){$d.obj.eCont.value='';};
this.dd.todayInput.onclick=function(){var d=$d.obj;d.pickDate(d.t_year,d.t_month,d.t_date,d.t_hour,d.t_minute,d.t_sec);};
this.dd.okInput.onclick=function(){$d.obj.modifyDate();};
if(this.eCont.value==""&&this.hasDate){if(((this.t_year*10000+this.t_month*100+this.t_date*1)>=(this.minDate.year*10000+this.minDate.month*100+this.minDate.date*1))&&((this.t_year*10000+this.t_month*100+this.t_date*1)<=(this.maxDate.year*10000+this.maxDate.month*100+this.maxDate.date*1))){this.dd.todayInput.style.display='inline';
this.dd.clearInput.style.display=this.dd.okInput.style.display='none';}
else{this.dd.okInput.style.display='inline';
this.dd.todayInput.style.display=this.dd.clearInput.style.display='none';}
this.dd.bDiv.onmouseover=function(){};
this.dd.bDiv.onmouseout=function(){};}
else{this.dd.okInput.style.display='inline';
this.dd.clearInput.style.display=this.dd.todayInput.style.display='none';
this.dd.bDiv.onmouseover=function(){$d.tDiv.style.display='none';
$d.clearInput.style.display='inline';
if((($d.obj.t_year*10000+$d.obj.t_month*100+$d.obj.t_date*1)>=($d.obj.minDate.year*10000+$d.obj.minDate.month*100+$d.obj.minDate.date*1))&&(($d.obj.t_year*10000+$d.obj.t_month*100+$d.obj.t_date*1)<=($d.obj.maxDate.year*10000+$d.obj.maxDate.month*100+$d.obj.maxDate.date*1))){$d.obj.dd.todayInput.style.display='inline';}
else{$d.obj.dd.todayInput.style.display='none';}};
this.dd.bDiv.onmouseout=function(){if($d.obj.showTime){$d.tDiv.style.display='inline';}
$d.clearInput.style.display=$d.todayInput.style.display='none';};}};
My97DatePicker.prototype._returnDateStr=function(Y,M,D,h,m,s,fmt)
{if(Y==null){Y=this.year;}
if(M==null){M=this.month;}
if(D==null){D=this.date;}
if(h==null){h=this.hour;}
if(m==null){m=this.minute;}
if(s==null){s=this.sec;}
if(fmt==null){fmt=this.dateFmt;}
var sDate=fmt.replace(/%[Yy]/,this._doStr(Y,4)).replace(/%[M]/,this._doStr(M,2)).replace(/%[Dd]/,this._doStr(D,2));
if(this.showTime){sDate=sDate.replace(/%[Hh]/,this._doStr(h,2)).replace(/%[m]/,this._doStr(m,2)).replace(/%[Ss]/,this._doStr(s,2));}
return sDate;};
My97DatePicker.prototype._doStr=function(s,len){s=s+'';
for(var i=s.length;i<len;i++){s='0'+s;}
return s;};
My97DatePicker.prototype._setRealValue=function(Y,M,D,h,m,s){if(this.showTime){this.eCont.setAttribute("REALVALUE",this._returnDateStr(Y,M,D,h,m,s,dpcfg.realValueLongFmt));}
else{this.eCont.setAttribute("REALVALUE",this._returnDateStr(Y,M,D,null,null,null,dpcfg.realValueShortFmt));}};
My97DatePicker.prototype.pickDate=function(Y,M,D,h,m,s){if(Y==null){Y=this.dd.yInput.value;}
if(M==null){M=this.dd.mInput.getAttribute("realValue");}
if(D==null){D=this.date;}
this.year=Y;this.month=M;this.date=D;
if(this.showTime){if(h==null){h=this.dd.hhInput.value;}
if(m==null){m=this.dd.mmInput.value;}
if(s==null){s=this.dd.ssInput.value;}
this.hour=h;this.minute=m;this.sec=s;
this.eCont.value=this._returnDateStr(Y,M,D,h,m,s);}
else{this.eCont.value=this._returnDateStr(Y,M,D);}
this._setRealValue(Y,M,D,h,m,s);
$d.obj._markValue(true);
parent.document.dateDiv.style.display='none';
this.onPicked();};
My97DatePicker.prototype.modifyDate=function(Y,M,D,h,m,s){if(Y==null){Y=this.dd.yInput.value;}
if(M==null){M=this.dd.mInput.getAttribute("realValue");}
if(D==null){D=this.date;}
this.year=Y;this.month=M;this.date=D;
if(this.showTime){if(h==null){h=this.dd.hhInput.value;}
if(m==null){m=this.dd.mmInput.value;}
if(s==null){s=this.dd.ssInput.value;}
this.hour=h;this.minute=m;this.sec=s;
this.eCont.value=this._returnDateStr(Y,M,D,h,m,s);}
else{this.eCont.value=this._returnDateStr(Y,M,D);}
if((Y*10000+M*100+D*1)<(this.minDate.year*10000+this.minDate.month*100+this.minDate.date*1)){Y=this.minDate.year;
M=this.minDate.month;
D=this.minDate.date;}
else if((Y*10000+M*100+D*1)>(this.maxDate.year*10000+this.maxDate.month*100+this.maxDate.date*1)){Y=this.maxDate.year;
M=this.maxDate.month;
D=this.maxDate.date;}else{while(!this._isDate(Y+'-'+M+'-'+D)&&D>0){D--;}}
this.pickDate(Y,M,D);}
My97DatePicker.prototype._markValue=function(bValue){if(bValue){this.eCont.className=this.eCont.className.replace(/ WdateFmtErr/,'');}
else{var tempMode=dpcfg.errDealMode;
while(true){switch(tempMode){case 0:
if(!confirm(dpcfg.errAlertMsg)){tempMode=2;
continue;}
case 1:
if(this.eCont.getAttribute("REALVALUE")){this._judgeCorrectDateTime(this.eCont.getAttribute("REALVALUE"));}else{this.eCont.value="";}
this.eCont.className=this.eCont.className.replace(/ WdateFmtErr/,'');
break;
case 2:
this.eCont.className=this.eCont.className.replace(/ WdateFmtErr/,'');
this.eCont.className=this.eCont.className.replace(/Wdate/,'Wdate WdateFmtErr');
break;}
break;}}};
My97DatePicker.prototype._judgeCorrectDateTime=function(sDateTime){var Y,M,D,h,m,s;
var v=sDateTime.split(/\W+/);
var f=this.dateFmt.match(/%./g);
for(var i=0;i<f.length;i++){if(f[i].toLowerCase()=='%y'){Y=Number(v[i]);
if(isNaN(Y)){return false;}}
else if(f[i]=='%M'){M=Number(v[i]);
if(isNaN(M)){return false;}}
else if(f[i].toLowerCase()=='%d'){D=Number(v[i]);
if(isNaN(D)){return false;}}
else if(f[i].toLowerCase()=='%h'){h=Number(v[i]);
if(isNaN(h)){return false;}}
else if(f[i]=='%m'){m=Number(v[i]);
if(isNaN(m)){return false;}}
else if(f[i].toLowerCase()=='%s'){s=Number(v[i]);
if(isNaN(s)){return false;}}}
Y=Y||this.minDate.year;
M=M||this.minDate.month;
D=D||this.minDate.date;
if(this._isDate(Y+'-'+M+'-'+D)&&(h==undefined||(h>=0)&&(h<=23))&&(s==undefined||(m>=0)&&(h<=59))&&(s==undefined||(s>=0)&&(s<=59))){this.eCont.value=this._returnDateStr(Y,M,D,h,m,s);
if(((Y*10000+M*100+D*1)>=(this.minDate.year*10000+this.minDate.month*100+this.minDate.date*1))&&((Y*10000+M*100+D*1)<=(this.maxDate.year*10000+this.maxDate.month*100+this.maxDate.date*1))){return true;}}
return false;};
My97DatePicker.prototype._doCustomDate=function(str,fmt,dvType){var defaultValue=(dvType=="min")?dpcfg.minDate:dpcfg.maxDate;
if(!str||str==''){str=defaultValue;}
var re=/\{(.*?)\}/
var year,month,day;
var arr;
var tmpEval="";
var hasLastDay=(str.indexOf('#lastDay#')>=0);
str=str.replace(/#Year#/g,this.t_year).replace(/#Month#/g,this.t_month).replace(/#Day#/g,this.t_date).replace(/#lastDay#/,'0');
if(str.substring(0,3)=="#F{"){if((arr=re.exec(str))!=null){str=parent.$dp.obj.win.eval(arr[1]);}
else{alert("Function error!");
return;}}
else{var i=0;
while((arr=re.exec(str))!=null){if(i++==97){break;}
arr.lastIndex=arr.index+arr[1].length+1;
tmpEval=parseInt(eval(arr[1]),10);
if(tmpEval<0){tmpEval='19700'+(-tmpEval);}
str=str.substring(0,arr.index)+tmpEval+str.substring(arr.lastIndex);}}
if(str==''){str=defaultValue;}
var v=str.split(/\W+/);
var f=fmt.match(/%./g);
for(var i=0;i<f.length;i++){if(v[i]){if(f[i].toLowerCase()=='%y'){year=parseInt(v[i],10);}
else if(f[i]=='%M'){month=parseInt(v[i],10);}
else if(f[i].toLowerCase()=='%d'){day=parseInt(v[i],10);}}}
year=(""+year).replace(/^19700/,"-");
month=(""+month).replace(/^19700/,"-");
day=(""+day).replace(/^19700/,"-");
if(!hasLastDay){month-=1;}
var tmp=new Date();
tmp=new Date(year,month,day);
year=tmp.getFullYear();
month=tmp.getMonth()+1;
day=tmp.getDate();
if(isNaN(year)||isNaN(month)||isNaN(day)){alert('\u65E5\u671F\u8303\u56F4\u683C\u5F0F\u9519\u8BEF(Invalid MINDATE or MAXDATE)!\n\nYear:'+year+'  Month:'+month+'  Day:'+day);}
return{'year':year,'month':month,'date':day};}
function hideYMSel(){$d.yDiv.style.display=$d.mDiv.style.display='none';}
function autoSizeIframe(){if(window!=parent){var ifs=parent.document.getElementsByTagName("IFRAME");
for(var i=0;i<ifs.length;i++){try
{if(ifs[i].contentWindow==window){var tmp=document.getElementsByTagName('div')[0];
ifs[i].style.width=tmp.offsetWidth+"px";
ifs[i].style.height=tmp.offsetHeight+"px";}}
catch(e){continue;}}}}
