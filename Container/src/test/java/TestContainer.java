import lab.chernyshev.Container;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class TestContainer {


    @Test
    public void testAdd() {
        Container<Integer> container = new Container<>();
        container.add(1);
        container.add(2);
        container.add(3);
        Assert.assertEquals(3, container.size());
    }

    @Test
    public void testGet() {
        Container<String> container = new Container<>();
        container.add("A");
        container.add("B");
        container.add("C");

        Assert.assertEquals("A", container.get(0));
        Assert.assertEquals("B", container.get(1));
        Assert.assertEquals("C", container.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetInvalidIndex() {
        Container<Double> container = new Container<>();
        container.get(0);
    }

    @Test
    public void testRemove() {
        Container<Integer> container = new Container<>();
        container.add(1);
        container.add(2);
        container.add(3);

        Assert.assertTrue(container.remove(2));
        Assert.assertEquals(2, container.size());
        Assert.assertFalse(container.remove(4));
    }

    @Test
    public void testIterator() {
        Container<Character> container = new Container<>();
        container.add('A');
        container.add('B');
        container.add('C');

        StringBuilder result = new StringBuilder();
        for (Character item : container) {
            result.append(item);
        }

        Assert.assertEquals("ABC", result.toString());
    }

    @Test
    public void testEmptyContainer() {
        Container<String> container = new Container<>();

        Assert.assertEquals(0, container.size());
        Assert.assertFalse(container.remove("A"));
    }

}
