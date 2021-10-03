package suites;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;


import tests.AgregarRecursosTest;
import tests.AltaProyectoTest;
import tests.ConsultarProyectoTest;


@RunWith(JUnitPlatform.class)
@SelectClasses({AltaProyectoTest.class, ConsultarProyectoTest.class, AgregarRecursosTest.class})

public class ProjectTests {
}





