import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import lab01.TqsStack;

class AutoTqsStackTest {

    private TqsStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new TqsStack<>();
    }

    @Test
    void testStackIsEmptyOnCreation() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void testPushIncreasesSize() {
        stack.push(10);
        stack.push(20);
        assertEquals(2, stack.size());
    }

    @Test
    void testPopReturnsLastPushedElement() {
        stack.push(5);
        stack.push(15);
        assertEquals(15, stack.pop());
        assertEquals(1, stack.size());
    }

    @Test
    void testPeekReturnsLastElementWithoutRemoving() {
        stack.push(7);
        assertEquals(7, stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void testPopOnEmptyStackThrowsException() {
        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void testPeekOnEmptyStackThrowsException() {
        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @Test
    void testPushBeyondCapacityThrowsException() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        assertThrows(IllegalStateException.class, () -> stack.push(6));
    }
}
