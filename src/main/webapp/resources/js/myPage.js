$(function(){
    contextPath = $('#contextPath').val();
    let memId = $('#memId').val();
    console.log(memId);

    let keyword = "";
    let dataSize = 12;
    let pageNo = 1;

    let condition = {
        "memId": memId,
        "keyword": keyword,
        "dataSize": dataSize,
        "pageNo": pageNo
    }

    printMyVideo(condition);

    $(document).on('click', '#btnRegVideo', function(){
        console.log()
        location.href = contextPath+"/youtube/myPage/reg";
    });

    //무한스크롤
    $(window).scroll(function(){
        if ($(window).scrollTop() + $(window).height() + 500 >= $(document).height()) {
            pageNo = pageNo + 1;
            condition = {
                "memId": memId,
                "keyword": keyword,
                "dataSize": dataSize,
                "pageNo": pageNo
            }
            printMyVideo(condition);
            console.log(pageNo)
        }
    });

    //내 영상 클릭 시 동작
    $(document).on('click', '#btnMyVideos', function(){
        $('#playListContainer').empty();
        $('#btnContainer').empty();
        $('#btnContainer').append($(
            '<button id="btnRegVideo" class="btn btn-light" style="border:none">영상 등록</button>\n' +
            '<button id="btnDeleteVideo" class="btn btn-light ml-2" style="border:none">선택 삭제</button>'
        ))
        let keyword = "";
        let dataSize = 12;
        let pageNo = 1;

        let condition = {
            "memId": memId,
            "keyword": keyword,
            "dataSize": dataSize,
            "pageNo": pageNo
        }
        printMyVideo(condition);
    });

    //재생목록 클릭 시 동작
    $(document).on('click', '#btnPlayList', function(){
        $('#myVideosContainer').empty();
        $('#btnContainer').empty();
        $('#btnContainer').append($(
            '<button id="btnCreatePlayList" class="btn btn-light" style="border:none">재생목록 추가</button>'
        ))
        let keyword = "";
        let dataSize = 12;
        let pageNo = 1;

        let condition = {
            "memId": memId,
            "keyword": keyword,
            "dataSize": dataSize,
            "pageNo": pageNo
        }
    });

    //선택 삭제 클릭 시 동작
    $(document).on('click', '#btnDeleteVideo', function(){
        let deleteList = [];
        let inputTag = $('body').find('input[type="checkbox"]');
        $.each(inputTag, function(){
            if($(this).prop('checked')){
                deleteList.push($(this).val());
        console.log(typeof $(this).val());
            }
        });
        console.log(deleteList);
        console.log(typeof deleteList);

        $.ajax({
            url:contextPath+"/youtube/myPage/deleteVideo",
            type: "POST",
            data: JSON.stringify(deleteList),
            contentType: "application/json",
            success: function(){
                console.log("삭제완료");
                location.href= contextPath+"/youtube/myPage";

            },
            error: function(){
                console.log("error");
            }
        });
    });
});

let ajaxRequest = function(condition){
    let re;
    $.ajax({
        url: contextPath+"/youtube/myPage/myVideos",
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

let contextPath;

let printMyVideo = function(condition){
    let list = ajaxRequest(condition);
    $.each(list, function(){
        const container = $('#myVideosContainer');
        let regDate = createdAt(this.ytbRegDate);
        let divVideos = $(
            '<div class="col col-xl-3 col-lg-3 col-md-3 col-sm-3 col-12">\n' +
            '   <input type="checkbox" class="form-control my-1 deleteCheck" value="'+this.ytbIdx+'"/>\n' +
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
        container.append(divVideos);
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



