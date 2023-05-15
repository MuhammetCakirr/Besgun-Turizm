function UI(){
    this.btn_rezervasyonStandartOda=document.querySelector(".btn_rezervasyonStandartOda"),
    this.btn_rezervasyonDenizOda=document.querySelector(".btn_rezervasyonDenizOda"),
    this.btn_rezervasyonSuitOda=document.querySelector(".btn_rezervasyonSuitOda")
}
const ui = new UI();

ui.btn_rezervasyonStandartOda.addEventListener("click",function(){
    window.location.href = "rezervasyonYap.html";
})

ui.btn_rezervasyonDenizOda.addEventListener("click",function(){
    window.location.href = "rezervasyonYap.html";
})

ui.btn_rezervasyonSuitOda.addEventListener("click",function(){
    window.location.href = "rezervasyonYap.html";
})