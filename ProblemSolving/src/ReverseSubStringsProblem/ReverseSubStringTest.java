package ReverseSubStringsProblem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseSubStringTest {
    ReverseSubString reverseSubString;

    @Before
    public void before() {
        reverseSubString = new ReverseSubString();
    }

    @Test
    public void testReverseSubStrings_whenOneParentheses() {
        assertEquals("abd(bnj)asdf", reverseSubString.reverseTheSubStrings("abd(jnb)asdf"));
    }

    @Test
    public void testReverseSubStrings_whenNoParentheses() {
        assertEquals("abdjnbasdf", reverseSubString.reverseTheSubStrings("abdjnbasdf"));
    }
    @Test
    public void testReverseSubStrings_whenTwoParentheses() {
        assertEquals("dd(fd)a(hhhg)", reverseSubString.reverseTheSubStrings("dd(df)a(ghhh)"));
    }

}