<%--
  Created by IntelliJ IDEA.
  User: 无名之辈
  Date: 2019/3/22
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/js/template.js"></script>
<%--模板1 已登录状态下显示--%>
<script type="text/html" id="welcome">
    <li class="userName">
        尊敬的{{name}},您好!
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
            <img class="img-circle" src="${pageContext.request.contextPath}/images/user.jpeg" height="30" />
            <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
            <li>
                <a href="#" data-toggle="modal" data-target="#modifyPasswordModal">
                    <i class="glyphicon glyphicon-cog"></i>修改密码
                </a>
            </li>
            <li>
                <a onclick="logout()">
                    <i class="glyphicon glyphicon-off" ></i> 退出
                </a>
            </li>
        </ul>
    </li>
</script>
<%--模板2 未登录状态下显示--%>
<script type="text/html" id="login">
    <li>
        <a href="#" data-toggle="modal" data-target="#loginModal">登陆</a>
    </li>
    <li>
        <a href="#" data-toggle="modal" data-target="#registModal">注册</a>
    </li>
</script>

<script>
    $(function(){
        if(${empty customer}){
            $('#navbarInfo').html(template('login'));
        }
        else {
            var data = {name:'${customer.name}'};
            $('#navbarInfo').html(template('welcome',data));
        }
    });
    //根据账号密码登录
    function loginByAccount() {
        $.post(
            '${pageContext.request.contextPath}/front/customer/loginByAccount',
                $('#frmLoginByAccount').serialize(),
            function (result) {
                //登录提示消息
                layer.msg(result.message,{time:1000});
                if(result.status == 1){
                    //局部刷新
                    $('#navbarInfo').html(template('welcome',result.data));
                    $('#loginModal').modal('hide');
                    $('#loginMessage').html();
                }
            }
        )
    }
    //退出登录
    function logout(){
      $.get(
            '${pageContext.request.contextPath}/front/customer/logout',
            function (result) {
                if(result.status == 1){
                    //全局刷新
                   // location.href='${pageContext.request.contextPath}/front/product/search?pageNum='+${pageInfo.pageNum};
                    //局部刷新
                   $('#navbarInfo').html(template('login'));
                }
            }
        );
    }
    //短信登录-获取短信验证码
    function sendVerificationCode(btn) {
        $.post(
            '${pageContext.request.contextPath}/front/sms/sendVerificationCode',
            {'phone':$('#phone').val()},
            function (result) {
                    if(result.status == 1){
                        var time = 60;
                        timer = setInterval(function () {
                            if(time>0){
                                $(btn).attr('disabled',true);
                                $(btn).html('重新发送('+time+')');
                            }
                            else{
                                $(btn).attr('disabled',false);
                                $(btn).html('重新发送');
                                //停止计时器，防止资源占用
                                clearInterval(timer);
                            }
                            time--;
                        },1000);
                        layer.msg(result.message);
                    }
            }
        );

    }

    //短信登录
    function loginBySms(){
        $.post(
            '${pageContext.request.contextPath}/front/sms/login',
            $('#frmLoginBySms').serialize(),
            function (result) {
                if(result.status == 1){
                    $('#navbarInfo').html(template('welcome',result.data));
                    $('#loginModal').modal('hide');
                }
                else{
                    alert("温馨提示:"+result.message);
                }
            }
        )
    }
    //注册
    function register() {
        $.post(
            '${pageContext.request.contextPath}/front/customer/register',
            $('#registerForm').serialize(),function (result) {
                //显示反馈信息
                layer.msg(result.message,{time:1000});
                //清空模态框
                $('#resetButton').trigger('click');
                //注册成功，则隐藏注册模态框
                if(result.status == 1){
                    $('#registModal').modal('hide');

                }
            }
        )
    }
</script>
<!-- navbar start -->
<div class="navbar navbar-default clear-bottom">
    <div class="container">

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" >
                <li class="active">
                    <a href="${pageContext.request.contextPath}/front/product/search">商城主页</a>
                </li>
                <li>
                    <a href="myOrders.html">我的订单</a>
                </li>
                <li>
                    <a href="cart.html">购物车</a>
                </li>
                <li class="dropdown">
                    <a href="center.html">会员中心</a>
                </li>
            </ul>
            <!--这里放模板1(已登录显示)或模板2(未登录显示) -->
            <ul class="nav navbar-nav navbar-right" id="navbarInfo"></ul>
        </div>
    </div>
</div>
<!-- navbar end -->

<!-- 修改密码模态框 start -->
<div class="modal fade" id="modifyPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel1">修改密码</h4>
            </div>
            <form action="" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">原密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">新密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">重复密码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                    <button type="reset" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                    <button type="submit" class="btn btn-warning">修&nbsp;&nbsp;改</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 修改密码模态框 end -->

<!-- 登录模态框 start  -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <!-- 用户名密码登陆 start -->
        <div class="modal-content" id="login-account">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">用户名密码登录</h4>
            </div>
            <form class="form-horizontal" method="post" id="frmLoginByAccount">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" placeholder="请输入用户名" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" placeholder="请输入密码" name="password">
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <a class="btn-link">忘记密码？</a> &nbsp;
                    <button type="button" class="btn btn-warning" onclick="loginByAccount()">登&nbsp;&nbsp;陆</button> &nbsp;&nbsp;
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                    <a class="btn-link" id="btn-sms-back">短信快捷登录</a>
                </div>
            </form>
        </div>
        <!-- 用户名密码登陆 end -->
        <!-- 短信快捷登陆 start -->
        <div class="modal-content" id="login-sms" style="display: none;" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">短信快捷登陆</h4>
            </div>
            <form class="form-horizontal" id="frmLoginBySms">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号：</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" placeholder="请输入手机号" name="phone" id="phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">验证码：</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" placeholder="请输入验证码" name="verificationCode" id="verificationCode">
                        </div>
                        <div class="col-sm-2">
                            <button class="pass-item-timer" type="button"  onclick="sendVerificationCode(this)">获取验证码</button>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <a class="btn-link">忘记密码？</a> &nbsp;
                    <button type="button" class="btn btn-warning" onclick="loginBySms()">登&nbsp;&nbsp;陆</button> &nbsp;&nbsp;
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                    <a class="btn-link" id="btn-account-back">用户名密码登录</a>
                </div>
            </form>
        </div>
        <!-- 短信快捷登陆 end -->
    </div>
</div>
<!-- 登录模态框 end  -->

<!-- 注册模态框 start -->
<div class="modal fade" id="registModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabelz">会员注册</h4>
            </div>
            <form id="registerForm" action="" class="form-horizontal" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户姓名:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登陆账号:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="loginName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录密码:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="password" name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">联系电话:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">联系地址:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="address">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                    <button type="reset" id= "resetButton" class="btn btn-warning">重&nbsp;&nbsp;置</button>
                    <button type="button" class="btn btn-warning" onclick="register()">注&nbsp;&nbsp;册</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 注册模态框 end -->

