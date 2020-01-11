package e2e;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TiempoActualTest.class, PrediccionTiempoTest.class, EtiquetaTest.class, FavoritosTest.class})
public class SuiteAcceptanceTest {
}
