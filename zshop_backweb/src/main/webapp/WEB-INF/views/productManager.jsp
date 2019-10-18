<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/file.css"/>
    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/bootstrapValidator.min.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrapValidator.min.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script>
        $(function(){
            //上传图像预览
            $('#product-image').on('change',function() {
                $('#img').attr('src', window.URL.createObjectURL(this.files[0]));
            });
            $('#pro-image').on('change',function() {
                $('#img2').attr('src', window.URL.createObjectURL(this.files[0]));
            });

            //服务器提示信息
            var successMsg = '${successMsg}';
            var errorMsg = '${errorMsg}';
            if(successMsg !=''){
                layer.msg(successMsg,{
                    time:2000
                })
            }
            if(errorMsg !=''){
                layer.msg(errorMsg,{
                    time:2000
                })
            }
            //使用bootstrap Validator插件进行表单数据的校验
            $('#formAddProduct').bootstrapValidator({
                feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                },
                fields:{
                    name:{
                        validators:{
                            notEmpty:{
                                message:'商品名称不能为空！'
                            },
                            remote:{
                                url:'${pageContext.request.contextPath}/back/product/checkName'
                            }
                        },
                     price:{
                            validators: {
                                notEmpty: '请输入价格！',
                                regexp: {
                                            regexp:/^\d+(\.\d+)?$/,
                                            message: "商品价格格式不正确！"
                                        }
                            }
                     },
                     file:{
                            validators:{
                                notEmpty:"请选择图片",
                            }
                     },
                      productTypeId:{
                            validators:{
                                notEmpty:"请选择商品类型"
                            }
                      }
                    }
                }

        })
            //如果当前页面数据为空，则加载最后一页的数据,这里针对删除商品时可能出现的显示bug
            if(${pageInfo.pages<pageInfo.pageNum}){
                //重新加载数据
                location.href='${pageContext.request.contextPath}/back/product/findAll?pageNum='+${pageInfo.pages};
            }
            //分页
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion:3,
                currentPage:${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
                pageUrl:function(type,page,current)
                {
                    return '${pageContext.request.contextPath}/back/product/findAll?pageNum='+page;
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

        //展示商品信息
        function showProduct(id){
            $.post(
                '${pageContext.request.contextPath}/back/product/findById',
                {'id':id},
                function(result){

                        $('#pro-num').val(result.data.id);
                        $('#pro-name').val(result.data.name);
                        $('#pro-price').val(result.data.price);
                        $('#pro-typeName').val(result.data.productType.id);
                        $('#img2').attr('src','${pageContext.request.contextPath}/back/product/getImage?path='
                            +result.data.image);
                }
            );
        }
        //显示删除商品确认窗口
        function showConfirmDelete(id,name){
            $('#deleteProductName').text(name);
            $('#deleteProductId').val(id);
            $('#deleteProductConfirm').modal('show');
        }
    </script>
</head>

<body>
<div class="panel panel-default" id="userPic">
    <div class="panel-heading">
        <h3 class="panel-title">商品管理</h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加商品" class="btn btn-primary" id="doAddPro">
        <br>
        <br>
        <div class="show-list text-center">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">商品</th>
                    <th class="text-center">价格</th>
                    <th class="text-center">产品类型</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>${product.productType.name}</td>
                        <td>
                            <c:if test="${product.productType.status == 1}">有效商品</c:if>
                            <c:if test="${product.productType.status == 0}">无效商品</c:if>
                        </td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doProModify" value="修改"
                                   onclick="showProduct(${product.id})">
                            <input type="button" class="btn btn-warning btn-sm doProDelete" value="删除"
                            onclick="showConfirmDelete(${product.id},'${product.name}')">
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

<!-- 添加商品 start -->
<div class="modal fade" tabindex="-1" id="Product">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <form action="${pageContext.request.contextPath}/back/product/add" class="form-horizontal" method="post"
            enctype="multipart/form-data" id="formAddProduct">
            <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">添加商品</h4>
                </div>
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <div class="form-group">
                            <label for="product-name" class="col-sm-4 control-label">商品名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="product-name" name="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-price" class="col-sm-4 control-label" >商品价格：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="product-price" name="price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-8" >
                                <a href="javascript:;" class="file">选择文件
                                    <input type="file" name="file" id="product-image">
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control" name="productTypeId">
                                    <option value="">--请选择--</option>
                                   <c:forEach items="${productTypes}" var="productType">
                                       <option value="${productType.id}">${productType.name}</option>
                                   </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <!-- 显示图像预览 -->
                        <img style="width: 160px;height: 180px;" id="img">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">添加</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 添加商品 end -->

<!-- 修改商品 start -->
<div class="modal fade" tabindex="-1" id="myProduct">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <form action="${pageContext.request.contextPath}/back/product/modify" class="form-horizontal" method="post"
            enctype="multipart/form-data">
            <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">修改商品</h4>
                </div>
                <div class="modal-body text-center row">
                    <div class="col-sm-8">
                        <div class="form-group">
                            <label for="pro-num" class="col-sm-4 control-label">商品编号：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="pro-num" name="id" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-name" class="col-sm-4 control-label">商品名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="pro-name" name="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-price" class="col-sm-4 control-label" >商品价格：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="pro-price" name="price">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pro-image" class="col-sm-4 control-label">商品图片：</label>
                            <div class="col-sm-8">
                                <a class="file">
                                    选择文件 <input type="file" name="file" id="pro-image">
                                </a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">商品类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="pro-typeName" name="productTypeId">
                                    <option>--请选择--</option>
                                    <c:forEach items="${productTypes}" var="productType">
                                        <option value="${productType.id}">${productType.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <!-- 显示图像预览 -->
                        <img style="width: 160px;height: 180px;" id="img2">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary updatePro" onclick="submit()">修改</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal"·>取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
    <!-- 修改商品 end -->

<!-- 确认删除商品 start -->
<div class="modal fade" tabindex="-1" id="deleteProductConfirm">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <form action="${pageContext.request.contextPath}/back/product/removeById" class="form-horizontal" method="post"
              enctype="multipart/form-data">
            <input type="hidden" name="pageNum" value="${pageInfo.pageNum}">
            <input type="hidden" id="deleteProductId" name="id">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">温馨提示</h4>
                </div>
                <div class="modal-body text-center row">
                    <h4>确认要删除商品'<a id="deleteProductName"></a>'吗?</h4>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary updatePro" onclick="submit()">删除</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 确认删除商品 end -->
</body>
</html>
