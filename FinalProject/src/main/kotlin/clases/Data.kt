package clases

data class Data(
    //Map of the Products Available
    val productList: Map<String, Float> = mapOf(
        "café" to 42.50f,
        "donas" to 12.40f,
        "waffle" to 52.50f,
        "pastel" to 120.50f,
        "gelatina" to 14.50f
     ),

    //Map of pre-registered user name and password
    val loginData: Map<String, String> = mapOf(
        "1234" to "gmlimon",
        "hola" to "teaby",
        "5678" to "mbravo"
    ),

    val iva: Float = 1.08f

)
