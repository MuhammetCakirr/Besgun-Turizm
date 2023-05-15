function UI(){
    this.btn_rezervasyonYurdisiTur=document.querySelector(".btn_rezervasyonYurdisiTur")
}
const ui = new UI();

ui.btn_rezervasyonYurdisiTur.addEventListener("click",function(){
    window.location.href = "rezervasyonYap.html";
})