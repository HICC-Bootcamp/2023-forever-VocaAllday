const menuMaketest=()=>{
    document.getElementById("contentFrame").setAttribute("src","01-MakeTest.html")
    document.getElementById("menuMaketest").style="color: #2D3E4E;"
    document.getElementById("menuTotalreport").style="color: #8CBDB9;"
    document.getElementById("menuMyreport").style="color: #8CBDB9;"
}
const menuTotalreport=()=>{
    document.getElementById("contentFrame").setAttribute("src","02-TotalReport.html")
    document.getElementById("menuMaketest").style="color: #8CBDB9;"
    document.getElementById("menuTotalreport").style="color: #2D3E4E;"
    document.getElementById("menuMyreport").style="color: #8CBDB9;"
}
const menuMyreport=()=>{
    document.getElementById("contentFrame").setAttribute("src","03-MyReport.html")
    document.getElementById("menuMaketest").style="color: #8CBDB9;"
    document.getElementById("menuTotalreport").style="color: #8CBDB9;"
    document.getElementById("menuMyreport").style="color: #2D3E4E;"
}