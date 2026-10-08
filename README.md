# Algebra Relazionale su File CSV

Implementazione in Java delle principali operazioni di Algebra Relazionale utilizzando dati provenienti da file CSV.

## Obiettivo

L'obiettivo dell'esercitazione è simulare un semplice DBMS relazionale implementando le principali operazioni dell'Algebra Relazionale e utilizzandole per interrogare il dataset World.

Le interrogazioni vengono eseguite sui seguenti file:

- `country.csv`
- `city.csv`
- `countrylanguage.csv`

---

# Struttura del Progetto

## Row

Rappresenta una singola tupla della relazione.

Contiene:

- lista dei valori della riga
- metodi di accesso ai campi

```java
Row
```

---

## Relation

Rappresenta una relazione (tabella).

Contiene:

- intestazione (`header`)
- insieme delle righe (`rows`)

Implementa le principali operazioni dell'Algebra Relazionale:

### Selection (σ)

```java
selection()
```

Filtra le righe che soddisfano una condizione.

Esempio:

```java
country.selection("Continent","Europe");
```

---

### Projection (π)

```java
projection()
```

Seleziona solo alcuni attributi della relazione.

Esempio:

```java
country.projection(campi);
```

---

### Renomination (ρ)

```java
renomination()
```

Rinomina un attributo.

Esempio:

```java
city.renomination("ID","Capital");
```

---

### Union (∪)

```java
union()
```

Unisce due relazioni compatibili.

---

### Difference (-)

```java
difference()
```

Restituisce gli elementi presenti nella prima relazione ma non nella seconda.

---

### Prodotto Cartesiano (×)

```java
prodotto()
```

Genera tutte le combinazioni possibili tra le tuple delle due relazioni.

---

### Join (⋈)

```java
join()
```

Esegue un equi-join tra due relazioni.

Esempio:

```java
country.join(city,new String[]{"Capital","ID"});
```

---

### Operazioni aggiuntive

Per risolvere alcune interrogazioni sono stati aggiunti operatori numerici.

#### Selection Greater Than

```java
selectionGreaterThan()
```

Filtra i record con valore maggiore di quello indicato.

---

#### Selection Between

```java
selectionBetween()
```

Filtra i record con un valore compreso tra due estremi.

---

#### Max

```java
max()
```

Restituisce la riga con il valore massimo per un attributo numerico.

---

#### Min

```java
min()
```

Restituisce la riga con il valore minimo per un attributo numerico.

---

## CSVLoader

Classe utilizzata per caricare i dati dai file CSV.

Legge:

- intestazione
- righe

e costruisce una nuova relazione.

---

# Dataset

## country.csv

| Campo |
|---------|
| Code |
| Name |
| Continent |
| Region |
| SurfaceArea |
| IndepYear |
| Population |
| LifeExpectancy |
| GNP |
| GNPOld |
| LocalName |
| GovernmentForm |
| HeadOfState |
| Capital |
| Code2 |

---

## city.csv

| Campo |
|---------|
| ID |
| Name |
| CountryCode |
| District |
| Population |

---

## countrylanguage.csv

| Campo |
|---------|
| CountryCode |
| Language |
| IsOfficial |
| Percentage |

---

# Interrogazioni Realizzate

## 1. Trovare tutte le nazioni europee

Utilizza:

- Selection

```text
σ Continent = Europe (Country)
```

---

## 2. Trovare tutte le città della Francia

Utilizza:

- Selection
- Projection

```text
π Name, CountryCode
(
    σ CountryCode = FRA (City)
)
```

---

## 3. Trovare le nazioni con popolazione compresa tra 100 e 200 milioni

Utilizza:

- Selection Between
- Projection

```text
π Name
(
    σ 100000000 ≤ Population ≤ 200000000
)
```

---

## 4. Trovare per tutte le nazioni del Sud America il nome della capitale, la popolazione e il nome dello stato

Utilizza:

- Renomination
- Join
- Selection

```text
σ Continent = South America
(
    Country ⋈ City
)
```

---

## 5. Trovare le nazioni asiatiche con popolazione maggiore di quella del Giappone

Utilizza:

- Selection
- Selection Greater Than

```text
σ Population > Population(Japan)
(
    σ Continent = Asia
)
```

---

## 6. Trovare per l'Italia la città con maggior numero di abitanti e quella con minor numero di abitanti

Utilizza:

- Selection
- Max
- Min

```text
σ CountryCode = ITA
```

seguito dal calcolo di:

```text
MAX(Population)
MIN(Population)
```

---

## 7. Trovare le nazioni in cui si parla inglese e non si parla francese

Utilizza:

- Selection
- Projection
- Difference
- Renomination
- Join

```text
(
    π CountryCode
    (
        σ Language = English
    )
)
-
(
    π CountryCode
    (
        σ Language = French
    )
)
```

Successivamente il risultato viene collegato alla relazione Country tramite Join per ottenere il nome delle nazioni.

---

# Concetti di Algebra Relazionale Utilizzati

| Operazione | Simbolo |
|------------|----------|
| Selection | σ |
| Projection | π |
| Join | ⋈ |
| Union | ∪ |
| Difference | − |
| Prodotto Cartesiano | × |
| Renomination | ρ |

---

# Autore

Esercitazione svolta per il corso di Sistemi Informativi e Basi di Dati.

Dataset utilizzato: World Database (Countries, Cities and Languages).
