package e2e;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class EtiquetaTest extends E2ETestBed {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    //R6HU1


    //R6HU2
    @Test
    public void addEtiquetaCoordenadasValidas() {
        controller.addEtiquetaCoordenadas("Casa", 39.9924751, -0.067382);
        Coordenadas coord = controller.getEtiqueta("Casa");
        assertEquals(coord.getLatitud(), 39.9924751);
        assertEquals(coord.getLongitud(), -0.067382);

    }

    @Test
    public void addEtiquetaCoordenadasNoValidas() {
        thrown.expect(IllegalArgumentException.class);
        controller.addEtiquetaCoordenadas("Casa", 1111132.222222, -1021313.8388383);
    }

}
