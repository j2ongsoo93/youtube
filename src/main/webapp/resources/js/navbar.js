$(function(){
    let contextPath = $('#contextPath').val();

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
});