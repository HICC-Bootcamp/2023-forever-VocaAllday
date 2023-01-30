//보기 체크 후 삭제
const delete_hint = (cnt,ck) => {
    const hint_box = document.getElementById("hint"+cnt);
    const checkbox = document.getElementById('hint_ck'+cnt);
    const is_checked = checkbox.checked;
    if(is_checked){
        hint_box.className="checked"
    }else{
        hint_box.className="hint"
    }
}