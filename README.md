# Partner's Area Manager

Registry new partner's and find by Coordinate

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them

```
Java 11 JDK
Maven
```

## How to run

Run the application through maven
```
./mvnw spring-boot:run
```

## Running the tests

Explain how to run the automated tests for this system

Run tests through maven

```
./mvnw test
```
## Usage

Create the partner's

```
POST http://localhost:8080/partners
{
  "pdvs": [
      {
          "id": "1",
          "tradingName": "Adega Osasco",
          "ownerName": "Ze da Ambev",
          "document": "02.453.716/000170",
          "coverageArea": {
              "type": "MultiPolygon",
              "coordinates": [
                  [
                      [
                          [
                              -43.36556,
                              -22.99669
                          ],
                          [
                              -43.36539,
                              -23.01928
                          ],
                          [
                              -43.26583,
                              -23.01802
                          ],
                          [
                              -43.25724,
                              -23.00649
                          ],
                          [
                              -43.23355,
                              -23.00127
                          ],
                          [
                              -43.2381,
                              -22.99716
                          ],
                          [
                              -43.23866,
                              -22.99649
                          ],
                          [
                              -43.24063,
                              -22.99756
                          ],
                          [
                              -43.24634,
                              -22.99736
                          ],
                          [
                              -43.24677,
                              -22.99606
                          ],
                          [
                              -43.24067,
                              -22.99381
                          ],
                          [
                              -43.24886,
                              -22.99121
                          ],
                          [
                              -43.25617,
                              -22.99456
                          ],
                          [
                              -43.25625,
                              -22.99203
                          ],
                          [
                              -43.25346,
                              -22.99065
                          ],
                          [
                              -43.29599,
                              -22.98283
                          ],
                          [
                              -43.3262,
                              -22.96481
                          ],
                          [
                              -43.33427,
                              -22.96402
                          ],
                          [
                              -43.33616,
                              -22.96829
                          ],
                          [
                              -43.342,
                              -22.98157
                          ],
                          [
                              -43.34817,
                              -22.97967
                          ],
                          [
                              -43.35142,
                              -22.98062
                          ],
                          [
                              -43.3573,
                              -22.98084
                          ],
                          [
                              -43.36522,
                              -22.98032
                          ],
                          [
                              -43.36696,
                              -22.98422
                          ],
                          [
                              -43.36717,
                              -22.98855
                          ],
                          [
                              -43.36636,
                              -22.99351
                          ],
                          [
                              -43.36556,
                              -22.99669
                          ]
                      ]
                  ]
              ]
          },
          "address": {
              "type": "Point",
              "coordinates": [
                  -43.297337,
                  -23.013538
              ]
          }
      }
  ]
}
```
Find partner by id
```
GET http://localhost:8080/partners/1
```
Find the nearest partner 
```
GET http://localhost:8080/partners?lng=-19.0&lat=-40.0
```

## Suggestions for improvement

- Apply clean architecture (separate into different projects)
- Use UUID instead of Long
- Create more tests
