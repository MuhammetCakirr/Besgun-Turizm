function UI(){
    this.btn_yetiskinArti = document.querySelector(".btn_yetiskinArti");
    this.btn_yetiskinEksi = document.querySelector(".btn_yetiskinEksi");
    this.btn_cocukArti = document.querySelector(".btn_cocukArti");
    this.btn_cocukEksi = document.querySelector(".btn_cocukEksi");
    this.btn_drop = document.querySelector(".btn_drop");
}

const ui = new UI();
let yetiskinSayisi2 = 1;
let cocukSayisi2 = 0;

ui.btn_yetiskinArti.addEventListener("click", function(){
    let yetiskinSayisi = document.querySelector("#yetiskinSayisi");
    yetiskinSayisi2 = parseInt(yetiskinSayisi.value);
    yetiskinSayisi2 = yetiskinSayisi2 + 1;
    yetiskinSayisi.value = yetiskinSayisi2.toString();
    ui.btn_drop.textContent = yetiskinSayisi2 + " Yetişkin, " + cocukSayisi2 + " Çocuk";
    console.log(yetiskinSayisi2.toString());
});

ui.btn_yetiskinEksi.addEventListener("click", function(){
    let yetiskinSayisi = document.querySelector("#yetiskinSayisi");
    yetiskinSayisi2 = parseInt(yetiskinSayisi.value);
    yetiskinSayisi2 = yetiskinSayisi2 - 1;
    yetiskinSayisi.value = yetiskinSayisi2.toString();
    ui.btn_drop.textContent = yetiskinSayisi2 + " Yetişkin, " + cocukSayisi2 + " Çocuk";
    console.log(yetiskinSayisi2.toString());
});

ui.btn_cocukArti.addEventListener("click", function(){
    let cocukSayisi = document.querySelector("#cocukSayisi");
    cocukSayisi2 = parseInt(cocukSayisi.value);
    cocukSayisi2 = cocukSayisi2 + 1;
    cocukSayisi.value = cocukSayisi2.toString();
    ui.btn_drop.textContent = yetiskinSayisi2 + " Yetişkin, " + cocukSayisi2 + " Çocuk";
    console.log(cocukSayisi2.toString());
});

ui.btn_cocukEksi.addEventListener("click", function(){
    let cocukSayisi = document.querySelector("#cocukSayisi");
    cocukSayisi2 = parseInt(cocukSayisi.value);
    cocukSayisi2 = cocukSayisi2 - 1;
    cocukSayisi.value = cocukSayisi2.toString();
    ui.btn_drop.textContent = yetiskinSayisi2 + " Yetişkin, " + cocukSayisi2 + " Çocuk";
    console.log(cocukSayisi2.toString());
});

var swiper = new Swiper('.swiper', {
    slidesPerView: 3,
    direction: getDirection(),
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
    on: {
        resize: function () {
            swiper.changeDirection(getDirection());
        },
    },
});

function getDirection() {
    var windowWidth = window.innerWidth;
    var direction = window.innerWidth <= 760 ? 'vertical' : 'horizontal';

    return direction;
}
