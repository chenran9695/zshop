/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-08-15 09:01:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write(" <head>\r\n");
      out.write("    <title>在线商城-后台管理系统</title>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/bootstrap.min.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/mycss.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/zshop.css\" />\r\n");
      out.write(" \t<link rel=\"stylesheet\"  href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/bootstrapValidator.min.css\"/>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.min.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/bootstrap.min.js\"></script>\r\n");
      out.write(" \t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/bootstrapValidator.min.js\"></script>\r\n");
      out.write(" \t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/layer/layer.js\"></script>\r\n");
      out.write(" \t<script>\r\n");
      out.write("\t\t//重新加载验证码\r\n");
      out.write("\t\tfunction reloadVerificationCodeImage()\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\t$('#VerificationCodeImage').attr('src','");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/back/verificationCode/create?time='+new Date().getTime());\r\n");
      out.write("\t\t\t$('#verificationCode').val('');\r\n");
      out.write("        }\r\n");
      out.write("        //使用bootstrap Validator插件进行登陆账号，密码，验证码的校验\r\n");
      out.write("        $(function () {\r\n");
      out.write("            $('#loginFrom').bootstrapValidator(\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfeedbackIcons:\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tvalid: 'glyphicon glyphicon-ok',\r\n");
      out.write("\t\t\t\t\tinvalid: 'glyphicon glyphicon-remove',\r\n");
      out.write("\t\t\t\t\tvalidating: 'glyphicon glyphicon-refresh'\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tfields:\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tloginName:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tvalidators:\r\n");
      out.write("\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\tnotEmpty:\r\n");
      out.write("\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\tmessage: '登录名不能为空！'\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\tpassword:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tvalidators:\r\n");
      out.write("\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\tnotEmpty:\r\n");
      out.write("\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\tmessage: '密码不能为空！'\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\tverificationCode:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("                        threshold: 4,\r\n");
      out.write("\t\t\t\t\t\tvalidators:\r\n");
      out.write("\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\tnotEmpty:\r\n");
      out.write("\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\tmessage:'请输入验证码！'\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\tremote:\r\n");
      out.write("\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\turl:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/back/verificationCode/check',\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\tmessage:'验证码错误！'\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("            //服务端提示消息\r\n");
      out.write("            var errorMsg = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${errorMsg}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("            if(errorMsg!=null){\r\n");
      out.write("                layer.msg(errorMsg,{time:1000});\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("\t</script>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("  \t<!-- 使用自定义css样式 div-signin 完成元素居中-->\r\n");
      out.write("    <div class=\"container div-signin\">\r\n");
      out.write("      <div class=\"panel panel-primary div-shadow\">\r\n");
      out.write("      \t<!-- h3标签加载自定义样式，完成文字居中和上下间距调整 -->\r\n");
      out.write("\t    <div class=\"panel-heading\">\r\n");
      out.write("\t    \t<h3>在线商城系统</h3>\r\n");
      out.write("\t    \t<span>ZSHOP Manager System</span>\r\n");
      out.write("\t    </div>\r\n");
      out.write("\t    <div class=\"panel-body\">\r\n");
      out.write("\t      <!-- login form start -->\r\n");
      out.write("\t      <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/back/admin/login\" class=\"form-horizontal\" method=\"post\" id=\"loginFrom\">\r\n");
      out.write("\t\t     <div class=\"form-group\">\r\n");
      out.write("\t\t       <label class=\"col-sm-3 control-label\">用户名：</label>\r\n");
      out.write("\t\t       <div class=\"col-sm-9\">\r\n");
      out.write("\t\t         <input class=\"form-control\" type=\"text\" placeholder=\"请输入用户名\" name=\"loginName\" id=\"loginName\">\r\n");
      out.write("\t\t       </div>\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t\t     <div class=\"form-group\">\r\n");
      out.write("\t\t       <label class=\"col-sm-3 control-label\">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>\r\n");
      out.write("\t\t       <div class=\"col-sm-9\">\r\n");
      out.write("\t\t         <input class=\"form-control\" type=\"password\" placeholder=\"请输入密码\" name=\"password\" id=\"password\">\r\n");
      out.write("\t\t       </div>\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t\t     <div class=\"form-group\">\r\n");
      out.write("\t\t       <label class=\"col-sm-3 control-label\">验证码：</label>\r\n");
      out.write("\t\t       <div class=\"col-sm-4\">\r\n");
      out.write("\t\t         <input class=\"form-control\" type=\"text\" placeholder=\"验证码\" name=\"verificationCode\" id=\"verificationCode\">\r\n");
      out.write("\t\t       </div>\r\n");
      out.write("\t\t       <div class=\"col-sm-2\">\r\n");
      out.write("\t\t       \t  <!-- 验证码 -->\r\n");
      out.write("\t\t\t      <img class=\"img-rounded\" id=\"VerificationCodeImage\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/back/verificationCode/create\" style=\"height: 32px; width: 70px;\"/>\r\n");
      out.write("\t\t       </div>\r\n");
      out.write("\t\t       <div class=\"col-sm-2\">\r\n");
      out.write("\t\t         <button type=\"button\" class=\"btn btn-link\" onclick=\"reloadVerificationCodeImage()\">看不清</button>\r\n");
      out.write("\t\t       </div>\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t\t    <div class=\"form-group\">\r\n");
      out.write("\t\t       <div class=\"col-sm-3\">\r\n");
      out.write("\t\t       </div>\r\n");
      out.write("\t\t       <div class=\"col-sm-9 padding-left-0\">\r\n");
      out.write("\t\t       \t <div class=\"col-sm-4\">\r\n");
      out.write("\t\t\t         <button type=\"submit\" class=\"btn btn-primary btn-block\">登&nbsp;&nbsp;陆</button>\r\n");
      out.write("\t\t       \t </div>\r\n");
      out.write("\t\t       \t <div class=\"col-sm-4\">\r\n");
      out.write("\t\t\t         <button type=\"reset\" class=\"btn btn-primary btn-block\">重&nbsp;&nbsp;置</button>\r\n");
      out.write("\t\t       \t </div>\r\n");
      out.write("\t\t       \t <div class=\"col-sm-4\">\r\n");
      out.write("\t\t\t         <button type=\"button\" class=\"btn btn-link btn-block\">忘记密码？</button>\r\n");
      out.write("\t\t       \t </div>\r\n");
      out.write("\t\t       </div>\r\n");
      out.write("\t\t    </div>\r\n");
      out.write("\t      </form>\r\n");
      out.write("\t      <!-- login form end -->\r\n");
      out.write("\t    </div>\r\n");
      out.write("\t  </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
