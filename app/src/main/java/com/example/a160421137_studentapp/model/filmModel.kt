package com.example.a160421137_studentapp.model

 data class filmModel (
    val judul:String?,
    val tahun:Int?,
    val sutradara: sutradara,
    val aktorUtama:List<String>?,
    val genre:List<String>?,
    val ulasan: ulasan
)
data class sutradara(
 val nama:String?,
 val negara:String?
)
 data class ulasan(
  val ulasanPenulis:String?,
  val rating:Int?
 )
