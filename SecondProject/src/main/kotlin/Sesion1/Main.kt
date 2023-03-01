package Sesion1
const val mayoriaEdad = 18

fun main() {
    var a = 10L
    var b = 5

    val result:Int
    //a= a.toInt().toLong()
    result = (a+b).toInt()
    println(result)

    val edad = readLine()!!.toInt()
    val eresMayor = edad >= mayoriaEdad
    println("Eres mayor de edad? $eresMayor")
}