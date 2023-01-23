var cnt_add = 9; //입력창개수

//add버튼 클릭 시 단어입력창 추가 
const add_input_word = () => {
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
    
    cnt_add++;
}

//next버튼 클릭 시 입력 조건 확인 후 완료/오류 알리기, input 단어 색전화, submit 버튼 활성화
const submit_word = () => {
    //next 
}
//submit 버튼 클릭 시 데이터 백으로 전달