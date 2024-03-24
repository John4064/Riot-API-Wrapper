package models

import kotlinx.serialization.Serializable

@Serializable
data class ChallengesDto(
    val assistStreakCount: Int?=0,
    val abilityUses: Int?=0,
    val acesBefore15Minutes: Int?=0,
    val alliedJungleMonsterKills: Int?=0,
    val baronBuffGoldAdvantageOverThreshold: Int?=0,
    val baronTakedowns: Int?=0,
    val blastConeOppositeOpponentCount: Int?=0,
    val bountyGold: Int?=0,
    val buffsStolen: Int?=0,
    val completeSupportQuestInTime: Int?=0,
    val controlWardTimeCoverageInRiverOrEnemyHalf: Double?=0.0,
    val controlWardsPlaced: Int?=0,
    val damagePerMinute: Double?=0.0,
    val damageTakenOnTeamPercentage: Double?=0.0,
    val dancedWithRiftHerald: Int?=0,
    val deathsByEnemyChamps: Int?=0,
    val dodgeSkillShotsSmallWindow: Int?=0,
    val doubleAces: Int?=0,
    val dragonTakedowns: Int?=0,
    val earliestBaron: Double?=0.0,
    val earliestDragonTakedown: Double?=0.0,
    val earliestElderDragon: Double?=0.0,
    val earlyLaningPhaseGoldExpAdvantage: Int?=0,
    val effectiveHealAndShielding: Double?=0.0,
    val elderDragonKillsWithOpposingSoul: Int?=0,
    val elderDragonMultikills: Int?=0,
    val enemyChampionImmobilizations: Int?=0,
    val enemyJungleMonsterKills: Int?=0,
    val epicMonsterKillsNearEnemyJungler: Int?=0,
    val epicMonsterKillsWithin30SecondsOfSpawn: Int?=0,
    val epicMonsterSteals: Int?=0,
    val epicMonsterStolenWithoutSmite: Int?=0,
    val fastestLegendary: Double?=0.0,
    val fasterSupportQuestCompletion: Double?=0.0,
    val firstTurretKilled: Int?=0,
    val firstTurretKilledTime: Double?=0.0,
    val flawlessAces: Int?=0,
    val fullTeamTakedown: Int?=0,
    val gameLength: Double?=0.0,
    val getTakedownsInAllLanesEarlyJungleAsLaner: Int?=0,
    val goldPerMinute: Double?=0.0,
    val hadAfkTeammate: Int?=0,
    val hadOpenNexus: Int?=0,
    val highestCrowdControlScore: Int?=0,
    val highestChampionDamage: Int?=0,
    val highestWardKills: Int?=0,
    val immobilizeAndKillWithAlly: Int?=0,
    val initialBuffCount: Int?=0,
    val initialCrabCount: Int?=0,
    val jungleCsBefore10Minutes: Double?=0.0,
    val junglerKillsEarlyJungle: Int?=0,
    val junglerTakedownsNearDamagedEpicMonster: Int?=0,
    val kTurretsDestroyedBeforePlatesFall: Int?=0,
    val kda: Double?=0.0,
    val killAfterHiddenWithAlly: Int?=0,
    val killParticipation: Double?=0.0,
    val killedChampTookFullTeamDamageSurvived: Int?=0,
    val killingSprees: Int?=0,
    val killsNearEnemyTurret: Int?=0,
    val killsOnLanersEarlyJungleAsJungler: Int?=0,
    val killsOnOtherLanesEarlyJungleAsLaner: Int?=0,
    val killsOnRecentlyHealedByAramPack: Int?=0,
    val killsUnderOwnTurret: Int?=0,
    val killsWithHelpFromEpicMonster: Int?=0,
    val knockEnemyIntoTeamAndKill: Int?=0,
    val landSkillShotsEarlyGame: Int?=0,
    val laneMinionsFirst10Minutes: Int?=0,
    val laningPhaseGoldExpAdvantage: Int?=0,
    val legendaryCount: Int?=0,
    val legendaryItemUsed: List<Int>,
    val lostAnInhibitor: Int?=0,
    val maxCsAdvantageOnLaneOpponent: Double?=0.0,
    val maxKillDeficit: Int?=0,
    val maxLevelLeadLaneOpponent: Int?=0,
    val mejaisFullStackInTime: Int?=0,
    val moreEnemyJungleThanOpponent: Double?=0.0,
    val multiKillOneSpell: Int?=0,
    val multiTurretRiftHeraldCount: Int?=0,
    val multikills: Int?=0,
    val multikillsAfterAggressiveFlash: Int?=0,
    val outerTurretExecutesBefore10Minutes: Int?=0,
    val outnumberedKills: Int?=0,
    val outnumberedNexusKill: Int?=0,
    val perfectDragonSoulsTaken: Int?=0,
    val perfectGame: Int?=0,
    val pickKillWithAlly: Int?=0,
    val playedChampSelectPosition: Int?=0,
    val poroExplosions: Int?=0,
    val quickCleanse: Int?=0,
    val quickFirstTurret: Int?=0,
    val quickSoloKills: Int?=0,
    val riftHeraldTakedowns: Int?=0,
    val saveAllyFromDeath: Int?=0,
    val scuttleCrabKills: Int?=0,
    val shortestTimeToAceFromFirstTakedown: Double?=0.0,
    val skillshotsDodged: Int?=0,
    val skillshotsHit: Int?=0,
    val snowballsHit: Int?=0,
    val soloBaronKills: Int?=0,
    val soloKills: Int?=0,
    val soloTurretsLategame: Int?=0,
    val stealthWardsPlaced: Int?=0,
    val survivedSingleDigitHpCount: Int?=0,
    val survivedThreeImmobilizesInFight: Int?=0,
    val takedownOnFirstTurret: Int?=0,
    val takedowns: Int?=0,
    val takedownsAfterGainingLevelAdvantage: Int?=0,
    val takedownsBeforeJungleMinionSpawn: Int?=0,
    val takedownsFirstXMinutes: Int?=0,
    val takedownsInAlcove: Int?=0,
    val takedownsInEnemyFountain: Int?=0,
    val teamBaronKills: Int?=0,
    val teamDamagePercentage: Double?=0.0,
    val teamElderDragonKills: Int?=0,
    val teamRiftHeraldKills: Int?=0,
    val teleportTakedowns: Int?=0,
    val thirdInhibitorDestroyedTime: Double?=0.0,
    val tookLargeDamageSurvived: Int?=0,
    val turretPlatesTaken: Int?=0,
    val turretTakedowns: Int?=0,
    val turretsTakenWithRiftHerald: Int?=0,
    val twentyMinionsIn3SecondsCount: Int?=0,
    val twoWardsOneSweeperCount: Int?=0,
    val unseenRecalls: Int?=0,
    val visionScoreAdvantageLaneOpponent: Double?=0.0,
    val visionScorePerMinute: Double?=0.0,
    val wardTakedowns: Int?=0,
    val wardTakedownsBefore20M: Int?=0,
    val wardsGuarded: Int
)