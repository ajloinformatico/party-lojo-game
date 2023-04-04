package com.example.party_lojo_game.data.constants

object Constants {
    // region online urls
    const val YO_NUNCA_URL =
        "5f5c8e953b1fc4a2f9e91aacb532439b/raw/96c2a851bf76b664761cd103ec2122c9c7df3c9d/party_lojo_game_yo_nunca.json"
    const val VERDAD_O_RETO_URL =
        "2211e1142f7805947a782cf99daf99d8/raw/14a6cb78dc96aee67b66e4e4ae04a88f38afa077/party_lojo_game_verdad_o_reto.json"
    const val BEBE_QUIEN_URL =
        "485a425065fe67edfb354e4b6ff08f4e/raw/3839826df28e61a3b9441a39e8e9a862c6ffefde/party_lojo_game_bebe_quien.json"
    const val INFOLOJO_WEB_SITE = "https://www.infolojo.es"
    // endregion online urls

    // region DTO layer types
    const val BEBE_QUIEN_DTO_TYPE = "bebe quien"
    const val YO_NUNCA_DTO_TYPE = "yo nunca"
    const val VERDAD_O_RETO_TYPE = "verdad o reto"
    // endregion DTO layer types

    // region VO layer titles
    const val BEBE_QUIEN_TITLE = "Bebe Quien"
    const val YO_NUNCA_TITLE = "Yo Nunca"
    const val VERAD_O_RETO_TITLE = "Verdad o reto"
    // endregion titles

    // region database
    const val DATABASE_NAME: String = "PartyLojoGameDataBase"
    // endregion database

    const val ERROR_UNKNOWN_ASK_TYPE = "Error: unknown ask type"

    // region database actions
    const val ADDED_YOU_NUNCA_TO_DATABASE = "yo nunca ask added to database"
    const val ADDED_VERDAD_O_RETO_TO_DATABASE = "verdad o reto ask added to database"
    const val ADDED_BEBE_QUIEN_TO_DATABASE = "bebe quien ask added to database"
    // endregion database actions
}
