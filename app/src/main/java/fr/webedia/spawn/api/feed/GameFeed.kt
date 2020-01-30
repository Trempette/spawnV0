package fr.webedia.spawn.api.feed

import com.google.gson.annotations.SerializedName

class GameFeed {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String? = ""

}