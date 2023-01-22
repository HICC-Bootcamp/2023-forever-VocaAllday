let pw_view = false; //비밀번호 열람 상태

//아이디에서 엔터치면 비밀번호로 커서 이동
const changeFocus = () => {
    if(window.event.keyCode===13){
        document.getElementById("pw").focus();
    }
}
//비밀번호에서 엔터치면 로그인
const enterLogin = () => {
    if(window.event.keyCode===13){
        login();
    }
}
//비밀번호 보기
const viewPw = () => {
    //비밀번호 아이콘 변환
    if(pw_view){
        document.getElementById("icon_pw").className="fa-solid fa-eye-slash basicStyle"
        document.getElementById("pw").type="password"
        pw_view = false;
    }else{
        document.getElementById("icon_pw").className="fa-solid fa-eye basicStyle"
        document.getElementById("pw").type="text"
        pw_view = true;
    }
    
}

//로그인 기능
const login = () => {
    let validation = true //로그인 조건 통과 여부
    //입력값 받아오기
    let id = document.getElementById('id').value;
    let pw = document.getElementById('pw').value;

    //아이디,비밀번호 입력여부 확인
    if(id===""){
        //아이디 입력이 안된 경우
        document.getElementById("error_id").innerText="아이디를 입력해주세요!"
        validation=false
    }else{
        document.getElementById("error_id").innerText=""
    }
    
    if(pw===""){
        //비밀번호 입력이 안된 경우
        document.getElementById("error_pw").innerText="비밀번호를 입력해주세요!"
        validation = false
    }else{
        document.getElementById("error_pw").innerText=""
    }

    /*
        api연동 : 로그인 정보 맞으면 validation=true, 그외 false
    */

    if(validation){
        //로그인 성공 시
        Swal.fire('로그인 성공',id+"님 반가워요!",'success')
    }else{
        //로그인 실패 시
        Swal.fire('로그인 실패',"로그인 정보를 확인해주세요!",'error')
    }
    
}