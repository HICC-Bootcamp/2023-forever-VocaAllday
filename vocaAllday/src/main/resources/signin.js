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
    if(window.event.keyCode===13){
        document.getElementById("id").focus();
    }
}
const changeFocus2 = () => {
    if(window.event.keyCode===13){
        document.getElementById("pw1").focus();
    }
}
const changeFocus3 = () => {
    if(window.event.keyCode===13){
        document.getElementById("pw2").focus();
    }
}
const changeFocus4 = () => {
    if(window.event.keyCode===13){
        signin();
    }
}

//비밀번호 보기
const viewPw1 = () => {
    //비밀번호 아이콘 변환
    if(pw_view1){
        document.getElementById("icon_pw1").className="fa-solid fa-eye-slash basicStyle"
        document.getElementById("pw1").type="password"
        pw_view1 = false;
    }else{
        document.getElementById("icon_pw1").className="fa-solid fa-eye basicStyle"
        document.getElementById("pw1").type="text"
        pw_view1 = true;
    }
}
const viewPw2 = () => {
    //비밀번호 아이콘 변환
    if(pw_view2){
        document.getElementById("icon_pw2").className="fa-solid fa-eye-slash basicStyle"
        document.getElementById("pw2").type="password"
        pw_view2 = false;
    }else{
        document.getElementById("icon_pw2").className="fa-solid fa-eye basicStyle"
        document.getElementById("pw2").type="text"
        pw_view2 = true;
    }
}

//중복확인 클릭 시
const ck_overlap = () => {
    let validation = true //중복조건 통과 여부
    /*
        api연동 : 중복 id 확인-> 성공 시 validation=true
    */

    //버튼 색변환
    document.getElementById("overlap_but").style="background: rgba(0, 120, 240, 0.3);"
    if(validation){
        //중복확인 통과
        document.getElementById("signin_but").disabled=false;
        document.getElementById("overlap_but").style="background: rgba(0, 120, 240, 0.2);"
    }else{
        //중복확인 실패
        Swal.fire('아이디 중복',"아이디 정보를 확인해주세요!",'warning')
        document.getElementById("overlap_but").style="background: red"
    }
}

//가입하기 기능
const signin = () => {
    let validation = true //가입 조건 통과 여부

    //입력값 받아오기
    let name = document.getElementById('name').value;
    let id = document.getElementById('id').value;
    let pw1 = document.getElementById('pw1').value;
    let pw2 = document.getElementById('pw2').value;

    //입력값 유효성 검사

    if(name===""||name.length>8||(!regex_ko.test(name)&&!regex_en.test(name))){
        document.getElementById("error_name").innerText="이름이 올바르지 않습니다."
        validation=false
    }else{
        document.getElementById("error_name").innerText=""
    }

    if(id===""||!regex_email.test(id)){
        document.getElementById("error_id").innerText="아이디가 올바르지 않습니다."
        validation=false
    }else{
        document.getElementById("error_id").innerText=""
    }
    
    if(pw1===""||!regex_pw.test(pw1)){
        document.getElementById("error_pw1").innerText="비밀번호가 올바르지 않습니다."
        validation = false
    }else{
        document.getElementById("error_pw1").innerText=""
    }
    if(pw2===""||!regex_pw.test(pw2)){
        document.getElementById("error_pw2").innerText="비밀번호가 올바르지 않습니다."
        validation = false
    }else{
        document.getElementById("error_pw2").innerText=""
    }

    if(pw1 !== pw2){
        document.getElementById("error_pw2").innerText="비밀번호가 일치하지 않습니다."
        validation = false
    }else{
        document.getElementById("error_pw2").innerText=""
    }

    /*
        api연동 : 회원가입 정보전달
    */

    if(validation){
        //회원가입 성공 -> 로그인 페이지로 이동
        Swal.fire('회원가입 성공',name+"님 환영해요!",'success')
    }else{
        Swal.fire('회원가입 실패',"회원가입 정보를 확인해주세요!",'error')
    }

}