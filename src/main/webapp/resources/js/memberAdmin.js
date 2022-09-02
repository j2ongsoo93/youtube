$(function(){
    contextPath = $('#contextPath').val();
    if($('#profileImgFile').val()!= ''){
        profileImgName = $('#profileImgFile').val();
    }else(
        profileImgName = "img.png"
    )

    console.log("profileImgName: "+profileImgName);
    console.log("ContextPath: "+contextPath);

    //검색조건
    let condition = {
        "searchCol": null,
        "keyword": null
    }

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

    let list = requestMemList(condition);
    console.log(list);
    printMemberList(requestMemList(condition));
});

let contextPath;
let profileImgName;

let requestMemList = function(condition){
    let list;

    $.ajax({
        url: contextPath+"/member/search",
        method: 'post',
        data: JSON.stringify(condition),
        contentType: "application/json",
        async:false,
        success: function(data){
            list = data;
        },
        error: function(){
            console.log("ERROR")
        }
    });
    return list;
}

let printMemberList = function(list){
    let table = $('<table class="table table-dark"></table>');
    let listHead = $('<thead id="memListHead">\n' +
        '                    <tr>\n' +
        '                        <th>ID</th>\n' +
        '                        <th>이름</th>\n' +
        '                        <th>가입일</th>\n' +
        '                        <th>email</th>\n' +
        '                        <th>상태</th>\n' +
        '                        <th>권한</th>\n' +
        '                        <th></th>\n' +
        '                    </tr>\n' +
        '                </thead>');
    let listBody = $('<tbody id="memListBody"></tbody>');

    $(table).append(listHead);

    $.each(list, function(){
        let enabled;
        if(this.enabled == 1){
            enabled = "활성";
        }else{
            enabled = "비활성";
        }

        let listTr = $(
            '                    <tr>\n' +
            '                        <td>'+this.memId+'</td>\n' +
            '                        <td>'+this.memName+'</td>\n' +
            '                        <td>'+dateFormatter(this.registDate)+'</td>\n' +
            '                        <td>'+this.memEmail+'</td>\n' +
            '                        <td>'+enabled+'</td>\n' +
            '                        <td>'+this.authority+'</td>\n' +
            '                        <td><button type="button" class="btn btn-dark btn-sm" ' +
            '                               data-toggle="modal" data-target="#'+this.memId+'"' +
            '                               memId="'+this.memId+'">수정</button></td>\n' +
            '                    </tr>');

        let memInfoModal =$('' +
            '<div class="modal fade" id="'+this.memId+'" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">\n' +
            '  <div class="modal-dialog modal-dialog-centered" role="document">\n' +
            '    <div class="modal-content" style="background: #454d55;color: #95999c;">\n' +
            '      <div class="modal-header" style="border-color: #95999c">\n' +
            '        <h5 class="modal-title" id="exampleModalLongTitle">'+this.memId+'</h5>\n' +
            '        <button type="button" class="close" data-dismiss="modal" aria-label="Close">\n' +
            '          <span aria-hidden="true">&times;</span>\n' +
            '        </button>\n' +
            '      </div>\n' +
            '      <div class="modal-body" style="border-color: #95999c">\n' +
            '        <table style="font-size: smaller">' +
            '           <tr>' +
            '               <td><span class="py-2">ID</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" style="height: 80%" value="'+this.memId+'"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>이름</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" style="height: 80%" value="'+this.memName+'"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>가입일</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" value="'+dateFormatter(this.registDate)+'"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>수정일</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" value="'+dateFormatter(this.updateDate)+'"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>가입ID</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" value="'+this.registId+'"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>수정ID</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" value="'+this.updateId+'"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>가입IP</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" value="'+this.registIp+'"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>수정IP</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input type="text" readonly class="form-control ml-3" value="'+this.updateIp+'"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>이메일</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td><input id="'+this.memId+'Email" type="text" class="form-control ml-3" value="'+this.memEmail+'"></td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>상태</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td>' +
            '                   <select class="form-control ml-3" name="enabled" id="'+this.memId+'Enabled">' +
            '                       <option class="'+this.memId+'Enabled" value="1">활성</option>' +
            '                       <option class="'+this.memId+'Disabled" value="0">비활성</option>' +
            '                   </select>' +
            '               </td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>권한</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td>' +
            '                   <select class="form-control ml-3" name="authority" id="'+this.memId+'Authority">' +
            '                       <option class="'+this.memId+'Master" value="master">master</option>' +
            '                       <option class="'+this.memId+'Manager" value="manager">manager</option>' +
            '                       <option class="'+this.memId+'User" value="user">user</option>' +
            '                   </select>' +
            '               </td>' +
            '           </tr>' +
            '           <tr>' +
            '               <td><span>프로필이미지&nbsp;&nbsp;</span></td>' +
            '               <td><span>:</span></td>' +
            '               <td>' +
            '                   <img class="rounded-circle ml-3" src="'+contextPath+'/resources/upload/profileImg/'+this.imgFileName+'" alt="" style="width: 100px; height: 100px">' +
            '                   <input class="form-control ml-3" id="'+this.memId+'Img" type="file">' +
            '               </td>' +
            '           </tr>' +
            '        </table>\n' +
            '      </div>\n' +
            '      <div class="modal-footer" style="border-color: #95999c">\n' +
            '        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>\n' +
            '        <button memid="'+this.memId+'" type="button" class="btn btn-dark btnSubmit">회원정보 수정</button>\n' +
            '      </div>\n' +
            '    </div>\n' +
            '  </div>\n' +
            '</div>');

        $(listBody).append(listTr);
        $('#memListContainer').append(memInfoModal);
    });
    $(table).append(listBody);
    $('#memListContainer').append(table);

    $.each(list, function(){
        //selectBox selected
        if(this.authority == 'master'){
            $('option[class="'+this.memId+'Master"]').prop('selected', true);
        }else if(this.authority == 'manager'){
            $('option[class="'+this.memId+'Manager"]').prop('selected', true);
        }else if(this.authority == 'user'){
            $('option[class="'+this.memId+'User"]').prop('selected', true);
        }

        if(this.enabled == 1){
            $('option[class="'+this.memId+'Enabled"]').prop('selected', true);
        }else{
            $('option[class="'+this.memId+'Disabled"]').prop('selected', true);
        }
    });
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