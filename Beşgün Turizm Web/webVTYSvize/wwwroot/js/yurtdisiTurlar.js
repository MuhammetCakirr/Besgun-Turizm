function UI(){
    this.btn_yurtdisiTur1=document.querySelector(".btn_yurtdisiTur1")
}
const ui = new UI();

ui.btn_yurtdisiTur1.addEventListener("click",function(){
    window.location.href = "yurtdisiTurlarDetay.html";
})
