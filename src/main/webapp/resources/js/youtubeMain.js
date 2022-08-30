$(function (){
    contextPath = $('#contextPath').val();
    console.log("Main01")
    let memId = $('#memId').val();
    let auth = $('#auth').val();

    let keyword;
    let dataSize = 12;
    let pageNo = 1;

    // console.log(decodeURI(location.search).split("=")[1]);
    let decodedKeyword =  decodeURI(location.search).split("=")[1]
    if(decodedKeyword != undefined){
        keyword = decodedKeyword;
    }

    let condition = {
       "keyword": keyword,
       "dataSize": dataSize,
       "pageNo": pageNo
    }

    //영상 리스트 출력(썸네일 형식)
    printVideoList(condition);

    //manager 이상 등급 로그인 시 영상 관리 버튼 출력
    if(auth == "master" || auth == "manager"){
        $('#managerBtnContainer').append(
            '<button id="manageVideos" class="btn btn-light mr-2" style="width: 100px">영상 관리</button>\n' +
            '<button id="listView" class="btn btn-light mr-2" style="width: 100px">리스트 보기</button>'
        );
    }

    //리스트 형태로 보기
    $(document).on('click', '#listView', function(){
        let managerBtnContainer = $('#managerBtnContainer');
        managerBtnContainer.empty();
        managerBtnContainer.append(
            '<button id="manageVideos" class="btn btn-light mr-2" style="width: 100px">영상 관리</button>\n' +
            '<button id="thumbView" class="btn btn-light mr-2" style="width: 100px">썸네일 보기</button>'
        );
        const container = $('#videoListContainer');
        container.empty();
        container.prop("list", true);
        pageNo = 1;
        condition = {
            "keyword": keyword,
            "dataSize": dataSize,
            "pageNo": pageNo
        }
        printListView(condition);
    });

    //썸네일 형태로 보기
    $(document).on('click', '#thumbView', function(){
        let managerBtnContainer = $('#managerBtnContainer');
        managerBtnContainer.empty();
        managerBtnContainer.append(
            '<button id="manageVideos" class="btn btn-light mr-2" style="width: 100px">영상 관리</button>\n' +
            '<button id="listView" class="btn btn-light mr-2" style="width: 100px">리스트 보기</button>'
        );
        const container = $('#videoListContainer');
        container.empty();
        container.prop("list", false);
        pageNo = 1;
        condition = {
            "keyword": keyword,
            "dataSize": dataSize,
            "pageNo": pageNo
        }
        printVideoList(condition);
    });



    //로그인 시 구독 목록 표시
    if(memId != undefined){
        $('#subsContainer').append($('<h6>구독</h6>'))
        printSubsList(memId);
    }

    //무한스크롤
    $(window).scroll(function(){
        if ($(window).scrollTop() + $(window).height() + 500 >= $(document).height()) {
            pageNo = pageNo + 1;
            condition = {
                "keyword": keyword,
                "dataSize": dataSize,
                "pageNo": pageNo
            }
            if($('#videoListContainer').prop('list')){
                printListView(condition);
            }else{
                printVideoList(condition);
            }
            console.log(pageNo)
        }
    });

    // 채널정보 클릭 시 유튜브 채널로 이동
    $(document).on('click', '.toChannel', function(){
        location.href = $(this).attr('url');
    });

});

let ajaxRequest = function(condition){
    let re;
    $.ajax({
        url: contextPath+"/youtube/list/main",
        method:"post",
        async:false,
        data: JSON.stringify(condition),
        contentType: "application/json",
        error: function(){
            console.log("error");
        },
        success: function(data){
            console.log(data);
            re = data;
        }
    });
    return re;
};

let contextPath

let printVideoList = function(condition){
    let list = ajaxRequest(condition);
    $.each(list, function(){
        const container = $('#videoListContainer');
        let regDate = createdAt(this.ytbRegDate);
        let divVideo = $(
            '<div class="col col-xl-3 col-lg-3 col-md-3 col-sm-3 col-12">\n' +
            '   <img src="'+this.ytbThumbnail+'" url="'+this.ytbIdx+'" class="img-fluid playThis" style="width: 100%; height: 20vh;">\n' +
            '       <div class="pt-2 d-flex">\n' +
            '          <img class="rounded-circle" src="'+this.ytbChannelThumb+'" alt="" style="width: 40px; height: 40px">\n' +
            '          <div class="ml-3">\n' +
            '               <a style="color: white; text-overflow:ellipsis;overflow:hidden; width: 100%;display: -webkit-box; -webkit-line-clamp: 2;-webkit-box-orient: vertical;" href="'+contextPath+'/youtube/play/'+this.ytbIdx+'"><span class="text-dark text-sm-left">'+this.ytbTitle+'</span></a>\n' +
            '               <span style="color: #74828e">'+this.memId+'</span>\n' +
            '               <br><span style="color: #74828e">조회수 '+this.ytbHit+'회</span>\n' +
            '               <br><span style="color: #74828e">'+regDate+'</span>\n' +
            '          <div/>\n' +
            '       </p>\n' +
            '</div>'
        );
        container.append(divVideo);
    });
}

let table = $('<table class="table table-dark"></table>');
let thead = $('<tr>\n' +
    '             <th><input type="checkbox" id="checkAll"></th>\n' +
    '             <th>#</th>\n' +
    '             <th style="width: 50%">제목</th>\n' +
    '             <th>작성자</th>\n' +
    '             <th>조회수</th>\n' +
    '             <th>등록일</th>\n' +
    '          </tr>'
);
$(table).append(thead);

let printListView = function(condition){
    let list = ajaxRequest(condition);
    const container = $('#videoListContainer');
    $.each(list, function(){
        let regDateRaw = createdAt(this.ytbRegDate);
        let tbody = $('<tr>\n' +
            '            <td><input type="checkbox"></td>\n' +
            '            <td>'+this.ytbIdx+'</td>\n' +
            '            <td>'+this.ytbTitle+'</td>\n' +
            '            <td>'+this.memId+'</td>\n' +
            '            <td>'+this.ytbHit+'</td>\n' +
            '            <td>'+regDateRaw+'</td>\n' +
            '          </tr>'
        );
        $(table).append(tbody)
    });
    container.append(table);
}

let printSubsList = function(memId){
    let subsList;
    $.ajax({
        url: contextPath+"/youtube/subsList/"+memId,
        success: function(data){
            console.log(data);
            subsList = data;
        },
        async: false
    });

    $.each(subsList, function(){
       let channelInfo =  $('<div class="py-2 d-flex" style="width: 100%">\n' +
           '                    <input type="hidden" id="toChannel" url="'+this.ytbChannelUrl+'">\n' +
           '                    <img class="rounded-circle ml-2 toChannel" url="'+this.ytbChannelUrl+'" src="'+this.ytbChannelThumb+'" alt="" style="width: 40px; height: 40px">\n' +
           '                    <div class="ml-3 toChannel" url="'+this.ytbChannelUrl+'">\n' +
           '                        <span class="toChannel" url="'+this.ytbChannelUrl+'">'+this.ytbChannelName+'</span>\n' +
           '                    </div>\n' +
           '                </div>');

       $('#subsContainer').append(channelInfo);
    });
}

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
    return `${Math.floor(years)}년 전`
}