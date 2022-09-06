$(function(){
    let contextPath = $('#contextPath').val();
    let memId = $('#memId').val();
    let auth = $('#auth').val();

    $(document).on('click', '#btnToLoginPage', function(){
       location.href = contextPath+'/member/login';
    });

    //검색
    $(document).on('click', '#btnSearch', function(){
        keyword = $('#keyword').val();
        location.href= contextPath+"/youtube/home?keyword="+keyword
    });

    //썸네일 클릭시 재생화면으로 이동
    $(document).on('click', '.playThis', function(){
       let url = $(this).attr("url");
       location.href= contextPath+"/youtube/play/"+url;
    });

    //계정설정 modal 생성
    $.ajax({
        url: contextPath+"/member/findById/"+memId,
        method: 'post',
        success: function(m){
            console.log(m);
            $('#accountSettingModal').append(memInfoModal(m));
        }
    });

    //유효성 체크 및 회원정보 수정
    $(document).on('click', '.btnSubmit', function(){
        let memId = $(this).attr('memid');
        let memEmail = $('#'+memId+'Email').val();
        let enabled = $('#'+memId+'Enabled').val();
        let authority = $('#'+memId+'Authority').val();
        let imgInputTagId = memId+'Img';
        console.log(imgInputTagId);
        console.log(isFileSelected(imgInputTagId));

        let memberInfo = {
            "memId": memId,
            "memEmail": memEmail,
            "authority": authority,
            "enabled": enabled
        }

        console.log(memberInfo);
        if(emailCheck(memEmail)){
            if(isFileSelected(imgInputTagId)){
                insertMemberInfo(memberInfo, imgInputTagId, memId);
            }else{
                insertMemberInfo(memberInfo, null, memId);
            }
        }
    });
});

//회원정보 전송
let insertMemberInfo = function(memberInfo, fileTagId, memId){
    $.ajax({
        url:contextPath+"/member/update",
        type:"post",
        data: JSON.stringify(memberInfo),
        contentType: "application/json",
        success: function(re){
            if(re == 1 && fileTagId != null){
                fileUpload(fileTagId, memId);
                alert("회원정보가 수정되었습니다.");
                location.href = contextPath
            }else if(re == 1){
                alert("회원정보가 수정되었습니다.");
                location.href = contextPath
            }
        },
        error: function(){
            alert("오류가 발생하였습니다,");
        }
    });
}
let auth;

