import clases.*

val datos = Data()
var costoDonas = 0.0f
var costoCafe = 0.0f

fun main(){
/*
    val donas = Donut(Size.Small, "Chocolate")
    costoDonas = donas.subTotal(3)

    val cafe = Coffee(Size.Jumbo, "Capuchino")
    costoCafe = cafe.subTotal(2)

    var total = (costoDonas + costoCafe) * datos.iva
    println("El total a pagar es: $total")*/


    //Registro de usuarios nuevo
    val user = User("William", 2, "1234", "wlimon@cituspower.com")

    //Recibe usurario y constraseña
    print("Escribe tu usuario: ")
    val usuario = readLine()!!
    print("Escribe tu contraseña: ")
    val contraseña = readLine()!!

    /*Un login exitoso te lleva al selección de productos
    **Si hay una falla aparece el mensaje usuario y/o contraseña incorrectos
     */
    if (user.login(usuario, contraseña)){
        println("Login exitoso")
        //println("Estos son nuestros productos: $productList")
        println(" (1) Café \n (2) Donas")
        print("Selecciona la opción correcta: 1 es para Café 2 es para Donas: ")
        var selección = readLine()!!.toInt()

        //Review the options and sets the correct key to get the proper value(price) of the map
       val selcadena = when (selección){
           1 -> "café"
           2 -> "donas"
           else -> "No existe producto"
        }
        if (selección > 2) {
            println(selcadena)
            return
        }
        println("El precio de $selcadena es: ${datos.productList.getValue(selcadena)}")
    } else println("Usuario y/o contraseña incorrectos")


/*  //Login function test
    println(login("teaby", "hola"))

    var precio = productList.getValue("coffee")
    println("El precio del producto es: $precio") */


}


