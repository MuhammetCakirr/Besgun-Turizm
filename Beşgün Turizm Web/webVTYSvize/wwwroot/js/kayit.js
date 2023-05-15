function UI(){
    this.btn_uyeOl=document.querySelector(".btn_uyeOl"),
    this.btn_girisYap=document.querySelector(".btn_girisYap")
}
const ui = new UI();

document.getElementById("uyeOl").style.visibility = "hidden";
ui.btn_uyeOl.addEventListener("click",function(){
    document.getElementById("girisYap").style.visibility = "hidden";
    document.getElementById("uyeOl").style.visibility = "visible";
})

ui.btn_girisYap.addEventListener("click",function(){
    document.getElementById("girisYap").style.visibility = "visible";
    document.getElementById("uyeOl").style.visibility = "hidden";
})