//프로필 사진 업로드
let fileUpload = function(fileTagId, memId){
    let form = new FormData();
    let fileTag = $('#'+fileTagId);
    form.append("profileImg", fileTag[0].files[0]);
    form.append("memId", memId);

    $.ajax({
        url: contextPath+"/member/sign-up/upload/profileImg/update",
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

//input 태그 파일 선택 유무 판단
let isFileSelected = function(tagId){
    let fileName = $('#'+tagId)[0].files[0];
    if(fileName != undefined){
        return true;
    }else{
        return false;
    }
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

let memInfoModal = function(m, auth){
    if(auth == 'master' || auth == 'manager'){

    return $('' +
        '<div class="modal fade" id="' + m.memId + '" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">\n' +
        '  <div class="modal-dialog modal-dialog-centered" role="document">\n' +
        '    <div class="modal-content" style="background: #454d55;color: #95999c;">\n' +
        '      <div class="modal-header" style="border-color: #95999c">\n' +
        '        <h5 class="modal-title" id="exampleModalLongTitle">' + m.memId + '</h5>\n' +
        '        <button type="button" class="close" data-dismiss="modal" aria-label="Close">\n' +
        '          <span aria-hidden="true">&times;</span>\n' +
        '        </button>\n' +
        '      </div>\n' +
        '      <div class="modal-body" style="border-color: #95999c">\n' +
        '        <table style="font-size: smaller">' +
        '           <tr>' +
        '               <td><span class="py-2">ID</span></td>' +
        '               <td><span>:</span></td>' +
        '               <td><input type="text" readonly class="form-control ml-3" style="height: 80%" value="' + m.memId + '"></td>' +
        '           </tr>' +
        '           <tr>' +
        '               <td><span>이름</span></td>' +
        '               <td><span>:</span></td>' +
        '               <td><input type="text" readonly class="form-control ml-3" style="height: 80%" value="' + m.memName + '"></td>' +
        '           </tr>' +
        '           <tr>' +
        '               <td><span>가입일</span></td>' +
        '               <td><span>:</span></td>' +
        '               <td><input type="text" readonly class="form-control ml-3" value="' + dateFormatter(m.registDate) + '"></td>' +
        '           </tr>' +
        '           <tr>' +
        '               <td><span>수정일</span></td>' +
        '               <td><span>:</span></td>' +
        '               <td><input type="text" readonly class="form-control ml-3" value="' + dateFormatter(m.updateDate) + '"></td>' +
        '           </tr>' +
        '           <tr>' +
        '               <td><span>가입ID</span></td>' +
        '               <td><span>:</span></td>' +
        '               <td><input type="text" readonly class="form-control ml-3" value="' + m.registId + '"></td>' +
        '           </tr>' +
        '           <tr>' +
        '               <td><span>수정ID</span></td>' +
        '               <td><span>:</span></td>' +
        '               <td><input type="text" readonly class="form-control ml-3" value="' + m.updateId + '"></td>' +
        '           </tr>' +
        '           <tr>' +
        '               <td><span>가입IP</span></td>' +
        '               <td><span>:</span></td>' +
        '               <td><input type="text" readonly class="form-control ml-3" value="' + m.registIp + '"></td>' +
        '           </tr>' +
        '           <tr>' +
        '               <td><span>수정IP</span></td>' +
        '               <td><span>:</span></td>' +
        '               <td><input type="text" readonly class="form-control ml-3" value="' + m.updateIp + '"></td>' +
        '           </tr>' +
        '           <tr>' +
        '               <td><span>이메일</span></td>' +
        '               <td><span>:</span></td>' +
        '               <td><input id="' + m.memId + 'Email" type="text" class="form-control ml-3" value="' + m.memEmail + '"></td>' +
        '           </tr>' +
        '           <tr>' +
        '               <td><span>상태</span></td>' +
        '               <td><span>:</span></td>' +
        '               <td>' +
        '                   <select class="form-control ml-3" name="enabled" id="' + m.memId + 'Enabled">' +
        '                       <option class="' + m.memId + 'Enabled" value="1">활성</option>' +
        '                       <option class="' + m.memId + 'Disabled" value="0">비활성</option>' +
        '                   </select>' +
        '               </td>' +
        '           </tr>' +
        '           <tr>' +
        '               <td><span>권한</span></td>' +
        '               <td><span>:</span></td>' +
        '               <td>' +
        '                   <select class="form-control ml-3" name="authority" id="' + m.memId + 'Authority">' +
        '                       <option class="' + m.memId + 'Master" value="master">master</option>' +
        '                       <option class="' + m.memId + 'Manager" value="manager">manager</option>' +
        '                       <option class="' + m.memId + 'User" value="user">user</option>' +
        '                   </select>' +
        '               </td>' +
        '           </tr>' +
        '           <tr>' +
        '               <td><span>프로필이미지&nbsp;&nbsp;</span></td>' +
        '               <td><span>:</span></td>' +
        '               <td>' +
        '                   <img class="rounded-circle ml-3" src="' + contextPath + '/resources/upload/profileImg/' + m.imgFileName + '" alt="" style="width: 100px; height: 100px">' +
        '                   <input class="form-control ml-3" id="' + m.memId + 'Img" type="file">' +
        '               </td>' +
        '           </tr>' +
        '        </table>\n' +
        '      </div>\n' +
        '      <div class="modal-footer" style="border-color: #95999c">\n' +
        '        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>\n' +
        '        <button memid="' + m.memId + '" type="button" class="btn btn-dark btnSubmit">회원정보 수정</button>\n' +
        '      </div>\n' +
        '    </div>\n' +
        '  </div>\n' +
        '</div>');
    }else{
        return $('' +
            '<div class="modal fade" id="' + m.memId + '" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">\n' +
            '  <div class="modal-dialog modal-dialog-centered" role="document">\n' +
            '    <div class="modal-content" style="background: #454d55;color: #95999c;">\n' +
            '      <div class="modal-header" style="border-color: #95999c">\n' +
            '        <h5 class="modal-title" id="exampleModalLongTitle">' + m.memId + '</h5>\n' +
            '        <button type="button" class="close" data-dismiss="modal" aria-label="Close">\n' +
            '          <span aria-hidden="true">&times;</span>\n' +
            '        </button>\n' +
            '      </div>\n' +
            '      <div class="modal-body" style="border-color: #95999c">\n' +
            '        <table style="font-size: smaller">' +
            '           <tr>' +
            '               <td><span class="py-2">ID</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" style="height: 80%" value="' + m.memId + '"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>이름</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" style="height: 80%" value="' + m.memName + '"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>가입일</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" value="' + dateFormatter(m.registDate) + '"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>수정일</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" value="' + dateFormatter(m.updateDate) + '"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>가입ID</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" value="' + m.registId + '"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>수정ID</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" value="' + m.updateId + '"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>가입IP</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" value="' + m.registIp + '"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>수정IP</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" value="' + m.updateIp + '"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>이메일</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input id="' + m.memId + 'Email" type="text" class="form-control ml-3" value="' + m.memEmail + '"></td>' +
            '           </tr>' +
            '           <tr>' +
            '           <tr>' +
            '               <td><span>프로필이미지&nbsp;&nbsp;</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td>' +
            '                   <img class="rounded-circle ml-3" src="' + contextPath + '/resources/upload/profileImg/' + m.imgFileName + '" alt="" style="width: 100px; height: 100px">' +
            '                   <input class="form-control ml-3" id="' + m.memId + 'Img" type="file">' +
            '               </td>' +
            '           </tr>' +
            '        </table>\n' +
            '      </div>\n' +
            '      <div class="modal-footer" style="border-color: #95999c">\n' +
            '        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>\n' +
            '        <button memid="' + m.memId + '" type="button" class="btn btn-dark btnSubmit">회원정보 수정</button>\n' +
            '      </div>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '</div>');
    }

}

let dateFormatter = function(isoDate){
    let date = new Date(isoDate);
    let yyyy = date.getFullYear();
    let MM = date.getMonth()+1;
    let dd = date.getDay();
    let HH = date.getHours();
    let mm = date.getMinutes();
    let ss = date.getSeconds();
    let formattedDate = yyyy+'-'+MM+'-'+dd+' '+HH+':'+mm+':'+ss
    return formattedDate;
}