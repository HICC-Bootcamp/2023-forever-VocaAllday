let pw_view1 = false; //비밀번호 열람 상태
let pw_view2 = false;



//엔터치면 커서 다음창으로 이동
const changeFocus1 = () => {
    if(event.keyCode===13){
        document.getElementById("email").focus();
    }
}
const changeFocus2 = () => {
    if(event.keyCode===13){
        document.getElementById("password").focus();
    }
}
const changeFocus3 = () => {
    if(event.keyCode===13){
        document.getElementById("password_check").focus();
    }
}

//비밀번호 보기
const viewPw1 = () => {
    //비밀번호 아이콘 변환
    if(pw_view1){
        document.getElementById("icon_pw1").className="fa-solid fa-eye-slash basicStyle"
        document.getElementById("password").type="password"
        pw_view1 = false;
    }else{
        document.getElementById("icon_pw1").className="fa-solid fa-eye basicStyle"
        document.getElementById("password").type="text"
        pw_view1 = true;
    }
}
const viewPw2 = () => {
    //비밀번호 아이콘 변환
    if(pw_view2){
        document.getElementById("icon_pw2").className="fa-solid fa-eye-slash basicStyle"
        document.getElementById("password_check").type="password"
        pw_view2 = false;
    }else{
        document.getElementById("icon_pw2").className="fa-solid fa-eye basicStyle"
        document.getElementById("password_check").type="text"
        pw_view2 = true;
    }
}
