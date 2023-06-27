package pubgkt

enum class GameMode(val id: String) {
    SoloTpp(id = "solo"),
    DuoTpp(id = "duo"),
    SquadTpp(id = "squad"),
    SoloFpp(id = "solo-fpp"),
    DuoFpp(id = "duo-fpp"),
    SquadFpp(id = "squad-fpp");

    override fun toString(): String = id
}
