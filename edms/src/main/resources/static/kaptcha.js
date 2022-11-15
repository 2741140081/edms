$(function(){
    $('#verify').click(function(){
        var code = $('#code').val();
        $.ajax({
            type: 'GET',
            url: '/verify?code=' + code,
            success: function (result) {
                //将验证结果显示在p标签中
                $('#verifyResult').html(result);
            },
            error: function() {
                alert('请求失败');
            },
        });
    });
});