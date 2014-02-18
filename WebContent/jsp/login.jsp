<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>系统登录</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/themes/icon.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui-lang-zh_CN.js"></script>
</head>
<body>
    <h2>系统登录</h2>
    <div style="margin:10px 0;"></div>
    <div class="easyui-panel" title="用户登录" style="width:400px">
        <div style="padding:10px 0 10px 60px">
        <form id="loginForm" method="post">
            <table>
                <tr>
                    <td>电子邮箱:</td>
                    <td><input class="easyui-validatebox" type="text" name="loginId" data-options="required:true,validType:'email'"></input></td>
                </tr>
                <tr>
                    <td>登录密码:</td>
                    <td><input class="easyui-validatebox" type="password" name="loginPassword" data-options="required:true"></input></td>
                </tr>
            </table>
        </form>
        </div>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">登录</a>
        </div>
    </div>
    <script>
        function submitForm(){
            $('#loginForm').form('submit', {
            	url:'<%=request.getContextPath() %>/user/login',
            	success:function(data){
            		alert(data);
            	}
            });
        }
    </script>
</body>
</html>