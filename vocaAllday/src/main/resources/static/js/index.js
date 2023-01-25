const menuMaketest=()=>{
    document.getElementById("contentFrame").setAttribute("src","../makeTest/makeTest.html")
    document.getElementById("menuMaketest").style="color: #2D3E4E;"
    document.getElementById("menuMyScore").style="color: #8CBDB9;"
    document.getElementById("menuMyTest").style="color: #8CBDB9;"
}
const menuMyScore=()=>{
    document.getElementById("contentFrame").setAttribute("src","../myScore/myScore.html")
    document.getElementById("menuMaketest").style="color: #8CBDB9;"
    document.getElementById("menuMyScore").style="color: #2D3E4E;"
    document.getElementById("menuMyTest").style="color: #8CBDB9;"
}
const menuMyTest=()=>{
    document.getElementById("contentFrame").setAttribute("src","../myTest/myReport.html")
    document.getElementById("menuMaketest").style="color: #8CBDB9;"
    document.getElementById("menuMyScore").style="color: #8CBDB9;"
    document.getElementById("menuMyTest").style="color: #2D3E4E;"
}
const menuLogin=()=>{
    document.getElementById("contentFrame").setAttribute("src","../login/login.html")
    document.getElementById("menuLogin").style="background:#8CBDB9"
    document.getElementById("menuMaketest").style="color: #8CBDB9;"
    document.getElementById("menuMyScore").style="color: #8CBDB9;"
    document.getElementById("menuMyTest").style="color: #8CBDB9;"
}