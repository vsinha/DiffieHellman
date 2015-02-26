/**
 * Created by viraj on 2/25/15.
 */

import scala.util.Random

class DiffieHellman (val p: BigInt, val g: Int) {

  // private key is generated once and for always
  // (re-generating the private key breaks the algorithm)
  private val privateKey: BigInt = this.random(1024)
  var peerPublicKeys: Array[BigInt] = Array[BigInt]()

  // public key is g^privateKey mod p
  def publicKey: BigInt = {
    expMod(g, privateKey, p)
  }g

  // multiply all the keys in the group of participants,
  // starting with our own
  def sharedSecret: BigInt = {
    this.publicKey * peerPublicKeys.foldLeft(1: BigInt)((a, b) => a * b)
  }

  // append the new key to the list of keys we have
  // this operation should really only be undertaken by the
  // DiffieHellman.doKeyExchange function.
  private def addPeerPublicKey(key: BigInt) = {
    peerPublicKeys :+= key
  }

  // calculates a^b mod c
  private def expMod(a: BigInt, b: BigInt, c: BigInt): BigInt = {
    a.modPow(b, c)
  }

  private def random(n: Int): BigInt = {
    BigInt(n, new Random())
  }
}

object DiffieHellman {
  def randomPrime(n: Int): BigInt = {
    BigInt.probablePrime(n, new Random())
  }

  // share all public keys with all participants,
  // for any number of participants
  def doKeyExchange(participants: Array[DiffieHellman]): Unit = {
    for {
      keySender: DiffieHellman <- participants
      keyReciever: DiffieHellman <- participants
      if keySender != keyReciever
    } yield {
      keyReciever.addPeerPublicKey(keySender.publicKey)
    }
  }
}