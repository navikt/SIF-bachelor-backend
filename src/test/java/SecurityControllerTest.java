import com.bachelor.vju_vm_apla2.Controller.SecurityController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SecurityControllerTest {
    /**
     * @UnitTests: SecurityController
     */
    //todo: creating a unit test for our security controller
    //how should i test the security controller

    @Mock
    private SecurityController securityController;
    @Test
    public void loginTest(){
     String test = String.valueOf(securityController.login());
        System.out.println(test.toString());
    }
}
