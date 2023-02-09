# projektarbeit-quick-check
## allgemein
Implementierung am Beispiel Liste
## todos
- Die "gleich" Liste in Haskell zu schreiben
- Diese mit Quickcheck (Haskell) unter der Verwendungen von Anforderungen an die Liste zu testen.
- Meinen Generator so erweitern, dass er Anforderungen an die Liste aktzeptiert und nur Listen ausgibt, die den anforderungen entsprechen (z.B. List.length() > 0)
- Die beiden Implementierungen bezüglich ihrerer Ergonomie für den Programmierer und ihrer Mächtigkeit vergleichen.

## Verfügbare Go Implementierungen
### Quick
  - https://betterprogramming.pub/test-better-with-quick-library-in-go-1bc59074b5b
  - https://medium.com/@richardlayte/testing-go-with-quickcheck-5d845467b761
  - https://blog.gopheracademy.com/advent-2017/property-based-testing/
  - https://earthly.dev/blog/property-based-testing/
  
### Rapid 
  - https://github.com/flyingmutant/rapid
### gopter 
  - https://github.com/leanovate/gopter
  - https://pkg.go.dev/github.com/leanovate/gopter?utm_source=godoc

## Bewertung
### Quick
 - in standard testing libary
 - lots of resources
 - ease of use
### Rapid
 - more control over generation
 - minimizes failing test case
### Gopter
 - clean output
 - much code overhead