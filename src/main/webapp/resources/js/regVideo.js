$(function(){
    let contextPath = $('#contextPath').val();

    $(document).on('click', '#btnCancel', function(){
        location.href = contextPath+"/youtube/myPage";
    });

    $(document).on('click', '#btnReg', function(){
        $('#regForm').submit();
    });
});



