package e2e;

import Controller.Aplicacion;
import org.junit.After;
import org.junit.Before;

public abstract class E2ETestBed {

    protected Aplicacion controller;

    @Before
    public void setUp() {
        controller = new Aplicacion();
    }

    @After
    public void tearDown() {
        controller = null;
    }
}
