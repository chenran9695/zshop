<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>

    <script>
        $(function(){
            $('#role').val(${adminParams.roleId});
            //分页
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion:3,
                currentPage:${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
                onPageClicked:function (event,orginalEvent,type,page) {
                    $('#searchPageNum').val(page);
                    $('#form-search').submit();

                },
                itemTexts:function(type,page, current)
                {//文字翻译
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "尾页";
                        case "page":
                            return page;
                    }
                }
            });

        });
        //显示修改管理员信息的模态框
        function showModifyAdminModal(id) {

            $.post(
                '${pageContext.request.contextPath}/back/admin/findById',
                {'id':id},
                function (result) {

                        $('#adminId').val(result.data.id);
                        $('#adminName').val(result.data.name);
                        $('#adminLoginName').val(result.data.loginName);
                        $('#adminPhone').val(result.data.phone);
                        $('#adminEmail').val(result.data.email);
                        $('#adminRole').val(result.data.role.id);
                }
            );
        }
        //修改管理员信息
        function modifyAdmin() {
            $.post(
                '${pageContext.request.contextPath}/back/admin/modify',
                {   'id':$('#adminId').val(),
                    'name':$('#adminName').val(),
                    'loginName': $('#adminLoginName').val(),
                    'phone':$('#adminPhone').val(),
                    'email':$('#adminEmail').val(),
                    'RoleId':$('#adminRole').val()
                },
                function (result) {
                    layer.msg(result.message,{time:1000});
                    if(result.status == 1){
                        location.href='${pageContext.request.contextPath}/back/admin/findAll?pageNum='+${pageInfo.pageNum};
                    }
                }
            );
        }
        //添加管理员
        function addAdmin(){
            $.post(
                '${pageContext.request.contextPath}/back/admin/add',
                    $('#add-admin-modal').serialize(),
                function (result) {
                    layer.msg(result.message,{time:1000});
                    if(result.status == 1){
                        location.href='${pageContext.request.contextPath}/back/admin/findAll?pageNum='+${pageInfo.pageNum};
                    }
                }
            );
        }
        
    </script>
</head>

<body>
<!-- 系统用户管理 -->
<div class="panel panel-default" id="adminSet">
    <div class="panel-heading">
        <h3 class="panel-title">系统用户管理</h3>
    </div>
    <div class="panel-body ">
        <div class="showmargersearch">
            <form class="form-inline" id="form-search" action="${pageContext.request.contextPath}/back/admin/findByParams" method="post">
                <input  type="hidden" id="searchPageNum" name="pageNum" value="${pageInfo.pageNum}">
                <div class="form-group">
                    <label for="userName">姓名:</label>
                    <input type="text" class="form-control" id="userName" placeholder="请输入姓名" name="name" value="${adminParams.name}">
                </div>
                <div class="form-group">
                    <label for="loginName">帐号:</label>
                    <input type="text" class="form-control" id="loginName" placeholder="请输入帐号" name="loginName" value="${adminParams.loginName}">
                </div>
                <div class="form-group">
                    <label for="phone">电话:</label>
                    <input type="text" class="form-control" id="phone" placeholder="请输入电话" name="phone" value="${adminParams.phone}">
                </div>
                <div class="form-group">
                    <label for="role">角色</label>
                    <select class="form-control" name="roleId" id="role">
                        <option value="-1">全部</option>
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.id}">${role.roleName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="status">状态</label>
                    <select class="form-control" name="isValid" id="status">
                        <option value="-1">全部</option>
                        <option value="1" <c:if test="${adminParams.isValid==1}">selected</c:if> >有效</option>
                        <option value="0" <c:if test="${adminParams.isValid==0}">selected</c:if> >无效</option>
                    </select>
                </div>
                <input type="submit" value="查询" class="btn btn-primary" id="doSearch">
            </form>
        </div>
        <br>
        <input type="button" value="添加系统用户" class="btn btn-primary" id="doAddManger">
        <div class="show-list text-center" style="position: relative; top: 10px;">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">序号</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">帐号</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">邮箱</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">注册时间</th>
                    <th class="text-center">角色</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="admin">
                    <tr>
                        <td>${admin.id}</td>
                        <td>${admin.name}</td>
                        <td>${admin.loginName}</td>
                        <td>${admin.phone}</td>
                        <td>${admin.email}</td>
                        <td>
                            <c:if test="${admin.isValid==1}">有效</c:if>
                            <c:if test="${admin.isValid==0}">无效</c:if>
                        </td>
                        <td>
                           <fmt:formatDate value="${admin.createDate}" type="both"/>
                        </td>
                        <td>
                            <c:if test="${admin.role.id==1}">超级管理员</c:if>
                            <c:if test="${admin.role.id==2}">营销经理</c:if>
                            <c:if test="${admin.role.id==3}">营销专员</c:if>
                        </td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doMangerModify" value="修改" onclick="showModifyAdminModal(${admin.id})">
                            <input type="button" class="btn btn-danger btn-sm doMangerDisable" value="禁用">
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 使用bootstrap paginator分页插件 -->
            <ul id="pagination"></ul>
        </div>
    </div>
</div>

<!-- 添加系统用户 start -->
<div class="modal fade" tabindex="-1" id="myMangerUser">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">添加系统用户</h4>
            </div>
            <form id="add-admin-modal">
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="marger-username" class="col-sm-4 control-label">用户姓名：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="marger-username" name="name">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="marger-loginName" class="col-sm-4 control-label">登录帐号：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="marger-loginName" name="loginName">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="marger-password" class="col-sm-4 control-label">登录密码：</label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="marger-password" name="password">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="marger-phone" class="col-sm-4 control-label">联系电话：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="marger-phone" name="phone">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="marger-email" class="col-sm-4 control-label">联系邮箱：</label>
                    <div class="col-sm-4">
                        <input type="email" class="form-control" id="marger-email" name="email">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="role" class="col-sm-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                    <div class=" col-sm-4">
                        <select class="form-control" name="roleId">
                            <option>--请选择--</option>
                            <c:forEach items="${roles}" var="role">
                                <option value="${role.id}">${role.roleName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary add-Manger" type="button" onclick="addAdmin()">添加</button>
                <button class="btn btn-primary cancel" data-dismiss="modal" type="button">取消</button>
            </div>
            </form>
    </div>
</div>
</div>
<!-- 添加系统用户 end -->

<!-- 修改系统用户 start -->
<div class="modal fade" tabindex="-1" id="myModal-Manger">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">系统用户修改</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="adminId" class="col-sm-4 control-label">用户编号：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="adminId" readonly="readonly">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="adminName" class="col-sm-4 control-label">用户姓名：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="adminName">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="adminLoginName" class="col-sm-4 control-label">登录帐号：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="adminLoginName" readonly="readonly">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="adminPhone" class="col-sm-4 control-label">联系电话：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="adminPhone">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="adminEmail" class="col-sm-4 control-label">联系邮箱：</label>
                    <div class="col-sm-4">
                        <input type="email" class="form-control" id="adminEmail">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="adminRole" class="col-sm-4 control-label">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                    <div class=" col-sm-4">
                        <select class="form-control" id="adminRole" name="roleId">
                            <option>--请选择--</option>
                            <c:forEach items="${roles}" var="role">
                                <option value="${role.id}">${role.roleName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary doMargerModal" onclick="modifyAdmin()">修改</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 修改系统用户 end -->
</body>
</html>
