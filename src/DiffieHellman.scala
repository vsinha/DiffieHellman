/**
 * Created by viraj on 2/25/15.
 */

import scala.util.Random

class DiffieHellman (val p: BigInt, val g: Int) {
  private var privateKey: BigInt = null
  var peerPublicKey: BigInt = null
  var peerPublicKeys: Array[BigInt] = Array[BigInt]()

  def generatePrivateKey(): Unit = {
    privateKey = this.random(1024)
  }

  def publicKey: BigInt = {
    // do this here in case it wasn't done
    // explicitly earlier
    if (privateKey == null) {
      generatePrivateKey()
    }

    // public key is g^privateKey mod p
    expMod(g, privateKey, p)
  }

  def addPeerPublicKey(key: BigInt) = {
    // append to the list of keys we have
    peerPublicKeys :+= key
  }

  def sharedSecret: BigInt = {
    // multiply all the keys in the group of participants,
    // starting with our own
    var secret: BigInt = this.publicKey
    for (publicKey <- peerPublicKeys) {
      secret *= publicKey
    }
    secret
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

  // share all public keys with all participants,
  // for any number of participants
  def doKeyExchange(participants: Array[DiffieHellman]): Unit = {
    for (i <- 0 until participants.length) {
      for (j <- 0 until participants.length) {
        if (i != j) {
          participants(i).addPeerPublicKey(participants(j).publicKey)
        }
      }
    }
  }
}