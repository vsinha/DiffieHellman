/**
 * Created by viraj on 2/25/15.
 */

import scala.util.Random

class DiffieHellman (val p: BigInt, val g: Int) {
  private var secretKey: BigInt = null
  var peerPublicKey: BigInt = null
  var peerPublicKeys: Array[BigInt] = null

  def generatePrivateKey(): Unit = {
    secretKey = random(128)
  }

  def getPublicKey: BigInt = {
    // public key is g^secretKey mod p
    expMod(g, secretKey, p)
  }

  def sharedSecret: BigInt = {
    expMod(peerPublicKey, secretKey, p)
  }

  def random(n: Int): BigInt = {
    val rnd = new Random()
    BigInt(n, rnd)
  }

  // calculates a^b mod c
  def expMod(a: BigInt, b: BigInt, c: BigInt): BigInt = {
    a.modPow(b, c)
  }
}

object DiffieHellman {
  def randomPrime(n: Int): BigInt = {
    val rand = new Random()
    BigInt.probablePrime(n, rand)
  }
}