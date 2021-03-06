package dbunit;

import static org.junit.Assert.assertEquals;

import java.util.List;
import com.engagepoint.university.messaging.dao.repository.UserDAO;
import com.engagepoint.university.messaging.dto.UserDTO;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.springtestdbunit.annotation.DatabaseSetup;

public class UserDAOTest extends DBUnitContextInit{

    @Autowired
    private UserDAO userDAO;

    @Test
    @DatabaseSetup("insertDataUserDAO.xml")
    public void testSave() throws Exception {
        List<UserDTO> userList = userDAO.getAll();

        assertEquals(2, userList.size());
        assertEquals("jonny", userList.get(0).getName());
    }

    @Test
    @DatabaseSetup("insertDataUserDAO.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "expectedDataUserDAO.xml")
    public void testRemove() throws Exception {
        userDAO.delete(1);
    }

}
