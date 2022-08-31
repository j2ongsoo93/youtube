$(function(){
   console.log("youtubePlay.js 01")

   contextPath = $('#contextPath').val();
   if($('#profileImgFile').val()!= ''){
      profileImgName = $('#profileImgFile').val();
   }else(
      profileImgName = "img.png"
   )

   //영상 인덱스 가져오기
   let str = location.href.split("/");
   let ytbIdx = str[str.length - 1];

   console.log("profileImgName: "+profileImgName);
   console.log("ContextPath: "+contextPath);

   // info의 /n을 <br>로 치환
   let content = $('#ytbInfo').html();
   $('#ytbInfo').html(content.replaceAll("\\n", "<br>"));
   
   //memId, 채널명 저장
   let ytbChannelName = $('#channelName').attr('channelName');

   // 구독 여부 기본값
   let isSubscribed = false;

   let inputData;
   let memId = $("#memId").val();
   if(memId != undefined){
      inputData = {"memId": memId ,"ytbChannelName": ytbChannelName};
      //채널 구독 여부 조회
      $.ajax({
         url:contextPath+"/youtube/isSubscribed",
         method: 'post',
         data: JSON.stringify(inputData),
         contentType: 'application/json',
         async:false,
         success: function(data){
            isSubscribed = data;
         },
         error: function(){
            console.log('error');
         }
      })
   }



   console.log("접속 아이디: "+memId);

   // 구독 여부에 따라 바뀔 구독 버튼
   let btnSubs = $('<button class="btn btn-primary ml-auto mr-1" style="background:red; border-color: red; width: 25%" id="subsChannel" channelName="${y.ytbChannelName}"><span className="font-weight-bold"> 구독 </span></button>');
   let btnCancelSubs = $('<button class="btn btn-light ml-auto mr-1" style="width: 25%" id="cancelSubs" channelName="${y.ytbChannelName}"><span class="font-weight-bold"> 구독중 </span></button>');
   
   // 구독과 로그인 여부에 따라 구독 버튼 배치
   if(memId != undefined){
      if (!isSubscribed) {
         $('#subsInfoContainer').append(btnSubs);
      } else {
         $('#subsInfoContainer').append(btnCancelSubs);
      }
   }

   //info 더보기
   $(document).on('click', '#moreInfo', function(){
      $('#ytbInfo').removeAttr("style");
      $(this).remove();
      $('#infoContainer').append('<br><span id="lessInfo">간략히</span>')
   });

   //info 간략히
   $(document).on('click', '#lessInfo', function(){
      $('#ytbInfo').attr("style", "color: white; text-overflow:ellipsis;overflow:hidden; width: 100%;display: -webkit-box; -webkit-line-clamp: 2;-webkit-box-orient: vertical;");
      $(this).remove();
      $('#infoContainer').append('<span id="moreInfo">더보기</span>')
   });

   // 채널정보 클릭 시 유튜브 채널로 이동
   $(document).on('click', '.toChannel', function(){
      location.href = $('#toChannel').attr('url');
   });

   //구독하기
   $(document).on('click', '#subsChannel', function(){
      console.log(inputData);
      $.ajax({
         url: contextPath+"/youtube/subsChannel",
         method: "post",
         data: JSON.stringify(inputData),
         contentType: "application/json",
         error: function(){
            console.log("error");
         },
         success: function(){
            $('#subsChannel').remove();
            $('#subsInfoContainer').append(btnCancelSubs);
         }
      });
   });

   //구독 취소
   $(document).on('click', '#cancelSubs', function(){
      console.log(inputData);
      $.ajax({
         url: contextPath+"/youtube/cancelSubs",
         method: "post",
         data: JSON.stringify(inputData),
         contentType: "application/json",
         error: function(){
            console.log("error");
         },
         success: function(){
            $('#cancelSubs').remove();
            $('#subsInfoContainer').append(btnSubs);
         }
      });
   });

   let pageNo = 1;
   let condition = {
      "keyword": "",
      "dataSize": 15,
      "pageNo": pageNo
   }

   //무한스크롤
   $(window).scroll(function(){
      if ($(window).scrollTop() + $(window).height() + 500 >= $(document).height()) {
         pageNo = pageNo + 1;
         condition = {
            "keyword": "",
            "dataSize": 15,
            "pageNo": pageNo
         }
         printVideoRecommendList(condition);
      }
   });

   //댓글 등록
   $(document).on('click', '#btnRegRe', function(){
      let reContent = $('#reInput').val();
      if(reContent!=""){
         let reInputData = {
            "memId": memId,
            "reContent": reContent,
            "ytbIdx": ytbIdx
         };

         console.log(reInputData);

         $.ajax({
            url: contextPath+'/youtube/play/regRe',
            method: 'post',
            data: JSON.stringify(reInputData),
            contentType: 'application/json',
            success: function(data){
               if(data == 1){
                  $('#reInput').val('');
                  printReply(ytbIdx, memId);
               }
            },
            async:false
         });
      }else{
         alert('댓글을 입력해주세요.');
      }
   });

   //대댓글 등록
   $(document).on('click', '#btnRegReRe', function(){
      let reIdx = $('#rereInput').attr('reidx');
      let rereContent = $('#rereInput').val();
      console.log(reIdx);
      console.log(rereContent);

      if(rereContent!=""){
         let inputData = {
            "memId": memId,
            "rereContent": rereContent,
            "reIdx": reIdx
         };

         console.log(inputData);

         $.ajax({
            url: contextPath+'/youtube/play/regReRe',
            method: 'post',
            data: JSON.stringify(inputData),
            contentType: 'application/json',
            success: function(data){
               if(data == 1){
                  printReply(ytbIdx, memId);
               }
            },
            async:false
         });
      }else{
         alert('답글을 입력해주세요.');
      }
   });

   //댓글 입력 폼
   let replyInputForm = $(
       '<div class="regReForm py-2 d-flex" style="width: 100%">\n' +
       '   <img class="rounded-circle ml-2" src="'+contextPath+'/resources/upload/profileImg/'+profileImgName+'" alt="" style="width: 38px; height: 38px">\n' +
       '   <div class="ml-3">\n' +
       '      <input id="reInput" type="text" class="form-control" style="width: 42vw" placeholder="댓글 추가...">\n' +
       '   </div>\n' +
       '   <div class="ml-auto">\n' +
       '      <button id="btnCancelRegRe" class="btn ml-auto mr-1"><span class="font-weight-bold" style="color: #00c9a7">취소</span></button>\n' +
       '      <button id="btnRegRe" type="button" class="btn btn-light ml-auto mr-1"><span class="font-weight-bold">댓글</span></button>\n' +
       '   </div>\n' +
       '</div>'
   );

   //대댓글 입력 폼 생성 function
   let createReReplyForm = function(reIdx){
      let reReplyInputForm;

      reReplyInputForm = $(
          '<div id="regReReForm" class="regReReForm py-2 d-flex" style="width: 100%">\n' +
          '   <div class="ml-5">\n' +
          '      <input id="rereInput" type="text" class="form-control ml-3" reIdx="'+reIdx+'" style="width: 41vw" placeholder="답글 추가...">\n' +
          '   </div>\n' +
          '   <div class="ml-auto">\n' +
          '      <button id="btnCancelRegReRe" class="btn ml-auto mr-1"><span class="font-weight-bold" style="color: #00c9a7">취소</span></button>\n' +
          '      <button id="btnRegReRe" type="button" class="btn btn-light ml-auto mr-1"><span class="font-weight-bold">답글</span></button>\n' +
          '   </div>\n' +
          '</div>'
      );
      return reReplyInputForm;
   }

   //대대댓글 입력 폼 생성 function
   let createReReReplyForm = function(rereIdx, memId){
      let reReReplyInputForm;

      reReReplyInputForm = $(
          '<div id="regReReReForm" class="regReReForm py-2 d-flex" style="width: 100%">\n' +
          '   <div class="ml-5">\n' +
          '      <input id="rereInput" type="text" class="form-control ml-3" rereidx="'+rereIdx+'" style="width: 37vw" placeholder="답글 추가..." value="@'+memId+'&nbsp;&nbsp;&nbsp;">\n' +
          '   </div>\n' +
          '   <div class="ml-auto">\n' +
          '      <button id="btnCancelRegReRe" class="btn ml-auto mr-1"><span class="font-weight-bold" style="color: #00c9a7">취소</span></button>\n' +
          '      <button id="btnRegReRe" type="button" class="btn btn-light ml-auto mr-1"><span class="font-weight-bold">답글</span></button>\n' +
          '   </div>\n' +
          '</div>'
      );
      return reReReplyInputForm;
   }

   // 댓글창 출력
   let printReply = function(ytbIdx, memId){
      let container = $('#replyContainer');
      container.empty();
      printReplyInputForm(memId);
      let reList = requestReplyList(ytbIdx);
      console.log("========== 댓글 리스트 ==========");
      console.log(reList);
      $.each(reList, function(){
         let regDate = createdAt(this.reRegDate);
         let loginInfo = getLoginInfo(this.memId);
         let imgFileName;
         if(loginInfo.img_file_name != null){
            imgFileName = loginInfo.img_file_name;
         }else{
            imgFileName = "img.png";
         }
         // console.log(loginInfo);
         
         //댓글 출력 부분
         let reply;
         if(memId == this.memId){
            reply = $(
                '<div id="reply'+this.reIdx+'" class="py-2">' +
                '   <div class="d-flex mb-1" style="width: 100%">\n' +
                '      <img class="rounded-circle ml-2" src="'+contextPath+'/resources/upload/profileImg/'+imgFileName+'" alt="" style="width: 38px; height: 38px">\n' +
                '      <div class="ml-3">\n' +
                '         <span class="font-weight-bold">'+this.memId+'</span><span class="ml-3" style="color: gray">'+regDate+'</span><br>\n' +
                '         <span>'+this.reContent+'</span>' +
                '         <a id="btnPrintReReForm" reIdx="'+this.reIdx+'" class="ml-3">답글</a>\n' +
                '         <a id="btnUpdateRe" reIdx="'+this.reIdx+'" class="ml-1">수정</a>\n' +
                '         <a id="btnDeleteRe" reIdx="'+this.reIdx+'" class="ml-1">삭제</a>\n' +
                '      </div>\n' +
                '   </div>' +
                '</div>');
         }else{
            reply = $(
                '<div id="reply'+this.reIdx+'" class="py-2">' +
                '   <div class="d-flex mb-1" style="width: 100%">\n' +
                '      <img class="rounded-circle ml-2" src="'+contextPath+'/resources/upload/profileImg/'+imgFileName+'" alt="" style="width: 38px; height: 38px">\n' +
                '      <div class="ml-3">\n' +
                '         <span class="font-weight-bold">'+this.memId+'</span><span class="ml-3" style="color: gray">'+regDate+'</span><br>\n' +
                '         <span>'+this.reContent+'</span>' +
                '         <a id="btnPrintReReForm" reIdx="'+this.reIdx+'" class="ml-3">답글</a>\n' +
                '      </div>\n' +
                '   </div>' +
                '</div>');
         }
         
         //댓글 하단 대댓글 출력 부분
         let reReList = requestReReList(this.reIdx);
         console.log("========== 대댓글 리스트 ==========");
         console.log(reReList);
         if (reReList.length != 0){
            let collapseHead = $(
                '<div class="ml-5 pl-3">\n' +
                '   <div id=reIdx'+this.reIdx+' class="accordion">\n' +
                '      <a class="btn-link" type="button" data-toggle="collapse" data-target="#collapse'+this.reIdx+'" aria-expanded="true" aria-controls="collapse'+this.reIdx+'">답글 '+reReList.length+'개</a>\n' +
                '   </div>\n' +
                '</div>'
            );
            $.each(reReList, function(){
               let loginInfo = getLoginInfo(this.memId);
               let reReImgFileName;
               if(loginInfo.img_file_name != null){
                  reReImgFileName = loginInfo.img_file_name;
               }else{
                  reReImgFileName = "img.png";
               }
               let rereRegDate = createdAt(this.rereRegDate);
               let rereAnnotation = "";
               if(this.rereAnnotation != null){
                  rereAnnotation = "@"+this.rereAnnotation
               }

               let reReply = $(
                   '   <div id="collapse'+this.reIdx+'" class="collapse" aria-labelledby="heading'+this.reIdx+'" data-parent="#reIdx'+this.reIdx+'">\n' +
                   '      <div id="reReply'+this.rereIdx+'">\n' +
                   '      <div class="d-flex pt-2">\n' +
                   '         <img class="rounded-circle ml-2" src="'+contextPath+'/resources/upload/profileImg/'+reReImgFileName+'" alt="" style="width: 38px; height: 38px">\n' +
                   '         <div class="ml-3 mb-2">\n' +
                   '            <span class="font-weight-bold">'+this.memId+'</span><span class="ml-3" style="color: gray">'+rereRegDate+'</span><br>\n' +
                   '            <span>'+rereAnnotation+'</span>'+this.rereContent+'</span><a id="btnPrintReReReForm" rereidx="'+this.rereIdx+'" class="ml-3">답글</a>\n' +
                   '         </div>\n' +
                   '      </div>\n' +
                   '      </div>\n' +
                   '   </div>');
               $(collapseHead).append(reReply);
            });
            $(reply).append(collapseHead)
         }
         container.append(reply);
      })
   }

   //로그인 시 댓글 입력창 표시
   let printReplyInputForm = function(memId){
      if(memId != undefined){
         $('#replyContainer').append(replyInputForm);
      }
   }

   //댓글 취소 버튼
   $(document).on('click', '#btnCancelRegRe', function(){
      $('#reInput').val('');
   });

   //답글 클릭시 답글 입력 폼 생성
   $(document).on('click', '#btnPrintReReForm', function(){
      let replyFormId = '#reply'+$(this).attr('reidx');
      let reIdx = $(this).attr('reidx');
      console.log(replyFormId);
      $('.regReReForm').remove();
      $(replyFormId).append(createReReplyForm(reIdx));
   });

   //대댓글의 답글 클릭 시 답글 입력 폼 생성
   $(document).on('click', '#btnPrintReReReForm', function(){
      let reReplyFormId = '#reReply'+$(this).attr('rereidx');
      let rereIdx = $(this).attr('rereidx');
      console.log(reReplyFormId);
      $('.regReReForm').remove();
      $(reReplyFormId).append(createReReReplyForm(rereIdx, 'memId'));
   });

   //답글 취소시 답글 입력 폼 제거
   $(document).on('click', '#btnCancelRegReRe', function(){
      $('#regReReForm').remove();
   });

   printReply(ytbIdx, memId);
   printVideoRecommendList(condition);
});

