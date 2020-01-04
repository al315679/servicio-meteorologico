package e2e;

import Controller.Aplicacion;
import org.junit.After;
import org.junit.Before;

import java.security.Principal;

public abstract class E2ETestBed {

    protected Aplicacion controller;
    protected Principal me;

    @Before
    public void setUp() {

        controller = new Aplicacion();
    }

    @After
    public void tearDown() {
        controller = null;
    }
}
