import org.scalatest.FunSuite

/**
 * Created by sauloaguiar on 10/4/14.
 */
class RsaTest extends FunSuite {

  test("key size positive") {
    intercept[NumberFormatException]{
      new RSA(-10)
    }
  }

  test("Encrypt empty string") {
    val a = new RSA(16)
    val enc = a.encrypt(" ".getBytes)
    assert(" ".getBytes sameElements a.decrypt(enc))
  }

  test("Encrypt string smaller than key"){
    val rsa = new RSA(16)
    val enc = rsa.encrypt("a".getBytes)
    assert("a".getBytes() sameElements rsa.decrypt(enc))
  }

  test("Encrypt string with same key size") {
    val rsa = new RSA(16)
    val enc = rsa.encrypt("ab".getBytes)
    assert("ab".getBytes sameElements rsa.decrypt(enc))
  }

  test("Encrypt string bigger than key"){
    val rsa = new RSA(48)
    val enc = rsa.encrypt("message".getBytes)
    assert("message".getBytes sameElements rsa.decrypt(enc))
  }

}
