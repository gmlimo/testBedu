import java.util.Scanner

fun main(args: Array<String>){

    val café = 45
    val donas = 22

    val nombre = "Martín"
    val empresa = "UPBC"
    val uid = "50015"
    val fondos = 525.75
    val gender = false
    val age = 40

    var total = 0.0f
    val iva = 1.08f

    print("¿Deseas ver nuestros productos? ")
    val respuesta = readLine()
    if(respuesta == "si"){
        println("El café cuesta: $café y las donas: $donas")
    }
    else {
        println("gracias")
    }

    print("¿Desea comprar algo? Escriba su respuesta: ")
    val respuesta2 = readLine()

    val reader = Scanner(System.`in`)
    print("Escriba la cantidad: ")
    var respuesta3:Int = reader.nextInt()

    if(respuesta2 == "donas"){
        total = donas*respuesta3*iva
        println("Su total a pagar es: $total")
    }

    else if(respuesta2 == "café"){
        total = café*respuesta3*iva
        println("Su total a pagar es: $total")
    }

    else{
        println("Nos vemos a la próxima")
    }

}