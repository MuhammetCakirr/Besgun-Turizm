package com.muhammetcakir.turizmacentasi.Models

data class Otel(
    val idOtel: Int,
    val adres: String,
    val HizmetBilgisi_idHizmetBilgisi: String,
    val puan:Double,
    val tema:String
)

data class ApiResponseOtel(
    val error: Boolean,
    val otel: List<Otel>
)

data class ApiResponseYurtD(
    val error: Boolean,
    val yurtdisitur: List<YurtdisiTur>
)
data class ApiResponseTekneT(
    val error: Boolean,
    val teknetur: List<TekneTur>
)

data class ApiResponseHizmetBilgisi(
    val error: Boolean,
    val hizmetbilgisi: List<HizmetBilgisi>
)
data class ApiResponseTurProgrami(
    val error: Boolean,
    val turprogrami: List<TurProgrami>
)
data class ApiResponseHizmet(
    val error: Boolean,
    val hizmet: List<Hizmet>
)
data class ApiResponseMusteri(
    val error: Boolean,
    val musteri: List<Musteri>
)
data class ApiResponseRezervasyon(
    val error: Boolean,
    val rezervasyon: List<Rezervasyon>
)
data class ApiResponseOtelView(
    val error: Boolean,
    val otel: List<OtelView>
)
data class ApiResponseYurtDisiTurView(
    val error: Boolean,
    val yurtdisitur: List<YurtDisiTurView>
)
data class ApiResponseTekneTurView(
    val error: Boolean,
    val teknetur: List<TekneTurView>
)