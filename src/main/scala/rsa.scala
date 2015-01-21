import scala.util.Random
import math._

/**
 * Created by sauloaguiar on 9/27/14.
 */

class RSA(val keySize: Int) {

  if(keySize <= 0 ) {
    throw new NumberFormatException("Invalid number!")
  }
  val bitLength = keySize/8
  val p = generatePrime(keySize/2)
  val q = generatePrime(keySize/2)
  val phi = (p.-(BigInt(1))).*(q.-(BigInt(1)))
  val n = p.*(q)

  var publicKey = BigInt(3)
  publicKey = generatePublicKey()
  val privateKey = publicKey.modInverse(phi)

  def generatePublicKey(): BigInt = {
    while (phi.gcd(publicKey).intValue() > 1) {
      publicKey = publicKey.+(BigInt(2))
    }
    publicKey
  }

  def generatePrime(bitLength: Int): BigInt = {
    BigInt.probablePrime(bitLength, Random)
  }

  def encrypt(message: BigInt): BigInt = {
    message.modPow(publicKey, n)
  }

  def decrypt(cipher: BigInt): BigInt = {
    cipher.modPow(privateKey, n)
  }

  def encrypt(message: Array[Byte]): Array[BigInt] = {
    message.map(x => Array[Byte](x)).map(enc)
  }

  def enc(message: Array[Byte]): BigInt = {
    BigInt(message).modPow(publicKey, n)
  }

  def decrypt(cipher: Array[BigInt]): Array[Byte] = {
    cipher.map(decrypt).map(_.toByteArray).flatMap(x => x)
  }

  override def toString() : String = {
    println("p: " + p)
    println("q: " + q)
    println("phi:" + phi)
    println("n: " + n)
    println("public: " + publicKey)
    println("private: " + privateKey)
    "RSA Algorithm in " + keySize + " bits."
  }
}