let contextPath;
let profileImgName;

let ajaxRequest = function(condition){
   let re;
   $.ajax({
      url: contextPath+"/youtube/list/recommend",
      method:"post",
      async:false,
      data: JSON.stringify(condition),
      contentType: "application/json",
      error: function(){
         console.log("error");
      },
      success: function(data){
         re = data;
      }
   });
   return re;
};

let printVideoRecommendList = function(condition){
   let list = ajaxRequest(condition);
   $.each(list, function(){
      const container = $('#recommendContainer');
      let recommendedVideo = $(
          '<div class="py-2 d-flex">\n' +
          '   <img src="'+this.ytbThumbnail+'" url="'+this.ytbIdx+'" class="img-fluid playThis" style="width: 40%;">\n' +
          '       <div class="pt-2 d-flex">\n' +
          '          <div class="ml-3">\n' +
          '               <a style="color: white; text-overflow:ellipsis;overflow:hidden; width: 100%;display: -webkit-box; -webkit-line-clamp: 2;-webkit-box-orient: vertical;" href="'+contextPath+'/youtube/play/'+this.ytbIdx+'"><span class="text-dark text-sm-left">'+this.ytbTitle+'</span></a>\n' +
          '               <span style="color: #74828e">'+this.memId+'</span>\n' +
          '               <br><span style="color: #74828e">조회수 '+this.ytbHit+'회</span>\n' +
          '          <div/>\n' +
          '       </p>\n' +
          '</div>'
      );
      container.append(recommendedVideo);
   });
}

