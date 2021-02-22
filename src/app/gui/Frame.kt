package app.gui

import lib.sRAD.gui.sComponent.SFrame
import kotlin.random.Random

object Frame: SFrame(ESTANDAR) {
    init {
        setMainBar("Simulación de Monte Carlo")
    }

    fun actualizar() {

    }
}

object App {
    var costoMuestra1 = 0.0
    var costoReclamo = 0.0
    var costoPBN = 0.0
    var productos = mutableListOf<Producto>()

    fun simular (cantidadProductos: Int) {
        //inicializa valores
        costoMuestra1 = 0.0
        costoReclamo = 0.0
        costoPBN = 0.0
        productos.clear()
        //inicia simulación
        for (i in 0 until cantidadProductos) {
            //crea el producto
            productos.add(Producto())
            //lo inserta en el modelo
            operacion1(productos.last())
        }
        //grafica
        Frame.actualizar()
    }

    fun operacion1 (producto: Producto) {
        //establece estado
        if (producto.estado == "R1") {
            producto.estado == "PC"
        }
        else if(Random.nextDouble() < 0.84) {
            producto.estado = "PC"
        }
        else {
            if (Random.nextDouble() < 0.18125) {
                producto.estado = "D"
            }
            else {
                producto.estado = "R1"
            }
        }
        //calcula tiempos
        producto.tiempoOp1 += 4.3 + Random.nextDouble()*(7.1-4.3)
        //envia producto a muestra 1
        muestra1 (producto)
    }

    fun muestra1 (producto: Producto) {
        if (Random.nextDouble() < 0.5) {
            operacion2(producto)
        }
        else {
            if (producto.estado == "C") {
                operacion2(producto)
            }
            else if (producto.estado == "R") {
                operacion1(producto)
            }
            costoMuestra1 += 7
        }
    }

    fun operacion2(producto: Producto) {
        if (producto.estado == "R2") {
            producto.estado == "PC"
        }
        else if (Random.nextDouble() < 0.927) {
            producto.estado = "PC"
        }
        else {
            val pseudoAleatorio = Random.nextDouble()
            if (pseudoAleatorio < 9.0/73.0) {
                producto.estado = "D"
            }
            else if (pseudoAleatorio < 45.0/73.0) {
                producto.estado = "R2"
            }
            else if (pseudoAleatorio < 62.0/73.0) {
                producto.estado = "RP"
            }
            else {
                producto.estado = "RC"
            }
        }
        //calcula tiempos
        producto.tiempoOp2 += 9.1 + Random.nextDouble()*(11.4-9.1)
        //envia los productos a la muestra 2
        muestra2 (producto)
    }

    fun muestra2 (producto: Producto) {
        if (Random.nextDouble() < 0.5) {
            producto.precio = 2800.0
            cliente(producto)
        }
        else {
            if (producto.estado == "D") {
                producto.precio = 9.0 //pre-venta
                cliente(producto)
            }
            else if (producto.estado == "RC") {
                producto.precio = 1800.0
                cliente(producto)
            }
            else if (producto.estado == "RP") {
                //taller - calcula tiempos
                producto.tiempoM2 += 5.2 + Random.nextDouble()*(7.3-5.2)
                producto.precio = 2800.0
                cliente(producto)
            }
            else if (producto.estado == "PC") {
                producto.precio = 2800.0
                cliente(producto)
            }
            else {
                operacion2(producto)
            }
        }
        //calcula tiempos
        producto.tiempoM2 += 3.7 + Random.nextDouble()*(9.9-3.7)
    }

    fun cliente (producto: Producto) {
        if (producto.estado == "D" && producto.precio > 9.0) {
            if(Random.nextDouble() < 103.0/138.0) {
                //reclama
                costoReclamo += 60.0
            }
            else {
                //no vuelve
                costoPBN += 170.0
            }
        }
    }
}

class Producto {
    /**
     * PC = Producto Conforme.
     * D = Desecho.
     * R1 = Re-proceso en operacion 1.
     * R2 = Re-proceso en operacion 2.
     * RP = Reparacion.
     * RC = Re-clasificacion.
     */
    var estado = ""
    var tiempoOp1 = 0.0
    var tiempoOp2 = 0.0
    var tiempoM2 = 0.0
    var tiempoT = 0.0
    var precio = 0.0
}