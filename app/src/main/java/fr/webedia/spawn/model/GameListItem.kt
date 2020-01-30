package fr.webedia.spawn.model

class GameListItem(var title: String, var listGames: List<Game>){

    companion object{
    fun getComingSoonListItem(): List<GameListItem>{
        val list = arrayListOf<GameListItem>()
        list.add(mostWaited)
        list.add(sortiesDuMois)
        list.add(mesSorties)
        return list
    }

    var mostWaited = GameListItem("Les plus attendus", listOf())
    var sortiesDuMois = GameListItem("Sorties du mois", listOf())
    var mesSorties = GameListItem("Mes sorties", listOf())
    }

}