let requestReplyList = function(ytbIdx){
   let re;
   $.ajax({
      url: contextPath+"/youtube/play/listRe/"+ytbIdx,
      success: function(data){
         // console.log(data);
         re = data;
      },
      async:false
   });
   return re;
}

let requestReReList = function(reIdx){
   let re;
   $.ajax({
      url: contextPath+"/youtube/play/listReRe/"+reIdx,
      success: function(data){
         // console.log(data);
         re = data;
      },
      async:false
   });
   return re;
}

let getLoginInfo = function(memId){
   let re;
   $.ajax({
      url: contextPath+"/member/loginInfo",
      method: 'post',
      data: memId,
      contentType: 'application/json',
      success: function(data){
         re = data;
      },
      async: false
   })
   return re;
}

//날짜변환 함수
function createdAt(dateRaw) {
   const milliSeconds = new Date() - new Date(dateRaw)
   const seconds = milliSeconds / 1000
   if (seconds < 60) return `방금 전`
   const minutes = seconds / 60
   if (minutes < 60) return `${Math.floor(minutes)}분 전`
   const hours = minutes / 60
   if (hours < 24) return `${Math.floor(hours)}시간 전`
   const days = hours / 24
   if (days < 7) return `${Math.floor(days)}일 전`
   const weeks = days / 7
   if (weeks < 5) return `${Math.floor(weeks)}주 전`
   const months = days / 30
   if (months < 12) return `${Math.floor(months)}개월 전`
   const years = days / 365
   return `${Math.floor(years)}년 전`;
}

