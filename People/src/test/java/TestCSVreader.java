import lab.chernyshev.CSVRead;
import lab.chernyshev.CSVRead;
import lab.chernyshev.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

@RunWith(JUnit4.class)
public class TestCSVreader {
    @Test
    public void testRead() throws IOException {
        List<Person> personList = CSVRead.Read("foreign_names.csv",';');
        Assert.assertEquals("invalid size",25898,personList.size());
    }
}
