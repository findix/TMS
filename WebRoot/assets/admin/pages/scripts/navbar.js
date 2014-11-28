/**
 * Created by Sean on 2014/11/27.
 */

/**
 * 修改密码
 */
var changePassword = function () {
    $.post("/auth/changePassword",
        {
            password: $("input[name='password']").val()
        },
        function (data, status) {
            $("#changeNameModal").modal('hide');
        });
    return false;
};