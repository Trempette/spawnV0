package fr.webedia.spawn.model

import androidx.room.Entity
import kotlinx.coroutines.flow.asFlow
import java.util.*
import kotlin.collections.ArrayList

@Entity(
    tableName = "games",
    primaryKeys = ["id"]
)
data class Game(
    var id: String,
    var name: String,
    var imageUrl: String,
    var releaseDate: Date,
    var genre: String,
    var editor: String
) {

    companion object {
        fun getListOfGames() : ArrayList<Game> {
            val list = arrayListOf<Game>()
            list.add(zeldaBreathOfTheWild)
            list.add(pokemon)
            list.add(superSmashBros)
            list.add(cyberPunk)
            list.add(animalCrossing)
            list.add(deathStranding)
            list.add(luigisMansion)
            list.add(monsterHunter)
            list.add(fireEmblem)
            list.add(gears5)
            list.add(destiny2)
            list.add(superMario)
            list.add(zeldaoot)
            list.add(fortnite)
            list.add(pokemonRedBlue)
            return list
        }


        private var zeldaBreathOfTheWild = Game(
            "1",
            "Zelda : Breath Of The Wild",
            "https://en.wikipedia.org/wiki/The_Legend_of_Zelda:_Breath_of_the_Wild#/media/File:The_Legend_of_Zelda_Breath_of_the_Wild.jpg",
            Date(1488538409000),
            "Action  RPG",
            "Nintendo"
        )

        private var pokemon = Game(
            "2",
            "Pokemon Version Epée/Bouclier",
            "https://www.actugaming.net/wp-content/uploads/2019/02/pokemon-epee.jpg",
            Date(1573815209000),
            "RPG",
            "Game Freaks"
        )

        private var superSmashBros = Game(
            "3",
            "Super Smash Bros Ultimate",
            "https://upload.wikimedia.org/wikipedia/en/thumb/5/50/Super_Smash_Bros._Ultimate.jpg/300px-Super_Smash_Bros._Ultimate.jpg",
            Date(1544180009000),
            "Combat",
            "Nintendo"
        )

        private var cyberPunk = Game(
            "4",
            "Cyberpunk 2077",
            "https://cdn.vox-cdn.com/thumbor/jld4Lvd1wmFuSkW3aHoZP0uOJQM=/0x0:2878x1396/1200x800/filters:focal(1317x291:1777x751)/cdn.vox-cdn.com/uploads/chorus_image/image/66115187/Screen_Shot_2019_06_09_at_4.22.17_PM.0.png",
            Date(1600253609000),
            "Action Aventure RPG",
            "CD Projekt"
        )

        private var animalCrossing = Game(
            "5",
            "Animal Crossing: New Horizons",
            "https://images-na.ssl-images-amazon.com/images/I/81s8etnYPrL._AC_SL1500_.jpg",
            Date(1584701609000),
            "Simulation RPG",
            "Nintendo"
        )

        private var deathStranding = Game(
            "6",
            "DeathStranding",
            "https://vignette.wikia.nocookie.net/deathstranding/images/8/8d/DS_key_art_final.jpg",
            Date(1573210409000),
            "Action",
            "Sony Interactive Entertainment"
        )

        private var luigisMansion = Game(
            "7",
            "Luigi's Mansion 3",
            "https://artwork.lighting/wp-content/uploads/2019/10/PP34574.jpeg",
            Date(1577789609000),
            "Action Aventure",
            "Nintendo"
        )

        private var monsterHunter = Game(
            "8",
            "Monster Hunter World : Iceborne",
            "https://i.pinimg.com/originals/00/5f/03/005f03c4f27f38fcff646f918692bd73.png",
            Date(1567767209000),
            "Action RPG",
            "Capcom"
        )

        private var fireEmblem = Game(
            "9",
            "Fire Emblem : Three Houses",
            "https://cdn03.nintendo-europe.com/media/images/08_content_images/games_6/nintendo_switch_7/nswitch_fireemblemthreehouses/NSwitch_FireEmblemThreeHouses_Digital.jpg",
            Date(1564138409000),
            "Tactical RPG",
            "Nintendo"
        )

        private var gears5 = Game(
            "10",
            "Gears 5",
            "https://i2.wp.com/www.sohaibxtreme.net/wp-content/uploads/2019/09/Gears-5-Osb79-PC.jpg",
            Date(1568112809000),
            "Action TPS",
            "Xbox Game Studio"
        )

        private var destiny2 = Game(
            "11",
            "Destiny 2 : Bastion des Ombres",
            "https://image.jeuxvideo.com/medias/156996/1569959200-6034-jaquette-avant.jpg",
            Date(1569927209000),
            "Action FPS MMO",
            "Bungie Activision"
        )

        private var superMario = Game(
            "12",
            "Mario Bros",
            "https://i.pinimg.com/originals/44/8e/bb/448ebb800d50467637796d7021e46943.jpg",
            Date(462192809000),
            "Plate-Forme",
            "Nintendo"
        )

        private var zeldaoot = Game(
            "13",
            "Zelda : Ocarina Of Time",
            "https://images-na.ssl-images-amazon.com/images/I/717h-VyruuL._AC_SY741_.jpg",
            Date(911645609000),
            "Action RPG",
            "Nintendo"
        )

        private var fortnite = Game(
            "14",
            "Fortnite",
            "https://www.britishvintageposters.co.uk/ekmps/shops/6d7106/images/large-fortnite-game-poster-a2-2489-p.jpg",
            Date(1505559209000),
            "Action Tir Shooter Battle Royale",
            "Epic Games"
        )

        private var pokemonRedBlue = Game(
            "15",
            "Pokemon : Version Rouge/Bleue",
            "https://images-na.ssl-images-amazon.com/images/I/61Mb3rnhUYL._AC_.jpg",
            Date(939380009000),
            "RPG",
            "Nintendo"
        )

    }
}
