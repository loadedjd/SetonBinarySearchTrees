import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    @Test
    public void testConstructorEmpty() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1Expected = this.createFromArgsRef();

        assertEquals(s1, s1Expected);
    }

    @Test
    public void testConstructorNonEmpty() {
        Set<String> s1 = this.createFromArgsTest("1", "2", "3");
        Set<String> s1Expected = this.createFromArgsRef("1", "2", "3");

        assertEquals(s1, s1Expected);
    }

    @Test
    public void testRemoveEmpty() {
        Set<String> s1 = this.createFromArgsTest("1");
        Set<String> s1Expected = this.createFromArgsRef();

        String temp = s1.remove("1");

        assertEquals(temp, "1");
        assertEquals(s1, s1Expected);
    }

    @Test
    public void testRemoveNonEmpty() {
        Set<String> s1 = this.createFromArgsTest("1", "2", "3");
        Set<String> s1Expected = this.createFromArgsRef("2", "3");

        String temp = s1.remove("1");

        assertEquals(temp, "1");
        assertEquals(s1, s1Expected);
    }

    @Test
    public void testRemoveAnyEmpty() {
        Set<String> s1 = this.createFromArgsTest("1");

        String temp = s1.removeAny();

        assertEquals(s1.contains(temp), false);
        assertEquals(s1.size(), 0);
    }

    @Test
    public void testRemoveAnyNonEmpty() {
        Set<String> s1 = this.createFromArgsTest("1", "2", "3");

        String temp = s1.removeAny();

        assertEquals(s1.contains(temp), false);
        assertEquals(s1.size(), 2);
    }

    @Test
    public void testDoesNotContains() {
        Set<String> s1 = this.createFromArgsTest("1", "2", "3");
        Set<String> s1Expected = this.createFromArgsRef("1", "2", "3");

        assertEquals(s1, s1Expected);
        assertEquals(s1.contains("4"), false);
    }

    @Test
    public void testDoesContain() {
        Set<String> s1 = this.createFromArgsTest("1", "2", "3");
        Set<String> s1Expected = this.createFromArgsRef("1", "2", "3");

        assertEquals(s1, s1Expected);
        assertEquals(s1.contains("1"), true);
    }

    @Test
    public void testAddEmpty() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1Expected = this.createFromArgsRef("1");

        s1.add("1");

        assertEquals(s1, s1Expected);
    }

    @Test
    public void testAddNonEmpty() {
        Set<String> s1 = this.createFromArgsTest("1", "2", "3");
        Set<String> s1Expected = this.createFromArgsRef("1", "2", "3", "4");

        s1.add("4");

        assertEquals(s1, s1Expected);
    }

    @Test
    public void testSizeEmpty() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1Expected = this.createFromArgsRef();

        assertEquals(s1.size(), 0);
        assertEquals(s1, s1Expected);
    }

    @Test
    public void testSizeNonEmpty() {
        Set<String> s1 = this.createFromArgsTest("1", "2", "3");
        Set<String> s1Expected = this.createFromArgsRef("1", "2", "3");

        assertEquals(s1.size(), 3);
        assertEquals(s1, s1Expected);
    }

}
