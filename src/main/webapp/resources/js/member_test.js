$(function(){
    let contextPath = $('#contextPath').val();

    // 유효성 체크 및 회원가입 실행
    $(document).on('click', '#btnSubmit', function(){
        fileUpload("memProfile");
    });
});

//프로필 사진 업로드
let fileUpload = function(fileTagId){
    let form = new FormData();
    let fileTag = $('#'+fileTagId);
    form.append("profileImg", fileTag[0].files[0]);

    $.ajax({
        url: "/member/sign-up/upload/profileImg",
        type: "post",
        processData: false,
        contentType: false,
        data: form,
        success: function(re){
            alert("업로드 성공")
        },
        error:function(jqXHR){
            console.log(jqXHR.responseText);
        }
    });
}

