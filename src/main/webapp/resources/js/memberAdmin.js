$(function(){
    contextPath = $('#contextPath').val();
    if($('#profileImgFile').val()!= ''){
        profileImgName = $('#profileImgFile').val();
    }else(
        profileImgName = "img.png"
    )

    console.log("profileImgName: "+profileImgName);
    console.log("ContextPath: "+contextPath);
});

let contextPath;
let profileImgName;