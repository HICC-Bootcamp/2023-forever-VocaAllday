let pw_view1 = false; //비밀번호 열람 상태
let pw_view2 = false;

/*
    유효성 검사용 정규식
*/
const regex_en = /^[a-z|A-Z]+$/;            //영어
const regex_ko = /^[ㄱ-ㅎ|가-힣]+$/;        //한글
const regex_num = /^[0-9]+$/;               //숫자
const regex_spc = /[~!@#$%^&*()_+|<>?:{}]/; //특수문자

const regex_pw = /^[A-Za-z0-9`~!@#\$%\^&\*\(\)\{\}\[\]\-_=\+\\|;:'"<>,\./\?]{8,16}$/; //비밀번호 : 영어+숫자+특수문자 조합 8~16자
const regex_email = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;    //이메일: 알파벳+숫자@알벳+숫자.알파벳+숫자

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

    //가입하기 기능
const signin = () => {
    let validation = true //가입 조건 통과 여부

    //입력값 받아오기
    let name = document.getElementById('name').value;
    let email = document.getElementById('email').value;
    let pw1 = document.getElementById('password').value;
    let pw2 = document.getElementById('password_check').value;
    let form = document.getElementById("form");

    //입력값 유효성 검사

    if(name===""||name.length>8||(!regex_ko.test(name)&&!regex_en.test(name))){
        //name 입력 오류 : 빈칸 or 8자초과 or 영어나 한글만x
        document.getElementById("error_name").innerText="이름이 올바르지 않습니다."
        validation=false
    }else{
        document.getElementById("error_name").innerText=""
    }

    if(email===""||!regex_email.test(email)){
        //email 입력 오류 : 빈칸 or 알파벳+숫자@알벳+숫자.알파벳+숫자 형식 x
        document.getElementById("error_email").innerText="이메일이 올바르지 않습니다."
        validation=false
    }else{
        document.getElementById("error_email").innerText=""
    }

    if(pw1===""||!regex_pw.test(pw1)){
        document.getElementById("error_pw1").innerText="비밀번호가 올바르지 않습니다."
        validation = false
    }else{
        document.getElementById("error_pw1").innerText=""
    }

    if(pw1 !== pw2){
        document.getElementById("error_pw2").innerText="비밀번호가 일치하지 않습니다."
        validation = false
    }else{
        document.getElementById("error_pw2").innerText=""
    }

    if(validation){
        //유효성 검사 통과
        form.submit();

    }else{
        Swal.fire('회원가입 실패',"회원가입 정보를 확인해주세요!",'error')
    }
}