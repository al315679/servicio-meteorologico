package Controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AplicacionTiempoActualTest.class, AplicacionTiempoPrediccionTest.class,
        AplicacionPrediccionSinConexionTest.class, AplicacionTiempoSinConexionTest.class})
public class SuiteIntegrationTest {
}
