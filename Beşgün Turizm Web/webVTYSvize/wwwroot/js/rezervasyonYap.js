function UI() {
    this.btn_yetiskinArti = document.querySelector(".btn_yetiskinArti"),
    this.btn_yetiskinEksi = document.querySelector(".btn_yetiskinEksi"),
    this.btn_cocukArti = document.querySelector(".btn_cocukArti"),
    this.btn_cocukEksi = document.querySelector(".btn_cocukEksi"),
    this.btn_drop = document.querySelector(".btn_drop")
}
const ui = new UI();

let yetiskinSayisi2 = 1
let cocukSayisi2 = 0

let yetiskinSayisi = document.getElementById("yetiskinSayisi")
let cocukSayisi = document.getElementById("cocukSayisi")

let formlarYetiskin = document.querySelector(".formlarYetiskin");
let adetYetiskin = 1;
let formlarCocuk = document.querySelector(".formlarCocuk");
let adetCocuk = 1;

function formYetiskinArti(adetYetiskin) {
    formlarYetiskin.innerHTML = "";
    for (let i = 1; i < adetYetiskin; i++) {
        let form = document.createElement("form");
        form.id = "form-" + i;
        form.innerHTML = `
        <div class="bg-light p-4 mb-3 rounded mx-auto">
        <h4 class="text-center"><span>${i}</span>.Yetiþkin</h4>
        <input type="name" class="form-control mb-3" id="name" placeholder="Ýsim">
        <input type="surname" class="form-control mb-3" id="surname" placeholder="Soyisim">
        <input type="date" class="form-control mb-3" name="date" id="date" placeholder="Doðum Tarihi" min="1923-01-01" max="2030-12-31">
        <input type="phone" class="form-control mb-3" id="phone" placeholder="Telefon Numarasý">
        <input type="email" class="form-control mb-3" id="email" placeholder="E-Posta">
        </div>
      `;
        formlarYetiskin.appendChild(form);
    }
}

function formYetiskinEksi() {
    if (adetYetiskin > 1) {
        let formId = "form-" + (adetYetiskin - 1); // son oluþturulan formun id'si
        let form = document.getElementById(formId);
        formlarYetiskin.removeChild(form); // form elemaný silinir
        adetYetiskin--;
    }
}

function formCocukArti(adetCocuk) {
    formlarCocuk.innerHTML = "";
    for (let i = 1; i < adetCocuk; i++) {
        let form = document.createElement("form");
        form.id = "form-" + i;
        form.innerHTML = `
        <div class="bg-light p-4 mb-3 rounded mx-auto">
        <h4 class="text-center"><span>${i}</span>.Çocuk</h4>
        <input type="name" class="form-control mb-3" id="name" placeholder="Ýsim">
        <input type="surname" class="form-control mb-3" id="surname" placeholder="Soyisim">
        <input type="date" class="form-control mb-3" name="date" id="date" placeholder="Doðum Tarihi" min="1923-01-01" max="2030-12-31">
        </div>
      `;
        formlarCocuk.appendChild(form);
    }
}

function formCocukEksi() {
    if (adetCocuk > 1) {
        let formId = "form-" + (adetCocuk - 1); // son oluþturulan formun id'si
        let form = document.getElementById(formId);
        formlarCocuk.removeChild(form); // form elemaný silinir
        adetCocuk--;
    }
}

ui.btn_yetiskinArti.addEventListener("click", function () {
    yetiskinSayisi2 = parseInt(yetiskinSayisi.value.toString())
    yetiskinSayisi2 = yetiskinSayisi2 + 1;
    yetiskinSayisi.value = yetiskinSayisi2.toString();
    ui.btn_drop.textContent = yetiskinSayisi2 + " Yetiþkin, " + cocukSayisi2 + " Çocuk"
    console.log(yetiskinSayisi2.toString())

    adetYetiskin++;
    formYetiskinArti(adetYetiskin);
});

ui.btn_yetiskinEksi.addEventListener("click", function () {
    yetiskinSayisi2 = parseInt(yetiskinSayisi.value.toString())
    yetiskinSayisi2 = yetiskinSayisi2 - 1;
    yetiskinSayisi.value = yetiskinSayisi2.toString();
    ui.btn_drop.textContent = yetiskinSayisi2 + " Yetiþkin, " + cocukSayisi2 + " Çocuk"
    console.log(yetiskinSayisi2.toString())

    formYetiskinEksi();
});

ui.btn_cocukArti.addEventListener("click", function () {
    cocukSayisi2 = parseInt(cocukSayisi.value.toString())
    cocukSayisi2 = cocukSayisi2 + 1;
    cocukSayisi.value = cocukSayisi2.toString();
    ui.btn_drop.textContent = yetiskinSayisi2 + " Yetiþkin, " + cocukSayisi2 + " Çocuk"
    console.log(cocukSayisi2.toString())

    adetCocuk++;
    formCocukArti(adetCocuk);
});

ui.btn_cocukEksi.addEventListener("click", function () {
    cocukSayisi2 = parseInt(cocukSayisi.value.toString())
    cocukSayisi2 = cocukSayisi2 - 1;
    cocukSayisi.value = cocukSayisi2.toString();
    ui.btn_drop.textContent = yetiskinSayisi2 + " Yetiþkin, " + cocukSayisi2 + " Çocuk"
    console.log(cocukSayisi2.toString())

    formCocukEksi();
});

const form = document.querySelector('form');



