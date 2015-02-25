/**
 * Created by viraj on 2/25/15.
 */
object DiffieHellmanTest extends App {
  val p = DiffieHellman.randomPrime(128)
  val g = 5

  // alice, bob, and carol agree to use prime number p and base g
  var alice = new DiffieHellman(p, g)
  var bob = new DiffieHellman(p, g)
  var carol = new DiffieHellman(p, g)

  // alice chooses a secret integer a
  alice.generatePrivateKey()
  // and sends bob A = g^a mod p
  bob.peerPublicKey = alice.getPublicKey

  // bob chooses a secret integer b
  bob.generatePrivateKey()
  // and sends alice B = g^b mod p
  alice.peerPublicKey = bob.getPublicKey

  // alice computes s = B^a mod p
  // bob computes s = A^b mod p
  // alice and bob now share a secret
  println("Alice's secret: " + alice.sharedSecret)
  println("Bobs's secret : " + bob.sharedSecret)
  if (alice.sharedSecret == bob.sharedSecret) {
    println("Alice and Bob share a secret")
  }
}