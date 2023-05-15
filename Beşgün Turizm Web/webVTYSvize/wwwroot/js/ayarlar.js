function UI() {
    this.btn_hesabim = document.querySelector(".btn_hesabim"),
        this.btn_rezervasyon = document.querySelector(".btn_rezervasyon"),
        this.btn_cikisYap = document.querySelector(".btn_cikisYap"),
        this.btn_hesabiSil = document.querySelector(".btn_hesabiSil"),
        this.formisim = document.querySelector(".formisim"),
        this.formsoyad = document.querySelector(".formsoyad"),
        this.formemail = document.querySelector(".formemail"),
        this.formtelefon = document.querySelector(".formtelefon"),
        this.formsifre = document.querySelector(".formsifre"),
        this.formdogumTarihi = document.querySelector(".formdogumTarihi")
}
const ui = new UI();

document.getElementById("divRezervasyon").style.visibility = "hidden";
document.getElementById("divHesabim").style.visibility = "visible";

document.querySelector('#hesabim').classList.add('active-hesabim');
document.querySelector('#rezervasyon').classList.remove('active-rezervasyon');
document.querySelector('#cikisYap').classList.remove('active-cikisYap');
document.querySelector('#hesabiSil').classList.remove('active-hesabiSil');

ui.btn_hesabim.addEventListener("click", function () {
    document.getElementById("divHesabim").style.visibility = "visible";
    document.getElementById("divRezervasyon").style.visibility = "hidden";

    document.querySelector('#divHesabim').classList.remove('position-absolute');
    document.querySelector('#divHesabim').classList.remove('top-50');
    document.querySelector('#divHesabim').classList.remove('start-50');
    document.querySelector('#divHesabim').classList.remove('translate-middle');
    document.querySelector('#divRezervasyon').classList.add('position-absolute');
    document.querySelector('#divRezervasyon').classList.add('top-50');
    document.querySelector('#divRezervasyon').classList.add('start-50');
    document.querySelector('#divRezervasyon').classList.add('translate-middle');

    document.querySelector('#hesabim').classList.add('active-hesabim');
    document.querySelector('#rezervasyon').classList.remove('active-rezervasyon');
    document.querySelector('#cikisYap').classList.remove('active-cikisYap');
    document.querySelector('#hesabiSil').classList.remove('active-hesabiSil');
})

ui.btn_rezervasyon.addEventListener("click", function () {
    document.getElementById("divHesabim").style.visibility = "hidden";
    document.getElementById("divRezervasyon").style.visibility = "visible";

    document.querySelector('#divHesabim').classList.add('position-absolute');
    document.querySelector('#divHesabim').classList.add('top-50');
    document.querySelector('#divHesabim').classList.add('start-50');
    document.querySelector('#divHesabim').classList.add('translate-middle');
    document.querySelector('#divRezervasyon').classList.remove('position-absolute');
    document.querySelector('#divRezervasyon').classList.remove('top-50');
    document.querySelector('#divRezervasyon').classList.remove('start-50');
    document.querySelector('#divRezervasyon').classList.remove('translate-middle');

    document.querySelector('#hesabim').classList.remove('active-hesabim');
    document.querySelector('#rezervasyon').classList.add('active-rezervasyon');
    document.querySelector('#cikisYap').classList.remove('active-cikisYap');
    document.querySelector('#hesabiSil').classList.remove('active-hesabiSil');
})

ui.btn_cikisYap.addEventListener("click", function () {
    document.querySelector('#hesabim').classList.remove('active-hesabim');
    document.querySelector('#rezervasyon').classList.remove('active-rezervasyon');
    document.querySelector('#cikisYap').classList.add('active-cikisYap');
    document.querySelector('#hesabiSil').classList.remove('active-hesabiSil');

    document.getElementById("divRezervasyon").style.visibility = "hidden";
    document.getElementById("divHesabim").style.visibility = "hidden";
})

ui.btn_hesabiSil.addEventListener("click", function () {
    document.querySelector('#hesabim').classList.remove('active-hesabim');
    document.querySelector('#rezervasyon').classList.remove('active-rezervasyon');
    document.querySelector('#cikisYap').classList.remove('active-cikisYap');
    document.querySelector('#hesabiSil').classList.add('active-hesabiSil');

    document.getElementById("divRezervasyon").style.visibility = "hidden";
    document.getElementById("divHesabim").style.visibility = "hidden";
})


//const myForm = document.getElementById('myForm');
//const myElement = document.getElementById('myElement');

//myForm.addEventListener('submit', (event) => {
//    event.preventDefault();

//    const formData = new FormData(myForm);
//    const adi = formData.get('adi');
//    const soyadi = formData.get('soyadi');
//    const eposta = formData.get('eposta');
//    const telefonNumarasi = formData.get('telefonNumarasi');
//    const sifre = formData.get('sifre');
//    const dogumTarihi = formData.get('dogumTarihi');
//    const myValue = myElement.dataset.myValue;

//    const xhr = new XMLHttpRequest();

//    xhr.open('POST', '/my-server-url');
//    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

//    xhr.onreadystatechange = function () {
//        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
//            console.log('Response received:', this.responseText);
//        }
//    };

//    xhr.send(`adi=${adi}&soyadi=${soyadi}}&eposta=${eposta}}&telefonNumarasi=${telefonNumarasi}}&sifre=${sifre}}&dogumTarihi=${dogumTarihi}&myValue=${myValue}`);
//});









//const params = new URLSearchParams(window.location.search);
//const ad = params.get("name");
//const soyad = params.get("surname");
//const eposta = params.get("email");
//const telefon = params.get("phone");
//const sifre = params.get("password");
//const dogumTarihi = params.get("date");

//console.log(`Ad: ${ad}, Soyad: ${soyad}, E-posta: ${eposta}, Telefon: ${telefon}, Þifre: ${sifre}, Doðum Tarihi: ${dogumTarihi}`);
//ui.formisim.value = ad;
//ui.formsoyad.value = soyad;
//ui.formemail.value = eposta;
//ui.formtelefon.value = telefon;
//ui.formsifre.value = sifre;
//ui.formdogumTarihi.value = dogumTarihi;
