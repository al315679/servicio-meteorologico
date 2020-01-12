package e2e;

import Controller.Aplicacion;
import Model.BaseDatos;
import Model.IBaseDatos;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FavoritosTest {

    private static IBaseDatos db;
    private static Aplicacion controller;

    @BeforeClass
    public static void setUp() {
        controller = new Aplicacion();
        db = controller.getDB();
    }

    @AfterClass
    public static void tearDown() {
        db = null;
        controller = null;
    }

    @Test
    public void A_addCiudadFavoritosSuc() {
        assertTrue(db.anadirCiudadFavorita("Castellón"));
    }

    @Test
    public void B_addCiudadExistenteFavoritosFail() {
        assertFalse(db.anadirCiudadFavorita("Castellón"));
    }

    @Test
    public void C_addCoordFavoritosSuc() {
        assertTrue(db.anadirCoordenadasFavoritas("39.9924751, -0.067382"));
    }

    @Test
    public void D_addCoordExistentesFavoritosFail() {
        assertFalse(db.anadirCoordenadasFavoritas("39.9924751, -0.067382"));
    }

    @Test
    public void E_deleteCiudadFavoritosSuc() {
        assertTrue(db.borrarCiudadFavorita("Castellón"));
    }

    @Test
    public void F_deleteCiudadNoExistenteFavoritosFail() {
        assertFalse(db.borrarCiudadFavorita("Castellón"));
    }

    @Test
    public void G_deleteCoordFavoritosSuc() {
        assertTrue(db.borrarCoordenadasFavoritas("39.9924751, -0.067382"));
    }

    @Test
    public void H_deleteCoordNoExistentesFavoritosFail() {
        assertFalse(db.borrarCoordenadasFavoritas("39.9924751, -0.067382"));
    }
}
