function UI(){
    this.btn_yetiskinArti=document.querySelector(".btn_yetiskinArti"),
    this.btn_yetiskinEksi=document.querySelector(".btn_yetiskinEksi"),
    this.btn_cocukArti=document.querySelector(".btn_cocukArti"),
    this.btn_cocukEksi=document.querySelector(".btn_cocukEksi"),
    this.btn_onayla=document.querySelector(".btn_onayla")
}
const ui = new UI();

ui.btn_yetiskinArti.addEventListener("click", function(){
    let yetiskinSayisi=document.querySelector(".yetiskinSayisi")
    let yetiskinSayisi2=0
    yetiskinSayisi2=parseInt(yetiskinSayisi.textContent.toString())
    yetiskinSayisi2=yetiskinSayisi2+1;
    yetiskinSayisi.textContent = yetiskinSayisi2.toString();
    console.log(yetiskinSayisi2.toString())
});

ui.btn_yetiskinEksi.addEventListener("click", function(){
    let yetiskinSayisi=document.querySelector(".yetiskinSayisi")
    let yetiskinSayisi2=0
    yetiskinSayisi2=parseInt(yetiskinSayisi.textContent.toString())
    yetiskinSayisi2=yetiskinSayisi2-1;
    yetiskinSayisi.textContent = yetiskinSayisi2.toString();
    console.log(yetiskinSayisi2.toString())
});

ui.btn_cocukArti.addEventListener("click", function(){
    let cocukSayisi=document.querySelector(".cocukSayisi")
    let cocukSayisi2=0
    cocukSayisi2=parseInt(cocukSayisi.textContent.toString())
    cocukSayisi2=cocukSayisi2+1;
    cocukSayisi.textContent = cocukSayisi2.toString();
    console.log(cocukSayisi2.toString())
});

ui.btn_cocukEksi.addEventListener("click", function(){
    let cocukSayisi=document.querySelector(".cocukSayisi")
    let cocukSayisi2=0
    cocukSayisi2=parseInt(cocukSayisi.textContent.toString())
    cocukSayisi2=cocukSayisi2-1;
    cocukSayisi.textContent = cocukSayisi2.toString();
    console.log(cocukSayisi2.toString())
});

