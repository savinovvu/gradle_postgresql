import org.junit.Test;
import ru.inbox.savinov_vu.entity.SavedFile;
import ru.inbox.savinov_vu.service.Service;

/**
 * Created by skorpion on 08.10.16.
 */
public class ServiceTest {

    Service service;

    @Test
    public void testCreate() {
        SavedFile savedFile = new SavedFile("testИван", "777-777");
        service.create(savedFile);
        System.out.println(savedFile);
    }




}
