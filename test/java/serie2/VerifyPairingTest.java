package serie2;

import static org.junit.Assert.*;
import static serie2.Utils.verifyPairing;

import org.junit.Test;

public class VerifyPairingTest {

	@Test
	public void  verifyPairing_empty_String() {
		assertTrue( verifyPairing("") );
	}

	@Test
	public void  verifyPairing_simple_pair() {
		assertTrue( verifyPairing("{}") );
		assertTrue( verifyPairing("()") );
		assertTrue( verifyPairing("[]") );
		assertTrue( verifyPairing("..{...}") );
		assertTrue( verifyPairing(".(.).") );
		assertTrue( verifyPairing("[.]..") );
	}
	
	@Test
	public void  verifyPairing_open_only() {
		assertFalse( verifyPairing("{") );
		assertFalse( verifyPairing("[") );
		assertFalse( verifyPairing("(") );
		assertFalse( verifyPairing("..{..") );
		assertFalse( verifyPairing(".[") );
		assertFalse( verifyPairing("(.") );
	}

	@Test
	public void  verifyPairing_close_only() {
		assertFalse( verifyPairing("}") );
		assertFalse( verifyPairing(")") );
		assertFalse( verifyPairing("]") );
		assertFalse( verifyPairing("..}..") );
		assertFalse( verifyPairing(".)") );
		assertFalse( verifyPairing("].") );
	}
	
	@Test
	public void  verifyPairing_close_and_open_inverted() {
		assertFalse( verifyPairing("}{") );
		assertFalse( verifyPairing(")(") );
		assertFalse( verifyPairing("][") );
		assertFalse( verifyPairing("..}{..") );
		assertFalse( verifyPairing(".)(") );
		assertFalse( verifyPairing("][.") );
		assertFalse( verifyPairing("][.") );
	}

	@Test
	public void  verifyPairing_different_pair() {
		assertFalse( verifyPairing( "{]" ) );
		assertFalse( verifyPairing( "(}" ) );
		assertFalse( verifyPairing( "[)" ) );
		assertFalse( verifyPairing( "..{...)" ) );
		assertFalse( verifyPairing( ".(.]." ) );
		assertFalse( verifyPairing( "[.}.." ) );
		assertFalse( verifyPairing( "{)." ) );
	}
	
	@Test
	public void  verifyPairing_pairs() {
		assertTrue( verifyPairing( "{(-)[]([--{--} -]--)-{-}-}---" ) );
		assertTrue( verifyPairing( "{{-}(()[])[--{--} -]-(-)-{-}-}---" ) );
		assertFalse( verifyPairing( "{(-)[]([-- {--} -]--)-----" ) );
		assertFalse( verifyPairing( "{(-)[]([----} -]--)-{-}----" ) );
	}
}
