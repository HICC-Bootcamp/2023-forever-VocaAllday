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

    //입력창 새로 만들기 (추가예정)
    
    cnt_add++;
}