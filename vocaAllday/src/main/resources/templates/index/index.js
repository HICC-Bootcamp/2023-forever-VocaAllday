const menuMaketest=()=>{
    document.getElementById("contentFrame").setAttribute("src","makeTest.html")
    document.getElementById("menuMaketest").style="color: #2D3E4E;"
    document.getElementById("menuTotalreport").style="color: #8CBDB9;"
    document.getElementById("menuMyreport").style="color: #8CBDB9;"
}
const menuTotalreport=()=>{
    document.getElementById("contentFrame").setAttribute("src","totalReport.html")
    document.getElementById("menuMaketest").style="color: #8CBDB9;"
    document.getElementById("menuTotalreport").style="color: #2D3E4E;"
    document.getElementById("menuMyreport").style="color: #8CBDB9;"
}
const menuMyreport=()=>{
    document.getElementById("contentFrame").setAttribute("src","myReport.html")
    document.getElementById("menuMaketest").style="color: #8CBDB9;"
    document.getElementById("menuTotalreport").style="color: #8CBDB9;"
    document.getElementById("menuMyreport").style="color: #2D3E4E;"
}
const menuLogin=()=>{
    document.getElementById("contentFrame").setAttribute("src","login.html")
    document.getElementById("menuLogin").style="background:#8CBDB9"
    document.getElementById("menuMaketest").style="color: #8CBDB9;"
    document.getElementById("menuTotalreport").style="color: #8CBDB9;"
    document.getElementById("menuMyreport").style="color: #8CBDB9;"
}