function UI(){
    this.btn_rezervasyonTekneTuru=document.querySelector(".btn_rezervasyonTekneTuru")
}
const ui = new UI();

ui.btn_rezervasyonTekneTuru.addEventListener("click",function(){
    window.location.href = "rezervasyonYap.html";
})