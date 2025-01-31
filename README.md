Voici quelques routes utilies pour les Differentes Entité : 

Pokemon : 

GET   localhost:8080/pokemons (obtenir tous les pokemons) 
POST  localhost;8080/pokemons (ajout de pokemons)
PUT   localhost;8080/pokemons/{uuid}
DEL   localhost;8080/pokemons/{uuid}

Carte : 

GET   localhost:8080/cartes
GET   localhost:8080/cartes/generate ( générer une carte aléatoire)
GET   localhost:8080/cartes/tirage (gènère 5 cartes par défault)

Dresseurs : 

GET localhost:8080/dresseurs
GET localhost:8080/dresseurs/{uuid}/init (gènère les 5 cartes combat ( les 5 première carte du deck secondaire par défaut)
POST localhost:8080/dresseurs
POST localhost:8080/dresseurs/{uuid}/interne (fait un échange interne entre les deux decks)
PATCH localhost:8080/dresseurs/{uuid}/tirage (tirage quotidien)
DEL localhost:8080/dresseurs/{uuid}

Echange : 

GET localhost:8080/echanges
POST localhost:8080/echange/create (crée la salle d'échange entre deux joueurs) 
PATCH localhost:8080/echanges/{uuid_echange}/echanger (problème 500 , l'échange entre deux joueurs ne fonctionne pas)
DEL localhost:8080/echanges/{uuid_echange}

