function getCurrentDateTime() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    return year + "-" + formatZero(month) + "-" + formatZero(day) + " " + formatZero(hours) + ":" + formatZero(minutes) + ":" + formatZero(seconds);
}

function getCurrentDate() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    return year + "-" + formatZero(month) + "-" + formatZero(day);
}

/**************************************/
var url;
var id;

function resetValue() {
    $('#fm').form('clear');
}

function closeDialog() {
    $("#dlg").dialog("close");
    resetValue();
}

function save() {
    $("#fm").form("submit", {
        url: url,
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (result) {
            result = eval('(' + result + ')');
            if (result.errNo == 0) {
                $.messager.alert("系统提示", "保存成功");
                $("#dlg").dialog("close");
                $("#dg").datagrid("reload");
                resetValue();
            } else {
                $.messager.alert("系统提示", result.message);
            }
        }
    });
}

function openAddDialog(title, module) {
    $("#dlg").dialog("open").dialog("setTitle", title);
    url = module;
}

function search() {
    $("#dg").datagrid('load', {
        "name": $("#name").val(),
    });
}

function del(url) {
    var selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length == 0) {
        $.messager.alert("系统提示", "请选择要删除的数据！");
        return;
    }
    var strIds = [];
    for (var i = 0; i < selectedRows.length; i++) {
        strIds.push(selectedRows[i].id);
    }
    var ids = strIds.join(",");
    $.messager.confirm(
        "系统提示", "请注意：如果删除园所，那么园所下的班级和学生都会被删除，同样的如果删除班级，班级下的学生也会被删除。确认删除吗？",
        function (r) {
            if (r) {
                $.post(
                    url,
                    {ids: ids},
                    function (result) {
                        if (result.errNo == 0) {
                            $.messager.alert("系统提示", "数据已成功删除！");
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("系统提示", result.message);
                        }
                    }, "json");
            }
        }).window({width: 500, height: 150});
}

function openModifyDialog(module) {
    var selectedRows = $("#dg").datagrid('getSelections');
    if (selectedRows.length != 1) {
        $.messager.alert("系统提示", "请选择一条要编辑的数据！");
        return;
    }
    var row = selectedRows[0];
    $("#dlg").dialog("open").dialog("setTitle", "修改信息");
    $('#fm').form('load', row);
    url = module + row.id;
}