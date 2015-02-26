/**
 * Created by viraj on 2/25/15.
 */
object DiffieHellmanTest extends App {
  // parties agree to use shared prime number p and base g
  val p = DiffieHellman.randomPrime(32)
  val g = 5 // 5 is a primitive root modulo 23

  // initialize participants
  var alice = new DiffieHellman(p, g, "alice")
  var bob = new DiffieHellman(p, g, "bob")
  var carol = new DiffieHellman(p, g, "carol")
  var david = new DiffieHellman(p, g, "david")

  // add to an array for convenience
  var participants = List(alice, bob, carol, david)

  println("Public Keys:     " + participants.map(x => x.publicKey).mkString(" "))

  // share all public keys with all participants
  val result = DiffieHellman.doKeyExchange(participants)

  println("Shared Secrets:  " + participants.map(p => p.secret).mkString(" "))

  println("All keys shared? " + result)
}