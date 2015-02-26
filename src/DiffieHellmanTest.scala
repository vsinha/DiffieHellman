/**
 * Created by viraj on 2/25/15.
 */
object DiffieHellmanTest extends App {
  // parties agree to use shared prime number p and base g
  val p = DiffieHellman.randomPrime(1024)
  val g = 5 // 5 is a primitive root modulo 23

  // initialize participants
  var alice = new DiffieHellman(p, g)
  var bob = new DiffieHellman(p, g)
  var carol = new DiffieHellman(p, g)
  var david = new DiffieHellman(p, g)

  // add to an array for convenience
  var participants = Array(alice, bob, carol, david)

  // share all public keys with all participants
  DiffieHellman.doKeyExchange(participants)

  // print some tasty output
  println("Alice's secret: " + alice.sharedSecret)
  println("Bobs's secret : " + bob.sharedSecret)
  println("Carol's secret: " + carol.sharedSecret)
  println("David's secret: " + david.sharedSecret)
  if (alice.sharedSecret == bob.sharedSecret
      && bob.sharedSecret == carol.sharedSecret
      && carol.sharedSecret == david.sharedSecret) {
    println("Alice, Bob, Carol, and David share a secret")
  } else {
    println("Uhh, something went wrong")
  }
}