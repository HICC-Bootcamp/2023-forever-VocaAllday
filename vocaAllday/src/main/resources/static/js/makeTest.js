var cnt_add = 8; //입력창개수(임시 8개 고정)

/*
    유효성 검사용 정규식
*/
const regex_en = /^[a-z|A-Z]+$/;            //영어
const regex_ko = /^[ㄱ-ㅎ|가-힣]+$/;        //한글

//add버튼 클릭 시 단어입력창 추가

const add_input_word = () => {
    /*
    //입력창 추가 개수 제한
    if(cnt_add>30){
        Swal.fire('단어 추가 실패!',"단어는 최대 30개까지만 입력할 수 있습니다.",'warning').then(function(){
        document.getElementById("add_but").style.color = "#C0C0C0"
        document.getElementById("add_but_text").style.color = "#C0C0C0"
        document.getElementById("add_but").disabled=true;
        })
        return
    }

    //입력창 새로 만들기 (thymeleaf태그추가)
        var new_input=document.createElement('div');
        new_input.className="input_word"
        new_input.id = "input_word_"+cnt_add
        var new_input_en=document.createElement('input');
        new_input_en.className="text_box"
        new_input_en.id="input_word_en"
        new_input_en.placeholder=cnt_add
        var new_input_ko=document.createElement('input');
        new_input_ko.className="text_box"
        new_input_ko.id="input_word_ko"
        new_input_ko.placeholder=cnt_add
        new_input.appendChild(new_input_en)
        new_input.appendChild(new_input_ko)

        //DOM에 추가
        var container = document.getElementById("left_body")
        container.appendChild(new_input)
    */

    cnt_add++;
}

const check_cnt = () => {
    return cnt_add;
}


//유효성 검사 후 form submit
const check_validation = () => {
    let validation = true //가입 조건 통과 여부
    let form = document.getElementById("form");
    let i = 1;
    //입력값 유효성 검사
    for (i; i<=cnt_add; i++){

        //word,meaning 한쌍 유효성검사
        let word = document.getElementById('word'+i).value;
        let meaning = document.getElementById('meaning'+i).value;
        if(word===""||!regex_en.test(word)){
            //word입력오류
            validation=false
        }
        if(meaning===""||!regex_ko.test(meaning)){
            //meaning입력오류
            validation=false
        }
    }
    if(vocaTitle===""){
       //vocaTitle입력오류
       validation=false
    }

    if(validation){
        //유효성 검사 통과
        form.submit();
    }else{
        Swal.fire('문제지 제작 실패',"입력한 단어,의미,단어장이름을 확인해주세요!",'error')
    }
}