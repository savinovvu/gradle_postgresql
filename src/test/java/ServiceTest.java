import org.junit.Test;
import ru.inbox.savinov_vu.entity.Human;
import ru.inbox.savinov_vu.service.Service;

/**
 * Created by skorpion on 08.10.16.
 */
public class ServiceTest {

    Service service;

    @Test
    public void testCreate() {
        Human human = new Human("testИван", "777-777");
        service.create(human);
        System.out.println(human);
    }




}
