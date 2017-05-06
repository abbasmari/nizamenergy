<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleServiceSoapProxyid" scope="session" class="org.tempuri.ServiceSoapProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleServiceSoapProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleServiceSoapProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleServiceSoapProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        org.tempuri.ServiceSoap getServiceSoap10mtemp = sampleServiceSoapProxyid.getServiceSoap();
if(getServiceSoap10mtemp == null){
%>
<%=getServiceSoap10mtemp %>
<%
}else{
        if(getServiceSoap10mtemp!= null){
        String tempreturnp11 = getServiceSoap10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String sAccessKey_1id=  request.getParameter("sAccessKey16");
            java.lang.String sAccessKey_1idTemp = null;
        if(!sAccessKey_1id.equals("")){
         sAccessKey_1idTemp  = sAccessKey_1id;
        }
        String sMobile_2id=  request.getParameter("sMobile18");
            java.lang.String sMobile_2idTemp = null;
        if(!sMobile_2id.equals("")){
         sMobile_2idTemp  = sMobile_2id;
        }
        String sMsgId_3id=  request.getParameter("sMsgId20");
            java.lang.String sMsgId_3idTemp = null;
        if(!sMsgId_3id.equals("")){
         sMsgId_3idTemp  = sMsgId_3id;
        }
        String sMsg_4id=  request.getParameter("sMsg22");
            java.lang.String sMsg_4idTemp = null;
        if(!sMsg_4id.equals("")){
         sMsg_4idTemp  = sMsg_4id;
        }
        java.lang.String sendSMS13mtemp = sampleServiceSoapProxyid.sendSMS(sAccessKey_1idTemp,sMobile_2idTemp,sMsgId_3idTemp,sMsg_4idTemp);
if(sendSMS13mtemp == null){
%>
<%=sendSMS13mtemp %>
<%
}else{
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(sendSMS13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
}
break;
case 24:
        gotMethod = true;
        String sAccessKey_5id=  request.getParameter("sAccessKey27");
            java.lang.String sAccessKey_5idTemp = null;
        if(!sAccessKey_5id.equals("")){
         sAccessKey_5idTemp  = sAccessKey_5id;
        }
        String sMobile_6id=  request.getParameter("sMobile29");
            java.lang.String sMobile_6idTemp = null;
        if(!sMobile_6id.equals("")){
         sMobile_6idTemp  = sMobile_6id;
        }
        java.lang.String getLocation24mtemp = sampleServiceSoapProxyid.getLocation(sAccessKey_5idTemp,sMobile_6idTemp);
if(getLocation24mtemp == null){
%>
<%=getLocation24mtemp %>
<%
}else{
        String tempResultreturnp25 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getLocation24mtemp));
        %>
        <%= tempResultreturnp25 %>
        <%
}
break;
case 31:
        gotMethod = true;
        String sAccessKey_7id=  request.getParameter("sAccessKey36");
            java.lang.String sAccessKey_7idTemp = null;
        if(!sAccessKey_7id.equals("")){
         sAccessKey_7idTemp  = sAccessKey_7id;
        }
        String sRequestId_8id=  request.getParameter("sRequestId38");
            java.lang.String sRequestId_8idTemp = null;
        if(!sRequestId_8id.equals("")){
         sRequestId_8idTemp  = sRequestId_8id;
        }
        org.tempuri.ReceiveSMSResponseReceiveSMSResult receiveSMS31mtemp = sampleServiceSoapProxyid.receiveSMS(sAccessKey_7idTemp,sRequestId_8idTemp);
if(receiveSMS31mtemp == null){
%>
<%=receiveSMS31mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">_any:</TD>
<TD>
<%
if(receiveSMS31mtemp != null){
org.apache.axis.message.MessageElement[] type_any34 = receiveSMS31mtemp.get_any();
        String temp_any34 = null;
        if(type_any34 != null){
        java.util.List list_any34= java.util.Arrays.asList(type_any34);
        temp_any34 = list_any34.toString();
        }
        %>
        <%=temp_any34%>
        <%
}%>
</TD>
</TABLE>
<%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>