Diffie-Hellman Key Exchange in scala
====

First initialize p and j, which are shared between all participants
```scala
val p = DiffieHellman.randomPrime(1024)
val g = 5 // 5 is a primitive root modulo 23
```

Then create the participants
```scala
var alice = new DiffieHellman(p, g)
var bob = new DiffieHellman(p, g)
var carol = new DiffieHellman(p, g)
var participants = Array(alice, bob, carol)
```

And perform the key exchange
```scala
DiffieHellman.doKeyExchange(participants)
```

Now all participants share a secret! 
