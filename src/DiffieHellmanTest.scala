/**
 * Created by viraj on 2/25/15.
 */
object DiffieHellmanTest extends App {

  // p and g taken from here:
  val p = DiffieHellman.randomPrime(1024)
  val g = 5 // 5 is a primitive root modulo 23

  // all operations are taken to be modulo p
  // parties agree to use prime number p and base g
  val numParties = 3
  var alice = new DiffieHellman(p, g)
  var bob = new DiffieHellman(p, g)
  var carol = new DiffieHellman(p, g)
  var participants = Array(alice, bob, carol)

  // share all public keys with all participants,
  DiffieHellman.doKeyExchange(participants)

  println("Alice's secret: " + alice.sharedSecret)
  println("Bobs's secret : " + bob.sharedSecret)
  println("Carol's secret: " + carol.sharedSecret)
  if (alice.sharedSecret == bob.sharedSecret
      && bob.sharedSecret == carol.sharedSecret) {
    println("Alice, Bob, and Carol share a secret")
  } else {
    println("uhh, something went wrong")
  }
}