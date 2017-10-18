/**

*注意: 下列使用顺序

* <script language='javascript'  src='js/jsCnsPullDownDiv.js'></script>

* <script language='javascript'  src='js/data.js'></script>

*

*

*示例:

*      <script  language=javascript  src='js/data.js'></script>

*      <INPUT id="Text1" onclick='popUpCalendar(this, this, " mm/dd/yyyy")' readOnly type="text"  size="11" name="Text1">

*      <INPUT id="Text1" onclick='popUpCalendar(this, this, " yyyy-mm-dd")' readOnly type="text"  size="11" name="Text1">

*

*/

<!--

if(typeof(jsIEFirefoxCalendar)!='number')

{

     var fixedX              = -1;   // x position (-1 if to appear below control)

     var fixedY              = -1;   // y position (-1 if to appear below control)

     var startAt             = 1;    // 0 - sunday ; 1 - monday

     var showWeekNumber      = 1;    // 0 - don't show; 1 - show

     var cnsJsIeFirefoxCalendarShowToday           = 1;    // 0 - don't show; 1 - show



     var imgDir              = "";



     var gotoString          = "转到当前月份";

     var todayString         = "";

     var weekString          = "周";

     var scrollLeftMessage   = "Click to scroll to previous month. Hold mouse button to scroll automatically.";

     var scrollRightMessage  = "Click to scroll to next month. Hold mouse button to scroll automatically.";

     var selectMonthMessage  = "点击选择月份";

     var selectYearMessage   = "点击选择年份";

     var selectDateMessage   = "选择[date]日期";   // do not replace [date], it will be replaced by date.



     var crossobj, cnsJsIeFirefoxCalendarCrossMonthObj, crossYearObj, monthSelected, yearSelected, dateSelected, omonthSelected, oyearSelected, odateSelected, monthConstructed, yearConstructed, intervalID1, intervalID2, timeoutID1, timeoutID2, ctlToPlaceValue, ctlNow, dateFormat, nStartingYear, nStartingMonth;



     var bPageLoaded    = false;



     var ie             = false;

     var cnsJsIeFirefoxCalendarDom            = document.getElementById;



     var ns4      = document.layers;

     var today    = new Date();

     var dateNow  = today.getDate();

     var monthNow = today.getMonth();

     var yearNow  = today.getYear();

     var imgsrc   = new Array("drop1.gif","drop2.gif","left1.gif","left2.gif","right1.gif","right2.gif");

     var img      = new Array();



     var bShow    = false;



     if((navigator.userAgent.toLowerCase().indexOf("opera") == -1) && (navigator.userAgent.toLowerCase().indexOf("msie") != -1))

     {

        ie = true;

     }



        /* 隐藏 <select> and <applet> objects (for IE only) 等:will框 */

        function cnsJsIeFirefoxCalendarHideElement( elmID, overDiv )

        {

          if( ie )

          {

            for( i = 0; i < document.all.tags( elmID ).length; i++ )

            {

              obj = document.all.tags( elmID )[i];

              if( !obj || !obj.offsetParent )

              {

                continue;

              }



              // Find the element's offsetTop and offsetLeft relative to the BODY tag.

              objLeft   = obj.offsetLeft;

              objTop    = obj.offsetTop;

              objParent = obj.offsetParent;



              while( objParent.tagName.toUpperCase() != "BODY" )

              {

                objLeft  += objParent.offsetLeft;

                objTop   += objParent.offsetTop;

                objParent = objParent.offsetParent;

              }



              objHeight = obj.offsetHeight;

              objWidth = obj.offsetWidth;



              if(( overDiv.offsetLeft + overDiv.offsetWidth ) <= objLeft );

              else if(( overDiv.offsetTop + overDiv.offsetHeight ) <= objTop );

              else if( overDiv.offsetTop >= ( objTop + objHeight ));

              else if( overDiv.offsetLeft >= ( objLeft + objWidth ));

              else

              {

                obj.style.visibility = "hidden";

              }

            }

          }

        }



        /*

        * 重新显示被隐藏的 <select> and <applet> objects (for IE only)

        */

        function cnsJsIeFirefoxCalendarShowElement( elmID )

        {

          if( ie )

          {

            for( i = 0; i < document.all.tags( elmID ).length; i++ )

            {

              obj = document.all.tags( elmID )[i];



              if( !obj || !obj.offsetParent )

              {

                continue;

              }



              obj.style.visibility = "";

            }

          }

        }



     function cnsJsIeFirefoxCalendarHolidayRec (d, m, y, desc)

     {

      this.d = d;

      this.m = m;

      this.y = y;

      this.desc = desc;

     }



     var HolidaysCounter = 0;

     var Holidays = new Array();



     function cnsJsIeFirefoxCalendarAddHoliday (d, m, y, desc)

     {

         Holidays[HolidaysCounter++] = new cnsJsIeFirefoxCalendarHolidayRec ( d, m, y, desc );

     }



     if (cnsJsIeFirefoxCalendarDom)

     {

      for (var i=0;i<imgsrc.length;i++)

      {

       img[i] = new Image;

       img[i].src = imgDir + imgsrc[i];

      }

      document.write ("<div onclick='bShow=true' id='calendar' style='z-index:+999;position:absolute;visibility:hidden;font-size:11px'><table width="+((showWeekNumber==1)?250:220)+" style='font-family:arial;font-size:11px;border-width:1px;border-style:solid;border-color:#a0a0a0;font-family:arial; font-size:11px' bgcolor='#ffffff'><tr bgcolor='#0000aa'><td><table width='"+((showWeekNumber==1)?248:218)+"'><tr><td style='padding:2px;font-family:arial; font-size:11px;'><font color='#ffffff'><B><span id='caption'></span></B></font></td><td align=right><a href='javascript:cnsJsIeFirefoxCalendarHideCalendar()'><font color=white><b>X</b></font></a></td></tr></table></td></tr><tr><td style='padding:2px' bgcolor=#ffffff><span id='content'></span></td></tr>");



      if (cnsJsIeFirefoxCalendarShowToday==1)

      {

       document.write ("<tr bgcolor=#f0f0f0><td style='padding:2px' align=center><span id='lblToday' style='font-size:12px' ></span></td></tr>");

      }



      document.write ("</table></div><div id='selectMonth' style='z-index:+999;position:absolute;visibility:hidden;'></div><div id='selectYear' style='z-index:+999;position:absolute;visibility:hidden;'></div>");

     }



     var monthName = new Array("01","02","03","04","05","06","07","08","09","10","11","12");

     var monthName2 = new Array("01","02","03","04","05","06","07","08","09","10","11","12");

     if (startAt==0)

     {

      var dayName = new Array ("日","一","二","三","四","五","六");

     }

     else

     {

     var  dayName = new Array ("一","二","三","四","五","六","日");

     }

     var styleAnchor="text-decoration:none;color:black;"

     var styleLightBorder="border-style:solid;border-width:1px;border-color:#a0a0a0;"





     function cnsJsIeFirefoxCalendarInit()

     {

      if (!ns4)

      {

       if (!ie)

       {

        yearNow += 1900;

       }



       var crossobj=(cnsJsIeFirefoxCalendarDom)?document.getElementById("calendar").style : ie? document.all.calendar : document.calendar;

       cnsJsIeFirefoxCalendarHideCalendar();



       cnsJsIeFirefoxCalendarCrossMonthObj=(cnsJsIeFirefoxCalendarDom)?document.getElementById("selectMonth").style : ie? document.all.selectMonth : document.selectMonth;



       crossYearObj=(cnsJsIeFirefoxCalendarDom)?document.getElementById("selectYear").style : ie? document.all.selectYear : document.selectYear;



       monthConstructed=false;

       yearConstructed=false;



       if (cnsJsIeFirefoxCalendarShowToday==1)

       {

        document.getElementById("lblToday").innerHTML = todayString + "<a onmousemove='window.status=\""+gotoString+"\"' onmouseout='window.status=\"\"' title='"+gotoString+"' style='"+styleAnchor+"' href='javascript:monthSelected=monthNow;yearSelected=yearNow;constructCalendar();'>周"+dayName[(today.getDay()-startAt==-1)?6:(today.getDay()-startAt)]+"  " + monthName[monthNow].substring(0,3) + "/" + dateNow + "/" + yearNow + "</a>";

       }



       var sHTML1="<span  id='spanLeft' style='border-style:solid;border-width:1px;border-color:#3366FF;cursor:pointer'     onclick='javascript:decMonth()' onmousedown='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"StartDecMonth()\",500)' onmouseup='clearTimeout(timeoutID1);clearInterval(intervalID1)'  title='上一月' >&nbsp;&laquo;&nbsp;</span>&nbsp;";

       sHTML1+="<span id='spanRight' style='border-style:solid;border-width:1px;border-color:#3366FF;cursor:pointer'   onclick='incMonth()' onmousedown='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"StartIncMonth()\",500)' onmouseup='clearTimeout(timeoutID1);clearInterval(intervalID1)' title='下一月' >&nbsp;&raquo;&nbsp;</span>&nbsp";

       sHTML1+="<span id='spanMonth' style='border-style:solid;border-width:1px;border-color:#3366FF;cursor:pointer'   onclick='popUpMonth()' title='选择月份' ></span>&nbsp;";

       sHTML1+="<span id='spanYear' style='border-style:solid;border-width:1px;border-color:#3366FF;cursor:pointer'    onclick='popUpYear()' title='选择年份'  ></span>&nbsp;";



       document.getElementById("caption").innerHTML  = sHTML1;



       bPageLoaded=true;

      }

     }



     function cnsJsIeFirefoxCalendarHideCalendar()

     {

       crossobj=(cnsJsIeFirefoxCalendarDom)?document.getElementById("calendar").style : ie? document.all.calendar : document.calendar;

      crossobj.visibility="hidden";

      if (cnsJsIeFirefoxCalendarCrossMonthObj != null)

      {

       cnsJsIeFirefoxCalendarCrossMonthObj.visibility="hidden";

      }

      if (crossYearObj != null)

      {

       crossYearObj.visibility="hidden";

      }



         cnsJsIeFirefoxCalendarShowElement( 'SELECT' );

      cnsJsIeFirefoxCalendarShowElement( 'APPLET' );

     }



     function padZero(num)

     {

      return (num < 10)? '0' + num : num ;

     }



     function constructDate(d,m,y)

     {

      var sTmp = dateFormat;

      sTmp = sTmp.replace ("dd","<e>");

      sTmp = sTmp.replace ("d","<d>");

      sTmp = sTmp.replace ("<e>",padZero(d));

      sTmp = sTmp.replace ("<d>",d);

      sTmp = sTmp.replace ("mmmm","<p>");

      sTmp = sTmp.replace ("mmm","<o>");

      sTmp = sTmp.replace ("mm","<n>");

      sTmp = sTmp.replace ("m","<m>");

      sTmp = sTmp.replace ("<m>",m+1);

      sTmp = sTmp.replace ("<n>",padZero(m+1));

      sTmp = sTmp.replace ("<o>",monthName[m]);

      sTmp = sTmp.replace ("<p>",monthName2[m]);

      sTmp = sTmp.replace ("yyyy",y);

      return sTmp.replace ("yy",padZero(y%100));

     }



     function closeCalendar()

     {

      var sTmp;



      cnsJsIeFirefoxCalendarHideCalendar();

      ctlToPlaceValue.value = constructDate(dateSelected,monthSelected,yearSelected);

     }



     /*** Month Pulldown ***/



     function StartDecMonth()

     {

      intervalID1=setInterval("decMonth()",80);

     }



     function StartIncMonth()

     {

      intervalID1=setInterval("incMonth()",80);

     }



     function incMonth ()

     {

      monthSelected++;

      if (monthSelected>11)

      {

       monthSelected=0;

       yearSelected++;

      }

      constructCalendar()

     }



     function decMonth ()

     {

      monthSelected--;

      if (monthSelected<0)

      {

       monthSelected=11;

       yearSelected--;

      }

      constructCalendar()

     }



     function upMonth()

     {

      if(nStartingMonth > 0)

      {

       nStartingMonth --;

       for (i=0; i<6; i++)

       {

        newMonth = (i + nStartingMonth);

        if (newMonth == monthSelected)

         txtMonth = "&nbsp;<B>" + monthName[newMonth] + "</B>&nbsp;";

        else

         txtMonth = "&nbsp;" + monthName[newMonth] + "&nbsp;";

        document.getElementById("m"+i).innerHTML = txtMonth;

       }

      }

      bShow=true;

     }



     function downMonth()

     {

      if(nStartingMonth < 6)

      {

       nStartingMonth ++;

       for (i=0; i<6; i++)

       {

        newMonth = (i + nStartingMonth);

        if (newMonth == monthSelected)

         txtMonth = "&nbsp;<B>" + monthName[newMonth] + "</B>&nbsp;";

        else

         txtMonth = "&nbsp;" + monthName[newMonth] + "&nbsp;";

        document.getElementById("m"+i).innerHTML = txtMonth;

       }

      }

      bShow=true;

     }





     function selectMonth(nMonth)

     {

      monthSelected=parseInt(nMonth+nStartingMonth);

      monthConstructed=false;

      constructCalendar();

      popDownMonth();

     }



     function constructMonth()

     {

      popDownYear()

      if (!monthConstructed)

      {

       sHTML = "<tr><td align='center' onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='clearInterval(intervalID1);this.style.backgroundColor=\"\"' style='cursor:pointer;font-size:12px' onmousedown='clearInterval(intervalID1);intervalID1=setInterval(\"upMonth()\",30)' onmouseup='clearInterval(intervalID1)'>-</td></tr>"

       j=0;

       i=(monthSelected-3);

       if(i < 0)

        i=0;

       if(i > 6)

        i=6;

       nStartingMonth = i;

       for (ii=0; ii<6; ii++, i++, j++)

       {

        sName = monthName[i];

        if (i==monthSelected)

        {

         sName = "<B>" + sName + "</B>";

        }

        sHTML += "<tr><td id='m" + j + "' onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer' onclick='selectMonth(" + j + ");event.cancelBubble=true'>&nbsp;" + sName + "&nbsp;</td></tr>";

       }



       sHTML += "<tr><td align='center' onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='clearInterval(intervalID2);this.style.backgroundColor=\"\"' style='cursor:pointer;font-size:12px' onmousedown='clearInterval(intervalID2);intervalID2=setInterval(\"downMonth()\",30)' onmouseup='clearInterval(intervalID2)'>+</td></tr>";



       document.getElementById("selectMonth").innerHTML = "<table width=32 style='font-family:arial; font-size:11px; border-width:1; border-style:solid; border-color:#a0a0a0;' bgcolor='#FFFFDD' cellspacing=0 onmouseover='clearTimeout(timeoutID1)' onmouseout='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"popDownMonth()\",100);event.cancelBubble=true'>" + sHTML + "</table>";



       monthConstructed=true;

      }

     }

     function popUpMonth()

     {

      constructMonth()

      cnsJsIeFirefoxCalendarCrossMonthObj.visibility = (cnsJsIeFirefoxCalendarDom||ie)? "visible" : "show";

      cnsJsIeFirefoxCalendarCrossMonthObj.left = parseInt(crossobj.left) + 50;

      cnsJsIeFirefoxCalendarCrossMonthObj.top = parseInt(crossobj.top) + 26;



      cnsJsIeFirefoxCalendarHideElement( 'SELECT', document.getElementById("selectMonth") );

      cnsJsIeFirefoxCalendarHideElement( 'APPLET', document.getElementById("selectMonth") );

     }



     function popDownMonth()

     {

      cnsJsIeFirefoxCalendarCrossMonthObj.visibility= "hidden";

     }



     /*** Year Pulldown ***/



     function incYear()

     {

      for (i=0; i<6; i++)

      {

       newYear = (i+nStartingYear)+1;

       if (newYear==yearSelected)

       {

        txtYear = "&nbsp;<B>" + newYear + "</B>&nbsp;"

       }

       else

       {

        txtYear = "&nbsp;" + newYear + "&nbsp;"

       }

       document.getElementById("y"+i).innerHTML = txtYear;

      }

      nStartingYear ++;

      bShow=true;

     }



     function decYear()

     {

      for (i=0; i<6; i++)

      {

       newYear = (i+nStartingYear)-1;

       if (newYear==yearSelected)

       {

        txtYear = "&nbsp;<B>" + newYear + "</B>&nbsp;";

       }

       else

       {

        txtYear = "&nbsp;" + newYear + "&nbsp;";

       }

       document.getElementById("y"+i).innerHTML = txtYear;

      }

      nStartingYear --;

      bShow=true;

     }



     function selectYear(nYear)

     {

      yearSelected=parseInt(nYear+nStartingYear);

      yearConstructed=false;

      constructCalendar();

      popDownYear();

     }



     function constructYear()

     {

      popDownMonth();

      sHTML = ""

      if (!yearConstructed)

      {

       sHTML = "<tr><td align='center' onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='clearInterval(intervalID1);this.style.backgroundColor=\"\"' style='cursor:pointer;font-size:12px' onmousedown='clearInterval(intervalID1);intervalID1=setInterval(\"decYear()\",30)' onmouseup='clearInterval(intervalID1)'>-</td></tr>";

       j = 0;

       nStartingYear = yearSelected-3;

       for (i=(yearSelected-3); i<(yearSelected+3); i++)

       {

        sName = i;

        if (i==yearSelected)

        {

         sName = "<B>" + sName + "</B>";

        }



        sHTML += "<tr><td id='y" + j + "' onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer;font-size:12px' onclick='selectYear("+j+");event.cancelBubble=true'>&nbsp;" + sName + "&nbsp;</td></tr>"

        j ++;

       }



       sHTML += "<tr><td align='center' onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='clearInterval(intervalID2);this.style.backgroundColor=\"\"' style='cursor:pointer;font-size:12px' onmousedown='clearInterval(intervalID2);intervalID2=setInterval(\"incYear()\",30)' onmouseup='clearInterval(intervalID2)'>+</td></tr>"



       document.getElementById("selectYear").innerHTML = "<table width=44 style='font-family:arial; font-size:11px; border-width:1; border-style:solid; border-color:#a0a0a0;' bgcolor='#FFFFDD' onmouseover='clearTimeout(timeoutID2)' onmouseout='clearTimeout(timeoutID2);timeoutID2=setTimeout(\"popDownYear()\",100)' cellspacing=0>" + sHTML + "</table>"



       yearConstructed = true;

      }

     }



     function popDownYear()

     {

      clearInterval(intervalID1);

      clearTimeout(timeoutID1);

      clearInterval(intervalID2);

      clearTimeout(timeoutID2);

      crossYearObj.visibility= "hidden";

     }



     function popUpYear()

     {

      var leftOffset



      constructYear();

      crossYearObj.visibility = (cnsJsIeFirefoxCalendarDom||ie)? "visible" : "show";

      leftOffset = parseInt(crossobj.left) + document.getElementById("spanYear").offsetLeft;

      if (ie)

      {

       leftOffset += 6;

      }

      crossYearObj.left = leftOffset;

      crossYearObj.top = parseInt(crossobj.top) + 26;

     }



     /*** calendar ***/

       function WeekNbr(n)

       {

          // Algorithm used:

          // From Klaus Tondering's Calendar document (The Authority/Guru)

          // hhtp://www.tondering.dk/claus/calendar.html

          // a = (14-month) / 12

          // y = year + 4800 - a

          // m = month + 12a - 3

          // J = day + (153m + 2) / 5 + 365y + y / 4 - y / 100 + y / 400 - 32045

          // d4 = (J + 31741 - (J mod 7)) mod 146097 mod 36524 mod 1461

          // L = d4 / 1460

          // d1 = ((d4 - L) mod 365) + L

          // WeekNumber = d1 / 7 + 1



          var year = n.getFullYear();

          var month = n.getMonth() + 1;

          if (startAt == 0)

          {

             var day = n.getDate() + 1;

          }

          else

          {

             var day = n.getDate();

          }



          var a = Math.floor((14-month) / 12);

          var y = year + 4800 - a;

          var m = month + 12 * a - 3;

          var b = Math.floor(y/4) - Math.floor(y/100) + Math.floor(y/400);

          var J = day + Math.floor((153 * m + 2) / 5) + 365 * y + b - 32045;

          var d4 = (((J + 31741 - (J % 7)) % 146097) % 36524) % 1461;

          var L = Math.floor(d4 / 1460);

          var d1 = ((d4 - L) % 365) + L;

          var week = Math.floor(d1/7) + 1;



          return week;

       }



     function constructCalendar ()

     {

      var aNumDays = Array (31,0,31,30,31,30,31,31,30,31,30,31);



      var dateMessage;

      var startDate = new Date (yearSelected,monthSelected,1);

      var endDate;



      if (monthSelected==1)

      {

       endDate = new Date (yearSelected,monthSelected+1,1);

       endDate = new Date (endDate - (24*60*60*1000));

       var numDaysInMonth = endDate.getDate();

      }

      else

      {

       var numDaysInMonth = aNumDays[monthSelected];

      }



      var datePointer = 0;

      var dayPointer = startDate.getDay() - startAt;



      if (dayPointer<0)

      {

       dayPointer = 6;

      }



      var sHTML = "<table  border=0 style='font-family:verdana;font-size:10px;'><tr>";



      if (showWeekNumber==1)

      {

          sHTML += "<td width=27 align=center style='font-size:12px' ><b>" + weekString + "</b></td><td width=1 rowspan=7 bgcolor='#d0d0d0' style='padding:0px'><img src='"+imgDir+"divider.gif' width=1></td>";

      }



      for (i=0; i<7; i++)

      {

       sHTML += "<td width='27' align='right'  style='font-size:12px' ><B>"+ dayName[i]+"</B></td>";

      }

      sHTML +="</tr><tr>";



      if (showWeekNumber==1)

      {

       sHTML += "<td align=right>" + WeekNbr(startDate) + "&nbsp;</td>";

      }



      for ( var i=1; i<=dayPointer;i++ )

      {

       sHTML += "<td>&nbsp;</td>";

      }



      for ( datePointer=1; datePointer<=numDaysInMonth; datePointer++ )

      {

       dayPointer++;

       sHTML += "<td align=right>";

       var sStyle=styleAnchor;

       if ((datePointer==odateSelected) && (monthSelected==omonthSelected) && (yearSelected==oyearSelected))

       { sStyle+=styleLightBorder; }



       var sHint="";

       for(var k=0;k<HolidaysCounter;k++)

       {

        if ((parseInt(Holidays[k].d)==datePointer)&&(parseInt(Holidays[k].m)==(monthSelected+1)))

        {

         if ((parseInt(Holidays[k].y)==0)||((parseInt(Holidays[k].y)==yearSelected)&&(parseInt(Holidays[k].y)!=0)))

         {

          sStyle+="background-color:#FFDDDD;"

          sHint+=sHint==""?Holidays[k].desc:"\n"+Holidays[k].desc;

         }

        }

       }



       var regexp= /\"/g;

       sHint=sHint.replace(regexp,"&quot;");



       dateMessage = "onmousemove='window.status=\""+selectDateMessage.replace("[date]",constructDate(datePointer,monthSelected,yearSelected))+"\"' onmouseout='window.status=\"\"' ";



       if ((datePointer==dateNow)&&(monthSelected==monthNow)&&(yearSelected==yearNow))

       {

        sHTML += "<b><a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:dateSelected="+datePointer+";closeCalendar();'><font color=#ff0000>&nbsp;" + datePointer + "</font>&nbsp;</a></b>";

       }

       else if (dayPointer % 7 == (startAt * -1)+1)

       {

        sHTML += "<a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:dateSelected="+datePointer + ";closeCalendar();'>&nbsp;<font color=#909090>" + datePointer + "</font>&nbsp;</a>";

       }

       else

       {

        sHTML += "<a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='javascript:dateSelected="+datePointer + ";closeCalendar();'>&nbsp;" + datePointer + "&nbsp;</a>";

       }



       sHTML += "";

       if ((dayPointer+startAt) % 7 == startAt)

       {

        sHTML += "</tr><tr>";

        if ((showWeekNumber==1)&&(datePointer<numDaysInMonth))

        {

         sHTML += "<td align=right>" + (WeekNbr(new Date(yearSelected,monthSelected,datePointer+1))) + "&nbsp;</td>";

        }

       }

      }



      document.getElementById("content").innerHTML   = sHTML;

      document.getElementById("spanMonth").innerHTML = "&nbsp;" + monthName[monthSelected] + "&nbsp;&darr;";

      document.getElementById("spanYear").innerHTML = "&nbsp;" + yearSelected + "&nbsp;&darr;";

     }



     function popUpCalendar(ctl, ctl2, format)

     {

      var leftpos=0;

      var toppos=0;



      var crossobj=(cnsJsIeFirefoxCalendarDom)?document.getElementById("calendar").style : ie? document.all.calendar : document.calendar;

      if (bPageLoaded)

      {

       if ( crossobj.visibility == "hidden" )

       {

        ctlToPlaceValue = ctl2;

        dateFormat=format;



        var formatChar = " ";

        var aFormat = dateFormat.split(formatChar);

        if (aFormat.length<3)

        {

         formatChar = "/";

         aFormat = dateFormat.split(formatChar);

         if (aFormat.length<3)

         {

          formatChar = ".";

          aFormat = dateFormat.split(formatChar);

          if (aFormat.length<3)

          {

           formatChar = "-";

           aFormat = dateFormat.split(formatChar);

           if (aFormat.length<3)

           {

            // invalid date format

            formatChar="";

           }

          }

         }

        }



        var tokensChanged = 0;

        if ( formatChar != "" )

        {

         var aData="";

         if(ctl2.value!="")

         {

         // use user's date

         

            if(ctl2.value=="0000-00-00")

               aDate = "";

            else

               aData = ctl2.value.split(formatChar);

         }

         

         for (i=0;i<3;i++)

         {

          if ((aFormat[i]=="d") || (aFormat[i]=="dd"))

          {

           dateSelected = parseInt(aData[i], 10);

           tokensChanged ++;

          }

          else if ((aFormat[i]=="m") || (aFormat[i]=="mm"))

          {

           monthSelected = parseInt(aData[i], 10) - 1;

           tokensChanged ++;

          }

          else if (aFormat[i]=="yyyy")

          {

           yearSelected = parseInt(aData[i], 10);

           tokensChanged ++;

          }

          else if (aFormat[i]=="mmm")

          {

           for (j=0; j<12; j++)

           {

            if (aData[i]==monthName[j])

            {

             monthSelected=j;

             tokensChanged ++;

            }

           }

          }

          else if (aFormat[i]=="mmmm")

          {

           for (j=0; j<12; j++)

           {

            if (aData[i]==monthName2[j])

            {

             monthSelected=j;

             tokensChanged ++;

            }

           }

          }

         }

        }



        if ((tokensChanged!=3)||isNaN(dateSelected)||isNaN(monthSelected)||isNaN(yearSelected))

        {

         dateSelected = dateNow;

         monthSelected = monthNow;

         yearSelected = yearNow;

        }



        odateSelected=dateSelected;

        omonthSelected=monthSelected;

        oyearSelected=yearSelected;



        var aTag = ctl;

        do

        {

         aTag = aTag.offsetParent;

         leftpos += aTag.offsetLeft;

         toppos += aTag.offsetTop;

        } while(aTag.tagName!="BODY");



        crossobj.left = fixedX==-1 ? ctl.offsetLeft + leftpos : fixedX;

        crossobj.top = fixedY==-1 ? ctl.offsetTop + toppos + ctl.offsetHeight + 2 : fixedY;

        constructCalendar (1, monthSelected, yearSelected);

        crossobj.visibility=(cnsJsIeFirefoxCalendarDom||ie)? "visible" : "show";



        cnsJsIeFirefoxCalendarHideElement( 'SELECT', document.getElementById("calendar") );

        cnsJsIeFirefoxCalendarHideElement( 'APPLET', document.getElementById("calendar") );



        bShow = true;

       }

       else

       {

        cnsJsIeFirefoxCalendarHideCalendar();

        if (ctlNow!=ctl) {popUpCalendar(ctl, ctl2, format);}

       }

       ctlNow = ctl;

      }

     }



     document.onkeypress = function hidecal1 ()

     {

      if (event.keyCode==27)

      {

       cnsJsIeFirefoxCalendarHideCalendar();

      }

     }

     document.onclick = function hidecal2 ()

     {

      if (!bShow)

      {

       cnsJsIeFirefoxCalendarHideCalendar();

      }

      bShow = false;

     }



     if(ie)

     {

      cnsJsIeFirefoxCalendarInit();

     }

     else

     {

      window.onload=cnsJsIeFirefoxCalendarInit;

     }



}//::if(jsIEFirefoxCalendar!=1)



var jsIEFirefoxCalendar=1;

//-->

