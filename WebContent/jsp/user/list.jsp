<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户维护</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/themes/icon.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui-lang-zh_CN.js"></script>
</head>
<body>
    <table id="dg" title="系统用户" class="easyui-datagrid" style="width:700px;height:250px"
    	data-options="singleSelect:true,collapsible:true,rownumbers:true,pagination:true,fitColumns:true,
    		url:'<%=request.getContextPath() %>/user/list',method:'get',
    		toolbar:'#toolbar'">
        <thead>
            <tr>
                <th data-options="field:'loginId',width:100">账号</th>
                <th data-options="field:'name',width:100">姓名</th>
                <th data-options="field:'email',width:100">邮箱</th>
                <th data-options="field:'branch',width:100,
                	formatter:function(value,row,index){
                		return row.branch.name;
                	}
                	">机构</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新建</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">移除</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">用户信息</div>
        <form id="fm" method="post" novalidate>
        	 <div class="fitem">
                <label>账号：</label>
                <input name="loginId" class="easyui-validatebox" data-options="required:true" />
            </div>
            <div class="fitem">
                <label>密码：</label>
                <input name="loginPwd" type="password" class="easyui-validatebox" data-options="required:true" />
            </div>
            <div class="fitem">
                <label>姓名：</label>
                <input name="name" class="easyui-validatebox" data-options="required:true" />
            </div>
            <div class="fitem">
                <label>邮箱：</label>
                <input name="email" class="easyui-validatebox" data-options="validType:'email'" />
            </div>
            <div class="fitem">
                <label>机构：</label>
                <input id="combotree-branch" name="branch" class="easyui-combotree" 
                	data-options="url:'<%=request.getContextPath() %>/branch/list',method:'get',required:true"
                />
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('setTitle','新建用户');
            $('#fm').form('clear');
            url = 'save_user.php';
        }
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','编辑用户');
                $('#fm').form('load',row);
                $('#combotree-branch').combotree('setValue', row.branch.branchId);
                url = '<%=request.getContextPath() %>/user/update';
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                    if (r){
                        $.post('destroy_user.php',{id:row.id},function(result){
                            if (result.success){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
    </style>
</body>
</html>