# projektarbeit-quick-check
## todos
- welche quick check für go varianten gibt es
- wie gut sind diese?
- quellen sammeln
- sinnvolle interessante beispiele wo quicksort eingesetzt wird
- evtl eigene implementierung

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

## Interessante Beispiele
- usable for all unit tests
- generating input when testing against an api