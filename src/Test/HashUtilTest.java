package Controlador;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HashUtilTest {


    //PRUEBA UNITARIA
    @Test
    void md5() {

        String input = ""; //cadena que se pasa como parámetro al llamar al método testeado

        String esperado = "d41d8cd98f00b204e9800998ecf8427e"; //hexadecimal equivalente a una cadena de texto vacía

        String resultado = HashUtil.md5(input); //variable que llama al método

        assertEquals(esperado, resultado,
                "Error. El hash no corresponde a la cadena proporcionada.");
    }


    @Test
    void md5_TextoConocido_DeberiaRetornarHashCorrecto() {

        String input = "HolaMundo"; //cadena que se pasa como parámetro al llamar al método testeado

        String esperado = "49342000ca291986c11b009b3127356f"; //hexadecimal equivalente a la cadena de texto "HolaMucho"

        String resultado = HashUtil.md5(input);  //variable que llama al método

        assertEquals(esperado, resultado, "El MD5 no corresponde a la cadena 'HolaMundo'");
    }



    @Test
    void md5_Null_DeberiaLanzarNullPointerException() {

        assertThrows(NullPointerException.class, () -> {
            HashUtil.md5(null);
        }, "Al invocar md5(null) debe lanzarse NullPointerException");
    }






    }
