$(function(){
    contextPath = $('#contextPath').val();

    // 아이디 중복체크 결과 표시
    $(document).on('keyup', '#memId', function(){
        let memId = $('#memId').val();
        let isIdUnique = idDupCheck(memId);
        if(memId!="") {
            if(isIdUnique){
                $('#dupCheck').html('사용 하실 수 있는 아이디 입니다');
            }else{
                $('#dupCheck').html('이미 존재 하는 아이디 입니다');
            }
        }else{
            $('#dupCheck').html('');
        }
    });

    // 유효성 체크 및 회원가입 실행
    $(document).on('click', '#btnSubmit', function(){
        let memId = $('#memId').val();
        let memName = $('#memName').val();
        let password = $('#password').val();
        let memEmail = $('#memEmail').val();
        let imgFile = $('#memProfile')[0].files[0];
        console.log(imgFile);

        let memberInfo = {
            "memId": memId,
            "memName": memName,
            "password": password,
            "memEmail": memEmail
        }
        console.log(memberInfo);
        if(idCheck(memId) && nameCheck(memName) && passwordCheck(password) && emailCheck(memEmail)){
            if(imgFile != undefined){
                insertMemberInfo(memberInfo, "memProfile");
            }else{
                insertMemberInfo(memberInfo, null);
            }
        }
    });
});

let contextPath;

//아이디 중복 체크
let idDupCheck = function(memId){
    let re = false;
    if(memId != ""){
        $.ajax({
            url: contextPath+"/member/sign-up/idCheck/"+memId,
            success: function(data){
                re = data;
                console.log("idChecked")
            },
            async: false
        });
    }
    return re;
}

//아이디 유효성 체크
let idCheck = function(id){
    if(id!=""){
        if(!/^[a-zA-Z0-9]{4,20}$/.test(id)){
            alert('아이디는 숫자와 영문 조합으로 4~20자리를 사용해야 합니다.');
            return false;
        }else{
            if(idDupCheck(id)){
                return true;
            }else{
                alert('이미 사용중인 아이디 입니다.');
                return false;
            }
        }
    }else{
        alert('아이디를 입력해 주세요');
        return false;
    }
}

//이름 유효성 체크
let nameCheck = function(name){
    console.log(name);
    if(name!=""){
        if(!/^[a-zA-Z가-힣]{2,20}$/.test(name)){
            alert('이름은 문자만 입력해야합니다.');
            return false;
        }else{
            return true
        }
    }else{
        alert('이름을 입력해 주세요.');
        return false;
    }
}

//비밀번호 유효성 체크
let passwordCheck = function(password){
    if(password!=""){
        if(!/^[a-zA-Z0-9]{8,20}$/.test(password)){
            alert('비밀번호는 숫자와 영문자 조합으로 8~20자리를 사용해야 합니다.');
            return false;
        }else{
            return true;
        }
    }else{
        alert('비밀번호를 입력해 주세요');
        return false;
    }
}

//비밀번호 확인 유효성 체크
let isPasswordSame = function(){

}

//이메일 유효성 체크
let emailCheck = function(email){
    if(email!=""){
        if(!/^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/.test(email)){
            alert('이메일 형식이 맞지 않습니다');
            return false;
        }else{
            return true;
        }
    }else{
        alert('이메일을 입력해 주세요');
        return false;
    }
}

//회원정보 전송
let insertMemberInfo = function(memberInfo, fileTagId){
    $.ajax({
        url:contextPath+"/member/insert",
        type:"post",
        data: JSON.stringify(memberInfo),
        contentType: "application/json",
        success: function(re){
            if(re == 1 && fileTagId != null){
                fileUpload(fileTagId);
                alert("회원가입이 완료되었습니다.")
                location.href = "/member/login";
            }
        },
        error: function(){
            alert("회원가입 오류입니다");
        }
    })
}

//프로필 사진 업로드
let fileUpload = function(fileTagId){
    let form = new FormData();
    let fileTag = $('#'+fileTagId);
    let memId = $('#memId').val();
    form.append("profileImg", fileTag[0].files[0]);
    form.append("memId", memId);

    $.ajax({
        url: contextPath+"/member/sign-up/upload/profileImg",
        type: "post",
        processData: false,
        contentType: false,
        data: form,
        success: function(re){
            console.log("업로드 성공")
        },
        error:function(jqXHR){
            console.log(jqXHR.responseText);
        }
    });
}

