function UI(){
    this.btn_tekneTurlari=document.querySelector(".btn_tekneTurlari")
}
const ui = new UI();

ui.btn_tekneTurlari.addEventListener("click",function(){
    window.location.href = "tekneTurlariDetay.html";
})

