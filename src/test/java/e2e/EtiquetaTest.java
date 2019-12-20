package e2e;

import Model.Coord;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;


public class EtiquetaTest extends E2ETestBed {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    //R6HU1


    //R6HU2
    @Test
    public void addEtiquetaCoordenadasValidas() {
        controller.addEtiquetaCoordenadas("Casa", new Coord(-0.067382, 39.9924751));
        Coord coord = controller.getEtiqueta("Casa");
        assertEquals(39.9924751, coord.getLat());
        assertEquals(-0.067382, coord.getLon());

    }

    @Test
    public void addEtiquetaCoordenadasNoValidas() {
        thrown.expect(IllegalArgumentException.class);
        controller.addEtiquetaCoordenadas("Casa", new Coord(1111132.222222, -1021313.8388383));
    }

}
