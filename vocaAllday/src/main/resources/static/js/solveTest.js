var cnt_add = 9; //입력창개수(임시 8개 고정)

/*
    유효성 검사용 정규식
*/
const regex_en = /^[a-z|A-Z]+$/;            //영어
const regex_ko = /^[ㄱ-ㅎ|가-힣]+$/;        //한글


//보기 체크 후 삭제
const delete_hint = (cnt,ck) => {
    const hint_box = document.getElementById("hint"+cnt);
    const checkbox = document.getElementById('hint_ck'+cnt);
    const is_checked = checkbox.checked;
    if(is_checked){
        hint_box.className="checked"
    }else{
        hint_box.className=""
    }
}

//유효성 검사 후 form submit
const ck_validation = () => {
    let validation = true //가입 조건 통과 여부
    let form = document.getElementById("form");
    let i = 1;

    //입력값 유효성 검사(비어있는지 여부만 확인)
    for (i; i<cnt_add; i++){
        //word,meaning 한쌍 유효성검사
        let word = document.getElementById('answer'+i).value;
        if(word===""){
            //word입력오류
            validation=false
        }
    }

    if(validation){
        //유효성 검사 통과
        Swal.fire('답안입력 성공',"채점하고 있습니다!",'success')
        form.submit();
    }else{
        Swal.fire('답안입력 실패',"입력한 답을 확인해주세요!",'error')
    }
}