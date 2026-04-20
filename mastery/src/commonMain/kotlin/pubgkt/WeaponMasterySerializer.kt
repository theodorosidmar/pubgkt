package pubgkt

import kotlinx.serialization.json.JsonObject

internal object WeaponMasterySerializer :
    JsonApiResourceDeserializer<WeaponMastery>("pubgkt.WeaponMastery") {

    @Suppress("LongMethod")
    override fun deserializeResource(
        attributes: JsonObject,
        id: String,
        relationships: JsonObject?,
    ): WeaponMastery {
        val weaponsSummary = attributes.requiredObject("weaponSummaries")
        return WeaponMastery(
            playerId = id,
            platform = Platform.valueOf(attributes.requiredString("platform").uppercase()),
            ace32 = weaponsSummary.optionalObject("Item_Weapon_ACE32_C")?.let(::deserializeWeaponSummary),
            ak47 = weaponsSummary.optionalObject("Item_Weapon_AK47_C")?.let(::deserializeWeaponSummary),
            aug = weaponsSummary.optionalObject("Item_Weapon_AUG_C")?.let(::deserializeWeaponSummary),
            awm = weaponsSummary.optionalObject("Item_Weapon_AWM_C")?.let(::deserializeWeaponSummary),
            s686 = weaponsSummary.optionalObject("Item_Weapon_Berreta686_C")?.let(::deserializeWeaponSummary),
            beryl = weaponsSummary.optionalObject("Item_Weapon_BerylM762_C")?.let(::deserializeWeaponSummary),
            bizon = weaponsSummary.optionalObject("Item_Weapon_BizonPP19_C")?.let(::deserializeWeaponSummary),
            bluezoneGrenade = weaponsSummary.optionalObject("Item_Weapon_BluezoneGrenade_C")
                ?.let(::deserializeWeaponSummary),
            c4 = weaponsSummary.optionalObject("Item_Weapon_C4_C")?.let(::deserializeWeaponSummary),
            crossbow = weaponsSummary.optionalObject("Item_Weapon_Crossbow_C")?.let(::deserializeWeaponSummary),
            dp12 = weaponsSummary.optionalObject("Item_Weapon_DP12_C")?.let(::deserializeWeaponSummary),
            dp28 = weaponsSummary.optionalObject("Item_Weapon_DP28_C")?.let(::deserializeWeaponSummary),
            desertEagle = weaponsSummary.optionalObject("Item_Weapon_DesertEagle_C")?.let(::deserializeWeaponSummary),
            dragunov = weaponsSummary.optionalObject("Item_Weapon_Dragunov_C")?.let(::deserializeWeaponSummary),
            famas = weaponsSummary.optionalObject("Item_Weapon_FAMASG2_C")?.let(::deserializeWeaponSummary),
            slr = weaponsSummary.optionalObject("Item_Weapon_FNFal_C")?.let(::deserializeWeaponSummary),
            g18 = weaponsSummary.optionalObject("Item_Weapon_G18_C")?.let(::deserializeWeaponSummary),
            g36 = weaponsSummary.optionalObject("Item_Weapon_G36C_C")?.let(::deserializeWeaponSummary),
            grenade = weaponsSummary.optionalObject("Item_Weapon_Grenade_C")?.let(::deserializeWeaponSummary),
            groza = weaponsSummary.optionalObject("Item_Weapon_Groza_C")?.let(::deserializeWeaponSummary),
            m416 = weaponsSummary.optionalObject("Item_Weapon_HK416_C")?.let(::deserializeWeaponSummary),
            js9 = weaponsSummary.optionalObject("Item_Weapon_JS9_C")?.let(::deserializeWeaponSummary),
            k2 = weaponsSummary.optionalObject("Item_Weapon_K2_C")?.let(::deserializeWeaponSummary),
            kar98k = weaponsSummary.optionalObject("Item_Weapon_Kar98k_C")?.let(::deserializeWeaponSummary),
            lynx = weaponsSummary.optionalObject("Item_Weapon_L6_C")?.let(::deserializeWeaponSummary),
            m16a4 = weaponsSummary.optionalObject("Item_Weapon_M16A4_C")?.let(::deserializeWeaponSummary),
            p1911 = weaponsSummary.optionalObject("Item_Weapon_M1911_C")?.let(::deserializeWeaponSummary),
            m249 = weaponsSummary.optionalObject("Item_Weapon_M249_C")?.let(::deserializeWeaponSummary),
            m24 = weaponsSummary.optionalObject("Item_Weapon_M24_C")?.let(::deserializeWeaponSummary),
            p92 = weaponsSummary.optionalObject("Item_Weapon_M9_C")?.let(::deserializeWeaponSummary),
            mg3 = weaponsSummary.optionalObject("Item_Weapon_MG3_C")?.let(::deserializeWeaponSummary),
            mp5k = weaponsSummary.optionalObject("Item_Weapon_MP5K_C")?.let(::deserializeWeaponSummary),
            mp9 = weaponsSummary.optionalObject("Item_Weapon_MP9_C")?.let(::deserializeWeaponSummary),
            mini14 = weaponsSummary.optionalObject("Item_Weapon_Mini14_C")?.let(::deserializeWeaponSummary),
            mk12 = weaponsSummary.optionalObject("Item_Weapon_Mk12_C")?.let(::deserializeWeaponSummary),
            mk14 = weaponsSummary.optionalObject("Item_Weapon_Mk14_C")?.let(::deserializeWeaponSummary),
            mutant = weaponsSummary.optionalObject("Item_Weapon_Mk47Mutant_C")?.let(::deserializeWeaponSummary),
            molotov = weaponsSummary.optionalObject("Item_Weapon_Molotov_C")?.let(::deserializeWeaponSummary),
            mortar = weaponsSummary.optionalObject("Item_Weapon_Mortar_C")?.let(::deserializeWeaponSummary),
            mosinNagant = weaponsSummary.optionalObject("Item_Weapon_Mosin_C")?.let(::deserializeWeaponSummary),
            r1895 = weaponsSummary.optionalObject("Item_Weapon_NagantM1895_C")?.let(::deserializeWeaponSummary),
            o12 = weaponsSummary.optionalObject("Item_Weapon_OriginS12_C")?.let(::deserializeWeaponSummary),
            p90 = weaponsSummary.optionalObject("Item_Weapon_P90_C")?.let(::deserializeWeaponSummary),
            panzerfaust = weaponsSummary.optionalObject("Item_Weapon_PanzerFaust100M_C")
                ?.let(::deserializeWeaponSummary),
            qbu = weaponsSummary.optionalObject("Item_Weapon_QBU88_C")?.let(::deserializeWeaponSummary),
            qbz = weaponsSummary.optionalObject("Item_Weapon_QBZ95_C")?.let(::deserializeWeaponSummary),
            r45 = weaponsSummary.optionalObject("Item_Weapon_Rhino_C")?.let(::deserializeWeaponSummary),
            scarl = weaponsSummary.optionalObject("Item_Weapon_SCAR-L_C")?.let(::deserializeWeaponSummary),
            sks = weaponsSummary.optionalObject("Item_Weapon_SKS_C")?.let(::deserializeWeaponSummary),
            s12k = weaponsSummary.optionalObject("Item_Weapon_Saiga12_C")?.let(::deserializeWeaponSummary),
            sawedOff = weaponsSummary.optionalObject("Item_Weapon_Sawnoff_C")?.let(::deserializeWeaponSummary),
            stickyBomb = weaponsSummary.optionalObject("Item_Weapon_StickyGrenade_C")?.let(::deserializeWeaponSummary),
            tommyGun = weaponsSummary.optionalObject("Item_Weapon_Thompson_C")?.let(::deserializeWeaponSummary),
            ump = weaponsSummary.optionalObject("Item_Weapon_UMP_C")?.let(::deserializeWeaponSummary),
            uzi = weaponsSummary.optionalObject("Item_Weapon_UZI_C")?.let(::deserializeWeaponSummary),
            vss = weaponsSummary.optionalObject("Item_Weapon_VSS_C")?.let(::deserializeWeaponSummary),
            vector = weaponsSummary.optionalObject("Item_Weapon_Vector_C")?.let(::deserializeWeaponSummary),
            win94 = weaponsSummary.optionalObject("Item_Weapon_Win1894_C")?.let(::deserializeWeaponSummary),
            winchester = weaponsSummary.optionalObject("Item_Weapon_Winchester_C")?.let(::deserializeWeaponSummary),
            skorpion = weaponsSummary.optionalObject("Item_Weapon_vz61Skorpion_C")?.let(::deserializeWeaponSummary),
        )
    }

    private fun deserializeWeaponSummary(weaponSummary: JsonObject): WeaponSummary =
        WeaponSummary(
            xpTotal = weaponSummary.requiredInt("XPTotal"),
            currentLevel = weaponSummary.requiredInt("LevelCurrent"),
            currentTier = weaponSummary.requiredInt("TierCurrent"),
            statsTotal = weaponSummary.requiredObject("StatsTotal").let(::deserializeWeaponStatsTotal),
            officialStatsTotal = weaponSummary.requiredObject("OfficialStatsTotal")
                .let(::deserializeWeaponOfficialStatsTotal),
            competitiveStatsTotal = weaponSummary.requiredObject("CompetitiveStatsTotal")
                .let(::deserializeWeaponOfficialStatsTotal),
        )

    private fun deserializeWeaponStatsTotal(statsTotal: JsonObject): WeaponStatsTotal =
        WeaponStatsTotal(
            mostDefeatsInAGame = statsTotal.requiredInt("MostDefeatsInAGame"),
            defeats = statsTotal.requiredInt("Defeats"),
            mostDamagePlayerInAGame = statsTotal.requiredDouble("MostDamagePlayerInAGame"),
            damagePlayer = statsTotal.requiredDouble("DamagePlayer"),
            mostHeadShotsInAGame = statsTotal.requiredInt("MostHeadShotsInAGame"),
            headShots = statsTotal.requiredInt("HeadShots"),
            longestDefeat = statsTotal.requiredDouble("LongestDefeat"),
            longRangeDefeats = statsTotal.requiredInt("LongRangeDefeats"),
            kills = statsTotal.requiredInt("Kills"),
            mostKillsInAGame = statsTotal.requiredInt("MostKillsInAGame"),
            groggies = statsTotal.requiredInt("Groggies"),
            mostGroggiesInAGame = statsTotal.requiredInt("MostGroggiesInAGame"),
        )

    private fun deserializeWeaponOfficialStatsTotal(officialStatsTotal: JsonObject): WeaponOfficialStatsTotal =
        WeaponOfficialStatsTotal(
            mostDefeatsInAGame = officialStatsTotal.requiredInt("MostDefeatsInAGame"),
            defeats = officialStatsTotal.requiredInt("Defeats"),
            damagePlayer = officialStatsTotal.requiredDouble("DamagePlayer"),
            headShots = officialStatsTotal.requiredInt("HeadShots"),
            kills = officialStatsTotal.requiredInt("Kills"),
            mostKillsInAGame = officialStatsTotal.requiredInt("MostKillsInAGame"),
            groggies = officialStatsTotal.requiredInt("Groggies"),
            longestKill = officialStatsTotal.requiredDouble("LongestKill"),
        )
}
